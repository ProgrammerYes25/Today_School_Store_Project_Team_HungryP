package today_school_store_project.team_hungryp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.view.View;

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
    private DatabaesHelper databaesHelper;

    //생성자
    public DatabaesHelper (Context context){
        super(context, databaseName, null, 2);
        Log.e(TAG, "db 생성됨 완료");
        databasePath = "/data/data/"+context.getPackageName()+ "/databases/";
        this.pContext = context;
        CheckDatabaseFileExist();
    }

    private void CheckDatabaseFileExist(){
        File file = new File(databasePath+databaseName);
        if(!file.exists()) {
            CopyDatabaseFile();
            Log.d(TAG, "데이터 베이스 복사함");
        }
//        pDatabase = getReadableDatabase();
//        Cursor cursor;
//        cursor = pDatabase.rawQuery("SELEct * From prTable Where pr_no = 0;",null);
//        int version = pDatabase.getVersion();
//        while (cursor.moveToNext()){
//            version = cursor.getInt(2);
//        }if(version < pDatabase.getVersion()){
// //           databaesHelper.onUpgrade(pDatabase, pDatabase.getVersion(),version);
//            CopyDatabaseFile();
//        }
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
        CopyDatabaseFile();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String filePath = databasePath + databaseName;
        File dbFile = new File(filePath);
        if(dbFile.delete()){
            Log.d(TAG,"삭제 성공");
        }else {
            Log.d(TAG, "삭제 실패");
        }
//        CheckDatabaseFileExist();
//        CopyDatabaseFile();
    }
}
