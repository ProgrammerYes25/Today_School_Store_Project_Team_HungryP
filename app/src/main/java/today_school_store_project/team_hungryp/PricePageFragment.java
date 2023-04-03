package today_school_store_project.team_hungryp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PricePageFragment extends Fragment {
    ListView priceListview;
    TextView priceTextView;
    List totalList;
    ArrayAdapter<String> adapter;
    // SQLite
//    DatabaesHelper databaesHelper;
//    SQLiteDatabase sqlDB;
    // !SQLite
    //Firebase Database
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    TextView drinkCategory;
    TextView snackCategory;
    TextView candyCategory;
    TextView icecreamCategory;
    TextView iceCategory;
    TextView etcCategory;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 플레그먼트를 메인에 넘길 뷰 정의
        View view = inflater.inflate(R.layout.activity_price_page, container, false);
//        setTitle("가격 페이지");
        priceTextView = view.findViewById(R.id.price_menu_text);
        priceListview = view.findViewById(R.id.price_listview);

        drinkCategory = view.findViewById(R.id.drink_category);
        snackCategory = view.findViewById(R.id.snack_category);
        candyCategory = view.findViewById(R.id.candy_category);
        icecreamCategory = view.findViewById(R.id.icecream_category);
        iceCategory = view.findViewById(R.id.ice_category);
        etcCategory = view.findViewById(R.id.etc_category);


        drinkCategory.setOnClickListener(categoryListener);
        snackCategory.setOnClickListener(categoryListener);
        candyCategory.setOnClickListener(categoryListener);
        icecreamCategory.setOnClickListener(categoryListener);
        iceCategory.setOnClickListener(categoryListener);
        etcCategory.setOnClickListener(categoryListener);


        // SQLite
//        databaesHelper = new DatabaesHelper(MainActivity.context);
//        sqlDB = databaesHelper.getReadableDatabase();
//        Cursor cursor;
//        cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"음료류\";", null);
//        totalList = new ArrayList();
//        setTotalList(cursor);
        // !SQLite

        //Firebase Database
        totalList = new ArrayList<>();

        adapter = new ArrayAdapter<String>(MainActivity.context, android.R.layout.simple_list_item_1, totalList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // 리스트 뷰 커스텀
                View view = super.getView(position, convertView, parent);
                TextView tv = view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.BLACK);
                tv.setPadding(40, 20, 20, 20);
                tv.setBackgroundResource(R.drawable.textlayout);
                return view;
            }
        };
        priceListview.setAdapter(adapter);
        database = MainActivity.databaseHelper.getDatabase();
        databaseReference = database.getReference("__collections__");
        Query databaseQuery = databaseReference.child("PR_TABLE").orderByChild("pr_category").equalTo("음료류");
        setDatabaseQuery(databaseQuery);

        return view;
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    };

    // Database에서 Qurey를 이용하여 data 가져와서 ListView에 넣기
    public void setDatabaseQuery(Query databaseQuery){
        databaseQuery.addValueEventListener(new ValueEventListener() {
                @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    totalList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String name = dataSnapshot.child("pr_name").getValue(String.class);
                    String price = dataSnapshot.child("pr_price").getValue(Integer.class).toString();
                    totalList.add(name+"\n 가격 : "+price+" 원");
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
//  category 별로 나누어 List 보기 위한 Listener
    View.OnClickListener categoryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Query databaseQuery;
            switch (v.getId()) {
                case R.id.drink_category:
                    priceTextView.setText(drinkCategory.getText());
                    databaseQuery = databaseReference.child("PR_TABLE").orderByChild("pr_category").equalTo("음료류");
                    setDatabaseQuery(databaseQuery);
                    break;
                case R.id.snack_category:
                    priceTextView.setText(snackCategory.getText());
                    databaseQuery = databaseReference.child("PR_TABLE").orderByChild("pr_category").equalTo("과자류");
                    setDatabaseQuery(databaseQuery);
                    break;
                case R.id.candy_category:
                    priceTextView.setText(candyCategory.getText());
                    databaseQuery = databaseReference.child("PR_TABLE").orderByChild("pr_category").equalTo("사탕젤리류");
                    setDatabaseQuery(databaseQuery);
                    break;
                case R.id.icecream_category:
                    priceTextView.setText(icecreamCategory.getText());
                    databaseQuery = databaseReference.child("PR_TABLE").orderByChild("pr_category").equalTo("아이스크림류");
                    setDatabaseQuery(databaseQuery);
                    break;
                case R.id.ice_category:
                    priceTextView.setText(iceCategory.getText());
                    databaseQuery = databaseReference.child("PR_TABLE").orderByChild("pr_category").equalTo("냉동식품");
                    setDatabaseQuery(databaseQuery);
                    break;
                case R.id.etc_category:
                    priceTextView.setText(etcCategory.getText());
                    databaseQuery = databaseReference.child("PR_TABLE").orderByChild("pr_category").equalTo("기타잡화");
                    setDatabaseQuery(databaseQuery);
                    break;
            }

        }
    };
    public static PricePageFragment newInstance() {
        return new PricePageFragment();
    }

}