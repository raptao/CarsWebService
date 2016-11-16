package fr.upem.rmi.car.loan.product;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by raptao on 11/11/2016.
 */
public class PriceTest {

    private Price price;

    public PriceTest(){
        price = new Price(3000);
    }

    @Test
    public void sellingPrice() throws Exception {
        assertEquals(3000d, price.sellingPrice());
    }



    @Test
    public void rentalPrice() throws Exception {
        assertEquals(60d, price.rentalPrice());
    }
}