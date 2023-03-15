package com.eventmanagement.EventManagement.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import com.eventmanagement.EventManagement.model.CurrentSession;
import com.eventmanagement.EventManagement.model.ReceipientType;
import com.eventmanagement.EventManagement.model.factory.NotificationFactory;
import com.eventmanagement.EventManagement.model.factory.SessionFactory;
import com.eventmanagement.EventManagement.model.interfaces.INotification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NotificationController {

    @RequestMapping("/notification/new")
    public String postNewNotification( @RequestParam String receipientType, @RequestParam String message,
                                       @RequestParam Integer eventId, @RequestParam Integer festivalId) {
        INotification notification = NotificationFactory.instance().makeNotification();
        ReceipientType rType = ReceipientType.valueOf(receipientType);
        notification.sendNotification(rType,eventId,message);
        return "redirect:/festival/"+festivalId+"/event/"+eventId;
    }

   @GetMapping("/notification/student")
   public String getNotificatiosnForStudent(Model model, HttpServletRequest request)
   {
       CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(request);
       Integer studentId = currentSession.getUserId();
       INotification notification = NotificationFactory.instance().makeNotification();
       List<INotification> notificationList = notification.getNotificationForStudent(studentId);
       model.addAttribute("notificationList", notificationList);
       return "viewnotification";
   }
}
