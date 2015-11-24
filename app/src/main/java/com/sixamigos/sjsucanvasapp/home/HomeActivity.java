package com.sixamigos.sjsucanvasapp.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.parse.Parse;
import com.sixamigos.sjsucanvasapp.R;
import com.sixamigos.sjsucanvasapp.assignments.AssignmentsFragment;
import com.sixamigos.sjsucanvasapp.courses.CoursesFragment;
import com.sixamigos.sjsucanvasapp.parse.Credentials;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jason Safaiyeh
 **/
public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.viewpager) ViewPager mViewPager;
    @Bind(R.id.tabs) TabLayout mTabLayout;
    @Bind(R.id.fab) FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // init token
        Credentials parseCredentials = new Credentials();
        Parse.initialize(this, parseCredentials.getToken1(), parseCredentials.getToken2());

        mToolbar.setTitle("Home");

        if (mViewPager != null) {
            setupViewPager(mViewPager);
        }

        if (mViewPager != null) {
            mTabLayout.setupWithViewPager(mViewPager);
        }

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set functionality of button
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CoursesFragment(), "Courses");
        adapter.addFragment(new AssignmentsFragment(), "Assignments");
        viewPager.setAdapter(adapter);
    }
}
