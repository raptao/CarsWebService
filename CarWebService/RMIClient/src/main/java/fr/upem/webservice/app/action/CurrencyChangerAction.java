package fr.upem.webservice.app.action;

import fr.upem.rmi.shared.interfaces.Member;
import fr.upem.webservice.app.CarWebService;
import fr.upem.webservice.app.Sessions;

/**
 * Created by raptao on 11/12/2016.
 */
public class CurrencyChangerAction extends ClientAction {

    private String newCurrency;

    public String getNewCurrency() {
        return newCurrency;
    }

    public void setNewCurrency(String newCurrency) {
        this.newCurrency = newCurrency;
    }

    @Override
    public String execute() throws Exception {
        CarWebService carWebService = (CarWebService) Sessions.getFromSession(getSession(), Sessions.CAR_WEB_SERVICE);
        if( carWebService == null){
            carWebService = CarWebService.getInstance();
        }
        Member currentMember = (Member) Sessions.getFromSession(getSession(), Sessions.LOGGED_IN_USER);
        if( currentMember == null ){
            return ERROR;
        }
        carWebService.changeCurrency(newCurrency, currentMember);
        getSession().put(Sessions.CURRENT_CURRENCY, newCurrency);
        reloadProductsState(carWebService);
        return SUCCESS;
    }

}
