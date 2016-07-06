package com.example.administrator.expert_oschina.Actvity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.example.administrator.expert_oschina.Fragment.SearchFragment;
import com.example.administrator.expert_oschina.HomeActivity;
import com.example.administrator.expert_oschina.R;
import com.example.administrator.expert_oschina.bearn.Tabs;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.search_tb)
    public Toolbar search_tb;
    @BindView(R.id.search_tablayout)
    public TabLayout search_tl;
    @BindView(R.id.search_viewpager)
    public ViewPager search_vp;
    private Tabs[] search_tab=new Tabs[]{
            new Tabs("软件",1),
            new Tabs("问答",1),
            new Tabs("博客",1),
            new Tabs("资讯",1)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        search_tb.setTitleTextColor(Color.WHITE);
        search_tb.setBackgroundColor(Color.GREEN);
        search_tb.setNavigationIcon(R.mipmap.icon_save);
        search_tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(SearchActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        search_vp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                SearchFragment sf = new SearchFragment();
                return sf;
            }

            @Override
            public int getCount() {
                return search_tab.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return search_tab[position].tab_name;
            }
        });
        search_tl.setTabTextColors(Color.BLACK, Color.GREEN);
        search_tl.setSelectedTabIndicatorColor(Color.GREEN);
        search_tl.setTabMode(TabLayout.MODE_FIXED);
        search_tl.setupWithViewPager(search_vp);

    }
}
