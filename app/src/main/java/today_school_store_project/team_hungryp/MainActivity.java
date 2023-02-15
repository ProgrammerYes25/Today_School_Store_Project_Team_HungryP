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
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    private long backBtnTime = 0l;  //뒤로가기 누른 횟수 계산하기 위한 변수
    DatabaesHelper databaesHelper;
    ImageView imgvPopular;
    ImageView imgvNew;
    ImageView imgvPrice;
    ImageView imgvSale;
    SQLiteDatabase pDatabase;
    ImageView newImageView, moneyOffImageView, homeImageView, storeImageView, starImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewFlipper viewFlip = findViewById(R.id.viewFlip);
        viewFlip.setFlipInterval(5000);
        viewFlip.startFlipping();
        databaesHelper = new DatabaesHelper(this);
        pDatabase = databaesHelper.getWritableDatabase();
//        Cursor cursor = pDatabase.rawQuery("SELEct * From prTable Where pr_no = 0;",null);
//        int version = pDatabase.getVersion();
//        while (cursor.moveToNext()){
//            version = cursor.getInt(2);
//        }
//        if(version != pDatabase.getVersion()){
//            databaesHelper.onUpgrade(pDatabase, pDatabase.getVersion(),version);
//        }
        //pDatabase.close();
        //databaesHelper.CloseDatabaseFile();
        setTitle("홈");
        imgvPopular = findViewById(R.id.imgv_popular);
        imgvNew = findViewById(R.id.imgv_new);
        imgvPrice = findViewById(R.id.imgv_price);
        imgvSale = findViewById(R.id.imgv_sale);
        imgvPopular.setOnClickListener(imgvListener);
        imgvNew.setOnClickListener(imgvListener);
        imgvPrice.setOnClickListener(imgvListener);
        imgvSale.setOnClickListener(imgvListener);
        //List<Product> productsList = initLoadMarketDatabase();

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

    }
    View.OnClickListener imgvListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imgv_popular:
                    Intent intent1 = new Intent(getApplicationContext(), PopularActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.imgv_new:
                    Intent intent2 = new Intent(getApplicationContext(), NewPageActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.imgv_price:
                    Intent intent3 = new Intent(getApplicationContext(), PricePageActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.imgv_sale:
                    Intent intent4 = new Intent(getApplicationContext(), SalePageActivity.class);
                    startActivity(intent4);
                    break;
            }

        }
    };

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