package fr.upem.rmi.car.loan.product;

import com.google.common.base.Preconditions;
import org.apache.commons.math3.util.Precision;

/**
 * Represents the price of a {@link fr.upem.rmi.shared.interfaces.Product} object.
 */
public class Price {

    public static final double PERCENTAGE = 2d/ 100;
    public static final int SCALE = 2;
    private final double sellingPrice;
    private final double rentalPrice;

    public Price(double price) {
        Preconditions.checkArgument(price > 0, "price must be > 0, yours is "+price);
        sellingPrice = price;
        rentalPrice = Precision.round(sellingPrice * PERCENTAGE, SCALE);
    }

    /**
     * @return a price for selling a product
     */
    public double sellingPrice() {
        return sellingPrice;
    }

    /**
     * The rental price is the selling_price / 10
     *
     * @return the rental price of a product.
     */
    public double rentalPrice() {
        return rentalPrice;
    }
}
