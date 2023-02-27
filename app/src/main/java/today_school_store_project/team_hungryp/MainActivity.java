package today_school_store_project.team_hungryp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Process;
import android.view.MenuItem;
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
        pricePageFragment = new PricePageFragment();
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
    //  Fragment 내의 Fragment 전환을 위한 메소드
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame_layout, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
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

    //네비게이션 플레그먼트 전환
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