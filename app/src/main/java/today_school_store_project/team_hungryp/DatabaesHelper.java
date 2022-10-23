package today_school_store_project.team_hungryp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaesHelper extends SQLiteOpenHelper {

    protected static String TAG = "DatabaseHelper";
    //데이터 베이스 경로
    private static String databasePath = "";
    //데이터 베이스 이름
    private static String databaseName = "Products.db";
    //데이터 베이스
    private static String tableName = "prTable";

    private final Context pContext;

    private SQLiteDatabase pDatabase;

    //생성자
    public DatabaesHelper (Context context){
        super(context, databaseName, null, 1);
        Log.e(TAG, "db 생성됨 완료");
        databasePath = "/data/data/"+context.getPackageName()+ "/databases/";
        this.pContext = context;
        CheckDatabaseFileExist();
    }
    private void CheckDatabaseFileExist(){
        File file = new File(databasePath+databaseName);
        if(!file.exists()){
            CopyDatabaseFile();
            Log.d(TAG,"데이터 베이스 복사함");
        }
    }
    private void CopyDatabaseFile(){
        try {
            File folder = new File(databasePath);
            if(!folder.exists()){
                folder.mkdir();
            }
            InputStream inputStream = pContext.getAssets().open(databaseName);
            String outputFileName = databasePath+databaseName;
            OutputStream outputStream = new FileOutputStream(outputFileName);

            byte[] pbuffer = new byte[1024];
            int plength;
            while ((plength = inputStream.read(pbuffer))>0){
                outputStream.write(pbuffer, 0, plength);
            }
            //outputStream.flush();
            //outputStream.close();
            //inputStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public List getTableData() {
        try {
            List pList = new ArrayList();
            String sql = "SELECT * FROM " + tableName;
            Cursor pCursor = pDatabase.rawQuery(sql, null);
            //테이블 읽어오기
            if (pCursor != null) {
                while (pCursor.moveToNext()) {
                    Product product = new Product();
                    product.setNo(pCursor.getString(0));
                    product.setName(pCursor.getString(1));
                    product.setPrice(pCursor.getInt(2));
                    product.setCategory(pCursor.getString(3));

                    pList.add(product);
                }
            }
            return pList;
        }catch (SQLException sqlException) {
            Log.e(TAG, sqlException.toString());
            throw sqlException;
        }
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.disableWriteAheadLogging();
    }

    public synchronized void close(){
        if(pDatabase != null){
            pDatabase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
