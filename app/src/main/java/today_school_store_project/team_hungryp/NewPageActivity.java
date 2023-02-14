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
    int[] food = { R.drawable.cider, R.drawable.mychu, R.drawable.jjang};
    ArrayList<String> textList;
    DatabaesHelper databaesHelper;
    SQLiteDatabase sqlDB;
    ImageView rbtn, lbtn, imgV1,imgV2,imgV3;
    TextView textvi;
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu1:
                Intent intenthome = new Intent(this,MainActivity.class);
                startActivity(intenthome);
                break;
            case R.id.menu2:
                Intent intentprice = new Intent(this,  PricePageActivity.class);
                startActivity(intentprice);
                break;
            case R.id.menu3:
                Intent intentpopular = new Intent(this, PopularActivity.class);
                startActivity(intentpopular);
                break;
            case R.id.menu4:
                Intent intentnew = new Intent(this, NewPageActivity.class);
                startActivity(intentnew);
                break;
            case R.id.menu5:
                Intent intentsale = new Intent(this, SalePageActivity.class);
                startActivity(intentsale);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}