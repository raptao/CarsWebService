package fr.upem.webservice.app.action;

import com.google.common.base.Preconditions;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import fr.upem.rmi.shared.interfaces.Product;
import fr.upem.webservice.app.CarWebService;
import fr.upem.webservice.app.Sessions;
import org.apache.struts2.interceptor.SessionAware;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import static fr.upem.webservice.app.Sessions.PRODUCTS;

/**
 * Created by raptao on 11/10/2016.
 */
public abstract class ClientAction extends ActionSupport implements SessionAware, ParameterNameAware {

    private Map<String, Object> userSession;

    public abstract String execute() throws Exception;

    @Override
    public final void setSession(Map<String, Object> session) {
        userSession = session;
    }

    @Override
    public boolean acceptableParameterName(String parameterName) {
        boolean allowedParameterName = true;
        if (parameterName.contains("session") || parameterName.contains("request")) {
            allowedParameterName = false;
        }
        return allowedParameterName;
    }

    public Map<String, Object> getSession() {
        return userSession;
    }

    public void reloadProductsState(CarWebService newState) throws RemoteException, NotBoundException {
        Preconditions.checkNotNull(newState, "newState should not be null ");
        getSession().put(Sessions.CAR_WEB_SERVICE, newState);
        loadProducts(newState);
    }

    public void loadProducts(CarWebService carWebService) throws RemoteException {
        Preconditions.checkNotNull(carWebService, "carWebService should not be null ");
        List<Product> products = carWebService.allProducts();
        if (products != null) {
            getSession().put(PRODUCTS, products);
        }
    }

}
