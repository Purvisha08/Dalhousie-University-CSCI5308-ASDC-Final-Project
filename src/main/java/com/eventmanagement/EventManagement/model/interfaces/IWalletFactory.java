package com.eventmanagement.EventManagement.model.interfaces;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.ITransactionDAO;

public interface IWalletFactory
{
    public IWallet makeWallet();
    public IWallet makeWallet(IStudentDAO studentDAO, ITransactionDAO transactionDAO);
    public ITransaction makeTransaction();
    public ITransaction makeTransaction(ITransactionDAO transactionDAO);
}
