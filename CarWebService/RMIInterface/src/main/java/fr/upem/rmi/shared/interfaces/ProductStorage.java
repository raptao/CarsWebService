package fr.upem.rmi.shared.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by frech on 30/10/2016.
 */
public interface ProductStorage extends Remote{

    /**
     * create and add a car in the container
     * @param model
     * @param brand
     * @throws RemoteException
     */
    public void add(String model, String brand, int price) throws RemoteException;

    /**
     * remove a car from the container
     *
     * @param product@throws RemoteException
     */
    public void remove(Product product) throws RemoteException;

    /**
     * create a list copy of the container
     * @return
     * @throws RemoteException
     */
    public List<Product> copy() throws RemoteException;

    void refresh() throws RemoteException;

    /**
     * get a {@link Product} object from this product storage
     * @param productId
     * @return
     */
    public Product get( int productId )  throws RemoteException;

}
