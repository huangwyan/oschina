package com.example.administrator.expert_oschina.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.expert_oschina.R;
import com.example.administrator.expert_oschina.bearn.Ask;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Utils.Simple_Okhttp;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class MoveFragment_child extends Fragment {
    private ListView mfc_lv;
    private List<Ask> askList;
    private int id;
    private MFCAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        id=getArguments().getInt("id");
        View view=inflater.inflate(R.layout.movefragment_child,container,false);
        getReponse();
        intiView(view);
        return view;
    }
  /* //初始化数据
    private void initData() {
        System.out.println("连网的方法");
        getReponse();
    }*/

    private void getReponse() {
        //String url="http://www.tngou.net/api/ask/list?id="+id;
        String url="http://www.tngou.net/api/lore/list?id="+id;
        System.out.println("网址"+url);
        OkHttpClient okHttpClient= Simple_Okhttp.getSingleOkhttp();
        Request request=new Request.Builder()
                .url(url)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response == null) return;
                String json = response.body().string();
                parseJson(json);

            }
        });


    }

    private void parseJson(String json) {
        System.out.println(json+"===============================");
        if (json==null)return;
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray array=jsonObject.getJSONArray("tngou");
            Ask ask=null;
            List<Ask> askList2=new ArrayList<>();
            int len=array.length();
            for (int i = 0; i < len; i++) {
                JSONObject obj= (JSONObject) array.get(i);
                ask=new Ask();
                ask.setTitle(obj.optString("title"));
                System.out.println("title" + obj.optString("title"));
                ask.setDescription(obj.optString("description"));
                System.out.println("description" + obj.optString("description"));
                ask.setId(obj.optInt("id"));
                System.out.println("id" + obj.optString("id"));
                ask.setImg(obj.optString("img"));
                System.out.println("img" + obj.optString("img"));
                ask.setTime(obj.optString("time"));
                System.out.println("time" + obj.optString("time"));
                askList2.add(ask);
                askList.addAll(askList2);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    private void intiView(View view) {
        mfc_lv= (ListView) view.findViewById(R.id.mf_child_lv);
        askList=new ArrayList<>();
        mfc_lv.setAdapter(adapter=new MFCAdapter(askList));

    }

    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        if (topList == null || topList.size() == 0) return;
        Top[] tops = new Top[topList.size()];
        topList.toArray(tops);
        outState.putParcelableArray("cache", tops);


    }*/
    class MFCAdapter extends BaseAdapter{
        private List<Ask> asks;

        public MFCAdapter(List<Ask> asks) {
            this.asks = asks;
        }

        @Override
        public int getCount() {
            return asks.size();
        }

        @Override
        public Object getItem(int position) {
            return asks.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView==null){
                viewHolder=new ViewHolder();
                convertView=LayoutInflater.from(getContext()).inflate(R.layout.movefragment_child,
                        null);
                viewHolder.mfc_name= (TextView) convertView.findViewById(R.id.mfc_name);
                viewHolder.mfc_content= (TextView) convertView.findViewById(R.id.mfc_content);
                viewHolder.mfc_time= (TextView) convertView.findViewById(R.id.mfc_time);
                viewHolder.sdv= (SimpleDraweeView) convertView.findViewById(R.id.mfc_simpledv);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }
            if (asks!=null) {
                //Ask ask = asks.get(position);
                viewHolder.mfc_name.setText(asks.get(position).getTitle());
                viewHolder.mfc_content.setText(asks.get(position).getDescription());
                viewHolder.mfc_time.setText(asks.get(position).getTitle());
                String url = "http://tnfs.tngou.net/image" + asks.get(position).getImg();
                viewHolder.sdv.setImageURI(url);
            }
            return convertView;
        }
    }
    class ViewHolder{
        TextView mfc_name,mfc_content,mfc_time;
        SimpleDraweeView sdv;
    }
}
