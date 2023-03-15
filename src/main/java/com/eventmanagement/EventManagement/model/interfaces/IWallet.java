package com.eventmanagement.EventManagement.model.interfaces;

import com.eventmanagement.EventManagement.model.PaymentMethod;
import java.util.List;

public interface IWallet
{
    public void loadWalletDetails(int studentId);
    public boolean addBalance(int studentId, PaymentMethod paymentMethod);
    public void loadBalance(int studentId);
    public double getCurrentBalance(int studentId);
    public List<ITransaction> getTransactions(int studentId);
}
