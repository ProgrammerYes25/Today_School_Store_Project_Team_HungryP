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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class dbAddDataPageActivity extends AppCompatActivity {
    String[] itmes = {"상품 등록", "할인상품 등록"};

    View DBdlg;

    EditText editNo, editName, editPrice;
    myDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("관리자 설정 페이지");
        setContentView(R.layout.activity_db_add_data_page);

        ListView tableList = findViewById(R.id.table_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,itmes);
        tableList.setAdapter(adapter);

        tableList.setOnItemClickListener(tableListListener);
    }

    AdapterView.OnItemClickListener tableListListener= new AdapterView.OnItemClickListener() {
        SQLiteDatabase db;
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(dbAddDataPageActivity.this);
            dlg.setTitle(itmes[i]);
            switch (i) {
                case 0:
                    DBdlg = view.inflate(dbAddDataPageActivity.this, R.layout.db_add_product_table_dlg, null);
                    dlg.setView(DBdlg);
                    dlg.setPositiveButton("완료", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            db = dbHelper.getWritableDatabase();
                            editNo = DBdlg.findViewById(R.id.edit_no);
                            editName = DBdlg.findViewById(R.id.edit_name);
                            editPrice = DBdlg.findViewById(R.id.edit_price);
                        }
                    });
            }
            dlg.show();
        }
    };



    public static class myDBHelper extends SQLiteOpenHelper{

        public myDBHelper(Context context){
            super(context, "todayStoreDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE prTable (pr_no char(5) primary key, pr_name char(30), " +
                    "pr_price integer, pr_imgid integer, pr_category char(20));");
            db.execSQL("CREATE TABLE  dcTable (dcpr_no char(5) primary key, dc_price Integer," +
                    " foreign key(dcpr_no) references prTable(pr_no));");
            //db.execSQL("CREATE TABLE  mixTable (mix_no char(5) primary key, mix_name char(30), pr1_no char(5), pr2_no char(5)," +
            //       " foreign key(pr1_no) references prTable(pr_no), foreign key(pr2_no) references prTable(pr_no));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("drop table if exists todayStoreDB");
            onCreate(db);
        }
    }
}