package com.eventmanagement.EventManagement.controller;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.ITransactionDAO;
import com.eventmanagement.EventManagement.model.*;
import com.eventmanagement.EventManagement.model.factory.MySQLDatabaseFactory;
import com.eventmanagement.EventManagement.model.factory.WalletFactory;
import com.eventmanagement.EventManagement.model.interfaces.IWallet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AddBalanceController
{
    IStudentDAO studentDAO;
    ITransactionDAO transactionDAO;
    public AddBalanceController()
    {
         this.studentDAO = MySQLDatabaseFactory.instance().makeStudentDAO();
         this.transactionDAO = MySQLDatabaseFactory.instance().makeTransactionDAO();
    }

    @GetMapping("/addbalance")
    public String getAddBalance(Model model)
    {
        PaymentMethod paymentCard = new PaymentCard();
        PaymentMethod netBankingPayment = new NetBankingPayment();
        model.addAttribute("cardForm", paymentCard);
        model.addAttribute("netBankingForm", netBankingPayment);
        model.addAttribute("activeTab",new String("card"));
        return "addbalance";
    }

    @PostMapping("/addbalancebycreditcard")
    public String addBalanceByCreditcard(@ModelAttribute("cardForm") PaymentCard card, BindingResult bindingResult,
                                         HttpServletRequest request, Model model)
    {
        if(card.validate(bindingResult))
        {
            Cookie userIdCookie = WebUtils.getCookie(request, "userId");
            int userId = Integer.parseInt(userIdCookie.getValue());

            IWallet wallet = WalletFactory.instance().makeWallet(studentDAO, transactionDAO);
            Boolean result = wallet.addBalance(userId,card);
            if(result)
            {
                return "redirect:/wallet";
            }
        }

        model.addAttribute("netBankingForm",new NetBankingPayment());
        model.addAttribute("activeTab",new String("card"));
        return "addbalance";

    }

    @PostMapping("/addbalancebynetbanking")
    public String addBalanceByNetBanking(@ModelAttribute("netBankingForm") NetBankingPayment netBankingPayment, BindingResult bindingResult,
                                         HttpServletRequest request, Model model)
    {
        if(netBankingPayment.validate(bindingResult))
        {
            Cookie userIdCookie = WebUtils.getCookie(request, "userId");
            int userId = Integer.parseInt(userIdCookie.getValue());
            IWallet wallet = WalletFactory.instance().makeWallet(studentDAO, transactionDAO);
            Boolean result = wallet.addBalance(userId,netBankingPayment);
            if(result)
            {
                return "redirect:/wallet";
            }
        }

        model.addAttribute("cardForm",new PaymentCard());
        model.addAttribute("activeTab",new String("netbanking"));
        return "addbalance";
    }

}
