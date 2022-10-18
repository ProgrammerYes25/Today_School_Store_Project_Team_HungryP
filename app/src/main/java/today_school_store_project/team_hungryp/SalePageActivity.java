package today_school_store_project.team_hungryp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
public class SalePageActivity extends AppCompatActivity {
    int[] food = {R.drawable.pizza, R.drawable.piknic, R.drawable.potato, R.drawable.chocolate, R.drawable.grape};
    String[] text = {"피자빵", "피크닉", "감자알칩", "자유시간","포도알맹이"};
    ImageView rbtn, lbtn, imgV1;
    TextView textvi;
    int foodi = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_page);
        setTitle("할인 상품 페이지");
        rbtn = findViewById(R.id.r);
        lbtn = findViewById(R.id.l);
        imgV1 = findViewById(R.id.imgv1);
        textvi = findViewById(R.id.textv);
        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodi++;        //배열 넘어가는 인덱스
                if(foodi ==food.length) {        //현재 마지막 사진인지 확인한는 조건문
                    foodi = 0;                //처음으로 인덱스 바꿈
                }
                imgV1.setImageResource(food[foodi]);    //이미지 바꿈
                textvi.setText(text[foodi]);
            }
        });

        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodi--;        //배열 넘어가는 인덱스
                if(foodi == -1) {               //현재 마지막 사진인지 확인한는 조건문
                    foodi = food.length-1;  //처음으로 인덱스 바꿈
                }
                imgV1.setImageResource(food[foodi]);    //이미지 바꿈
                textvi.setText(text[foodi]);
            }
        });
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
                Intent intentpopular = new Intent(this, SeenALotPageActivity.class);
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