
import com.mongodb.*;

import java.util.regex.Pattern;


public class DatabaseHelper {
    private MongoClient client;
    private DBCollection dbCollectionBooks;
    private DBCollection dbColectionAuthors;
    private DB db;


    public DatabaseHelper() {
        client = new MongoClient("localhost", 27017);
        db = client.getDB("personalLibrary");
        createCollection();

    }


    private void createCollection() {
        dbCollectionBooks = db.getCollection("Books");
        dbColectionAuthors = db.getCollection("Authors");

    }


    public void addBook(Book book, Author author) {
        // book object.
        BasicDBObject bookObj = new BasicDBObject("title", book.getTitle())
                .append("author", book.getAuthor())
                .append("year", book.getYear())
                .append("edition", book.getEdition())
                .append("category", book.getCategory())
                .append("subcategory", book.getSubCategory());
        dbCollectionBooks.insert(bookObj);
        // author  object
        BasicDBObject authorobj = new BasicDBObject();
        authorobj.put("name", author.getName());
        authorobj.put("year", author.getYear());
        dbColectionAuthors.insert(authorobj);
    }

    public DBCursor getAllBooks() {
        return dbCollectionBooks.find();
    }

    public DBCursor getAllAuthors() {
        return dbColectionAuthors.find();
    }

    public DBCursor getBooksLargerThanOne() {
        BasicDBObject editionQuery = new BasicDBObject();
        editionQuery.put("edition", new BasicDBObject("$gt", 1));
        return dbCollectionBooks.find(editionQuery);
    }

    public long getHowManyBooksPublishedInYear(int year) {
        BasicDBObject yearQuery = new BasicDBObject();
        yearQuery.put("year", year);
        return dbCollectionBooks.count(yearQuery);
    }

    public DBCursor getAllBooksByCategory(String category) {
        BasicDBObject catQuery = new BasicDBObject();

        BasicDBObject[] obj1 = new BasicDBObject[2];
        obj1[0] = (new BasicDBObject("category", Pattern.compile(category, Pattern.CASE_INSENSITIVE)));
        obj1[1] = (new BasicDBObject("subcategory", Pattern.compile(category, Pattern.CASE_INSENSITIVE)));
        catQuery.put("$or", obj1);

        return dbCollectionBooks.find(catQuery);

    }

    public boolean authorExists(String name) {

        BasicDBObject authorQuery = new BasicDBObject();
        authorQuery.put("name", name);

        DBCursor cursor = dbColectionAuthors.find(authorQuery);

        if (cursor.hasNext()) {
            return true;
        }
        return false;
    }

    private void deleteAuthor(String name) {

        BasicDBObject authorQuery = new BasicDBObject();
        authorQuery.put("name", Pattern.compile(name, Pattern.CASE_INSENSITIVE));

        DBCursor cursor = dbCollectionBooks.find(authorQuery);

        if(!cursor.hasNext()) {
            dbColectionAuthors.remove(authorQuery);
        }
    }

    public boolean bookExists(String title, String author) {
        BasicDBObject bookQuery = new BasicDBObject();

        BasicDBObject[] obj1 = new BasicDBObject[2];
        obj1[0] = (new BasicDBObject("title", Pattern.compile(title, Pattern.CASE_INSENSITIVE)));
        obj1[1] = (new BasicDBObject("author", Pattern.compile(author, Pattern.CASE_INSENSITIVE)));
        bookQuery.put("$and", obj1);

        DBCursor cursor = dbCollectionBooks.find(bookQuery);
        if (cursor.hasNext()) {
            return true;
        }
        return false;
    }
//    public boolean bookExistsWithEdition(String title, String author, int edition) {
//        BasicDBObject bookQuery = new BasicDBObject();
//
//        BasicDBObject[] obj1 = new BasicDBObject[3];
//        obj1[0] = (new BasicDBObject("title", Pattern.compile(title, Pattern.CASE_INSENSITIVE)));
//        obj1[1] = (new BasicDBObject("author", Pattern.compile(author, Pattern.CASE_INSENSITIVE)));
//        obj1[2] = (new BasicDBObject("edition", edition ));
//        bookQuery.put("$and", obj1);
//
//        DBCursor cursor = dbCollectionBooks.find(bookQuery);
//        if (cursor.hasNext()) {
//            return true;
//        }
//        return false;
//    }

    public DBCursor getBooksByAuthor(String author) {
        BasicDBObject regexQuery = new BasicDBObject();
        regexQuery.put("author",
                new BasicDBObject("$regex", author).append("$options", "i"));

        return dbCollectionBooks.find(regexQuery);
    }

    public DBCursor getBooksByTitle(String title) {
        BasicDBObject regexQuery = new BasicDBObject();
        regexQuery.put("title",
                new BasicDBObject("$regex", title)
                        .append("$options", "i"));


        return dbCollectionBooks.find(regexQuery);

    }

    public void deleteBookByTitleAndAuthor(String title, String author){
        BasicDBObject bookQuery = new BasicDBObject();

        BasicDBObject[] obj1 = new BasicDBObject[2];
        obj1[0] = (new BasicDBObject("title", title));
        obj1[1] = (new BasicDBObject("author", author));
        bookQuery.put("$and", obj1);

        dbCollectionBooks.remove(bookQuery);
        deleteAuthor(author);

    }

    public void deleteBookByTitleAndAuthorAndEdition(String title, String author, int edition){
        BasicDBObject bookQuery = new BasicDBObject();

        BasicDBObject[] obj1 = new BasicDBObject[3];
        obj1[0] = (new BasicDBObject("title", title));
        obj1[1] = (new BasicDBObject("author", author));
        obj1[2] = (new BasicDBObject("edition", edition ));
        bookQuery.put("$and", obj1);

        dbCollectionBooks.remove(bookQuery);
        deleteAuthor(author);
    }

}

