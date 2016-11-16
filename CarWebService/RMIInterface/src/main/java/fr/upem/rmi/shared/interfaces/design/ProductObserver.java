package fr.upem.rmi.shared.interfaces.design;

import java.rmi.RemoteException;

/**
 * This class defines an object which will observe the changes made on an {@link ObservableProduct}
 */
public interface ProductObserver {

    /**
     * Subscribe this {@link ProductObserver} to the {@link ObservableProduct}
     * @param o the ObservableProduct to be subscribed to.
     */
    public void subscribe(ObservableProduct o) throws RemoteException;


    /**
     * Unsubscribe this {@link ProductObserver} from the ObservableProduct
     * @param o the ObservableProduct to unsubscribe from
     */
    public void unSubscribe(ObservableProduct o) throws RemoteException;

    /**
     * This method is called whenever the {@link ObservableProduct} is available for rent.
     * @param car
     */
    public void update(ObservableProduct car) throws RemoteException;
}
