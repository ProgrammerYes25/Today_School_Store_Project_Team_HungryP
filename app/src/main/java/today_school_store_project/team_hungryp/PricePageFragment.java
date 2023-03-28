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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

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
        databaseReference.child("PR_TABLE").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                   String name = dataSnapshot.child("pr_name").getValue(String.class);
                   String price = dataSnapshot.child("pr_price").getValue(Integer.class).toString();
                   if(price.equals(null)){
                       continue;
                   }
                   totalList.add(name+"\n 가격 : "+price+" 원");
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
    public void setTotalList(Cursor cursor){
        if(cursor!=null){
            totalList.clear();
        }
        while(cursor.moveToNext()){
            totalList.add(cursor.getString(1)+"\n 가격 : "+cursor.getInt(2)+" 원");
        }
    }


    View.OnClickListener categoryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cursor cursor;
//            switch (v.getId()) {
//                case R.id.drink_category:
//                    priceTextView.setText(drinkCategory.getText());
//                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"음료류\";", null);
//                    setTotalList(cursor);
//                    adapter.notifyDataSetChanged();
//                    break;
//                case R.id.snack_category:
//                    priceTextView.setText(snackCategory.getText());
//                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"과자류\";", null);
//                    setTotalList(cursor);
//                    adapter.notifyDataSetChanged();
//                    break;
//                case R.id.candy_category:
//                    priceTextView.setText(candyCategory.getText());
//                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"사탕젤리류\";", null);
//                    setTotalList(cursor);
//                    adapter.notifyDataSetChanged();
//                    break;
//                case R.id.icecream_category:
//                    priceTextView.setText(icecreamCategory.getText());
//                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"아이스크림류\";", null);
//                    setTotalList(cursor);
//                    adapter.notifyDataSetChanged();
//                    break;
//                case R.id.ice_category:
//                    priceTextView.setText(iceCategory.getText());
//                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"냉동식품\";", null);
//                    setTotalList(cursor);
//                    adapter.notifyDataSetChanged();
//                    break;
//                case R.id.etc_category:
//                    priceTextView.setText(etcCategory.getText());
//                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"기타잡화\";", null);
//                    setTotalList(cursor);
//                    adapter.notifyDataSetChanged();
//                    break;
//            }

        }
    };
    public static PricePageFragment newInstance() {
        return new PricePageFragment();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.menu_option,menu);
//        return true;//super.onCreateOptionsMenu(menu);
//    }

}





//package today_school_store_project.team_hungryp;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.net.HttpCookie;
//import java.util.ArrayList;
//
//public class PricePageFragment extends Fragment {
//    ListView priceListview;
//    TextView priceTextView;
//    ArrayList totalList;
//    ArrayAdapter<String> adapter;
//
//    // 데이터 베이스
////    DatabaesHelper databaesHelper;
////    SQLiteDatabase sqlDB;
//
//    // 파이어 베이스
//    private FirebaseDatabase database;
//    private DatabaseReference databaseReference;
//    TextView drinkCategory;
//    TextView snackCategory;
//    TextView candyCategory;
//    TextView icecreamCategory;
//    TextView iceCategory;
//    TextView etcCategory;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // 플레그먼트를 메인에 넘길 뷰 정의
//        View view = inflater.inflate(R.layout.activity_price_page, container, false);
////        setTitle("가격 페이지");
//        priceTextView = view.findViewById(R.id.price_menu_text);
//        priceListview = view.findViewById(R.id.price_listview);
//
//        drinkCategory = view.findViewById(R.id.drink_category);
//        snackCategory = view.findViewById(R.id.snack_category);
//        candyCategory = view.findViewById(R.id.candy_category);
//        icecreamCategory = view.findViewById(R.id.icecream_category);
//        iceCategory = view.findViewById(R.id.ice_category);
//        etcCategory = view.findViewById(R.id.etc_category);
//
//
//        drinkCategory.setOnClickListener(categoryListener);
//        snackCategory.setOnClickListener(categoryListener);
//        candyCategory.setOnClickListener(categoryListener);
//        icecreamCategory.setOnClickListener(categoryListener);
//        iceCategory.setOnClickListener(categoryListener);
//        etcCategory.setOnClickListener(categoryListener);
////        databaesHelper = new DatabaesHelper(MainActivity.context);
////        sqlDB = databaesHelper.getReadableDatabase();
////        Cursor cursor;
////        cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"음료류\";", null);
//
//        //데이터 베이스
//        database = FirebaseDatabase.getInstance();
//        databaseReference = database.getReference();
//
//        databaseReference.child("PR_TABLE").addValueEventListener(valueEventListener);
//
//        totalList = new ArrayList();
////        setTotalList(cursor);
//
//        adapter = new ArrayAdapter<String>(MainActivity.context, android.R.layout.simple_list_item_1, totalList) {
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                View view = super.getView(position, convertView, parent);
//                TextView tv = view.findViewById(android.R.id.text1);
//                tv.setTextColor(Color.BLACK);
//                tv.setPadding(40, 20, 20, 20);
//                tv.setBackgroundResource(R.drawable.textlayout);
//                return view;
//            }
//        };
//        priceListview.setAdapter(adapter);
//        return view;
//    }
//    public void setTotalList(Cursor cursor){
//        if(cursor!=null){
//            totalList.clear();
//        }
//        while(cursor.moveToNext()){
//            totalList.add(cursor.getString(1)+"\n 가격 : "+cursor.getInt(2)+" 원");
//        }
//    }
//    // 각각의 Fragment마다 Instance를 반환해 줄 메소드
//     public static PricePageFragment newInstance() {
//        return new PricePageFragment();
//    }
//
//    View.OnClickListener categoryListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
////            Cursor cursor;
////            switch (v.getId()) {
////                case R.id.drink_category:
////                    priceTextView.setText(drinkCategory.getText());
////                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"음료류\";", null);
////                    setTotalList(cursor);
////                    adapter.notifyDataSetChanged();
////                    break;
////                case R.id.snack_category:
////                    priceTextView.setText(snackCategory.getText());
////                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"과자류\";", null);
////                    setTotalList(cursor);
////                    adapter.notifyDataSetChanged();
////                    break;
////                case R.id.candy_category:
////                    priceTextView.setText(candyCategory.getText());
////                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"사탕젤리류\";", null);
////                    setTotalList(cursor);
////                    adapter.notifyDataSetChanged();
////                    break;
////                case R.id.icecream_category:
////                    priceTextView.setText(icecreamCategory.getText());
////                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"아이스크림류\";", null);
////                    setTotalList(cursor);
////                    adapter.notifyDataSetChanged();
////                    break;
////                case R.id.ice_category:
////                    priceTextView.setText(iceCategory.getText());
////                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"냉동식품\";", null);
////                    setTotalList(cursor);
////                    adapter.notifyDataSetChanged();
////                    break;
////                case R.id.etc_category:
////                    priceTextView.setText(etcCategory.getText());
////                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"기타잡화\";", null);
////                    setTotalList(cursor);
////                    adapter.notifyDataSetChanged();
////                    break;
////            }
//
//        }
//    };
//    ValueEventListener valueEventListener = new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            String value = dataSnapshot.getValue(String.class);
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError error) {
//
//        }
//    };
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        MenuInflater inflater=getMenuInflater();
////        inflater.inflate(R.menu.menu_option,menu);
////        return true;//super.onCreateOptionsMenu(menu);
////    }
//
//}