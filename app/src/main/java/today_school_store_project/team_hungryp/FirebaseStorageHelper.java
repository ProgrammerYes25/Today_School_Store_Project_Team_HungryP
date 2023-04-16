package today_school_store_project.team_hungryp;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Calendar;

public class FirebaseStorageHelper {

    FirebaseStorage Storage;
    public FirebaseStorageHelper(){
        Storage = FirebaseStorage.getInstance();
    }
    public FirebaseStorage getDatabase(){
        return Storage;
    }
}
