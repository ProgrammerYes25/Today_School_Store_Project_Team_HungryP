package today_school_store_project.team_hungryp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Process;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static Context context;
    private long backBtnTime = 0l;  //뒤로가기 누른 횟수 계산하기 위한 변수
    DatabaesHelper databaesHelper;
    SQLiteDatabase pDatabase;
    Fragment newPageFragment, salePageFragment, mainFragment, pricePageFragment, popularFragment;
    BottomNavigationView mainBottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("홈");
        databaesHelper = new DatabaesHelper(this);
        pDatabase = databaesHelper.getWritableDatabase();
        context = this;

        //네비게이션바 플레그먼트 정의
        newPageFragment = new NewPageFragment();
        salePageFragment = new SalePageFragment();
        mainFragment = new MainFragment();
        pricePageFragment = new PricePageActivity();
        popularFragment = new PopularFragment();
        mainBottomNavigationView = findViewById(R.id.main_bottom_navigation_view);

        getSupportFragmentManager().beginTransaction().add(R.id.main_frame_layout, mainFragment).commitAllowingStateLoss();

        mainBottomNavigationView.setOnNavigationItemSelectedListener( mainNavigationItemSelectedListener);
//    private List<Product> initLoadMarketDatabase(){
//        DatabaesHelper databaesHelper = new DatabaesHelper(getApplicationContext());
//        databaesHelper.OpenDatabaseFile();
//        setContentView(R.layout.loading_page);
//        List<Product> productsList = databaesHelper.getTableData();
//        Log.e("test", String.valueOf(productsList.size()));
//        setContentView(R.layout.activity_main);
//        //databaesHelper.close();
//        return productsList;
//
//    }
    }
    @Override
    public void onBackPressed() {//뒤로 가기 누르면 종료
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed();
            moveTaskToBack(true);
            Process.killProcess(Process.myPid());
            System.exit(1);
        }
        else {
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
        }
    }
    BottomNavigationView.OnNavigationItemSelectedListener  mainNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.new_item:
                    setTitle("신규 상품 페이지");
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, newPageFragment).commitAllowingStateLoss();
                    break;
                case R.id.money_off_item:
                    setTitle("할인 상품 페이지");
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, salePageFragment).commitAllowingStateLoss();
                    break;
                case R.id.home_item:
                    setTitle("홈");
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, mainFragment).commitAllowingStateLoss();
                    break;
                case R.id.store_item:
                    setTitle("가격 페이지");
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, pricePageFragment).commitAllowingStateLoss();
                    break;
                case R.id.star_item:
                    setTitle("인기 상품 페이지");
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, popularFragment).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    };
}