package fr.upem.webservice.app;

import com.google.common.base.Preconditions;
import org.apache.commons.math3.util.Precision;
import org.tempuri.ConverterLocator;
import org.tempuri.ConverterSoap;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Active internet connection is needed to used this class
 */
public class CurrencyConverter {

    public final static String EUR = "EUR";
    public final static String GBP = "GBP";
    public final static String USD = "USD";
    public final static String CNY = "CNY";
    public final static String JPY = "JPY";
    public final static String AUD = "AUD";
    public final static String CHF = "CHF";
    public static final int SCALE = 2;

    private final String currency;

    public CurrencyConverter(String currency) {
        Preconditions.checkNotNull(currency, "currency should not be null");
        this.currency = currency.toUpperCase();
    }

    /**
     * Converts the given value to the target currency
     * @param value value to convert
     * @param targetCurrency the target currency
     * @throws ServiceException
     * @throws RemoteException
     */
    public double convert( double value, String targetCurrency ) throws ServiceException, RemoteException{
        Preconditions.checkNotNull(targetCurrency, "targetCurrency should not be null");
		ConverterSoap converter = new ConverterLocator().getConverterSoap();
        double conversionRate = converter.getConversionRate(currency, targetCurrency.toUpperCase(), converter.getLastUpdateDate()).doubleValue();
        return Precision.round(conversionRate*value, SCALE);
	}

	public static List<String> availableCurrency() throws RemoteException, ServiceException {
        ConverterSoap converter = new ConverterLocator().getConverterSoap();
        String[] currencies = converter.getCurrencies();
        return Stream.of(currencies).collect(Collectors.toList());
    }

    public String getCurrency(){
        return currency;
    }
}
