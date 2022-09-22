package today_school_store_project.team_hungryp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class db_add_product_table_pr extends AppCompatActivity {
    TextView textNo,textName, textPrice;
    EditText editNo, editName, editPrice, editResultNo,editResultName,editResultPr,editResultC;
    RadioGroup layoutCategory;
    RadioButton btnDrink, btnSnacks, btnIcecream, btnIce, btnEtc;
    Button btnSelect;
    //데이터 베이스
    DBHelper dbHelper;
    LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_add_product_table_pr);
        //textView
            textNo = findViewById(R.id.text_no);
            textName = findViewById(R.id.text_name);
            textPrice = findViewById(R.id.text_price);
            //editView
            editNo = findViewById(R.id.edit_no);
            editName = findViewById(R.id.edit_name);
            editPrice = findViewById(R.id.edit_price);

        editResultNo = findViewById(R.id.edit_result_no);
        editResultName = findViewById(R.id.edit_result_name);
        editResultPr = findViewById(R.id.edit_result_pr);
        editResultC  = findViewById(R.id.edit_result_c);
            //Radio
            layoutCategory = findViewById(R.id.Layout_category);
            btnDrink = findViewById(R.id.btn_drink);
            btnSnacks = findViewById(R.id.btn_snacks);
            btnIcecream = findViewById(R.id.btn_icecream);
            btnIce = findViewById(R.id.btn_etc);
            //btn
        Button btnInit = findViewById(R.id.btn_init);
        Button btnInsert = findViewById(R.id.btn_insert);
        Button btnUpdate = findViewById(R.id.btn_update);
        Button btnDelete = findViewById(R.id.btn_delete);
        btnSelect = findViewById(R.id.btn_select);
        btnInit.setOnClickListener(btnListener);
        btnInsert.setOnClickListener(btnListener);
        btnUpdate.setOnClickListener(btnListener);
        btnDelete.setOnClickListener(btnListener);
        btnSelect.setOnClickListener(btnListener);
        dbHelper = new DBHelper(this);
    }

    View.OnClickListener btnListener= new View.OnClickListener() {
        SQLiteDatabase db;
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_init:
                    db = dbHelper.getWritableDatabase();
                    dbHelper.onUpgrade(db,1,2);
                    //db.close();
                    break;

                case R.id.btn_insert:
                    String categoryText = "기타";
                    db = dbHelper.getWritableDatabase();
                    db.execSQL("INSERT INTO prTable VALUES('"+editNo.getText().toString()+ "', '"+  editName.getText().toString()+ "', '" +editPrice.getText().toString()+ "', '" + categoryText + "');");
                    //db.close();
                    Toast.makeText(getApplicationContext(),"새로운 정보가 추가되었습니다.", Toast.LENGTH_LONG).show();
                    editNo.setText("");
                    editName.setText("");
                    editPrice.setText("");
                    btnSelect.callOnClick();
                    break;

                case R.id.btn_update:
                    db = dbHelper.getWritableDatabase();
                    db.execSQL("update prTable set pr_no = "+ editNo.toString() +" where pr_  pr_price = "+ editPrice.getText().toString()+");");
                    btnSelect.callOnClick();
                    //db.close();
                    editNo.setText("");
                    editName.setText("");
                    editPrice.setText("");
                    break;

                case R.id.btn_delete:
                    db = dbHelper.getWritableDatabase();
                    db.execSQL("delete from prTable where name =' "+ editName.getText().toString() +" ';");
                    btnSelect.callOnClick();
                    //db.close();
                    editNo.setText("");
                    editName.setText("");
                    editPrice.setText("");
                    break;

                case R.id.btn_select:
                    db = dbHelper.getReadableDatabase();
                    Cursor c = db.rawQuery("select * from prTable;",null);

                    String strNo = "제품번호\r\n__________\r\n";
                    String strName = "제품명\r\n__________\r\n";
                    String strPr= "제품가격\r\n__________\r\n";
                    String strC = "종류\r\n__________\r\n";

                    while (c.moveToNext()){
                        strNo += c.getString(0) + "\r\n";
                        strName += c.getString(1) + "\r\n";
                        strPr +=c.getString(2)+ "\r\n";
                        strC += c.getString(3) + "\r\n";
                    }

                    editResultNo.setText(strNo);
                    editResultName.setText(strName);
                    editResultPr.setText(strPr);
                    editResultC.setText(strC);

                    c.close();
                    db.close();
                    break;
            }
        }
    };
    public class DBHelper extends SQLiteOpenHelper {
        //생성자
        public DBHelper(Context context){
            super(context, "todayStoreDB", null, 1);
        }

        //onCreate() - 테이블 생성 쿼리
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE prTable (pr_no CHAR(20) primary key, pr_name CHAR(20), pr_price INTEGER, pr_category CHAR(20));");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL( "DROP TABLE IF EXISTS prTable " );
            onCreate(db);
        }
    }
}

