package fr.upem.rmi.car.loan.product;

import fr.upem.rmi.car.loan.client.Student;
import fr.upem.rmi.shared.interfaces.Product;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static junit.framework.TestCase.*;

/**
 * Created by raptao on 11/5/2016.
 */
public class CarContainerTest {
    @Test
    public void create() throws Exception {
        InputStream path = CarContainerTest.class.getResourceAsStream("/products-generator.txt");
        path.mark(0);
        CarContainer carContainer = CarContainer.create(path);
        Product product = carContainer.get(1);
        assertFalse(product.isBuyable());
        path.reset();
        assertEquals( carContainer.copy().size(), IOUtils.readLines(path, StandardCharsets.UTF_8).size());
    }

    @Test
    public void refresh() throws IOException {
        InputStream path = CarContainerTest.class.getResourceAsStream("/products-generator.txt");
        path.mark(0);
        CarContainer carContainer = CarContainer.create(path);
        Student member = new Student(3, "first", "last", 234);
        member.setAccountBalance(999999d);
        Product product = carContainer.get(1);
        assertTrue( carContainer.copy().contains(product));
        member.rent(product);
        member.restore();
        member.buy(product);
        assertTrue(product.isBought());
        carContainer.refresh();
        assertFalse( carContainer.copy().contains(product));

        double d = 7/3d;

    }
}