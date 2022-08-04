package today_school_store_project.team_hungryp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class dbAddDataPageActivity extends AppCompatActivity {
    String[] itmes = {"상품 등록", "인기상품 등록", "할인상품 등록", "꿀조합등록"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("관리자 설정 페이지");
        setContentView(R.layout.activity_db_add_data_page);

        ListView tableList = findViewById(R.id.table_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,itmes);
        tableList.setAdapter(adapter);
    }
}