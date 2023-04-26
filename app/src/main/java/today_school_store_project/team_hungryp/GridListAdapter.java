package today_school_store_project.team_hungryp;


import android.content.Context;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class GridListAdapter extends BaseAdapter {
    ArrayList<GridItem> items = new ArrayList<GridItem>();
    Context context;

    public void addItem(GridItem item){
        Log.d("불러온 내용확인 : ", item+"");
        items.add(item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Log.d("확인 :", "호출 성공");
        context = viewGroup.getContext();
        GridItem gridItem = items.get(position);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_grid_view, viewGroup,false);
        }
        ImageView popularimage = view.findViewById(R.id.popular_image_view);
        TextView nameText = view.findViewById(R.id.popular_text_view);
        gridItem.getStorageReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("확인 :", "가져오기 성공");
                //다운로드 URL이 파라미터로 전달되어 옴.
                Glide.with(context).load(uri).into(popularimage);
            }
        });
        nameText.setText(gridItem.getName());
        return view;
    }


}
