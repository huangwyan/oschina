package com.example.administrator.expert_oschina.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.expert_oschina.R;
import com.example.administrator.expert_oschina.bearn.Top;

import java.util.List;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class Home_fragmentAdaper extends BaseAdapter{
    private Context context;
    private List<Top>topList;

    public Home_fragmentAdaper(Context context, List<Top>topList) {
        this.context = context;
        this.topList = topList;
    }

    @Override
    public int getCount() {
        return topList.size();
    }

    @Override
    public Object getItem(int position) {
        return topList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView==null){
            vh=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.homefragment_list_item,null);
            vh.home_fragment_title= (TextView) convertView.findViewById(R.id.home_fragment_title);
            vh.home_fragment_description= (TextView) convertView.findViewById(R.id.home_fragment_description);
            vh.home_fragment_time= (TextView) convertView.findViewById(R.id.home_fragment_time);
            vh.home_fragment_content= (TextView) convertView.findViewById(R.id.home_fragment_content);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
        Top top=this.topList.get(position);
        vh.home_fragment_title.setText(top.getTitle());
        vh.home_fragment_description.setText(top.getDescription());
        vh.home_fragment_time.setText(top.getFromname());
        vh.home_fragment_content.setText(top.getTopclass());
        return convertView;
    }
    class ViewHolder{
        TextView home_fragment_title,home_fragment_description,home_fragment_time,home_fragment_content;
    }
}
