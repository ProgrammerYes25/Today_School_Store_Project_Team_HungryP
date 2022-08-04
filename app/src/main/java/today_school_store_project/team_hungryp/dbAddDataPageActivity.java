package today_school_store_project.team_hungryp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
    public class myDBHelper extends SQLiteOpenHelper{

        public myDBHelper(Context context){
            super(context, "todaySchoolStoreDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE productTable (proName CHAR(30) PRIMARY KEY NOT NULL, proPrice INTEGER NOT NULL, category CHAR(20) NOT NULL,  CHAR(20) NOT NULL, , proImage BLOB, addDay UNIXTIME);");
            db.execSQL("CREATE TABLE popularTable (rank INTEGER PRIMARY KEY NOT NULL, popName CHAR(30) NOT NULL, popImage BLOB);");
            db.execSQL("CREATE TABLE newTable (newName CHAR(30) PRIMARY KEY NOT NULL, newImage BLOB);");
            db.execSQL("CREATE TABLE sealTable (sealName CHAR(30) NOT NULL,Price INTEGER NOT NULL, sealPrice INTEGER, sealImage BLOB);");
            db.execSQL("CREATE TABLE honeyTable (mixName CHAR(30) NOT NULL, honeyName1 CHAR(30) NOT NULL, honeyNamew CHAR(30) NOT NULL, honeyImage1 BLOB, honeyImage2 BLOB);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        }
    }
}