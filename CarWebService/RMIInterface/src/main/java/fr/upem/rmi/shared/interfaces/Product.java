package fr.upem.rmi.shared.interfaces;


import fr.upem.rmi.shared.interfaces.design.ObservableProduct;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

/**
 * This interface specifies the behaviour of a product.
 */
public interface Product extends ObservableProduct, Remote {

    /**
     * @return true if this {@link Product} can be rented, false otherwise
     */
    public boolean isAvailable() throws RemoteException;

    /**
     * @return true if this object can be bought
     */
    public default boolean isBuyable() throws RemoteException {
        LocalDate today = LocalDate.now();
        LocalDate twoYearsAgo = today.minusYears(2);
        LocalDate inBaseDate = getInBaseDate();
        if (inBaseDate == null ){
            return false;
        }

        return !isBought() && (rentedTimes() >= 1) && inBaseDate.isBefore(twoYearsAgo);
    }

    /**
     * @return the date when this product had been added to the 'database' of products.
     */
    public LocalDate getInBaseDate() throws RemoteException;

    void incrementRentedTimes() throws RemoteException;

    /**
     * @return the number of times this product has been rented
     */
    public int rentedTimes() throws RemoteException;

    /**
     *
     * @return string describing the product
     * @throws RemoteException
     */
    public String description() throws RemoteException;

    /**
     * Specifies the future final owner of this {@link Product} object.
     * @param member the member who will buy this product.
     */
    public void willBeBoughtBy(Member member)throws RemoteException;

    /**
     *
     * @return true if this {@link Product} is bought, false otherwise
     */
    public boolean isBought() throws RemoteException;

    /**
     *
     * @return the id of the product
     * @throws RemoteException
     */
    public int getId() throws RemoteException;

    /**
     *
     * @return the brand of the product
     * @throws RemoteException
     */
    public String getBrand() throws RemoteException;

    /**
     *
     * @return the model of the product
     * @throws RemoteException
     */
    public String getModel() throws RemoteException;

    /**
     *
     * @return the average mark of the product
     * @throws RemoteException
     */
    public double getAverageMark() throws RemoteException;

    /**
     *
     * @return all the comments about this product
     * @throws RemoteException
     */
    public List<String> getComments() throws RemoteException;

    /**
     * Adds a comment with a grade to this product
     * @param comment
     * @param grade
     * @throws RemoteException
     */
    public void addComment(String comment, int grade ) throws RemoteException;


    /**
     *
     * @return the rental price of this product
     * @throws RemoteException
     */
    public double rentalPrice() throws RemoteException;

    /**
     *
     * @return the selling price of this product
     * @throws RemoteException
     */
    public double sellingPrice() throws RemoteException;

    /**
     *
     * @param newPrice gives a new price to this product
     * @throws RemoteException
     */
    public void setPrice(double newPrice)throws RemoteException;
}
