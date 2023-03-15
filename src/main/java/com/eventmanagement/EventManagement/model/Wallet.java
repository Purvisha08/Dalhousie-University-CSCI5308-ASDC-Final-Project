package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.ITransactionDAO;
import com.eventmanagement.EventManagement.model.interfaces.ITransaction;
import com.eventmanagement.EventManagement.model.interfaces.IWallet;
import java.util.ArrayList;
import java.util.List;

public class Wallet implements IWallet
{
    private Integer studentId;
    private Double currentBalance;
    private ArrayList<ITransaction> transactions;
    private IStudentDAO studentDAO;
    private ITransactionDAO transactionDAO;

    public Wallet()
    {
    }

    public Wallet(int studentId)
    {
        this.studentId = studentId;
    }

    public Wallet (IStudentDAO studentDAO, ITransactionDAO transactionDAO)
    {
        this.currentBalance = Double.valueOf(-1);
        this.studentId = -1;
        this.transactions = null;
        this.studentDAO = studentDAO;
        this.transactionDAO = transactionDAO;
    }

    public Integer getStudentId()
    {
        return this.studentId;
    }

    public void setStudentId(Integer studentId)
    {
        this.studentId = studentId;
    }

    public Double getCurrentBalance()
    {
        return this.currentBalance;
    }

    public void setCurrentBalance(Double currentBalance)
    {
        this.currentBalance = currentBalance;
    }

    public ArrayList<ITransaction> getTransactions()
    {
        return  this.transactions;
    }

    public void setTransactions(ArrayList<ITransaction> transactions)
    {
        this.transactions = transactions;
    }

    @Override
    public void loadWalletDetails(int studentId)
    {
        try
        {
            loadBalance(studentId);
            this.transactions = (ArrayList<ITransaction>) transactionDAO.getTransactions(studentId);
            setTransactions(transactions);
        }
        catch (Exception ex)
        {
            System.out.println("Exception found while loading wallet details  - "+ex.getMessage());
        }
    }

    @Override
    public boolean addBalance(int studentId, PaymentMethod paymentMethod)
    {
        try
        {
            Transaction transaction = new Transaction();
            Double amount = Double.valueOf(paymentMethod.getAmountRequested());
            TransactionType transactionType = TransactionType.Credit;
            TransactionSource transactionSource = paymentMethod.getTransactionSource();
            String description = paymentMethod.getSummary();
            transaction.add(studentId,amount,transactionType,description,transactionSource);
            return true;
        }
        catch (Exception ex)
        {
            System.out.println("Exception found while adding balance - "+ex.getMessage());
            return false;
        }
    }

    @Override
    public void loadBalance(int studentId)
    {
        try
        {
            this.currentBalance = this.studentDAO.getWalletBalance(studentId);
        }
        catch (Exception ex)
        {
            System.out.println("Exception found while loading balance  - "+ex.getMessage());
        }
    }

    @Override
    public double getCurrentBalance(int studentId)
    {
        if(currentBalance == -1)
        {
            try
            {
                Double balance = studentDAO.getWalletBalance(studentId);
                setCurrentBalance(balance);
            }
            catch (Exception ex)
            {
                System.out.println("Exception found while loading balance  - "+ex.getMessage());
            }
        }
        return this.currentBalance;
    }

    @Override
    public List<ITransaction> getTransactions(int studentId) {

        if(this.transactions == null)
        {
            loadWalletDetails(studentId);
        }
        return this.transactions;
    }


}
