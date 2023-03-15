package com.eventmanagement.EventManagement.model.factory;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.ITransactionDAO;
import com.eventmanagement.EventManagement.model.Transaction;
import com.eventmanagement.EventManagement.model.Wallet;
import com.eventmanagement.EventManagement.model.interfaces.ITransaction;
import com.eventmanagement.EventManagement.model.interfaces.IWallet;
import com.eventmanagement.EventManagement.model.interfaces.IWalletFactory;

public class WalletFactory implements IWalletFactory
{
    private static WalletFactory singleInstance;

    private WalletFactory()
    {

    }

    public static WalletFactory instance()
    {
        if(singleInstance == null)
        {
            singleInstance = new WalletFactory();
        }
        return singleInstance;
    }

    @Override
    public IWallet makeWallet() {
        return new Wallet();
    }

    @Override
    public IWallet makeWallet(IStudentDAO studentDAO, ITransactionDAO transactionDAO) {
        return new Wallet(studentDAO, transactionDAO);
    }

    @Override
    public ITransaction makeTransaction() {
        return new Transaction();
    }

    @Override
    public ITransaction makeTransaction(ITransactionDAO transactionDAO) {
        return new Transaction(transactionDAO);
    }

}
