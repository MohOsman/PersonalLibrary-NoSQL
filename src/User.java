
/**
 * Operational Class User
 */
public class User {
    private View view ;
    private DatabaseHelper db = new DatabaseHelper();;
    private Book  book;
    private Author author;

    public User(View view) {
        this.view = view;
        book = new Book();
        author  = new Author();

        system();
    }

    /**
     * Method to run menu in a while-loop. Calls View to get user inputs and to get what methods to call from that input.
     */
    private void  system(){
        view.showWelcomeMessage();
      while (true) {
          view.showMenu();
          int choice = view.getInput();
          if(choice == 1){
              addBook();
          }
          else if(choice == 2) {
              deleteBook();
          }
          else if(choice == 3) {
              searchOptions();
          }
      }
    }

    /**
     * A sub-menu for when the user selects "search" in the main menu. This loops until user selects to go back.
     *  Calls View to get user inputs and to get what methods to call from that input.
     */
    private void searchOptions() {
        while(true) {
            view.showSearchMenu();
            int choice = view.getInput();
            if(choice == 1) {
                getBooksByTitle();
            }
            else if(choice == 2) {
                getAllBooks();
            }
            else if(choice == 3) {
                getAllBooksWithEditionLargerThanOne();
            }
            else if(choice == 4) {
                getBooksByAuthor();
            }
            else if(choice == 5) {
                getAllBooksByCategory();
            }
            else if(choice == 6) {
                getHowManyBooksPublishedInYear();
            }
            else if(choice == 7) {
                getAllAuthors();
            }
            else {
                break;
            }
        }
    }

    /*
    Following methods are query methods to read, insert or remove data to/from the database.
    They call the View class to print out the result.
     */


    private void getHowManyBooksPublishedInYear() {
        System.out.print("Enter year: ");
        int year = view.getInt();
//        view.print(db.getHowManyBooksPublishedInYear(year));
    }

    private void getAllAuthors() {
        db.getAllAuthors();
    }

    private void getAllBooksByCategory() {
        System.out.print("\nSearch category: ");
        String category = view.getString();
//        view.printBooksShort(db.getAllBooksByCategory(category));
    }

    private void getAllBooks() {
//        view.printBooks(db.getAllBooks());
        System.out.println(db.getAllBooks());
    }

    private void getAllBooksWithEditionLargerThanOne() {
    db.getbookslargerthanone();
    }

    private void getBooksByAuthor() {
        System.out.print("\nSearch: ");
        String author = view.getString();
//        view.printBooks(db.getBooksByAuthor(author));
    }

    private void getBooksByTitle() {
        System.out.print("\nSearch: ");
        String title = view.getString();
//        view.printBooks(db.getBooksByTitle(title));
    }

    /**
     * Method to remove a Book from the database. The user will be asked to input Title, Author (and edition) of the Book to remove.
     */
    private void deleteBook() {
        Book tempBook = view.getTitleAuthorEdition();   // Creates a temporary Book of input Title, Author (and edition)
//        if (db.bookExists(tempBook.getTitle(), tempBook.getAuthor())) { // Checks if the Book exists in the database
//            if (tempBook.getEdition() == -1) {
//                db.deleteBook(tempBook.getTitle(), tempBook.getAuthor());
//            } else {
//                db.deleteSpecificBook(tempBook.getTitle(), tempBook.getAuthor(), tempBook.getEdition());
//            }
//        }else{
//            System.out.println("The book does not exist");
//        }
    }

    /**
     * Method to add a Book to the database.
     */
    private void addBook() {
        view.getBookInformation(book,author);
//        if(!db.authorExists(author.getName())) {
//            view.addYearToAuthor(author);
//        }
        db.addBook(book, author);
    }


}
