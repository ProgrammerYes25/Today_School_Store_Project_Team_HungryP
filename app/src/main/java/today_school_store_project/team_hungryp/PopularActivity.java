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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PopularActivity extends AppCompatActivity {
    private long backBtnTime = 0l;  //뒤로가기 누른 횟수 계산하기 위한 변수
    DatabaesHelper databaesHelper;
    SQLiteDatabase sqlDB;
    TextView top1Text, top2Text, top3Text, top4Text, top5Text, top6Text
    , top7Text, top8Text, top9Text;
    ImageView newImageView, moneyOffImageView, homeImageView, storeImageView, starImageView;
    ImageView top1img, top2img, top3img;
    int[] imgList = {R.drawable.wheim, R.drawable.hershey, R.drawable.ohyes};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);
        setTitle("인기 상품 페이지");

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
//        Cursor cursor;
//        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 1;",null);
//        setPopularList(top1Text, cursor);
//        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 2;",null);
//        setPopularList(top2Text, cursor);
//        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 3;",null);
//        setPopularList(top3Text, cursor);
//        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 4;",null);
//        setPopularList(top4Text, cursor);
//        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 5;",null);
//        setPopularList(top5Text, cursor);
//        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 6;",null);
//        setPopularList(top6Text, cursor);
//        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 7;",null);
//        setPopularList(top7Text, cursor);
//        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 8;",null);
//        setPopularList(top8Text, cursor);
//        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 9;",null);
//        setPopularList(top9Text, cursor);

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
                case R.id.new_image_view:
                    startActivityM(NewPageActivity.class);
                    break;
                case R.id.money_off_image_view:
                    startActivityM(SalePageActivity.class);
                    break;
                case R.id.home_image_view:
                    startActivityM(MainActivity.class);
                    break;
                case R.id.store_image_view:
                    startActivityM(PricePageActivity.class);
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