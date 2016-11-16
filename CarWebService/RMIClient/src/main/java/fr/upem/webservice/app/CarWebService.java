package fr.upem.webservice.app;

import com.google.common.base.Preconditions;
import fr.upem.rmi.shared.interfaces.*;
import org.apache.commons.math3.util.Precision;

import javax.xml.rpc.ServiceException;
import java.net.URL;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

/**
 * Created by raptao on 10/26/2016.
 */
public class CarWebService {

    private static final String DEFAULT_CURRENCY = fr.upem.webservice.app.CurrencyConverter.EUR;
    private static CarWebService INSTANCE;
    private final ProductStorage productStorage;
    private final MemberDatabase memberDatabase;
    private fr.upem.webservice.app.CurrencyConverter converter;

    private CarWebService() throws RemoteException, NotBoundException {
        setUpRMI();
        Registry registry = LocateRegistry.getRegistry(1099);
        productStorage = (ProductStorage) registry.lookup("CarStorageService");
        memberDatabase = (MemberDatabase) registry.lookup("MemberDatabase");
        converter = new fr.upem.webservice.app.CurrencyConverter(fr.upem.webservice.app.CurrencyConverter.EUR);
    }

    public static CarWebService getInstance() throws RemoteException, NotBoundException {
        if (INSTANCE == null) {
            INSTANCE = new CarWebService();
        }
        // retry once again if rmi cannot be contacted again.
        try {
            INSTANCE.allProducts();
        } catch (ConnectException e) {
            INSTANCE = new CarWebService();
        }
        return INSTANCE;
    }

    public static void main(String[] args) throws RemoteException, NotBoundException {
    }

    public Member getMember(int id, String password) throws RemoteException {
        return memberDatabase.get(id, password);
    }

    public Member addMember(MemberType memberType, String firstName, String lastName, int accountId, String password)
            throws RemoteException {
        return memberDatabase.add(memberType, firstName, lastName, accountId, password);
    }

    public List<Product> allProducts() throws RemoteException {
        return productStorage.copy();
    }

    public Product getProduct(int productId) throws RemoteException {
        return productStorage.get(productId);
    }

    public void refreshProductStorage() throws RemoteException {
        productStorage.refresh();
    }

    public void addProduct(String brand, String model, int price) throws RemoteException {
        Preconditions.checkNotNull(brand, "model should not be null");
        Preconditions.checkNotNull(model, "model should not be null");
        Preconditions.checkArgument(price > 0, "price should be > 0");
        productStorage.add(model, brand, price);
    }

    /**
     * Change the currency of a specified {@link Member}
     * @param targetCurrency the new currency
     * @param member the member
     * @throws RemoteException
     */
    public void changeCurrency(String targetCurrency, Member member) throws RemoteException, ServiceException {
        Preconditions.checkNotNull(targetCurrency, "targetCurrency should not be null ");
        double currencyRate = converter.convert(1d, targetCurrency);
        if (!targetCurrency.equals(converter.getCurrency())){
            // changing all products currency
            productStorage.copy().forEach(product -> {
                try {
                    double newPrice = Precision.round(product.sellingPrice() * currencyRate, 2);
                    product.setPrice(newPrice);
                } catch (RemoteException e) {
                }
            });

            // changing current member currency
            Member changed = memberDatabase.get(member.getId(), member.getPassword());
            changed.setAccountBalance(Precision.round(changed.getAccountBalance()*currencyRate, 2));
            // changing car web service currency
            converter = new CurrencyConverter(targetCurrency);
        }
    }

    public String getCurrency(){
        return converter.getCurrency();
    }

    private static void setUpRMI() {
        URL policy = CarWebService.class.getResource("/securityPermissions.policy");
        URL stubs = CarWebService.class.getResource("/car-loan.stubs-client.jar");
        System.setProperty("java.security.policy", policy.toString());
        System.setProperty("java.rmi.server.codebase", stubs.toString());
        System.setSecurityManager(new SecurityManager());
    }
}

