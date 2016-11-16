package fr.upem.rmi.shared.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by frech on 10/11/2016.
 */
public interface Account extends Remote {

    public double getBalance() throws RemoteException;

    public void setBalance(double newValue) throws RemoteException;

    /**
     * Withdraw this value from the account
     * @param value
     */
    void withdraw(double value) throws RemoteException;
}
