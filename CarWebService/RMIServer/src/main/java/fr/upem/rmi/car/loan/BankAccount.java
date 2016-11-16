package fr.upem.rmi.car.loan;

import com.google.common.base.Preconditions;
import fr.upem.rmi.shared.interfaces.Account;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by frech on 10/11/2016.
 */
public class BankAccount extends UnicastRemoteObject implements Account {

    private final int id;
    private double balance;

    public BankAccount(int id) throws RemoteException{
        this.id = id;
        this.balance = 0;
    }

    public double getBalance() throws RemoteException{
        return balance;
    }

    public void setBalance(double newValue) throws RemoteException{
        balance = newValue;
    }

    @Override
    public void withdraw(double value) {
        Preconditions.checkArgument(value>=0, "value must be positive");
        balance -= value;
    }
}
