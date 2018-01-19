package com.twinkle.cdnubbs;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {


    private BottomNavigationView navigation;
    private ViewPager vpr_main;
    private List<Fragment> list_frag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        apply();


    }

    private void init() {
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        vpr_main = (ViewPager) findViewById(R.id.vpr_main);
    }

    private void apply() {

        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        vpr_main.addOnPageChangeListener(this);
        initFragments();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_index:
                    vpr_main.setCurrentItem(0);
                    return true;
                case R.id.navigation_news:
                    vpr_main.setCurrentItem(1);
                    return true;
                case R.id.navigation_info:
                    vpr_main.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    private void initFragments() {
        list_frag = new ArrayList<>();
        list_frag.add(new IndexFragment());
        list_frag.add(new NewsFragment());
        list_frag.add(new InfoFragment());
        vpr_main.setAdapter(new FragAdapter(getSupportFragmentManager()));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                navigation.setSelectedItemId(R.id.navigation_index);
                break;
            case 1:
                navigation.setSelectedItemId(R.id.navigation_news);
                break;
            default:
                navigation.setSelectedItemId(R.id.navigation_info);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class FragAdapter extends FragmentPagerAdapter {
        private FragAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list_frag.get(position);
        }

        @Override
        public int getCount() {
            return list_frag.size();
        }
    }
}
