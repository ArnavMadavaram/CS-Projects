public class Main {
    public static void main(String[] args) {
        Box<Book> bookBox = new Box<>();
        bookBox.store(new Book("The Great Gatsby"));

        Box<Toy> toyBox = new Box<>();
        toyBox.store(new Toy("Action Figure"));

        System.out.println("The item in the book box is: " + bookBox.getContents().getName());
        System.out.println("The weight of the book box contents is: " + bookBox.getContents().getWeight() + " grams");

        System.out.println("The item in the toy box is: " + toyBox.getContents().getName());
        System.out.println("The weight of the toy box contents is: " + toyBox.getContents().getWeight() + " grams");
    }
}
