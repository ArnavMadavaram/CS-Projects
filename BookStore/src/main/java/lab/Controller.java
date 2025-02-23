package lab;

/**
 * Name: Rishigesh Rajendrakumar
 * Username: rajer03
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Controller {

    @FXML private VBox root;
    @FXML private MenuItem loadBooksMenuItem;
    @FXML private MenuItem themeMenuItem;
    @FXML private MenuItem exitMenuItem;
    @FXML private ListView<Book> availableBooksListView;
    @FXML private Button addToCartButton;
    @FXML private Button removeFromCartButton;
    @FXML private ListView<Book> cartListView;

    private ObservableList<Book> availableBooks = FXCollections.observableArrayList();
    private ObservableList<Book> cartItems = FXCollections.observableArrayList();
    private static final double TAX_RATE = 0.07;

    @FXML
    public void initialize() {
        availableBooksListView.setItems(availableBooks);
        cartListView.setItems(cartItems);

        loadBooksMenuItem.setOnAction(e -> loadBooks());
        themeMenuItem.setOnAction(e -> toggleTheme());
        exitMenuItem.setOnAction(e -> System.exit(0));

        addToCartButton.setOnAction(e -> addToCart());
        removeFromCartButton.setOnAction(e -> removeFromCart());
    }

    private void loadBooks() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    String title = (String) ois.readObject();
                    double price = ois.readDouble();
                    availableBooks.add(new Book(title, price));
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void toggleTheme() {
        if (root.getStyleClass().contains("light-theme")) {
            root.getStyleClass().remove("light-theme");
            root.getStyleClass().add("dark-theme");
        } else {
            root.getStyleClass().remove("dark-theme");
            root.getStyleClass().add("light-theme");
        }
    }

    @FXML
    private void clearCart() {
        cartItems.clear();
    }

    @FXML
    private void checkOut() {
        double subtotal = cartItems.stream().mapToDouble(Book::getPrice).sum();
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Checkout");
        alert.setHeaderText("Checkout Details");
        alert.setContentText(String.format(
                "Subtotal: $%.2f\nTax: $%.2f\nTotal: $%.2f",
                subtotal, tax, total
        ));
        alert.showAndWait();
        cartItems.clear();
    }

    private void addToCart() {
        Book selectedBook = availableBooksListView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            cartItems.add(selectedBook);
        }
    }

    private void removeFromCart() {
        Book selectedBook = cartListView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            cartItems.remove(selectedBook);
        }
    }
}
