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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PricePageActivity extends AppCompatActivity {

    ListView priceListview;
    TextView priceTextView;
    int[] categoryIDList = new int[]{R.id.drink_category, R.id.icecream_category, R.id.candy_category,R.id.icecream_category, R.id.ice_category, R.id.etc_category};
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

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, totalList);
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