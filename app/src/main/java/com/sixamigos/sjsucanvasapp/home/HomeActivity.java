package com.sixamigos.sjsucanvasapp.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.sixamigos.sjsucanvasapp.R;
import com.sixamigos.sjsucanvasapp.assignments.AssignmentsFragment;
import com.sixamigos.sjsucanvasapp.courses.CoursesFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jason Safaiyeh & Kevin Hou
 **/
public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.tabs)
    TabLayout mTabLayout;
    @Bind(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    //
    private String mSelectedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mToolbar.setTitle("Home");

        if (mViewPager != null) {
            setupViewPager(mViewPager);
        }

        if (mViewPager != null) {
            mTabLayout.setupWithViewPager(mViewPager);
        }

        setupFloatingActionButton();
    }

    private void setupViewPager(ViewPager viewPager) {
        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CoursesFragment(), "Courses");
        adapter.addFragment(new AssignmentsFragment(), "Assignments");
        viewPager.setAdapter(adapter);
    }

    private void setupFloatingActionButton() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTabLayout.getSelectedTabPosition() == 0) {
                    showCourseDialog();
                } else if (mTabLayout.getSelectedTabPosition() == 1) {
                    showAssignmentDialog();
                }
            }
        });
    }

    private void showCourseDialog() {
        new MaterialDialog.Builder(this)
                .title("Add a Course")
                .customView(R.layout.dialog_content_add_course, true)
                .positiveText("Add")
                .positiveColor(getResources().getColor(android.R.color.black))
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        EditText mAddCourseEditText = (EditText) dialog.getCustomView().findViewById(R.id.input_course_name);
                        String courseName = mAddCourseEditText.getText().toString();

                        // local data store
                        ParseObject course = new ParseObject(courseName); // create a DB called <courseName>
                        course.put("courseName", courseName); // add the attribute courseName and the actual name

                        // save to Parse (local)
                        course.pinInBackground(); // this can be changed to saveInBackground() to upload to Parse cloud

                    }
                })
                .negativeText("Cancel")
                .negativeColor(getResources().getColor(android.R.color.black))
                .show();
    }

    private void showAssignmentDialog() {
        new MaterialDialog.Builder(this)
                .title("Add an Assignment")
                .customView(setupAssignmentSpinner(), true)
                .positiveText("Add")
                .positiveColor(getResources().getColor(android.R.color.black))
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {

                        // retrieve assignment name
                        EditText mAddAssignmentEditText = (EditText) dialog.getCustomView().findViewById(R.id.input_assignment_name);
                        final String assignmentName = mAddAssignmentEditText.getText().toString();
                        Log.d("Assignment Name", assignmentName);

                        // determine which class was selected
                        // TODO: Jason you gotta do this part cause IDK how to check which one the user selected

                        // add assignment to selected course db
                        ParseObject course = new ParseObject("CS151"); // TODO: selected courseName as argument
                        course.put("assignmentName", assignmentName);

                        course.pinInBackground();

                    }
                })
                .negativeText("Cancel")
                .negativeColor(getResources().getColor(android.R.color.black))
                .show();
    }

    private View setupAssignmentSpinner() {
        View view = getLayoutInflater().inflate(R.layout.dialog_content_add_assignment, null);

        Spinner spinner = (Spinner) view.findViewById(R.id.input_spinner_assignment_name);

        String[] data = new String[]{
                "CS157A", "Test Class 2", "Test Class 3"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, data);
        spinner.setAdapter(adapter);

        return view;
    }

    @Override
    public void onBackPressed() {
        //Do Nothing
    }
}
