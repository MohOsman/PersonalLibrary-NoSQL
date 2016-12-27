
import com.mongodb.*;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;


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

    public DBCursor getbookslargerthanone() {

        BasicDBObject gtQuery = new BasicDBObject();
        gtQuery.put("edition", new BasicDBObject("$gt", 1));
        return dbCollectionBooks.find(gtQuery);

    }
}