package fr.upem.webservice.app.action;

import fr.upem.rmi.shared.interfaces.Member;
import fr.upem.rmi.shared.interfaces.Product;
import fr.upem.webservice.app.CarWebService;

import java.rmi.RemoteException;
import java.util.List;

import static fr.upem.webservice.app.Sessions.*;

/**
 * Created by raptao on 11/5/2016.
 */
public class LoggingAction extends ClientAction {


    private String formId;
    private String formPassword;

    private List<Product> products;
    private Member member;

    public LoggingAction() {
        super();
    }

    public Member getMember() {
        return member;
    }

    public String getPassword() throws RemoteException {
        return member.getPassword();
    }

    public void setPassword(String password) throws RemoteException {
        member.setPassword(password);
    }

    public String execute() throws Exception {
        CarWebService carWebService = CarWebService.getInstance();
        getSession().put(CAR_WEB_SERVICE, carWebService);
        this.member = carWebService.getMember(Integer.parseInt(formId), formPassword);
        if (member != null) {
            getSession().put(LOGGED_IN_USER, member);
        }
        loadProducts(carWebService);
        getSession().put(CURRENT_CURRENCY, carWebService.getCurrency());
        return (member == null) ? ERROR : SUCCESS;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getFormPassword() {
        return formPassword;
    }

    public void setFormPassword(String formPassword) {
        this.formPassword = formPassword;
    }

    public List<Product> getProducts() {
        return products;
    }


}
