package today_school_store_project.team_hungryp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Process;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {


    ImageView imgvPopular;
    ImageView imgvNew;
    ImageView imgvPrice;
    ImageView imgvSale;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View 객체 정의
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ViewFlipper viewFlip = view.findViewById(R.id.viewFlip);
        viewFlip.setFlipInterval(5000);
        viewFlip.startFlipping();

        //        Cursor cursor = pDatabase.rawQuery("SELEct * From prTable Where pr_no = 0;",null);
//        int version = pDatabase.getVersion();
//        while (cursor.moveToNext()){
//            version = cursor.getInt(2);
//        }
//        if(version != pDatabase.getVersion()){
//            databaesHelper.onUpgrade(pDatabase, pDatabase.getVersion(),version);
//        }
        //pDatabase.close();
        //databaesHelper.CloseDatabaseFile();
        imgvPopular = view.findViewById(R.id.imgv_popular);
        imgvNew = view.findViewById(R.id.imgv_new);
        imgvPrice = view.findViewById(R.id.imgv_price);
        imgvSale = view.findViewById(R.id.imgv_sale);
//        imgvPopular.setOnClickListener(imgvListener);
//        imgvNew.setOnClickListener(imgvListener);
//        imgvPrice.setOnClickListener(imgvListener);
//        imgvSale.setOnClickListener(imgvListener);
        //List<Product> productsList = initLoadMarketDatabase();
        return view;
    }

    // /하단 메뉴바

}
//    View.OnClickListener imgvListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.imgv_popular:
//                    Intent intent1 = new Intent(getApplicationContext(), PopularActivity.class);
//                    startActivity(intent1);
//                    break;
//                case R.id.imgv_new:
//                    Intent intent2 = new Intent(getApplicationContext(), NewPageActivity.class);
//                    startActivity(intent2);
//                    break;
//                case R.id.imgv_price:
//                    Intent intent3 = new Intent(getApplicationContext(), PricePageActivity.class);
//                    startActivity(intent3);
//                    break;
//                case R.id.imgv_sale:
//                    Intent intent4 = new Intent(getApplicationContext(), SalePageActivity.class);
//                    startActivity(intent4);
//                    break;
//            }
//
//        }
//    };


