package today_school_store_project.team_hungryp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class NewPageActivity extends AppCompatActivity {
    private long backBtnTime = 0l;  //뒤로가기 누른 횟수 계산하기 위한 변수
    int[] food = { R.drawable.cider, R.drawable.mychu, R.drawable.jjang};
    ArrayList<String> textList;
    DatabaesHelper databaesHelper;
    SQLiteDatabase sqlDB;
    ImageView rbtn, lbtn, imgV1,imgV2,imgV3;
    TextView textvi;
    ImageView newImageView, moneyOffImageView, homeImageView, storeImageView, starImageView;
    int foodi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_page);
        ViewFlipper viewFlip2 = findViewById(R.id.viewFlip2);
        viewFlip2.setFlipInterval(5000);
        viewFlip2.startFlipping();
        setTitle("신규 상품 페이지");
        rbtn = findViewById(R.id.r);
        lbtn = findViewById(R.id.l);
        imgV1 = findViewById(R.id.imgv1);
        imgV2 = findViewById(R.id.imgv2);
        imgV3 = findViewById(R.id.imgv3);
        textvi = findViewById(R.id.textv);


        //하단 메뉴바
        newImageView = findViewById(R.id.new_image_view);
        moneyOffImageView = findViewById(R.id.money_off_image_view);
        homeImageView = findViewById(R.id.home_image_view);
        storeImageView = findViewById(R.id.store_image_view);
        starImageView = findViewById(R.id.star_image_view);

        newImageView.setOnClickListener(menuOnClickListener);
        moneyOffImageView.setOnClickListener(menuOnClickListener);
        homeImageView.setOnClickListener(menuOnClickListener);
        storeImageView.setOnClickListener(menuOnClickListener);
        starImageView.setOnClickListener(menuOnClickListener);
        // /하단 메뉴바

        databaesHelper = new DatabaesHelper(this);
        sqlDB = databaesHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_new = 1;",null);
        textList = new ArrayList<String>();
        setTextList(cursor);
        imgV1.setImageResource(food[foodi]);
        textvi.setText(textList.get(foodi));
        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textList.get(0).equals("현재 할인상품이 없습니다.\n업데이트를 기대해주세요" )){
                    Toast.makeText(getApplicationContext(), "현재 할인상품이 없습니다.", Toast.LENGTH_SHORT).show();
                }
                foodi++;        //배열 넘어가는 인덱스
                if(foodi ==textList.size()) {        //현재 마지막 사진인지 확인한는 조건문
                    foodi = 0;                //처음으로 인덱스 바꿈
                }
                imgV1.setImageResource(food[foodi]);    //이미지 바꿈
                textvi.setText(textList.get(foodi));
            }
        });
        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textList.get(0).equals("현재 할인상품이 없습니다.\n업데이트를 기대해주세요" )){
                    Toast.makeText(getApplicationContext(), "현재 할인상품이 없습니다.", Toast.LENGTH_SHORT).show();
                }
                foodi--;        //배열 넘어가는 인덱스
                if(foodi == -1) {               //현재 마지막 사진인지 확인한는 조건문
                    foodi = textList.size()-1;  //처음으로 인덱스 바꿈
                }
                imgV1.setImageResource(food[foodi]);    //이미지 바꿈
                textvi.setText(textList.get(foodi));
            }
        });
    }
    public void setTextList(Cursor cursor){
        if(cursor.isAfterLast()){
            textList.add("현재 신상품이 없습니다.\n업데이트를 기대해주세요");
        }
        while(cursor.moveToNext()){
            textList.add(cursor.getString(1));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_option,menu);
        return true;//super.onCreateOptionsMenu(menu);
    }

    //하단 메뉴바
    View.OnClickListener menuOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.money_off_image_view:
                    startActivityM(SalePageActivity.class);
                    break;
                case R.id.home_image_view:
                    startActivityM(MainActivity.class);
                    break;
                case R.id.store_image_view:
                    startActivityM(PricePageActivity.class);
                    break;
                case R.id.star_image_view:
                    startActivityM(PopularActivity.class);
                    break;
            }
        }
    };

    private void startActivityM(Class activityClass){ //activity전환 메소드
        Intent intent = new Intent(this, activityClass);
        finish();
        startActivity(intent);

    }
    @Override
    public void onBackPressed() {//뒤로 가기 누르면 종료
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        else {
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
        }
    }
}