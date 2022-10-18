package today_school_store_project.team_hungryp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class PopularPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_page);
        setTitle("많이 본 상품 페이지");
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
                Intent intentpopular = new Intent(this,PopularPageActivity.class);
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