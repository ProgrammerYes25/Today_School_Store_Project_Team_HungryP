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
import android.widget.TextView;

public class PopularActivity extends AppCompatActivity {
    DatabaesHelper databaesHelper;
    SQLiteDatabase sqlDB;
    TextView top1Text, top2Text, top3Text, top4Text, top5Text, top6Text
    , top7Text, top8Text, top9Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);
        setTitle("인기 상품 페이지");

        top1Text = findViewById(R.id.top_1_text);
        top2Text = findViewById(R.id.top_2_text);
        top3Text = findViewById(R.id.top_3_text);
        top4Text = findViewById(R.id.top_4_text);
        top5Text = findViewById(R.id.top_5_text);
        top6Text = findViewById(R.id.top_6_text);
        top7Text = findViewById(R.id.top_7_text);
        top8Text = findViewById(R.id.top_8_text);
        top9Text = findViewById(R.id.top_9_text);

        databaesHelper = new DatabaesHelper(this);
        sqlDB = databaesHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 1;",null);
        setPopularList(top1Text, cursor);
        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 2;",null);
        setPopularList(top2Text, cursor);
        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 3;",null);
        setPopularList(top3Text, cursor);
        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 4;",null);
        setPopularList(top4Text, cursor);
        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 5;",null);
        setPopularList(top5Text, cursor);
        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 6;",null);
        setPopularList(top6Text, cursor);
        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 7;",null);
        setPopularList(top7Text, cursor);
        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 8;",null);
        setPopularList(top8Text, cursor);
        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 9;",null);
        setPopularList(top9Text, cursor);

    }
    public void setPopularList(TextView textView, Cursor cursor){
        while(cursor.moveToNext()){
            textView.setText(cursor.getString(1));
        }
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