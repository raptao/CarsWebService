package fr.upem.rmi.car.loan.product;


import fr.upem.rmi.car.loan.client.Student;
import fr.upem.rmi.car.loan.client.Teacher;
import fr.upem.rmi.shared.interfaces.Member;
import org.apache.commons.math3.util.Precision;
import org.junit.Test;

import java.rmi.RemoteException;
import java.time.LocalDate;

import static junit.framework.TestCase.*;

/**
 * Created by raptao on 10/26/2016.
 */
public class CarTest {
    private final Car car;

    public CarTest() throws RemoteException {
        car = new Car(12, "Peugeot", "206");
        car.setPrice(12);
    }

    @Test
    public void equals() throws Exception {
        Car carCopy = new Car(12, "Peugeot", "206");
        Car differentId = new Car(11, "Peugeot", "206");
        Car differentBrand = new Car(12, "Renault", "206");
        Car differentModel = new Car(12, "Renault", "206");

        assertTrue(car.equals(car));
        assertTrue(car.equals(carCopy));

        assertFalse(car.equals(differentId));
        assertFalse(car.equals(differentBrand));
        assertFalse(car.equals(differentModel));
        assertFalse(car.equals(null));
    }

    @Test
    public void hashCodeTest() throws Exception {
        Car carCopy = new Car(12, "Peugeot", "206");
        Car differentId = new Car(11, "Peugeot", "206");
        Car differentBrand = new Car(12, "Renault", "206");
        Car differentModel = new Car(12, "Renault", "206");

        assertEquals(car.hashCode(), carCopy.hashCode());

        assertNotSame(car.hashCode(), differentId.hashCode());
        assertNotSame(car.hashCode(), differentBrand.hashCode());
        assertNotSame(car.hashCode(), differentModel.hashCode());
    }

    @Test
    public void getAndSetSellingPrice() throws Exception {
        car.setPrice(1000);
        assertEquals(1000d, car.sellingPrice());
    }

    @Test
    public void getRentalPrice() throws Exception {
        car.setPrice(3000);
        assertEquals(60d, car.rentalPrice());
    }

    @Test(expected = NullPointerException.class)
    public void setStoredDateNull() {
        car.setStoredDate(null);
    }

    @Test
    public void setStoredDate() throws Exception {
        LocalDate now = LocalDate.now();
        car.setStoredDate(now);
        assertEquals(car.getInBaseDate(), now);
    }

    @Test
    public void addAndRemoveObserver() throws Exception {
        Member observer = new Student(12, "java", "test",12);
        car.addObserver(observer);
        assertTrue(car.getObserverCollection().contains(observer));

        car.removeObserver(observer);
        assertFalse(car.getObserverCollection().contains(observer));

        Member teacher = new Teacher(12, "java", "test",123);
        car.addObserver(teacher);
        assertTrue(car.getObserverCollection().contains(teacher));

        car.removeObserver(teacher);
        assertFalse(car.getObserverCollection().contains(teacher));
    }

    @Test
    public void notifyObserver() throws Exception {
        // TODO: 10/28/2016
    }


    @Test
    public void notifyObservers() throws Exception {
        // TODO: 10/28/2016
    }

    @Test(expected = NullPointerException.class)
    public void addObserverNull() throws RemoteException {
        car.addObserver(null);
    }

    @Test(expected = NullPointerException.class)
    public void removeObserverNull() throws RemoteException {
        car.removeObserver(null);
    }

    @Test
    public void getObserverCollection() throws Exception {
        Member student = new Student(12, "java", "test",2342);
        Member teacher = new Teacher(12, "java", "teacher",23423);

        car.addObserver(student);

        car.addObserver(teacher);

        assertTrue(car.getObserverCollection().size() == 2);
        assertTrue(car.getObserverCollection().contains(student));
        assertTrue(car.getObserverCollection().contains(teacher));

    }


    @Test(expected = NullPointerException.class)
    public void CarNullBrand() throws RemoteException {
        new Car(1, null, "Model ");
    }

    @Test(expected = NullPointerException.class)
    public void CarNullModel() throws RemoteException {
        new Car(1, "Brand", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CarWrongId() throws RemoteException {
        new Car(0, "Brand", "Model");
    }

    @Test
    public void setAvailable() throws Exception {
        car.setAvailable(false);
        assertFalse(car.isAvailable());
        car.setAvailable(true);
        assertTrue(car.isAvailable());
    }


    @Test
    public void isAvailable() throws Exception {
        Car newCar = new Car(13, "brandnew", "model");
        assertTrue(newCar.isAvailable());
        newCar.setAvailable(false);
        assertFalse(newCar.isAvailable());
    }

    @Test
    public void rentedTimes() throws Exception {
        car.incrementRentedTimes();
        car.incrementRentedTimes();
        assertEquals(2, car.rentedTimes());
    }

    @Test
    public void isBuyable() throws RemoteException {
        car.setStoredDate(LocalDate.now());
        assertFalse(car.isBuyable());

        car.setStoredDate(LocalDate.now().minusYears(3));
        car.incrementRentedTimes();
        assertTrue(car.isBuyable());
    }

    @Test
    public void getDescription( ) throws RemoteException {
        String expected = "Car id : 12\nCar brand : Peugeot\nCar model : 206";
        String description = car.description();
        assertEquals(expected, description);
    }

    @Test
    public void willBeBoughtBy() throws Exception {
        Member renter = new Student(1, "renter", "renter",123);
        Member buyer = new Student(1 , "buyer" , "of this car",12314);
        car.setStoredDate(LocalDate.MIN);
        assertTrue(car.isAvailable());

        // renter rents car
        renter.rent(car);
        assertFalse(car.isAvailable());
        assertTrue(car.getObserverCollection().contains(renter));
        assertEquals(renter.currentlyRenting(), car);

        // buyer subscribe to buy car
        buyer.buy(car);

        // test tat renter still uses car
        assertFalse(car.isAvailable());
        assertTrue(car.getObserverCollection().contains(renter));
        assertEquals(renter.currentlyRenting(), car);

        // renter restores car
        renter.restore();

        // test that car is bought
        assertFalse(car.isAvailable());
        assertTrue(car.isBought());
        assertFalse(car.isBuyable());
        assertTrue(car.getObserverCollection().isEmpty());

        // renter tries to rent car again
        renter.rent(car);
        assertTrue(car.getObserverCollection().isEmpty());
    }

    @Test
    public void averageMark(){
        assertEquals(10d,car.getAverageMark());
        car.addComment("",10);
        car.addComment("",5);
        assertEquals(7.5d, car.getAverageMark());
        car.addComment("",5);
        assertEquals(Precision.round(20d/3,2), car.getAverageMark());
    }
}