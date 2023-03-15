package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.DataAccessObjects.TransactionDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.ITransactionDAO;
import com.eventmanagement.EventManagement.model.interfaces.ITransaction;

import java.time.LocalDateTime;

public class Transaction implements ITransaction
{
    private Integer transactiondId;
    private Integer studentId;
    private Double amount;
    private TransactionType transactonType;
    private LocalDateTime transactionDate;
    private TransactionSource transactionSource;
    private String description;
    private ITransactionDAO transactionDAO;

    public Transaction(ITransactionDAO transactionDAO)
    {
        this.transactionDAO = transactionDAO;
    }

    public Transaction()
    {
        this.transactionDAO = new TransactionDAO();
    }

    public Integer getTransactionId()
    {
        return this.transactiondId;
    }

    public void setTransactiondId(Integer transactiondId)
    {
        this.transactiondId = transactiondId;
    }

    public Integer getStudentId()
    {
        return this.studentId;
    }

    public void setStudentId(Integer studentId)
    {
        this.studentId = studentId;
    }

    public Double getAmount()
    {
        return this.amount;
    }

    public void setAmount(Double amount)
    {
        this.amount = amount;
    }

    public TransactionType getTransactionType()
    {
        return this.transactonType;
    }

    public void setTransactonType(TransactionType transactonType)
    {
        this.transactonType = transactonType;
    }

    public LocalDateTime getTransactionDate()
    {
        return this.transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate)
    {
        this.transactionDate = transactionDate;
    }

    public TransactionSource getTransactionSource()
    {
        return this.transactionSource;
    }

    public void setTransactionSource(TransactionSource transactionSource)
    {
        this.transactionSource = transactionSource;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public boolean add(int studentId, double amount, TransactionType transactionType, String description, TransactionSource transactionSource)
    {
        this.transactionDAO.addTransaction(studentId, amount, transactionType.toString(), description, transactionSource.toString());
        return false;
    }

}
