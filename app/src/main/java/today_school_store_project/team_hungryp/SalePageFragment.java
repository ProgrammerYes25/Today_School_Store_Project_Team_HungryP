package today_school_store_project.team_hungryp;
import androidx.fragment.app.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SalePageFragment extends Fragment {
    int[] food = {};
    String[] text = {"피자빵", "피크닉", "감자알칩", "자유시간","포도알맹이"};
    ImageView rbtn, lbtn, imgV1;
    TextView textvi;
    ArrayList<String> textList;
    DatabaesHelper databaesHelper;
    SQLiteDatabase sqlDB;
    ImageView newImageView, moneyOffImageView, homeImageView, storeImageView, starImageView;
    int foodi = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View 객체 정의
        View view = inflater.inflate(R.layout.fragment_sale_page, container, false);
//        setTitle("할인 상품 페이지");
        rbtn = view.findViewById(R.id.r);
        lbtn = view.findViewById(R.id.l);
        imgV1 = view.findViewById(R.id.imgv1);
        textvi = view.findViewById(R.id.textv);



        databaesHelper = new DatabaesHelper(MainActivity.context);
        sqlDB = databaesHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_dcprice = 1;",null);
        textList = new ArrayList<String>();
        setTextList(cursor);
        textvi.setText(textList.get(foodi));
        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textList.get(0).equals("현재 할인상품이 없습니다.\n업데이트를 기대해주세요" )){
                    Toast.makeText(MainActivity.context, "현재 할인상품이 없습니다.", Toast.LENGTH_SHORT).show();
                }
                foodi++;        //배열 넘어가는 인덱스
                if(foodi ==textList.size()) {        //현재 마지막 사진인지 확인한는 조건문
                    foodi = 0;                //처음으로 인덱스 바꿈
                }
                //imgV1.setImageResource(food[foodi]);    //이미지 바꿈
                textvi.setText(textList.get(foodi));
            }
        });

        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textList.get(0).equals("현재 할인상품이 없습니다.\n업데이트를 기대해주세요" )){
                    Toast.makeText(MainActivity.context, "현재 할인상품이 없습니다.", Toast.LENGTH_SHORT).show();
                }
                foodi--;        //배열 넘어가는 인덱스
                if(foodi == -1) {               //현재 마지막 사진인지 확인한는 조건문
                    foodi = textList.size()-1;  //처음으로 인덱스 바꿈
                }
                //imgV1.setImageResource(food[foodi]);    //이미지 바꿈
                textvi.setText(textList.get(foodi));
            }
        });
        return view;
    }
    public void setTextList(Cursor cursor){
        if(cursor.isAfterLast()){
            textList.add("현재 할인상품이 없습니다.\n업데이트를 기대해주세요");
        }
        while(cursor.moveToNext()){
            textList.add(cursor.getString(1));
        }
    }

}