package fr.upem.rmi.car.loan.product;

import com.google.common.base.Preconditions;
import fr.upem.rmi.car.loan.Comment;
import fr.upem.rmi.shared.interfaces.Member;
import fr.upem.rmi.shared.interfaces.Product;
import fr.upem.rmi.shared.interfaces.design.ProductObserver;
import org.apache.commons.math3.util.Precision;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@SuppressWarnings("serial")
public class Car extends UnicastRemoteObject implements Product {

    // basic fields
    private int id;
    private final String brand;
    private final String model;

    // fields for availability status
    private volatile boolean available;
    private boolean isBought;
    private int rentedTimes;

    // fields for rental
    private ProductObserver currentRenter; // null when the car is available
    private LocalDate storedDate;
    private double averageMark;
    private final Collection<Comment> comments;
    private final Queue<ProductObserver> students;
    private final Queue<ProductObserver> teachers;
    private Member futureBuyer;

    private Price price;

    // for concurrency

    /**
     * Initializes a newly created {@link Car}
     *
     * @param id    the id of this car
     * @param brand the brand of this car
     * @param model the model of this car
     * @throws RemoteException
     */
    public Car(int id, String brand, String model) throws RemoteException {
        Preconditions.checkArgument(id > 0, "id must be > 0");
        this.id = id;
        this.model = Preconditions.checkNotNull(model);
        this.brand = Preconditions.checkNotNull(brand);
        this.comments = new ArrayList<>();
        this.students = new ArrayDeque<>();
        this.teachers = new ArrayDeque<>();
        this.available = true;
        this.currentRenter = null;
        this.isBought = false;
        this.rentedTimes = 0;
        this.averageMark = 10d;
        this.storedDate = null;
    }

    @Override
    public boolean isBuyable() throws RemoteException {
        return !isBought() && Product.super.isBuyable();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Car)) return false;
        Car car = (Car) o;
        return id == car.id &&
                brand.equals(car.brand) &&
                model.equals(car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model);
    }

    /**
     * Sets the availability of the current car.
     *
     * @param state of availability
     * @throws RemoteException
     */
    public void setAvailable(boolean state) throws RemoteException {
            available = state;
    }

    @Override
    public boolean isBought() throws RemoteException {
        return isBought;
    }

    @Override
    public int getId() throws RemoteException {
        return id;
    }

    @Override
    public String getBrand() throws RemoteException {
        return brand;
    }

    @Override
    public String getModel() throws RemoteException {
        return model;
    }

    /**
     * @return true if the car is available, false otherwise
     */
    @Override
    public boolean isAvailable() {
        return !isBought && available;
    }

    /**
     * @return the date when this car was added to a product storage.
     */
    @Override
    public LocalDate getInBaseDate() {
        return storedDate;
    }

    /**
     * increments the number of rental of this car. And sets this car as not available for rent.
     */
    @Override
    public void incrementRentedTimes() {
        rentedTimes++;
        available = false;
    }

    @Override
    public int rentedTimes() {
        return rentedTimes;
    }

    @Override
    public String description() throws RemoteException {
        return "Car id : " + id + "\nCar brand : " + brand + "\nCar model : " + model;
    }

    /**
     * Sets when this car is added to a car container.
     *
     * @param storedDate the date when this car has been stored.
     */
    public Car setStoredDate(LocalDate storedDate) {
        Preconditions.checkNotNull(storedDate, "storedDate should not be null");
        this.storedDate = storedDate;
        return this;
    }

    @Override
    public void addObserver(ProductObserver newObserver) throws RemoteException {
        // prevent adding new observer if this car is bought already
        if (!isBought) {
            switch (((Member) newObserver).type()) {
                case STUDENT:
                    students.offer(newObserver);
                    break;
                case TEACHER:
                    teachers.offer(newObserver);
                    break;
                default:
                    break;
            }
            if (isAvailable()) {
                notifyObservers();
            }
        } else {
            clearQueues();
        }
    }


    /**
     * Clears all queues of observers of this car.
     */
    private void clearQueues() {
        students.clear();
        teachers.clear();
    }

    @Override
    public void removeObserver(ProductObserver observer) throws RemoteException {
        switch (((Member) observer).type()) {
            case STUDENT:
                students.remove(observer);
                break;
            case TEACHER:
                teachers.remove(observer);
                break;
            default:
                break;
        }
        // after removing that observer,
        // the car is available for purchase
        if (observer.equals(currentRenter)) {
            setAvailable(true);
            if (isAvailable()) {
                notifyObservers();
            }
        }
    }


    /**
     * Specifies the buyer -> the new owner of this car.
     *
     * @param member the member who will buy this product.
     */
    @Override
    public void willBeBoughtBy(Member member) throws RemoteException {
        Preconditions.checkNotNull(member, "member should not be null");
        if (!isBought()) {
            futureBuyer = member;
            if (teachers.isEmpty() && students.isEmpty()){
                available = false;
                isBought = true;
                notifyObserver(futureBuyer);
                clearQueues();
            }
        }
    }

    @Override
    public void notifyObserver(ProductObserver observer) throws RemoteException {
        observer.update(this);
    }

    /**
     * Notifies the observer with the highest priority<br>
     * Teacher > Student
     */
    @Override
    public void notifyObservers() throws RemoteException {
        if (futureBuyer != null) {
            notifyObserver(futureBuyer);
            available = false;
            isBought = true;
            clearQueues();
        } else if (!teachers.isEmpty()) {
            ProductObserver topTeacher = teachers.peek();
            notifyObserver(topTeacher);
            currentRenter = topTeacher;
        }
        // there is at least one student in queue
        else if (!students.isEmpty()) {
            ProductObserver topStudent = students.peek();
            notifyObserver(topStudent);
            currentRenter = topStudent;
        }
        // otherwise the car is available
        else {
            available = true;
            currentRenter = null;
        }
    }

    @Override
    public Collection<ProductObserver> getObserverCollection() {
        Queue<ProductObserver> all = new ArrayDeque<>();
        all.addAll(teachers);
        all.addAll(students);
        return Collections.unmodifiableCollection(all);
    }


    private void updateAverageMark(){
        OptionalDouble average = comments.stream().mapToDouble(Comment::getMark).average();
        averageMark = Precision.round(average.getAsDouble(), 2);
    }
    /**
     * @return averageMark
     */
    @Override
    public double getAverageMark() {
        return averageMark;
    }

    @Override
    public List<String> getComments() throws RemoteException {
        return comments.stream().map(Comment::getSummary).collect(Collectors.toList());
    }

    @Override
    public void addComment(String comment, int grade) {
        Preconditions.checkNotNull(comment, "comment should not be null");
        Preconditions.checkNotNull(grade >= 0 && grade <= 10, "grade should be in [0,10]");
        comments.add(new Comment(grade, comment));
        updateAverageMark();
    }

    /**
     * return the price of the car
     *
     * @return the price this car is sold at
     */
    public double sellingPrice() throws RemoteException {
        return price.sellingPrice();
    }

    public double rentalPrice() throws RemoteException {
        return price.rentalPrice();
    }

    public void setPrice(double newPrice) throws RemoteException {
        Preconditions.checkArgument(newPrice >= 0, "newPrice must be positive, yours is "+newPrice);
        price = new Price(newPrice);
    }
}
