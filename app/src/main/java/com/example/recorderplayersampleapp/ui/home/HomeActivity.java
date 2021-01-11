package com.example.recorderplayersampleapp.ui.home;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.recorderplayersampleapp.R;
import com.example.recorderplayersampleapp.base.view.BaseActivity;
import com.example.recorderplayersampleapp.base.view.BaseFragment;
import com.example.recorderplayersampleapp.base.view.BasePagerAdapter;
import com.example.recorderplayersampleapp.ui.recordedFiles.RecordedFilesFragment;
import com.example.recorderplayersampleapp.ui.sampleAudios.SampleAudiosFragment;

import java.util.ArrayList;

/**
 * Home Activity
 */
public class HomeActivity extends BaseActivity {
    public static final int TAB_RECORDED_FILES = 0;
    public static final int TAB_SAMPLE_AUDIOS = 1;
    private ArrayList<Fragment> fragmentList;
    private ViewPager vpHomeScreen;
    private BottomNavigationView mBottomNavigation;

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        super.initView();

        mBottomNavigation = findViewById(R.id.bottom_navigation);
        vpHomeScreen = findViewById(R.id.vpHomeScreen);

        fragmentList = new ArrayList<>();
        fragmentList.add(RecordedFilesFragment.getInstance());
        fragmentList.add(SampleAudiosFragment.getInstance());
        setUpViewPager();
    }

    private void setUpViewPager() {
        BasePagerAdapter mPagerAdapter = new BasePagerAdapter(getSupportFragmentManager(), fragmentList);
        vpHomeScreen.setAdapter(mPagerAdapter);

        vpHomeScreen.setCurrentItem(TAB_RECORDED_FILES);
        setBottomNavigationOnPageChange(TAB_RECORDED_FILES);
        vpHomeScreen.setOffscreenPageLimit(2);

        vpHomeScreen.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(final int position) {
                Fragment fragment = fragmentList.get(position);
                if (fragment instanceof BaseFragment) {
                    ((BaseFragment) fragment).refreshData();
                }
                setBottomNavigationOnPageChange(position);
            }
        });

        setBottomNavigationView();
    }

    private void setBottomNavigationView() {
        mBottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.recordedFiles:
                    vpHomeScreen.setCurrentItem(TAB_RECORDED_FILES);
                    break;
                case R.id.sampleAudios:
                    vpHomeScreen.setCurrentItem(TAB_SAMPLE_AUDIOS);
                    break;
                default:
                    break;
            }
            return true;
        });
    }


    /**
     * setBottomNavigationOnPageChange
     *
     * @param position the position
     */
    public void setBottomNavigationOnPageChange(final int position) {
        switch (position) {
            case TAB_RECORDED_FILES:
                mBottomNavigation.getMenu().findItem(R.id.recordedFiles).setChecked(true);
                break;
            case TAB_SAMPLE_AUDIOS:
                mBottomNavigation.getMenu().findItem(R.id.sampleAudios).setChecked(true);
                break;
            default:
                break;
        }
    }


}