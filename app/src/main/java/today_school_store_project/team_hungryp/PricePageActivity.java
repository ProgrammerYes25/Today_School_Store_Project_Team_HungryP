package today_school_store_project.team_hungryp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PricePageActivity extends AppCompatActivity {
    private long backBtnTime = 0l;  //뒤로가기 누른 횟수 계산하기 위한 변수
    ListView priceListview;
    TextView priceTextView;
    ArrayList totalList;
    ArrayAdapter<String> adapter;
    DatabaesHelper databaesHelper;
    SQLiteDatabase sqlDB;
    TextView drinkCategory;
    TextView snackCategory;
    TextView candyCategory;
    TextView icecreamCategory;
    TextView iceCategory;
    TextView etcCategory;
    ImageView newImageView, moneyOffImageView, homeImageView, storeImageView, starImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_page);
        setTitle("가격 페이지");
        priceTextView = findViewById(R.id.price_menu_text);
        priceListview = findViewById(R.id.price_listview);

        drinkCategory = findViewById(R.id.drink_category);
        snackCategory = findViewById(R.id.snack_category);
        candyCategory = findViewById(R.id.candy_category);
        icecreamCategory = findViewById(R.id.icecream_category);
        iceCategory = findViewById(R.id.ice_category);
        etcCategory = findViewById(R.id.etc_category);
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

        drinkCategory.setOnClickListener(categoryListener);
        snackCategory.setOnClickListener(categoryListener);
        candyCategory.setOnClickListener(categoryListener);
        icecreamCategory.setOnClickListener(categoryListener);
        iceCategory.setOnClickListener(categoryListener);
        etcCategory.setOnClickListener(categoryListener);
        databaesHelper = new DatabaesHelper(this);
        sqlDB = databaesHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"음료류\";",null);
        totalList = new ArrayList();
        setTotalList(cursor);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, totalList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.BLACK);
                tv.setPadding(40,20,20,20);
                tv.setBackgroundResource(R.drawable.textlayout);
                return view;
            }
        };
        priceListview.setAdapter(adapter);
    }
    public void setTotalList(Cursor cursor){
        if(cursor!=null){
            totalList.clear();
        }
        while(cursor.moveToNext()){
            totalList.add(cursor.getString(1)+"\n 가격 : "+cursor.getInt(2)+" 원");
        }
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
    View.OnClickListener categoryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cursor cursor;
            switch (v.getId()) {
                case R.id.drink_category:
                    priceTextView.setText(drinkCategory.getText());
                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"음료류\";", null);
                    setTotalList(cursor);
                    adapter.notifyDataSetChanged();
                    break;
                case R.id.snack_category:
                    priceTextView.setText(snackCategory.getText());
                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"과자류\";", null);
                    setTotalList(cursor);
                    adapter.notifyDataSetChanged();
                    break;
                case R.id.candy_category:
                    priceTextView.setText(candyCategory.getText());
                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"사탕젤리류\";", null);
                    setTotalList(cursor);
                    adapter.notifyDataSetChanged();
                    break;
                case R.id.icecream_category:
                    priceTextView.setText(icecreamCategory.getText());
                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"아이스크림류\";", null);
                    setTotalList(cursor);
                    adapter.notifyDataSetChanged();
                    break;
                case R.id.ice_category:
                    priceTextView.setText(iceCategory.getText());
                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"냉동식품\";", null);
                    setTotalList(cursor);
                    adapter.notifyDataSetChanged();
                    break;
                case R.id.etc_category:
                    priceTextView.setText(etcCategory.getText());
                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=\"기타잡화\";", null);
                    setTotalList(cursor);
                    adapter.notifyDataSetChanged();
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

}