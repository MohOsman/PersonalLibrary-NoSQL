
import com.mongodb.*;

import java.util.Arrays;
import java.util.List;




public class DatabaseHelper {

    public static void main( String args[] ) {



        try{

// Since 2.10.0, uses MongoClient
            MongoClient mongo = new MongoClient( "localhost" , 27017 );

            // Now connect to your databases
            DB db = mongo.getDB("test");

            System.out.println("Connect to database successfully");
//            boolean auth = db.authenticate(myUserName, myPassword);
//            System.out.println("Authentication: "+auth);

            DBCollection coll = db.getCollection("testCollection");
            System.out.println(coll.count());

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}