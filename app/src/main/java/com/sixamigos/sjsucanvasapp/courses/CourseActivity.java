package com.sixamigos.sjsucanvasapp.courses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.sixamigos.sjsucanvasapp.R;
import com.sixamigos.sjsucanvasapp.canvas.CanvasConnector;
import com.sixamigos.sjsucanvasapp.login.canvas.CanvasLoginFailureException;
import com.sixamigos.sjsucanvasapp.login.canvas.CanvasToken;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CourseActivity extends AppCompatActivity {

  @Bind(R.id.toolbar)
  Toolbar mToolbar;
  private Course course;
  private HashMap<String, String> data;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_course);
    ButterKnife.bind(this);


    Intent i = getIntent();
    course = (Course) i.getSerializableExtra("COURSE");

    mToolbar.setTitle(course.getCourseName().split(" ")[1]);

    data = new HashMap<>();

    showAssignments();
  }

  public void showAssignments() {

    //Make Request for the assignments.
    CanvasConnector canvasConnector = new CanvasConnector(this, course.getId());
    canvasConnector.setCallback(new CanvasConnector.CanvasConnectorCallback() {
      @Override
      public <T> void onCoursesReceived(List<T> data) {
        //Manage the data
      }
    });

    try {
      canvasConnector.getAssignments(data);
    } catch (CanvasLoginFailureException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }
}
