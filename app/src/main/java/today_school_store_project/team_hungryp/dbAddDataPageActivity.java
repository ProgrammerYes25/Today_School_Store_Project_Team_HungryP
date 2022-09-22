package today_school_store_project.team_hungryp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;

public class dbAddDataPageActivity extends AppCompatActivity {
    String[] itmes = {"상품 등록", "할인상품 등록"};  //listview의 imte 이름 배열 선언

    View DBdlgView;     //dlg 선언

    RadioGroup LayoutCategory;
    RadioButton btnDrink, btnSnacks, btnIcecream, btnIce, btnEtc;

    EditText editNo, editName, editPrice;     //EditText 선언
    String categoryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("관리자 설정 페이지");
        setContentView(R.layout.activity_db_add_data_page);
        //ListView 정의
        ListView tableList = findViewById(R.id.table_list);

        //에딥터 선언 및 정의
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,itmes);
        //tableList Adapter 정의
        tableList.setAdapter(adapter);
        tableList.setOnItemClickListener(tableListListener);
    }

    AdapterView.OnItemClickListener tableListListener= new AdapterView.OnItemClickListener() {
        Button btnInput;

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i) {
                //상품 등록
                case 0:
                    setContentView(R.layout.activity_db_add_product_table_pr);
                    //할인상품 등록
                //case 1:
                    //DBdlgView = view.inflate(dbAddDataPageActivity.this, R.layout.db_add_product_table_dlg, null);
            }

        }
    };
}