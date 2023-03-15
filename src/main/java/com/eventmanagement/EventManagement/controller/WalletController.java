package com.eventmanagement.EventManagement.controller;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.ITransactionDAO;
import com.eventmanagement.EventManagement.model.factory.MySQLDatabaseFactory;
import com.eventmanagement.EventManagement.model.Wallet;
import com.eventmanagement.EventManagement.model.factory.WalletFactory;
import com.eventmanagement.EventManagement.model.interfaces.IWallet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class WalletController
{
    private IStudentDAO studentDAO;
    private ITransactionDAO transactionDAO;

    public WalletController()
    {
        this.studentDAO = MySQLDatabaseFactory.instance().makeStudentDAO();
        this.transactionDAO = MySQLDatabaseFactory.instance().makeTransactionDAO();
    }

    @GetMapping("/wallet")
    public String getWalletDashboard(Model model, HttpServletRequest request)
    {
        Cookie userId = WebUtils.getCookie(request, "userId");
        int studentId = Integer.parseInt(userId.getValue());
        IWallet wallet = (Wallet) WalletFactory.instance().makeWallet(studentDAO,transactionDAO);
        wallet.loadWalletDetails(studentId);
        model.addAttribute("currentBalance", wallet.getCurrentBalance(studentId));
        model.addAttribute("transactions",wallet.getTransactions(studentId));
        return "wallet";
    }
}
