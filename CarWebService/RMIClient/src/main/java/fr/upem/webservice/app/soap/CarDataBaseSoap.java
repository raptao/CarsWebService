package fr.upem.webservice.app.soap;

import fr.upem.rmi.shared.interfaces.Product;
import fr.upem.rmi.shared.interfaces.ProductStorage;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by raptao on 11/13/2016.
 */
public class CarDataBaseSoap {
    private ProductStorage products;

    public CarDataBaseSoap(ProductStorage carStorageService) {
        this.products = carStorageService;
    }

    public SoapProduct[] copy() throws RemoteException {
        List<SoapProduct> collect = products.copy().stream().map(SoapProduct::new).collect(Collectors.toList());
        SoapProduct[] array = new SoapProduct[collect.size()];
        return collect.toArray(array);
    }

    public SoapProduct get(int productId) throws RemoteException {
        return new SoapProduct(products.get(productId));

    }
    
    public void add( String brand, String model, int price ) throws RemoteException{
    	products.add(model, brand, price);
    }
}
