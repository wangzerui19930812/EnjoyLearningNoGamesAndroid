package com.lexuejinyou.enjoylearningnogames;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Window;
import android.widget.RadioGroup;

import com.lexuejinyou.enjoylearningnogames.base.BaseFragment;
import com.lexuejinyou.enjoylearningnogames.fragment.JinYouFagment;
import com.lexuejinyou.enjoylearningnogames.fragment.LeXueFagment;
import com.lexuejinyou.enjoylearningnogames.fragment.MineFagment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private RadioGroup rg_main;

    private List<BaseFragment> fragments = new ArrayList<>();

    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initFragment();
        initView();
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int toFragmentIndex;
                switch (checkedId){
                    case R.id.rb_lexue_frame:
                        toFragmentIndex = 0;
                        break;
                    case R.id.rb_jinyou_frame:
                        toFragmentIndex = 1;
                        break;
                    case R.id.rb_mine_frame:
                        toFragmentIndex = 2;
                        break;
                    default:
                        toFragmentIndex = 0;
                        break;
                }
                Fragment toFragment = getFragmentByIndex(toFragmentIndex);
                switchFragment(currentFragment,toFragment);
            }
        });
    }

    private Fragment getFragmentByIndex(int index){
        return fragments.get(index);
    };

    private void switchFragment(Fragment fromFragment,Fragment toFragment){
        // h
        //获取FragmentManager
        FragmentManager fm = getSupportFragmentManager();
        //开启事务
        FragmentTransaction ft = fm.beginTransaction();
        if(fromFragment != toFragment){
            currentFragment = toFragment;
            if(!toFragment.isAdded()){
                if(fromFragment != null){
                    ft.hide(fromFragment);
                }
                if(toFragment != null){
                    ft.add(R.id.fl_content,toFragment).commit();
                }
            }else{
                if(fromFragment != null){
                    ft.hide(fromFragment);
                }
                if(toFragment != null){
                    ft.show(toFragment).commit();
                }
            }
        }
    }

    private void initFragment() {
        fragments.add(new LeXueFagment());
        fragments.add(new JinYouFagment());
        fragments.add(new MineFagment());
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        rg_main = findViewById(R.id.rg_main);
        rg_main.check(R.id.rb_lexue_frame);
        switchFragment(null,getFragmentByIndex(0));
    }
}
