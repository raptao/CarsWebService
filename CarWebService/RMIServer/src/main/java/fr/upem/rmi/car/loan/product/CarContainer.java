package fr.upem.rmi.car.loan.product;

import com.google.common.base.Preconditions;
import fr.upem.rmi.shared.interfaces.Product;
import fr.upem.rmi.shared.interfaces.ProductStorage;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by raptao on 10/26/2016.
 */
public class CarContainer extends UnicastRemoteObject implements ProductStorage {

    private final Collection<Product> products;
    private static int ID_INCREMENT = 1;

    /**
     * initialize a new CarContainer
     *
     * @throws RemoteException
     */
    public CarContainer() throws RemoteException {
        super();
        products = new LinkedList<>();
    }

    public static CarContainer create(InputStream inputStream) throws IOException {
        CarContainer carContainer = new CarContainer();
        List<Car> collect = IOUtils.readLines(inputStream, StandardCharsets.UTF_8).stream()
                .map(line -> line.split(" "))
                .map(tokens -> {
                    try {
                        Car car = new Car(Integer.parseInt(tokens[1]), tokens[2], tokens[3]);
                        LocalDate inBaseDate = LocalDate.parse(tokens[5]);
                        car.setPrice(Integer.parseInt(tokens[4]));
                        car.setStoredDate(inBaseDate);
                        return car;
                    } catch (RemoteException e) {
                        return null;
                    }
                }).filter(car -> car != null).collect(Collectors.toList());
        carContainer.products.addAll(collect);
        ID_INCREMENT = collect.size() + 1;
        return carContainer;
    }

    /**
     * create and add a new car in the CarContainer with the parameters
     *
     * @param model
     * @param brand
     * @param price
     * @throws RemoteException
     */
    public void add(String model, String brand, int price) throws RemoteException {
        Objects.requireNonNull(model);
        Objects.requireNonNull(brand);
        Preconditions.checkArgument(price > 0, "price must be > 0");
        int id = ID_INCREMENT;
        ID_INCREMENT++;
        Car car = new Car(id, brand, model);
        car.setPrice(price);
        products.add(car);
    }

    private void add(Car car) {
        Preconditions.checkNotNull(car, "car should not be null ");
        products.add(car);
    }

    /**
     * remove a car from the CarContainer
     *
     * @param product@throws RemoteException
     */
    public void remove(Product product) throws RemoteException {
        Objects.requireNonNull(product);
        if (product.isBought()) {
            products.remove(product);
        }
    }

    /**
     * create a list copy of the CarContainer
     *
     * @return list copy of CarContainer
     */
    public List<Product> copy() {
        return products.stream().collect(Collectors.toList());
    }

    /**
     * Refreshes the car list, removes all bought cars.
     * @throws RemoteException
     */
    @Override
    public void refresh() throws RemoteException{
        List<Product> toBeRemoved = products.stream().filter((product) -> {
            try {
                return product.isBought();
            } catch (RemoteException e) {
                return false;
            }
        }).collect(Collectors.toList());
        toBeRemoved.forEach(products::remove);
    }
    /**
     *
     * @param productId
     * @return the product associated with this id.
     */
    @Override
    public Product get(int productId) {
        Optional<Product> first = products.stream().filter(product -> {
            try {
                return productId == product.getId();
            } catch (RemoteException e) {
                return false;
            }
        }).findFirst();
        return first.isPresent() ? first.get() : null;
    }

}
