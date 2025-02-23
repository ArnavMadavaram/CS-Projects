package lab;

/**
 * Name: Rishigesh Rajendrakumar
 * Username: rajer03
 */

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private double price;

    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s - $%.2f", title, price);
    }
}
