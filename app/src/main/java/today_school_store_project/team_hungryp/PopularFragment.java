package today_school_store_project.team_hungryp;

import androidx.fragment.app.Fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PopularFragment extends Fragment {
    private long backBtnTime = 0l;  //뒤로가기 누른 횟수 계산하기 위한 변수
//    DatabaesHelper databaesHelper;
//    SQLiteDatabase sqlDB;
//    TextView top1Text, top2Text, top3Text, top4Text, top5Text, top6Text, top7Text, top8Text, top9Text;
//    ImageView newImageView, moneyOffImageView, homeImageView, storeImageView, starImageView;
//    ImageView top1img, top2img, top3img;
//    int[] imgList = {R.drawable.wheim, R.drawable.hershey, R.drawable.ohyes};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        //setTitle("인기 상품 페이지");

        //하단 메뉴바
//        newImageView = view.findViewById(R.id.new_image_view);
//        moneyOffImageView = view.findViewById(R.id.money_off_image_view);
//        homeImageView = view.findViewById(R.id.home_image_view);
//        storeImageView = view.findViewById(R.id.store_image_view);
//        starImageView = view.findViewById(R.id.star_image_view);
//
//        newImageView.setOnClickListener(menuOnClickListener);
//        moneyOffImageView.setOnClickListener(menuOnClickListener);
//        homeImageView.setOnClickListener(menuOnClickListener);
//        storeImageView.setOnClickListener(menuOnClickListener);
//        starImageView.setOnClickListener(menuOnClickListener);

//        Cursor cursor;
//        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 1;",null);
//        setPopularList(top1Text, cursor);
//        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 2;",null);
//        setPopularList(top2Text, cursor);
//        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 3;",null);
//        setPopularList(top3Text, cursor);
//        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 4;",null);
//        setPopularList(top4Text, cursor);
//        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 5;",null);
//        setPopularList(top5Text, cursor);
//        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 6;",null);
//        setPopularList(top6Text, cursor);
//        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 7;",null);
//        setPopularList(top7Text, cursor);
//        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 8;",null);
//        setPopularList(top8Text, cursor);
//        cursor = sqlDB.rawQuery("SELECT * FROM prTable WHERE pr_popular = 9;",null);
//        setPopularList(top9Text, cursor);
        return view;
    }


}