package fr.upem.rmi.car.loan.client;

import com.google.common.base.Preconditions;
import fr.upem.rmi.car.loan.BankAccount;
import fr.upem.rmi.shared.interfaces.Account;
import fr.upem.rmi.shared.interfaces.Member;
import fr.upem.rmi.shared.interfaces.Product;
import fr.upem.rmi.shared.interfaces.design.ObservableProduct;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

abstract class AbstractMember extends UnicastRemoteObject implements Member {

    private final int id; // member id
    private String firstName;
    private String lastName;
    private Integer accountId; // member bankAccount id
    private boolean boughtAProduct;
    private String password;
    private Account bankAccount;

    private Product currentlyRenting;

    AbstractMember(int id, String firstName, String lastName, Integer accountId) throws RemoteException {
        super(); // initializing the UnicastRemoteObject
        Preconditions.checkArgument(id > 0, "id must be > 0");
        Preconditions.checkNotNull(firstName, "firstName is null");
        Preconditions.checkNotNull(lastName, "lastName is null");
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.boughtAProduct = false;
        this.accountId = accountId;
        this.bankAccount = accountId == null ? null : new BankAccount(accountId);
    }

    /**
     * Sets the password of this member
     *
     * @param password the new password of this user
     */
    public void setPassword(String password) {
        Preconditions.checkNotNull(password, "password should not be null ");
        this.password = password;
    }

    /**
     * @return the password of this current user.
     */
    public String getPassword() {
        return password;
    }

    @Override
    public int getId() throws RemoteException {
        return id;
    }

    @Override
    public String toString() {
        try {
            return description();
        } catch (RemoteException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public void subscribe(ObservableProduct o) throws RemoteException {
        Preconditions.checkNotNull(o, "o should not be null");
        o.addObserver(this);
    }

    @Override
    public void unSubscribe(ObservableProduct o) throws RemoteException {
        Preconditions.checkNotNull(o, "o should not be null");
        o.removeObserver(this);
    }

    /**
     * This method is called when the product is available for this {@link Member}. <br>
     * It will increment the number of times of rental of the {@link Product}, and set this product as the currently rented product.
     *
     * @param product
     */
    @Override
    public void update(ObservableProduct product) throws RemoteException {
        Preconditions.checkNotNull(product, "product should not be null");
        // not renting any car
        if (currentlyRenting == null) {
            Product asProduct = (Product) product;
            asProduct.incrementRentedTimes();
            currentlyRenting = asProduct;
            if (asProduct.isBought()) {
                bankAccount.withdraw(asProduct.sellingPrice());
            } else {
                bankAccount.withdraw(asProduct.rentalPrice());
            }
        } else {
            unSubscribe(product);
        }
    }

    /**
     * Subscribes to the product member rental list.
     *
     * @param product
     */
    @Override
    public void rent(Product product) throws RemoteException {
        Preconditions.checkNotNull(product, "product should not be null");
        if (!hasBoughtAProduct()) {
            subscribe(product);
        }
    }

    @Override
    public void restore() throws RemoteException {
        if (isRenting()) {
            // cannot return a bought car
            if (currentlyRenting.isBought() && hasBoughtAProduct()) {
                return;
            }
            unSubscribe(currentlyRenting);
            currentlyRenting = null;
        }
    }

    private boolean isRenting() {
        return currentlyRenting != null;
    }

    @Override
    public int getAccountId() throws RemoteException {
        return accountId;
    }

    @Override
    public boolean hasBoughtAProduct() throws RemoteException {
        return boughtAProduct;
    }

    @Override
    public void buy(Product product) throws RemoteException {
        Preconditions.checkNotNull(product, "product should not be null");
        if (product.isBuyable()) {
            if (!hasBoughtAProduct()) {
                // restore any owned car
                if (currentlyRenting != null) {
                    restore();
                }
                product.willBeBoughtBy(this);
                boughtAProduct = true;
            }
        }
    }


    @Override
    public Product currentlyRenting() throws RemoteException {
        return currentlyRenting;
    }

    @Override
    public String getFirstName() throws RemoteException {
        return capitalize(firstName);
    }

    @Override
    public void setFirstName(String firstName) throws RemoteException {
        Preconditions.checkNotNull(firstName, "firstName should not be null");
        this.firstName = firstName;
    }

    @Override
    public String getLastName() throws RemoteException {
        return lastName.toUpperCase();
    }

    @Override
    public void setId(int id) throws RemoteException {
        // does nothing as id is final.
    }

    @Override
    public void setLastName(String lastName) throws RemoteException {
        Preconditions.checkNotNull(lastName, "lastName should not be null");
        this.lastName = lastName;
    }

    @Override
    public void comment(Product product, String newComment, int grade) throws RemoteException {
        Preconditions.checkNotNull(product, "product should not be null ");
        Preconditions.checkNotNull(newComment, "newComment should not be null ");
        Preconditions.checkNotNull(grade >= 0 && grade <= 10, "grade should be in [0,10]");
        product.addComment(newComment, grade);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Member)) return false;
        AbstractMember that = (AbstractMember) o;
        boolean sameType;
        try {
            sameType = that.type().equals(this.type());
        } catch (RemoteException e) {
            sameType = false;
        }
        return sameType && accountId.equals(that.accountId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        int typeHashcode;
        try {
            typeHashcode = type().hashCode();
        } catch (RemoteException e) {
            typeHashcode = 0;
        }
        return Objects.hash(typeHashcode, firstName, lastName, accountId);
    }

    @Override
    public String description() throws RemoteException {
        return type().name().toUpperCase() + ":" + "\n" +
                "ID : " + id +
                "\nFirst name : " + capitalize(firstName) +
                "\nLast name : " + lastName.toUpperCase();
    }

    private static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public double getAccountBalance() throws RemoteException {
        return bankAccount.getBalance();
    }

    public void setAccountBalance(double newValue) throws RemoteException {
        bankAccount.setBalance(newValue);
    }
}
