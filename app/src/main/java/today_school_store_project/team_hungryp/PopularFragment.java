package today_school_store_project.team_hungryp;

import androidx.fragment.app.Fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class PopularFragment extends Fragment {
    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference databaseReferenceGet;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        // 플레그먼트를 메인에 넘길 뷰 정의
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        GridView gridView = view.findViewById(R.id.popular_grid_view);
        GridListAdapter adapter = new GridListAdapter();
        database = MainActivity.databaseHelper.getDatabase();
        databaseReferenceGet = database.getReference("pr_table");
        storage = MainActivity.storageHelper.getDatabase();




        return view;
    }
    public static PopularFragment newInstance() {
        return new PopularFragment();
    }  // 각각의 Fragment마다 Instance를 반환해 줄 메소드

}