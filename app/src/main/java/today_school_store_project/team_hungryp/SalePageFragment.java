package today_school_store_project.team_hungryp;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class SalePageFragment extends Fragment {
    int[] food = {};
    String[] text = {"피자빵", "피크닉", "감자알칩", "자유시간","포도알맹이"};
    ImageView rbtn, lbtn, imgV1;
    TextView textvi;
    ArrayList<String> textList;
    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference databaseReferenceGet, databaseReferenceSet;
    ImageView newImageView, moneyOffImageView, homeImageView, storeImageView, starImageView;
    int foodi = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 플레그먼트를 메인에 넘길 뷰 정의
        View view = inflater.inflate(R.layout.fragment_sale_page, container, false);
//        setTitle("할인 상품 페이지");
        rbtn = view.findViewById(R.id.r);
        lbtn = view.findViewById(R.id.l);
        imgV1 = view.findViewById(R.id.imgv1);
        textvi = view.findViewById(R.id.textv);
        textList = new ArrayList<>();
        LinearLayout linearLayout = view.findViewById(R.id.salepage_product_ll);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.navigateToPriceFragment();
            }
        });

        database = MainActivity.databaseHelper.getDatabase();
        databaseReferenceGet = database.getReference("pr_table");
        databaseReferenceSet = database.getReference("pr_table");
        storage = MainActivity.storageHelper.getDatabase();
        Query databaseQuery = databaseReferenceGet.orderByChild("pr_sale").startAt(0);
        setDatabaseQuery(databaseQuery);

        return view;
    }
    // 각각의 Fragment마다 Instance를 반환해 줄 메소드
    public static SalePageFragment newInstance() {
        return new SalePageFragment();
    }

    public void setTextList(){
        if(textList.get(0).equals(null)) {
            textList.add("현재 신상품이 없습니다.\n업데이트를 기대해주세요");
        }
    }

    public void setDatabaseQuery(Query databaseQuery){
        databaseQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                textList.clear();
                Log.d("확인 : ", "MakeList");
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String name = dataSnapshot.child("pr_name").getValue(String.class);
                    String price = dataSnapshot.child("pr_price").getValue(Integer.class).toString();
                    String sale = dataSnapshot.child("pr_sale").getValue(Integer.class).toString();
                    textList.add(name+"\n 가격 : "+price+" 원 ->> "+sale+"원");
                    Log.d("확인 : ", name+"\n 가격 : "+price+" 원");
                }
                Log.d("확인 : ",textList+"");
                setTextList();
                textvi.setText(textList.get(0));
                rbtn.setOnClickListener(onClickListener);
                lbtn.setOnClickListener(onClickListener);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("error : ", String.valueOf(error));
            }
        });

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.r:
                    if(textList.get(0).equals("현재 할인상품이 없습니다.\n업데이트를 기대해주세요" )){
                        Toast.makeText(MainActivity.context, "현재 할인상품이 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                    foodi++;        //배열 넘어가는 인덱스
                    if(foodi ==textList.size()) {        //현재 마지막 사진인지 확인한는 조건문
                        foodi = 0;                //처음으로 인덱스 바꿈
                    }
                    //imgV1.setImageResource(food[foodi]);    //이미지 바꿈
                    textvi.setText(textList.get(foodi));
                    break;
                case R.id.l:
                    if(textList.get(0).equals("현재 할인상품이 없습니다.\n업데이트를 기대해주세요" )){
                        Toast.makeText(MainActivity.context, "현재 할인상품이 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                    foodi--;        //배열 넘어가는 인덱스
                    if(foodi == -1) {               //현재 마지막 사진인지 확인한는 조건문
                        foodi = textList.size()-1;  //처음으로 인덱스 바꿈
                    }
                    //imgV1.setImageResource(food[foodi]);    //이미지 바꿈
                    textvi.setText(textList.get(foodi));
                    break;
            }
        }
    };

}