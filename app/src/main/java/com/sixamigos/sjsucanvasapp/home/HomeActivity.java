package com.sixamigos.sjsucanvasapp.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sixamigos.sjsucanvasapp.R;
import com.sixamigos.sjsucanvasapp.canvas.CanvasConnector;
import com.sixamigos.sjsucanvasapp.canvas.Course;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
    mToolbar.setTitle("Canvas Client");
    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
    if (viewPager != null) {
      setupViewPager(viewPager);
    }


    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
    if (viewPager != null) {
      tabLayout.setupWithViewPager(viewPager);
    }

    FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
    floatingActionButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //set functionality of button
      }
    });

    /** DEBUG */
    CanvasConnector canvasConnector = new CanvasConnector();
    canvasConnector.setCallback(new CanvasConnector.CanvasConnectorCallback() {
      @Override
      public void onCoursesReceived(Course[] courses) {

      }
    });
    canvasConnector.getCourses();
  }

  private void setupViewPager(ViewPager viewPager) {
    Adapter adapter = new Adapter(getSupportFragmentManager());
    adapter.addFragment(new HomeScreenFragment(), "Category 1");
    adapter.addFragment(new HomeScreenFragment(), "Category 2");
    adapter.addFragment(new HomeScreenFragment(), "Category 3");
    viewPager.setAdapter(adapter);
  }

  static class Adapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();

    public Adapter(FragmentManager fm) {
      super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
      mFragments.add(fragment);
      mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
      return mFragments.get(position);
    }

    @Override
    public int getCount() {
      return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return mFragmentTitles.get(position);
    }
  }
}
