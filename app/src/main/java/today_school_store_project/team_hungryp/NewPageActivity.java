package today_school_store_project.team_hungryp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class NewPageActivity extends AppCompatActivity {
    int[] food = {R.drawable.pizza, R.drawable.piknic, R.drawable.potato, R.drawable.chocolate};
    String[] text = {"피자빵", "피크닉", "감자알칩", "자유시간"};
    ImageView rbtn, lbtn, imgV1;
    TextView textvi;
    int foodi =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_page);

        setTitle("신규 상품 페이지");
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
}