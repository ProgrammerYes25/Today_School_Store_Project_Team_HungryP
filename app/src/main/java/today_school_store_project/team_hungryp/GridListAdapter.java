package today_school_store_project.team_hungryp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class GridListAdapter extends BaseAdapter {
    ArrayList<GridItem> items = new ArrayList<GridItem>();
    Context context;

    public void addItem(GridItem item){
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
        context = viewGroup.getContext();
        GridItem gridItem = items.get(position);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_grid_view, viewGroup,false);
        }
        return view;
    }


}
