package fr.upem.webservice.app.action;

import fr.upem.rmi.shared.interfaces.Member;
import fr.upem.rmi.shared.interfaces.Product;
import fr.upem.webservice.app.CarWebService;
import fr.upem.webservice.app.Sessions;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.function.BiConsumer;

import static fr.upem.webservice.app.Sessions.CAR_WEB_SERVICE;
import static fr.upem.webservice.app.Sessions.LOGGED_IN_USER;
import static fr.upem.webservice.app.Sessions.SELECTED_CAR;

/**
 * Created by raptao on 11/8/2016.
 */
public class HomePageAction extends ClientAction {


    private int selectedCar;
    private List<String> comments;

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public HomePageAction() {
        super();
    }

    public int getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(int selectedCar) {
        this.selectedCar = selectedCar;
    }

    public String add(){
        return SUCCESS;
    }
    /**
     * Rents the selected car
     *
     * @return
     * @throws Exception
     */
    public String rent() throws Exception {
        return consumeProduct((member, product) -> {
            try {
                member.rent(product);
            } catch (RemoteException e) {
            }
        });
    }

    /**
     * Buy the selected car
     *
     * @return
     * @throws Exception
     */
    public String buy() throws Exception {
        CarWebService carWebService = (CarWebService) Sessions.getFromSession(getSession(), CAR_WEB_SERVICE);
        if (carWebService == null) {
            carWebService = CarWebService.getInstance();
        }
        Product product = carWebService.getProduct(selectedCar);
        Member currentMember = (Member) Sessions.getFromSession(getSession(), LOGGED_IN_USER);
        if (currentMember == null) {
            return ERROR;
        }
        getSession().put(Sessions.SELECTED_CAR, product);
        return SUCCESS;
    }

    private String consumeProduct(BiConsumer<Member, Product> consumer) throws RemoteException, NotBoundException {
        CarWebService carWebService = (CarWebService) Sessions.getFromSession(getSession(), CAR_WEB_SERVICE);
        if (carWebService == null) {
            carWebService = CarWebService.getInstance();
        }
        Product product = carWebService.getProduct(selectedCar);
        Member currentMember = (Member) Sessions.getFromSession(getSession(), LOGGED_IN_USER);
        if (currentMember == null) {
            return ERROR;
        }
        consumer.accept(currentMember, product);
        return SUCCESS;
    }

    /**
     * disconnect from the service
     *
     * @return
     * @throws Exception
     */
    public String disconnect() throws Exception {
        Sessions.unset(getSession(), LOGGED_IN_USER);
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String getAllComments() throws RemoteException, NotBoundException {
        CarWebService carWebService = (CarWebService) Sessions.getFromSession(getSession(), CAR_WEB_SERVICE);
        if (carWebService == null) {
            carWebService = CarWebService.getInstance();
        }
        Product product = carWebService.getProduct(selectedCar);
        if( product == null ){
            return INPUT;
        }
        comments = product.getComments();
        getSession().put(SELECTED_CAR, product);
        return SUCCESS;
    }
}
