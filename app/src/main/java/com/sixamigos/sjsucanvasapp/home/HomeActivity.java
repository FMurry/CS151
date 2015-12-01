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

    private View mAssigmentAddDialog;

    //
    private String mSelectedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mToolbar.setTitle("Home");

        mAssigmentAddDialog = setupAssignmentSpinner();
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
                        ParseObject courses = new ParseObject("Courses"); // create a DB called <courseName>
                        Log.d("Course Name", courseName);
                        courses.put("courseName", courseName); // add the attribute courseName and the actual name

                        // save to Parse (local)
                        courses.saveInBackground();
                        //course.pinInBackground(); // this can be changed to saveInBackground() to upload to Parse cloud
                    }
                })
                .negativeText("Cancel")
                .negativeColor(getResources().getColor(android.R.color.black))
                .show();
    }

    private void showAssignmentDialog() {
        final Spinner spinner = (Spinner) mAssigmentAddDialog.findViewById(R.id.input_spinner_assignment_name);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Courses");
        query.whereExists("courseName");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {

                    ArrayList<String> database = new ArrayList<>();
                    for (ParseObject course : objects) { // retrieve from database
                        database.add(course.getString("courseName"));
                    }

                    String[] data = new String[database.size()];
                    data = database.toArray(data); // convert to string array

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                            android.R.layout.simple_spinner_item, data);
                    spinner.setAdapter(adapter); // adapter needs to be notified of any updates
                } else {
                    Log.e("Error", e.getMessage());
                }
            }
        });
        new MaterialDialog.Builder(this)
                .title("Add an Assignment")
                .customView(mAssigmentAddDialog, true)
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

                        String courseName = spinner.getSelectedItem().toString();
                        Log.d("Course Name", courseName);

                        // add assignment to selected course db
                        ParseObject courses = new ParseObject("Assignments");
                        courses.put("assignmentName", assignmentName);
                        courses.put("courseName", courseName);

                        courses.saveInBackground();
                        // course.pinInBackground();

                    }
                })
                .negativeText("Cancel")
                .negativeColor(getResources().getColor(android.R.color.black))
                .show();
    }

    private View setupAssignmentSpinner() {
        final View view = getLayoutInflater().inflate(R.layout.dialog_content_add_assignment, null);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Courses");
        query.whereExists("courseName");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {

                    Spinner spinner = (Spinner) view.findViewById(R.id.input_spinner_assignment_name);

                    ArrayList<String> database = new ArrayList<>();
                    for (ParseObject course : objects) { // retrieve from database
                        database.add(course.getString("courseName"));
                    }

                    String[] data = new String[database.size()];
                    data = database.toArray(data); // convert to string array

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                            android.R.layout.simple_spinner_item, data);
                    spinner.setAdapter(adapter); // adapter needs to be notified of any updates
                } else {
                    Log.e("Error", e.getMessage());
                }
            }
        });

        return view;
    }

    @Override
    public void onBackPressed() {
        //Do Nothing
    }
}
