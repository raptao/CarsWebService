package fr.upem.rmi.shared.interfaces;

import fr.upem.rmi.shared.interfaces.design.ProductObserver;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Optional;

/**
 * Created by raptao on 10/26/2016.
 * <p>
 * This class defines a Member of the university.<br>
 * At this point, a member is either a student or a teacher.
 */
public interface Member extends Remote, ProductObserver {

    /**
     * @return the type of the member.
     */
    public MemberType type() throws RemoteException;

    /**
     * Rents a {@link Product}. The product won"t be available until this member
     * restores it.
     */
    public void rent(Product product) throws RemoteException;

    /**
     * Return the {@link Product} so that it will be available for the other members.
     *
     */
    public void restore() throws RemoteException;


    /**
     * Buys a {@link Product}. The product will no longer be availlable
     */
    public void buy(Product product) throws RemoteException;

    /**
     *
     * @return an {@link Optional} containing or not the product that this member is currently renting.<br>
     *     An empty {@link Optional} if this member is not renting any product.
     * @throws RemoteException
     */
    public Product currentlyRenting() throws RemoteException;

    /**
     *
     * @return the {@link String} description of this member.
     * @throws RemoteException
     */
    public String description()throws RemoteException;

    /**
     *
     * @return true if this member has already bought a product, false otherwise.
     */
    public boolean hasBoughtAProduct() throws RemoteException;

    /**
     *
     * @return the id of this member.
     * @throws RemoteException
     */
    public int getId() throws RemoteException;

    /**
     * sets the id of the member
     * @param id the new id of the member
     * @throws RemoteException
     */
    public void setId(int id) throws RemoteException;
    /**
     *
     * @return the password of this member.
     * @throws RemoteException
     */
    public String getPassword() throws RemoteException;

    /**
     * Sets the new password of this user.
     * @param newPassword
     * @throws RemoteException
     */
    public void setPassword( String newPassword ) throws RemoteException;

    /**
     *
     * @return the first name of this member
     * @throws RemoteException
     */
    public String getFirstName() throws RemoteException;

    /**
     * sets the first name of this member
     * @param firstName the new first name
     * @throws RemoteException
     */
    public void setFirstName(String firstName) throws RemoteException;

    /**
     *
     * @return the last name of this member
     * @throws RemoteException
     */
    public String getLastName() throws RemoteException;

    /**
     * sets the last name of this member
     * @param lastName the new last name
     * @throws RemoteException
     */
    public void setLastName(String lastName ) throws RemoteException;

    /**
     * Comments the product.
     * @param product the product to be commented.
     * @throws RemoteException
     */
    public void comment( Product product , String comment, int grade) throws RemoteException;

    /**
     *
     * @return the account id of this member
     * @throws RemoteException
     */
    public int getAccountId() throws RemoteException;
    
    public double getAccountBalance() throws RemoteException;

    public void setAccountBalance(double newValue) throws RemoteException;
}
