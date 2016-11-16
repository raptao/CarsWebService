package fr.upem.webservice.app.soap;

import fr.upem.rmi.shared.interfaces.MemberDatabase;
import fr.upem.rmi.shared.interfaces.ProductStorage;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by raptao on 11/13/2016.
 */
public class CarSoapService {

    private CarDataBaseSoap carDataBase;
    private MemberDataBaseSoap memberDatabase;

    public CarSoapService() throws RemoteException, NotBoundException {
        setUpRMI();
        Registry registry = LocateRegistry.getRegistry(1099);
        carDataBase = new CarDataBaseSoap((ProductStorage) registry.lookup("CarStorageService"));
        memberDatabase = new MemberDataBaseSoap( (MemberDatabase) registry.lookup("MemberDatabase"));
    }

    private void setUpRMI() {
        URL policy = CarSoapService.class.getResource("/securityPermissions.policy");
        URL stubs = CarSoapService.class.getResource("/car-loan.stubs-client.jar");
        System.setProperty("java.security.policy", policy.toString());
        System.setProperty("java.rmi.server.codebase", stubs.toString());
        System.setSecurityManager(new SecurityManager());
    }

    public SoapMember getMember(int id, String password) throws RemoteException {
        return memberDatabase.get(id, password);
    }

    public SoapMember addMember(String memberType, String firstName, String lastName, int accountId, String password)
            throws RemoteException {
        return memberDatabase.add(memberType, firstName, lastName, accountId, password);
    }

    public SoapProduct[] allProducts() throws RemoteException {
        return carDataBase.copy();

    }

    public SoapProduct getProduct(int productId) throws RemoteException {
        return carDataBase.get(productId);
    }
    
    public void addProduct(String brand, String model, int price ) throws RemoteException{
    	carDataBase.add(model, brand, price);
    }
}
