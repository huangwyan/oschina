package com.example.administrator.expert_oschina.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.expert_oschina.R;
import com.example.administrator.expert_oschina.bearn.Tabs;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class MFragment extends Fragment{
    private Tabs[]uf_tabs=new Tabs[]{
            new Tabs("最新动弹",2),
            new Tabs("热门动弹",3),
            new Tabs("我的动弹",4)
    };

    private TabLayout uf_tablayout;
    private ViewPager uf_viewpager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.undefinedfragment,null);
        intiView(view);
        return view;
    }

    private void intiView(View view) {
        uf_tablayout= (TabLayout) view.findViewById(R.id.uf_tablayout);
        uf_viewpager= (ViewPager) view.findViewById(R.id.uf_viewpager);
        uf_viewpager.setAdapter(new UF_Adapter(getChildFragmentManager()));
        uf_tablayout.setTabTextColors(Color.BLACK, Color.GREEN);
        uf_tablayout.setSelectedTabIndicatorColor(Color.GREEN);
        uf_tablayout.setTabMode(TabLayout.MODE_FIXED);
        uf_tablayout.setupWithViewPager(uf_viewpager);





    }
    class UF_Adapter extends FragmentStatePagerAdapter {

        public UF_Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Home_Fragment hf=new Home_Fragment();
            Bundle bundle=new Bundle();
            bundle.putInt("id",uf_tabs[position].list_id);
            hf.setArguments(bundle);
            return hf;
        }

        @Override
        public int getCount() {
            return uf_tabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return uf_tabs[position].tab_name;
        }
    }
}
