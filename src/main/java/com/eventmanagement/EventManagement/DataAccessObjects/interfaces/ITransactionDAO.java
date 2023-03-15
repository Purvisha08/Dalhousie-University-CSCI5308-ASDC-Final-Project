package com.eventmanagement.EventManagement.DataAccessObjects.interfaces;

import com.eventmanagement.EventManagement.model.interfaces.ITransaction;

import java.util.List;

public interface ITransactionDAO
{
    public boolean addTransaction(int studentId, double amount, String transactionType, String description, String transactionSource);
    public List<ITransaction> getTransactions(int studentId);
}
