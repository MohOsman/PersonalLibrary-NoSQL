import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class View {

    private Scanner scanner;

    public void showWelcomeMessage() {
        System.out.println(" Welcome to Your Personal Library!");
    }

    public void showMenu() {

        System.out.println("\n1. Add book ");
        System.out.println("2. Delete book");
        System.out.println("3. Search options");

    }

    public void showSearchMenu() {
        System.out.println("\n1. Search books by title");
        System.out.println("2. List all books");
        System.out.println("3. List all books with edition > 1");
        System.out.println("4. Search books by author");
        System.out.println("5. Search books by category");
        System.out.println("6. Number of books published");
        System.out.println("7. List all authors");

        System.out.println("\n9. Back");
    }

    public int getInput() {
        scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public String getString() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    /**
     * Method that asks the user to input information about Book and Author.
     * @param book Book to add information to
     * @param author Author to add information to.
     */
    public void getBookInformation(Book book, Author author) {
        scanner = new Scanner(System.in);
        System.out.print("Title of the Book: ");
        book.setTitle(scanner.nextLine());

        System.out.print("Name of the author: ");
        author.setName(scanner.nextLine());
        book.setAuthor(author.getName());

        System.out.print("year published: ");
        book.setYear(Integer.valueOf(scanner.nextLine()));

        System.out.print("Category: ");
        book.setCategory(scanner.nextLine());

        System.out.print("Subcategory (press enter if no subcategory): ");
        book.setSubCategory(scanner.nextLine());

        System.out.print("Edition: ");
        book.setEdition(Integer.valueOf(scanner.nextLine()));
    }

    /**
     * Method to add Year attribute to Author.
     * @param author Author to add year attribute to.
     */
    public void addYearToAuthor(Author author) {
        scanner = new Scanner(System.in);
        System.out.print("This is a new author, please enter year the author was born: ");
        author.setYear(scanner.nextInt());
    }

    /**
     * Method that creates a temporary Book of inputs by the user. This gets Title and edition of Book and Name of Author.
     * @return Book temporary book created by input data.
     */
    public Book getTitleAuthorEdition() {
        Book tempBook = new Book();
        scanner = new Scanner(System.in);
        System.out.print("Title of the Book: ");
        tempBook.setTitle(scanner.nextLine());
        System.out.print("Name of the Author: ");
        tempBook.setAuthor(scanner.nextLine());
        System.out.print("Edition (enter -1 to get all): ");
        tempBook.setEdition(scanner.nextInt());
        return tempBook;
    }

    /**
     * Method to print out Books with all their attributes of a given ResultSet. Prints out Title, Author, Edition, Year and Category (/subcategory).
     * @param cursor ResultSet containing Books to print out.
     */
    public void printBooks(DBCursor cursor) {
        DBObject obj;
            while(cursor.hasNext()) {
                obj = cursor.next();
                System.out.println(obj.get("title") + ", by " +
                        obj.get("author") + ", year " +
                        obj.get("year") + " edition: " +
                        obj.get("edition") + ", " +
                        obj.get("category") + "/" +
                        obj.get("subcategory") + "."
                );
            }
            System.out.println();
    }


    public void print(long num) {
        System.out.println(num);
    }

    public int getInt() {
        scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


}








