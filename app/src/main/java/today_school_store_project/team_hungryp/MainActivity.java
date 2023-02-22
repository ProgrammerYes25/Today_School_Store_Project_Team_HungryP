package today_school_store_project.team_hungryp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Process;
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
    SQLiteDatabase pDatabase;
    ImageView newImageView, moneyOffImageView, homeImageView, storeImageView, starImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("홈");
        databaesHelper = new DatabaesHelper(this);
        pDatabase = databaesHelper.getWritableDatabase();
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