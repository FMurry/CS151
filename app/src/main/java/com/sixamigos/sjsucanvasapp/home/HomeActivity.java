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

/**
 * Created by Jason Safaiyeh
 **/
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // init token
        Credentials parseCredentials = new Credentials();
        Parse.initialize(this, parseCredentials.getToken1(), parseCredentials.getToken2());

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
    }

    private void setupViewPager(ViewPager viewPager) {
        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CoursesFragment(), "Courses");
        adapter.addFragment(new AssignmentsFragment(), "Assignments");
        viewPager.setAdapter(adapter);
    }
}
