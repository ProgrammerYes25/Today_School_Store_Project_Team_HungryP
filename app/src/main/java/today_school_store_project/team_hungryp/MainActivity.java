package today_school_store_project.team_hungryp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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

    }
    View.OnClickListener imgvListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imgv_pop:
                    Intent intent1 = new Intent(getApplicationContext(), PopularPageActivity.class);
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
}