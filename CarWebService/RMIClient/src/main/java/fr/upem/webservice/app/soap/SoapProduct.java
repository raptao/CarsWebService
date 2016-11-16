package fr.upem.webservice.app.soap;

import fr.upem.rmi.shared.interfaces.Product;

import java.rmi.RemoteException;

/**
 * Created by raptao on 11/13/2016.
 */
public class SoapProduct {

    Product product;

    public SoapProduct(){
    	
    }
    
    public void setProduct(Product product) {
		this.product = product;
	}

	public SoapProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public String description() throws RemoteException {
        return product.description();
    }
    
    public String getBrand() throws RemoteException{
    	return product.getBrand();
    }
    
    public String getModel() throws RemoteException{
    	return product.getModel();
    }

    
    public double getPrice() throws RemoteException{
    	return product.sellingPrice();
    }
    
    public String[] comments() throws RemoteException{
    	String[] array = new String[product.getComments().size()];
    	return product.getComments().toArray(array);
    }
}
