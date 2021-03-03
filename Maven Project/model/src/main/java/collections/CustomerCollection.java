package collections;

import cinemaObjects.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the collection of customers,
 * that may modify and provide different show
 *
 * @author Phong Nguyen
 * @version 2021-03-02
 */

public class CustomerCollection extends AbstractCollection {

    private static final long serialVersionUID = 398906222416370481L;
    private final List<Customer> allCustomers;
    private final String filename;

    /**
     * Constructor for initializing the CustomerCollection instance
     *
     * @param filename Filename of the object when serializing
     */

    public CustomerCollection(String filename) {
        this.filename = filename;
        allCustomers = new ArrayList<>();
    }

    /**
     * Gets a customer given ssn
     *
     * @param ssn - the ssn that being searched
     * @return a customer
     */

    public Customer getCustomer(String ssn) {
        for (Customer c : this.allCustomers) {
            if (c.getSSN().equals(ssn)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Adds a customer to the objects customer list
     *
     * @param c - the customer being added
     */
    public void addCustomer(Customer c) {
        this.allCustomers.add(c);
    }


    /**
     * Get method for the filename when serializing
     *
     * @return a string of the filename
     */

    public String getFilename() {
        return this.filename;
    }

    /**
     * Get method for the list of customers
     *
     * @return a list of customers
     */

    public List<Customer> getAllCustomers() {
        return allCustomers;
    }

    /**
     * Read serialized file from the filenames path
     *
     * @return CustomerCollection of the read file
     */
    public CustomerCollection readCollection() {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(this.filename))) {

            CustomerCollection readThis = (CustomerCollection) stream.readObject();
            System.out.println("File: " + this.filename + " has been read");
            return readThis;
        } catch (ClassCastException | ClassNotFoundException | FileNotFoundException | NullPointerException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return new CustomerCollection(this.filename);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
