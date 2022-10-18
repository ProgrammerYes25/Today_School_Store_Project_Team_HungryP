package today_school_store_project.team_hungryp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
ImageView imgvPop;
ImageView imgvNew;
ImageView imgvPrice;
ImageView imgvSale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("홈");
        imgvPop = findViewById(R.id.imgv_pop);
        imgvNew = findViewById(R.id.imgv_new);
        imgvPrice = findViewById(R.id.imgv_price);
        imgvSale = findViewById(R.id.imgv_sale);
        imgvPop.setOnClickListener(imgvListener);
        imgvNew.setOnClickListener(imgvListener);
        imgvPrice.setOnClickListener(imgvListener);
        imgvSale.setOnClickListener(imgvListener);
        List<Product> productsList = initLoadMarketDatabase();

    }
    View.OnClickListener imgvListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imgv_pop:
                    Intent intent1 = new Intent(getApplicationContext(), SeenALotPageActivity.class);
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
                Intent intentpopular = new Intent(this, SeenALotPageActivity.class);
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

    private List<Product> initLoadMarketDatabase(){
        DatabaesHelper databaesHelper = new DatabaesHelper(getApplicationContext());
        databaesHelper.OpenDatabaseFile();

        List<Product> productsList = databaesHelper.getTableData();
        Log.e("test", String.valueOf(productsList.size()));

        //databaesHelper.close();
        return productsList;
    }
}