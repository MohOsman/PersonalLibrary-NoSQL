
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
        client = new MongoClient("localhost" , 27017);
        db = client.getDB("personalLibrary");
        createCollection();

    }


    private void createCollection(){
        dbCollectionBooks = db.getCollection("Books");
        dbColectionAuthors = db.getCollection("Authors");

    }

    public  void createDocumnet (){
     BasicDBObject dbObject = new BasicDBObject();
        dbCollectionBooks.insert();




    }


    public void addBook(Book book, Author author) {
        BasicDBObject bookObj = new BasicDBObject("title", book.getTitle())
                .append("author", book.getAuthor())
                .append("year", book.getYear())
                .append("edition", book.getEdition())
                .append("category", book.getCategory())
                .append("subcategory",book.getSubCategory());
        dbCollectionBooks.insert(bookObj);
    }

    public long getAllBooks() {
        return dbCollectionBooks.count();
    }
}