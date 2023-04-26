package today_school_store_project.team_hungryp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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


        Query databaseQuery = databaseReferenceGet.orderByChild("pr_popular").limitToLast(9);
        databaseQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("확인 :", snapshot + "확인");
                String name = "이름", image = "이미지";
                StorageReference storageReference = storage.getReference();
                Log.d("레퍼런스확인 : ", databaseReferenceGet + "");
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    name = dataSnapshot.child("pr_name").getValue(String.class);
                    image = dataSnapshot.child("pr_image_no").getValue(String.class);
                    Log.d("불러온 내용확인 : ", "이름 "+name +", 사진 "+image );
                    adapter.addItem(new GridItem(storageReference.child(image+".png"), name));
                }
                Log.d("확인 :", adapter+"확인");
                gridView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("error : ", String.valueOf(error));
            }
        });

        return view;
    }
    public static PopularFragment newInstance() {
        return new PopularFragment();
    }  // 각각의 Fragment마다 Instance를 반환해 줄 메소드

}