package today_school_store_project.team_hungryp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class activity_new_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_page);
        ActionBar ac = getSupportActionBar();
        ac.setTitle("신규 상품 페이지");
    }
}