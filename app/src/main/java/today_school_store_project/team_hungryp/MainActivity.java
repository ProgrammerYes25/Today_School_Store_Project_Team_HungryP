package today_school_store_project.team_hungryp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
ImageView imgvPrice;
ImageView imgvNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("홈");
        imgvPrice = findViewById(R.id.imgv_price);
        imgvNew = findViewById(R.id.imgv_new);
        imgvPrice.setOnClickListener(imgvListener);
        imgvNew.setOnClickListener(imgvListener);

    }
    View.OnClickListener imgvListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imgv_price:
                    Intent intent1 = new Intent(getApplicationContext(), PricePageActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.imgv_new:
                    Intent intent2 = new Intent(getApplicationContext(), NewPageActivity.class);
                    startActivity(intent2);
                    break;
            }

        }
    };
}