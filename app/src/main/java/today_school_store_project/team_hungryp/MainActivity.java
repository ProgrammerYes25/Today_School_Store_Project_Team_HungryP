package today_school_store_project.team_hungryp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Process;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseException;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class MainActivity extends AppCompatActivity {
    public void navigateToPriceFragment() {
        PricePageFragment priceFragment = new PricePageFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame_layout, priceFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static Context context;
    private long backBtnTime = 0l;  //뒤로가기 누른 횟수 계산하기 위한 변수

    //SQLite
//    DatabaesHelper databaesHelper;
//    SQLiteDatabase pDatabase;
    //!SQLite
    Fragment newPageFragment, salePageFragment, mainFragment, pricePageFragment, popularFragment;
    BottomNavigationView mainBottomNavigationView;
    LinearLayout productLinear;
    // Firebase Database
    static FirebaseDatabaseHelper databaseHelper;
    static FirebaseStorageHelper storageHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("홈");
        //SQLite
//        databaesHelper = new DatabaesHelper(this);
//        pDatabase = databaesHelper.getWritableDatabase();
        //!SQLite

        // Firebase Database
        databaseHelper = new FirebaseDatabaseHelper();
        FirebaseDatabase database = databaseHelper.getDatabase();
        storageHelper = new FirebaseStorageHelper();
        FirebaseStorage storage = storageHelper.getDatabase();
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
    }
    static FirebaseDatabaseHelper getDatabaseHelper (){
        return databaseHelper;
    }

    //  Fragment 내의 Fragment 전환을 위한 메소드(버튼을 이용한 전환)
    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, fragment).commitAllowingStateLoss();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

    //뒤로 가기 누르면 종료
    @Override
    public void onBackPressed() {
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
                    replaceFragment( newPageFragment);

                    break;
                case R.id.money_off_item:
                    setTitle("할인 상품 페이지");
                    replaceFragment(salePageFragment);
                    break;
                case R.id.home_item:
                    setTitle("홈");
                    replaceFragment( mainFragment);
                    break;
                case R.id.store_item:
                    setTitle("가격 페이지");
                    replaceFragment(pricePageFragment);
                    break;
                case R.id.star_item:
                    setTitle("인기 상품 페이지");
                    replaceFragment(popularFragment);
                    break;
            }
            return true;
        }
    };
}