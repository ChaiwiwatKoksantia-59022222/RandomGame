package marlon.melonchan.randomgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by androidworkspace on 2/25/2017 AD.
 */

public class CustomListView extends BaseAdapter{

    private String[] data_number;
    private LayoutInflater inflater_s;
    private int[] data_type;

    public CustomListView(Context context,String[] str,int[] ty){
        data_number = str;
        data_type = ty;
        inflater_s = LayoutInflater.from(context);
    }

    static class ViewHolder {
        TextView tv_L;
        ImageView iv_ca;
        //TextView iv_ch;
    }

    @Override
    public int getCount() {
        return data_number.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater_s.inflate(R.layout.row,parent,false);
            viewHolder = new ViewHolder();

            viewHolder.tv_L = (TextView)convertView.findViewById(R.id.Rtv);
            viewHolder.iv_ca = (ImageView) convertView.findViewById(R.id.Riv);
            //viewHolder.iv_ch = (TextView) convertView.findViewById(R.id.Riv_ch);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.iv_ca.setImageResource(data_type[position]);
        viewHolder.tv_L.setText(data_number[position]);

        return convertView;
    }
}
