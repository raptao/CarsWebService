package fr.upem.rmi.car.loan.client;

import fr.upem.rmi.car.loan.product.Car;
import fr.upem.rmi.shared.interfaces.Product;
import fr.upem.rmi.shared.interfaces.design.ObservableProduct;
import org.junit.Test;

import java.rmi.RemoteException;
import java.time.LocalDate;

import static junit.framework.TestCase.*;


/**
 * Created by raptao on 10/29/2016.
 */
public class AbstractMemberTest {


    private final AbstractMember member;

    public AbstractMemberTest() throws RemoteException {
        this.member = new Student(1, "thierry", "rabearijao",1234);
    }

    @Test(expected = IllegalArgumentException.class)
    public void AbstractMemberWrongId() throws RemoteException {
        new Student(-1, "thierry", "rabearijao");
    }

    @Test(expected = NullPointerException.class)
    public void AbstractMemberNullFirstName() throws RemoteException {
        new Student(1, null, "rabearijao");
    }

    @Test(expected = NullPointerException.class)
    public void AbstractMemberNullLastName() throws RemoteException {
        new Student(1, "thierry", null);
    }


    @Test
    public void subscribeAndUnsubscibe() throws Exception {
        ObservableProduct product = new Car(12, "Peugeot", "234");
        ((Product)product).setPrice(1);
        member.subscribe(product);
        assertTrue(product.getObserverCollection().contains(member));

        member.unSubscribe(product);
        assertTrue(product.getObserverCollection().isEmpty());
    }

    @Test( expected = NullPointerException.class)
    public void updateNull( ) throws RemoteException {
        member.update(null);
    }

    @Test
    public void update() throws Exception {
        ObservableProduct oldProduct = new Car(12, "Peugeot", "234");
        ((Product)oldProduct).setPrice(10);
        oldProduct.notifyObserver(member); // implicitly calls member.update()
        assertEquals(oldProduct, member.currentlyRenting());

        ObservableProduct newAvailableProduct = new Car(13, "Renault", "5");
        newAvailableProduct.notifyObserver(member);
        assertEquals(oldProduct, member.currentlyRenting());
    }

    @Test( expected = NullPointerException.class)
    public void rentNull( ) throws RemoteException {
        member.rent(null);
    }

    @Test
    public void rentAndRestore() throws Exception {
        member.restore();
        Product product = new Car(12, "Peugeot", "234");
        product.setPrice(1000);
        member.setAccountBalance(10000);
        member.rent(product);
        assertEquals(9980d, member.getAccountBalance());
        assertEquals(product, member.currentlyRenting());
        member.restore();
        assertTrue(member.currentlyRenting() == null);
    }

    @Test
    public void restoreAnyProduct() throws RemoteException {
        member.restore();
        Product product = new Car(12, "Peugeot", "234");
        product.setPrice(10);
        member.rent(product);
        member.restore();
        assertFalse(member.currentlyRenting() != null);
    }

    @Test
    public void equals() throws Exception {
        AbstractMember memberCopy = new Student(1, "thierry", "rabearijao",1234);
        AbstractMember differentType = new Teacher(1, "thierry", "rabearijao");
        AbstractMember differentFirstName = new Student(1, "raptao", "rabearijao");
        AbstractMember differentLastName = new Student(1, "thierry", "raptao");
        assertTrue(member.equals(member));
        assertTrue(member.equals(memberCopy));

        assertFalse(member.equals(differentType));
        assertFalse(member.equals(differentFirstName));
        assertFalse(member.equals(differentLastName));
    }

    @Test
    public void hashCodeTest() throws Exception {
        AbstractMember memberCopy = new Student(1, "thierry", "rabearijao",1234);
        AbstractMember differentType = new Teacher(1, "thierry", "rabearijao");
        AbstractMember differentFirstName = new Student(1, "raptao", "rabearijao");
        AbstractMember differentLastName = new Student(1, "thierry", "raptao");

        assertEquals(member.hashCode(), memberCopy.hashCode());

        assertNotSame(member.hashCode(), differentType.hashCode());
        assertNotSame(member.hashCode(), differentFirstName.hashCode());
        assertNotSame(member.hashCode(), differentLastName.hashCode());
    }



    @Test
    public void memberDescription( ) throws RemoteException {
        String expectedDescription = member.type().name().toUpperCase()+":\nID : "+member.getId()+"\nFirst name : Thierry\nLast name : RABEARIJAO";
        String description = member.description();
        assertEquals(expectedDescription, description);
    }

    @Test
    public void toStringTest( ) throws RemoteException {
        String description = member.description();
        assertEquals(description, member.toString());
    }

    @Test
    public void buy() throws RemoteException, InterruptedException {
        member.setAccountBalance(10000);
        Product product = new Car(1, "test", "car").setStoredDate(LocalDate.MIN);
        product.setPrice(3000);
        member.rent(product);
        assertEquals(9940d, member.getAccountBalance());
        member.restore();
        member.rent(product);
        assertEquals(9880d, member.getAccountBalance());
        member.restore();
        assertTrue(product.isAvailable());
        assertTrue(product.isBuyable());
        member.buy(product);
        assertTrue(member.hasBoughtAProduct());
        assertFalse(product.isBuyable());
        assertFalse(product.isAvailable());
        assertTrue(product.isBought());
        assertEquals(6880d, member.getAccountBalance());
        assertEquals(product, member.currentlyRenting());

        member.restore();

        assertTrue(member.hasBoughtAProduct());
        assertEquals(product, member.currentlyRenting());
        assertFalse(product.isBuyable());
        assertFalse(product.isAvailable());
        assertTrue(product.isBought());


    }
}