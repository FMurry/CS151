package com.sixamigos.sjsucanvasapp.courses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sixamigos.sjsucanvasapp.R;
import com.sixamigos.sjsucanvasapp.assignments.Assignment;
import com.sixamigos.sjsucanvasapp.canvas.CanvasConnector;
import com.sixamigos.sjsucanvasapp.login.canvas.CanvasLoginFailureException;
import com.sixamigos.sjsucanvasapp.login.canvas.CanvasToken;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CourseActivity extends AppCompatActivity {

  @Bind(R.id.toolbar) Toolbar mToolbar;
  @Bind(R.id.assignment_list)
  LinearLayout mLinearLayout;
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
        for (Assignment assignment : (List<Assignment>) data) {
          createCard(assignment);
        }
      }
    });

    try {
      canvasConnector.getAssignments(data);
    } catch (CanvasLoginFailureException e) {
      e.printStackTrace();
    }
  }

  public void createCard(Assignment assignment) {
    View view = getLayoutInflater().inflate(R.layout.card_assignment_all, null);
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    );
    layoutParams.setMargins(
        0,
        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()),
        0,
        0);
    view.setLayoutParams(layoutParams);

    TextView mAssignmentNameTextView = (TextView) view.findViewById(R.id.assignment_name);
    mAssignmentNameTextView.setText(assignment.getName());

    TextView mAssignmentDescriptionTextView = (TextView) view.findViewById(R.id.assignment_description);
    if (!assignment.getDescription().equals("null"))
      mAssignmentDescriptionTextView.setText(Html.fromHtml(assignment.getDescription()));
    else mAssignmentDescriptionTextView.setText("No Description");
    mAssignmentDescriptionTextView.setMovementMethod(LinkMovementMethod.getInstance());

    TextView mAssignmentGradeTextView = (TextView) view.findViewById(R.id.assignment_grade);
    mAssignmentGradeTextView.setText("Max Score: " + assignment.getTotalPoints());

    mLinearLayout.addView(view);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }
}
