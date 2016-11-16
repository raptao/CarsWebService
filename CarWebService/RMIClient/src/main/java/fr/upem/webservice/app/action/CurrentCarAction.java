package fr.upem.webservice.app.action;

import fr.upem.rmi.shared.interfaces.Member;
import fr.upem.rmi.shared.interfaces.Product;
import fr.upem.webservice.app.CarWebService;
import fr.upem.webservice.app.Sessions;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.function.Consumer;

/**
 * Created by raptao on 11/9/2016.
 */
public class CurrentCarAction extends ClientAction {

    public CurrentCarAction() {
        super();
    }

    private String comment;
    private int rating;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String buy() throws RemoteException, NotBoundException {
        CarWebService carWebService = (CarWebService) Sessions.getFromSession(getSession(), Sessions.CAR_WEB_SERVICE);
        if (carWebService == null) {
           carWebService = CarWebService.getInstance();
        }
        Member currentMember = (Member) Sessions.getFromSession(getSession(), Sessions.LOGGED_IN_USER);
        if (currentMember == null) {
            return ERROR;
        }
        Product selectedProduct = currentMember.currentlyRenting();
        getSession().put(Sessions.SELECTED_CAR, selectedProduct);
        carWebService.refreshProductStorage();
        return SUCCESS;
    }

    /**
     * Redirects to the comment page for this car
     *
     * @return
     */
    public String comment() throws RemoteException, NotBoundException {

        return consumeMember(member -> {
            try {
                member.comment(member.currentlyRenting(), comment, rating);
                getSession().put(Sessions.SELECTED_CAR, member.currentlyRenting());
            } catch (RemoteException e) {
                //ignored
            }
        });
    }

    /**
     * Restores the current car, makes it available again
     *
     * @return
     * @throws RemoteException
     */
    public String restore() throws RemoteException, NotBoundException {

        return consumeMember(member -> {
            try {
                if (member.hasBoughtAProduct() && member.currentlyRenting().isBought()) {
                    return;
                }
                member.restore();
            } catch (RemoteException e) {
            }
        });
    }

    /**
     * Applies and action to the current car of the user.
     *
     * @param consumer
     * @return
     */
    private String consumeMember(Consumer<Member> consumer) throws RemoteException, NotBoundException {
        CarWebService carWebService = (CarWebService) Sessions.getFromSession(getSession(), Sessions.CAR_WEB_SERVICE);
        if (carWebService == null) {
            return ERROR;
        }
        Member currentMember = (Member) Sessions.getFromSession(getSession(), Sessions.LOGGED_IN_USER);
        consumer.accept(currentMember);
        reloadProductsState(carWebService);
        return SUCCESS;
    }


}
