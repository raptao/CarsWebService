package fr.upem.rmi.shared.interfaces.design;

import java.rmi.RemoteException;
import java.util.Collection;

/**
 * Interface giving the behaviour of an observable object
 */
public interface ObservableProduct {

    /**
     * Adds an {@link ProductObserver} object to the list of observers of this current object.
     *
     * @param o the observer to add to the list
     */
    public void addObserver(ProductObserver o) throws RemoteException;

    /**
     * Removes an {@link ProductObserver} from the list of observers of this current object.
     *
     * @param o the observer to remove from the list.
     */
    public void removeObserver(ProductObserver o) throws RemoteException;

    /**
     * Notifies an {@link ProductObserver} object about changes made on this current {@link ObservableProduct}.
     *
     * @param o the observer that will be notified.
     */
    public void notifyObserver(ProductObserver o) throws RemoteException;

    /**
     * Notifies all the observers of the current {@link ObservableProduct}.
     */
    public void notifyObservers() throws RemoteException;

    /**
     * @return a {@link Collection} of the observers of the current {@link ObservableProduct}
     */
    public Collection<ProductObserver> getObserverCollection() throws RemoteException;
}
