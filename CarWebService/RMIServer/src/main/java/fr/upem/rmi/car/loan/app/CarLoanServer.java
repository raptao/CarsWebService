package fr.upem.rmi.car.loan.app;

import fr.upem.rmi.car.loan.client.MemberList;
import fr.upem.rmi.car.loan.product.CarContainer;

import java.io.InputStream;
import java.rmi.NoSuchObjectException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by raptao on 10/26/2016.
 */
public class CarLoanServer {


    public static final int PORT = 1099;

    public static void main(String[] args) throws NoSuchObjectException {
        Registry registry = null;
        try {
            // creates a local registry on port 1099
            registry = LocateRegistry.createRegistry(PORT);
            InputStream productsGeneratorURI = CarLoanServer.class.getResourceAsStream("/products-generator.txt");
            InputStream memberGeneratorURI = CarLoanServer.class.getResourceAsStream("/members-generator.txt");
            CarContainer carContainer = CarContainer.create(productsGeneratorURI);
            MemberList list = MemberList.create(memberGeneratorURI);
            //binding objects to make the available
            registry.bind("CarStorageService", carContainer);
            registry.bind("MemberDatabase", list);
            System.out.println("RMI server is launched on port :" + PORT);
        } catch (Exception e) {

        }
    }
}
