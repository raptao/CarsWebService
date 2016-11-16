package fr.upem.webservice.app.action;

import fr.upem.rmi.shared.interfaces.Member;
import fr.upem.rmi.shared.interfaces.Product;
import fr.upem.webservice.app.CarWebService;
import fr.upem.webservice.app.Sessions;

import static fr.upem.webservice.app.Sessions.CAR_WEB_SERVICE;
import static fr.upem.webservice.app.Sessions.LOGGED_IN_USER;
import static fr.upem.webservice.app.Sessions.SELECTED_CAR;

/**
 * Created by raptao on 11/12/2016.
 */
public class BuyCarAction extends ClientAction {
    @Override
    public String execute() throws Exception {
        CarWebService carWebService = (CarWebService) Sessions.getFromSession(getSession(), CAR_WEB_SERVICE);
        if( carWebService == null ){
            carWebService = CarWebService.getInstance();
        }
        Product selectedCar = (Product) Sessions.getFromSession(getSession(), SELECTED_CAR);
        if (selectedCar == null) {
            return INPUT;
        }
        Member currentMember = (Member) Sessions.getFromSession(getSession(), LOGGED_IN_USER);
        if (currentMember == null) {
            return ERROR;
        }
        currentMember.buy(selectedCar);
        carWebService.refreshProductStorage();
        return SUCCESS;
    }
}
