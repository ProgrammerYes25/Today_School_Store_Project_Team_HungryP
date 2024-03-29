package today_school_store_project.team_hungryp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PricePageFragment extends Fragment {
    ListView priceListview;
    TextView priceTextView;
    List totalList;
    ArrayAdapter<String> adapter;
    final int makeList = 0;
    final int makeDialog = 1;
    final int addTouch = 2;
    // SQLite
//    DatabaesHelper databaesHelper;
//    SQLiteDatabase sqlDB;
    // !SQLite
    //Firebase Database
    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference databaseReferenceGet, databaseReferenceSet;
    Dialog dlg;
    int catecoryNum;
    Integer popular;
    String no;

    Map<String, Object> prMap;
    TextView drinkCategory;
    TextView snackCategory;
    TextView candyCategory;
    TextView icecreamCategory;
    TextView iceCategory;
    TextView etcCategory;
    Query databaseQueryD;

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
        prMap = new HashMap<>();
        totalList = new ArrayList<>();
        catecoryNum = 101;
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
        no ="0";
        popular =0;
        priceListview.setAdapter(adapter);
        priceListview.setOnItemClickListener(onItemClickListener);
        database = MainActivity.databaseHelper.getDatabase();
        databaseReferenceGet = database.getReference("pr_table");
        databaseReferenceSet = database.getReference("pr_table");
        storage = MainActivity.storageHelper.getDatabase();
        dlg = null;
        Query databaseQuery = databaseReferenceGet.orderByChild("pr_category").equalTo("음료류");

        setDatabaseQuery(databaseQuery);
        return view;
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int prNo = position+catecoryNum;
            Log.d("확인 : ", "Go to MakeList"+position);
            Query databaseQuery = databaseReferenceGet.orderByChild("pr_catecory_no").equalTo(prNo);
            Log.d("확인 : ", "Go");
            setMakeDialog(databaseQuery);
            //Log.d("확인 : ","다시 돌아왔다.");
            Log.d("값 확인 : ", popular + "개");

        }
    };
    // Database에서 Qurey를 이용하여 data 가져와서 ListView에 넣기
    public void setDatabaseQuery(Query databaseQuery){
        databaseQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                totalList.clear();
                Log.d("확인 : ", "MakeList");
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String name = dataSnapshot.child("pr_name").getValue(String.class);
                    String price = dataSnapshot.child("pr_price").getValue(Integer.class).toString();
                    totalList.add(name+"\n 가격 : "+price+" 원");
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("error : ", String.valueOf(error));
            }
        });

    }

    public void setMakeDialog(Query databaseQuery){
        //databaseReference.child(no).child("pr_popular").setValue(popular);
        if(dlg!=null){
            dlg.dismiss();
            Log.d("확인 :", "dlg 소멸");
        }
        Log.d("확인 :", databaseQuery+"");
        databaseQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("확인 :", snapshot + "확인");
                String name = "이름", price = "가격", image = "이미지";
                Integer prPopular =0, prPrice = null;
                Integer newPrPopular = null;
                Log.d("레퍼런스확인 : ", databaseReferenceGet + "");
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    name = dataSnapshot.child("pr_name").getValue(String.class);
                    prPrice = dataSnapshot.child("pr_price").getValue(Integer.class);
                    price = prPrice.toString();
                    prPopular = dataSnapshot.child("pr_popular").getValue(Integer.class);
                    no =dataSnapshot.child("pr_no").getValue(Integer.class).toString();
                    image = dataSnapshot.child("pr_image_no").getValue(String.class);
                    break;
                }
                Log.d("확인 :", no+" + " +name + " + " + price + " + " + prPopular);
                StorageReference storageReference = storage.getReference();
                StorageReference storageReferenceImage = storageReference.child(image+".png");
                if(storageReferenceImage!=null){
                    dlg = new DialogClass(MainActivity.context, name, price, storageReferenceImage);
                    dlg.show();
                    Log.d("확인 : ", name+"창 띄위기");
                }else{
                    Log.d("확인 : ", "가져오기 실패");
                }
                newPrPopular = prPopular + 1;
                Log.d("값 확인 : ", newPrPopular + "개");
                popular = newPrPopular;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("error : ", String.valueOf(error));
            }
        });
        databaseReferenceSet.child(no).child("pr_popular").setValue(popular);
    }


//  category 별로 나누어 List 보기 위한 Listener
    View.OnClickListener categoryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Query databaseQuery;
            switch (v.getId()) {
                case R.id.drink_category:
                    catecoryNum = 101;
                    priceTextView.setText(drinkCategory.getText());
                    databaseQuery = databaseReferenceGet.orderByChild("pr_category").equalTo("음료류");
                    setDatabaseQuery(databaseQuery);
                    break;
                case R.id.snack_category:
                    catecoryNum = 501;
                    priceTextView.setText(snackCategory.getText());
                    databaseQuery = databaseReferenceGet.orderByChild("pr_category").equalTo("과자류");
                    setDatabaseQuery(databaseQuery);
                    break;
                case R.id.candy_category:
                    catecoryNum = 301;
                    priceTextView.setText(candyCategory.getText());
                    databaseQuery = databaseReferenceGet.orderByChild("pr_category").equalTo("사탕젤리류");
                    setDatabaseQuery(databaseQuery);
                    break;
                case R.id.icecream_category:
                    catecoryNum = 201;
                    priceTextView.setText(icecreamCategory.getText());
                    databaseQuery = databaseReferenceGet.orderByChild("pr_category").equalTo("아이스크림류");
                    setDatabaseQuery(databaseQuery);
                    break;
                case R.id.ice_category:
                    catecoryNum = 401;
                    priceTextView.setText(iceCategory.getText());
                    databaseQuery = databaseReferenceGet.orderByChild("pr_category").equalTo("냉동식품");
                    setDatabaseQuery(databaseQuery);
                    break;
                case R.id.etc_category:
                    catecoryNum = 601;
                    priceTextView.setText(etcCategory.getText());
                    databaseQuery = databaseReferenceGet.orderByChild("pr_category").equalTo("기타잡화");
                    setDatabaseQuery(databaseQuery);
                    break;
            }

        }
    };
    public static PricePageFragment newInstance() {
        return new PricePageFragment();
    }

}