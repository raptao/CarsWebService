package fr.upem.rmi.car.loan;

import fr.upem.rmi.car.loan.client.MemberList;
import fr.upem.rmi.car.loan.product.CarContainer;
import fr.upem.rmi.shared.interfaces.Member;
import fr.upem.rmi.shared.interfaces.MemberDatabase;
import fr.upem.rmi.shared.interfaces.Product;
import fr.upem.rmi.shared.interfaces.ProductStorage;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.*;

/**
 * Created by raptao on 11/8/2016.
 */
public class RentAndBuyTest {

    @Test
    public void rent() throws IOException {
        ProductStorage carContainer = CarContainer.create(RentAndBuyTest.class.getResourceAsStream("/products-generator.txt"));
        assertFalse( carContainer.copy().isEmpty());
        MemberDatabase memberDatabase = MemberList.create(RentAndBuyTest.class.getResourceAsStream("/members-generator.txt"));
        Member member = memberDatabase.get(1, "secretPassword");
        Member member2 = memberDatabase.get(2, "secretPassword");
        Product product = carContainer.get(1);
        assertNotNull(member);
        assertNotNull(product);
        member.rent(product);
        assertFalse(product.isAvailable());
        member2.rent(product);
        assertNull(member2.currentlyRenting());
        member.restore();
        assertEquals(product, member2.currentlyRenting());
    }

    @Test
    public void buyAndRestore() throws IOException {
        ProductStorage carContainer = CarContainer.create(RentAndBuyTest.class.getResourceAsStream("/products-generator.txt"));
        MemberDatabase memberDatabase = MemberList.create(RentAndBuyTest.class.getResourceAsStream("/members-generator.txt"));
        Member member = memberDatabase.get(1, "secretPassword");
        Product product = carContainer.get(1);
        member.rent(product);
        member.restore();
        assertTrue(product.isBuyable());
        member.buy(product);
        assertTrue(member.hasBoughtAProduct());
        assertTrue(product.isBought());
        assertFalse(product.isBuyable());
        assertFalse(product.isAvailable());
        member.restore();
        assertTrue(member.hasBoughtAProduct());
        assertTrue(product.isBought());
        assertFalse(product.isBuyable());
        assertFalse(product.isAvailable());
        assertEquals(product, member.currentlyRenting());
    }

}
