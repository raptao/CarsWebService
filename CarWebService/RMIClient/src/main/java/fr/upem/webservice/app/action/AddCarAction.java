package fr.upem.webservice.app.action;

import fr.upem.rmi.shared.interfaces.Member;
import fr.upem.webservice.app.CarWebService;
import fr.upem.webservice.app.Sessions;

/**
 * Created by raptao on 11/10/2016.
 */
public class AddCarAction extends ClientAction {

    private String carBrand;
    private String carModel;
    private int carPrice;
    private String userPassword;

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(int carPrice) {
        this.carPrice = carPrice;
    }

    @Override
    public String execute() throws Exception {
        CarWebService carWebService = (CarWebService) Sessions.getFromSession(getSession(), Sessions.CAR_WEB_SERVICE);
        if (carWebService == null) {
            carWebService = CarWebService.getInstance();
        }
        Member currentMember = (Member) Sessions.getFromSession(getSession(), Sessions.LOGGED_IN_USER);
        if (currentMember == null) {
            return INPUT;
        }
        if (!currentMember.getPassword().equals(userPassword)) {
            return ERROR;
        }
        carWebService.addProduct(carBrand, carModel, carPrice);
        reloadProductsState(carWebService);
        return SUCCESS;
    }
}
