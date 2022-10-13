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
            super(context, "todayStoreDB", null, 1);
            Log.e(TAG, "db 생성됨 완료");
            if (Build.VERSION.SDK_INT >= 17){
                databasePath = context.getApplicationInfo().dataDir + "/databases/";
            }
            else {
                databasePath = "/data/data/" + context.getPackageName() + "/databases/";
            }

            this.pContext = context;
        }

    //데이터 베이스 파일 열기
    public boolean OpenDatabaseFile() throws SQLException {
        if(!CheckDatabaseFileExist()){
           CreateDatabase();//데이터 베이스 있는지 확인하고 없으면 생성
        }
        String mPath = databasePath + databaseName;
        try {
            pDatabase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Log.e(TAG, "[ERROR]"+databaseName+"Open Datadase");
        }catch (SQLException sqlException){
            Log.e(TAG, "[ERROR] Cat't Open Datadase");
        }
        return pDatabase != null;
    }

    private boolean CheckDatabaseFileExist() {
        File file = new File(databasePath+databaseName);
        return file.exists();
    }

    private void CreateDatabase() throws SQLException {
        this.getReadableDatabase();
        //this.close();
        try {
            CopyDatabaseFile();
            Log.e(TAG, "[SUCCESS]" + databaseName + "are Created");
        } catch (IOException ioException) {
            Log.e(TAG, "[ERROR]"+databaseName+"Unable to create");
            throw new Error(TAG);
        }

    }

    private void CopyDatabaseFile() throws IOException {
        InputStream inputStream = pContext.getAssets().open(databaseName);
        String outputFileName = databasePath+databaseName;
        OutputStream outputStream = new FileOutputStream(outputFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer))>0){
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        //outputStream.close();
       // inputStream.close();
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

    public void CloseDatabaseFile(){
        if (pDatabase != null){
           // pDatabase.close();
        }
    }

    public synchronized void close(){
        CloseDatabaseFile();
        //super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
