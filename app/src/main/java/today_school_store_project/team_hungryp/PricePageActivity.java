package today_school_store_project.team_hungryp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=" + drinkCategory.getText().toString() + ";", null);
                    setTotalList(cursor);
                    break;
                case R.id.snack_category:
                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=" + snackCategory.getText().toString() + ";", null);
                    setTotalList(cursor);
                    break;
                case R.id.candy_category:
                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=" + candyCategory.getText().toString() + ";", null);
                    setTotalList(cursor);
                    break;
                case R.id.icecream_category:
                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=" + icecreamCategory.getText().toString() + ";", null);
                    setTotalList(cursor);
                    break;
                case R.id.ice_category:
                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=" + iceCategory.getText().toString() + ";", null);
                    setTotalList(cursor);
                    break;
                case R.id.etc_category:
                    cursor = sqlDB.rawQuery("SELEct * From prTable Where pr_category=" + etcCategory.getText().toString() + ";", null);
                    setTotalList(cursor);
                    break;
            }
        }
    };

}