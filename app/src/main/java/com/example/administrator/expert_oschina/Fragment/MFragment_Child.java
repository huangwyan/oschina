package com.example.administrator.expert_oschina.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.expert_oschina.Adapter.Home_fragmentAdaper;
import com.example.administrator.expert_oschina.R;
import com.example.administrator.expert_oschina.bearn.Top;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
public class MFragment_Child extends Fragment {
    // @BindView(R.id.home_fragment_lv)
    public ListView homefragment_lv;
    private int id;
    private List<Top> topList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, container, false);
        id = getArguments().getInt("id");
        homefragment_lv = (ListView) view.findViewById(R.id.home_fragment_lv);
        if (savedInstanceState != null) {
            Top[] tops = (Top[]) savedInstanceState.getParcelableArray("cache");
            if (tops != null && tops.length != 0) {
                List<Top> topList = Arrays.asList(tops);
                homefragment_lv.setAdapter(new Home_fragmentAdaper(getContext(), topList));
            } else {
                initData();
                intiListview();
            }
        }

        return view;
    }

    private void initData() {
        String url = "http://www.tngou.net/api/top/list?id=" + id;
        topList = new ArrayList<Top>();
        OkHttpClient ohc = Simple_Okhttp.getSingleOkhttp();
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        ohc.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response == null) return;
                String json = response.body().string();
                Top top;
                try {
                    JSONObject jsonObject = new JSONObject(String.valueOf(json));
                    JSONArray array = jsonObject.getJSONArray("tngou");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = (JSONObject) array.get(i);
                        top = new Top();
                        top.setTitle(obj.optString("title"));
                        top.setDescription(obj.optString("description"));
                        top.setFromname(obj.optString("fromname"));
                        top.setId(obj.optInt("id"));
                        top.setTopclass(obj.optString("topclass"));
                        topList.add(top);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    private void intiListview() {
        homefragment_lv.setAdapter(new Home_fragmentAdaper(getContext(), topList));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (topList == null || topList.size() == 0) return;
        Top[] tops = new Top[topList.size()];
        topList.toArray(tops);
        outState.putParcelableArray("cache", tops);


    }
}
