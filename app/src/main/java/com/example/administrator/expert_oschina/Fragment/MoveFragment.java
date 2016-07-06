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
 * Created by Administrator on 2016/7/1 0001.
 */
public class MoveFragment extends Fragment{
    private TabLayout move_tablayout;
    private ViewPager move_viewpager;
    private Tabs[] move_tab=new Tabs[]{
            new Tabs("最新动弹",2),
            new Tabs("热门动弹",3),
            new Tabs("我的动弹",4)
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.movefragment,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        move_tablayout= (TabLayout) view.findViewById(R.id.move_tab);
        move_viewpager= (ViewPager) view.findViewById(R.id.move_viewpager);
        move_viewpager.setAdapter(new MoveFragAdapter(getChildFragmentManager()));
        move_tablayout.setTabTextColors(Color.BLACK, Color.GREEN);
        move_tablayout.setSelectedTabIndicatorColor(Color.GREEN);
        move_tablayout.setTabMode(TabLayout.MODE_FIXED);
        move_tablayout.setupWithViewPager(move_viewpager);


    }
    class MoveFragAdapter extends FragmentStatePagerAdapter {

        public MoveFragAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            MoveFragment_child mfc=new MoveFragment_child();
            Bundle bundle=new Bundle();
            bundle.putInt("id",move_tab[position].list_id);
            mfc.setArguments(bundle);
            return mfc;
        }

        @Override
        public int getCount() {
            return move_tab.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return move_tab[position].tab_name;
        }
    }
}
