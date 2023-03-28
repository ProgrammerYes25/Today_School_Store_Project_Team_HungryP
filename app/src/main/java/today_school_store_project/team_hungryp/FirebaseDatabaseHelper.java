package today_school_store_project.team_hungryp;

import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDatabaseHelper {

    FirebaseDatabase database;
    public FirebaseDatabaseHelper(){
        database = FirebaseDatabase.getInstance();
    }
    public FirebaseDatabase getDatabase(){
        return database;
    }
}
