package com.example.administrator.expert_oschina;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.expert_oschina.Actvity.SearchActivity;
import com.example.administrator.expert_oschina.Fragment.FindFragment;
import com.example.administrator.expert_oschina.Fragment.MFragment;
import com.example.administrator.expert_oschina.Fragment.MoveFragment;
import com.example.administrator.expert_oschina.Fragment.MyFragment;
import com.example.administrator.expert_oschina.Fragment.UndefinedFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.home_toolbar)
    public Toolbar home_toolbar;
    @BindView(R.id.home_rg)
    public   RadioGroup home_rg;
    @BindView(R.id.home_rb1)
    public RadioButton rg_one;
    @BindView(R.id.home_rb2)
    public RadioButton rg_two;
    @BindView(R.id.home_rb3)
    public RadioButton rg_three;
    @BindView(R.id.home_rb4)
    public RadioButton rg_four;
    @BindView(R.id.home_rb5)
    public RadioButton rg_five;
    private UndefinedFragment uFragment;
    private MFragment mFragment;
    private FindFragment fFragment;
    private MyFragment myFragment;
    private MoveFragment moveFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        home_toolbar.setTitle("开源中国");
        home_toolbar.setTitleTextColor(Color.WHITE);
        home_toolbar.setBackgroundColor(Color.GREEN);
        home_toolbar.inflateMenu(R.menu.toolbar_menu);
        home_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.contentshare_toolbar:
                        Intent intent=new Intent();
                        intent.setClass(HomeActivity.this, SearchActivity.class);
                        startActivity(intent);
                        break;
                }



                return false;
            }
        });
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (uFragment==null){
            uFragment=new UndefinedFragment();
            ft.add(R.id.home_framelayout, uFragment);
        }else {
            ft.show(uFragment);
        }
            ft.commit();
        home_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                hideFragment(ft);
                switch (checkedId) {
                    case R.id.home_rb1:
                        if (uFragment == null) {
                            uFragment = new UndefinedFragment();
                            ft.add(R.id.home_framelayout, uFragment);
                        } else {
                            ft.show(uFragment);
                        }
                        ft.commit();
                        break;
                    case R.id.home_rb2:
                       /* if (mFragment == null) {
                            mFragment = new MFragment();
                            ft.add(R.id.home_framelayout, mFragment);
                        } else {
                            ft.show(mFragment);
                        }*/

                         if (moveFragment == null) {
                            moveFragment = new MoveFragment();
                            ft.add(R.id.home_framelayout, moveFragment);
                        } else {
                            ft.show(moveFragment);
                        }

                        ft.commit();
                        break;
                    case R.id.home_rb3:
                            final PopupWindow popupWindow = new PopupWindow(getApplicationContext());
                            View contentView = LayoutInflater.from(getApplicationContext())
                                            .inflate(R.layout.popwindow, null, false);
                            contentView.setBackgroundColor(Color.WHITE);
                            popupWindow.setContentView(contentView);
                            int width=getWindowManager().getDefaultDisplay().getWidth();
                            int hight=getWindowManager().getDefaultDisplay().getHeight()/3;
                            popupWindow.setHeight(hight);
                            popupWindow.setWidth(width);
                            popupWindow.setFocusable(true);
                            popupWindow.setOutsideTouchable(true);
                            if (!popupWindow.isShowing()) {
                                popupWindow.showAtLocation(rg_three, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                            }
                            RadioButton radioButton= (RadioButton) findViewById(R.id.home_rb3);
                            radioButton.setChecked(false);
                            contentView.setOnTouchListener(new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    if (popupWindow!=null&&popupWindow.isShowing()){
                                        popupWindow.dismiss();
                                    }else{
                                        popupWindow.showAtLocation(rg_three, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                                    }


                                    return true;
                                }
                            });
                            break;
                    case R.id.home_rb4:
                        if (fFragment==null){
                            fFragment=new FindFragment();
                            ft.add(R.id.home_framelayout,fFragment);
                        }else {
                            ft.show(fFragment);
                        }
                        ft.commit();
                        break;
                    case R.id.home_rb5:
                        if (myFragment==null){
                            myFragment=new MyFragment();
                            ft.add(R.id.home_framelayout,myFragment);
                        }else {
                            ft.show(myFragment);
                        }
                        ft.commit();
                        break;
                }
            }
        });




    }

    private void hideFragment(FragmentTransaction ft) {
        if (uFragment!=null){
            ft.hide(uFragment);
        }
        if (mFragment!=null){
            ft.hide(mFragment);
        }
        if (fFragment!=null){
            ft.hide(fFragment);
        }
        if (myFragment!=null){
            ft.hide(myFragment);
        }
        if (moveFragment!=null){
            ft.hide(moveFragment);
        }
    }

}
