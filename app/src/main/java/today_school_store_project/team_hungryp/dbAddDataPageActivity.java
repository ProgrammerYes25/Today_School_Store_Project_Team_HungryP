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

    myDBHelper dbHelper;    //myDBHelper 선언

    RadioGroup LayoutCategory;
    RadioButton btnDrink, btnSnacks, btnIcecream, btnIce, btnEtc;

    EditText editNo, editName, editPrice;     //EditText 선언

    SQLiteDatabase db;
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
        dbHelper = new myDBHelper(this);
        //tableList Adapter 정의
        tableList.setAdapter(adapter);
        tableList.setOnItemClickListener(tableListListener);
    }

    AdapterView.OnItemClickListener tableListListener= new AdapterView.OnItemClickListener() {
        Button btnInput;

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            DBdlgView = View.inflate(dbAddDataPageActivity.this,  R.layout.db_add_product_table_dlg, null);
            AlertDialog.Builder dlg = new AlertDialog.Builder(dbAddDataPageActivity.this);
            btnInput=DBdlgView.findViewById(R.id.btn_input);
            dlg.setTitle(itmes[i]);
            switch (i) {
                //상품 등록
                case 0:
                    //EditText 정의
                    editNo = DBdlgView.findViewById(R.id.edit_no);
                    editName = DBdlgView.findViewById(R.id.edit_name);
                    editPrice = DBdlgView.findViewById(R.id.edit_price);

                    //Radio 관련 정의
                    LayoutCategory = DBdlgView.findViewById(R.id.Layout_category);
                    btnDrink = DBdlgView.findViewById(R.id.btn_drink);
                    btnSnacks = DBdlgView.findViewById(R.id.btn_snacks);
                    btnIcecream = DBdlgView.findViewById(R.id.btn_icecream);
                    btnIce = DBdlgView.findViewById(R.id.btn_ice);
                    btnEtc = DBdlgView.findViewById(R.id.btn_etc);

                    dlg.setView(DBdlgView);
                    btnInput.setOnClickListener(btnListener);
                    //할인상품 등록
               /* case 1:
                    DBdlgView = view.inflate(dbAddDataPageActivity.this, R.layout.db_add_product_table_dlg, null);*/
            }
            dlg.show();
        }
    };
   // btnListener
    View.OnClickListener btnListener = new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           db = dbHelper.getWritableDatabase();
           //Category 선택 분류
           switch (LayoutCategory.getCheckedRadioButtonId()){
               case R.id.btn_drink:
                   categoryText = btnDrink.getText().toString();
                   break;
               case R.id.btn_snacks:
                   categoryText = btnSnacks.getText().toString();
                   break;
               case R.id.btn_icecream:
                   categoryText = btnIcecream.getText().toString();
                   break;
               case R.id.btn_ice:
                   categoryText = btnIce.getText().toString();
                   break;
               case R.id.btn_etc:
                   categoryText = btnEtc.getText().toString();
                   break;
           }
           //DB입력
           db.execSQL("insert into prTable values("+editNo.getText().toString()+", "+editName.getText().toString()+
                   ", "+editPrice.getText().toString()+", "+categoryText+");");
       }
    };

    public static class myDBHelper extends SQLiteOpenHelper{

        public myDBHelper(Context context){
            super(context, "todayStoreDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //DB입력
            db.execSQL("CREATE TABLE prTable (pr_no char(5) primary key, pr_name char(30), " +
                    "pr_price integer, pr_imgid integer, pr_category char(20));");
            db.execSQL("CREATE TABLE  dcTable (dcpr_no char(5) primary key, dc_price Integer," +
                    " foreign key(dcpr_no) references prTable(pr_no));");
            //db.execSQL("CREATE TABLE  mixTable (mix_no char(5) primary key, mix_name char(30), pr1_no char(5), pr2_no char(5)," +
            //       " foreign key(pr1_no) references prTable(pr_no), foreign key(pr2_no) references prTable(pr_no));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            //DB 수정
            db.execSQL("drop table if exists todayStoreDB");
            onCreate(db);
        }
    }
}