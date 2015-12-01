package com.sixamigos.sjsucanvasapp.assignments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sixamigos.sjsucanvasapp.R;
import com.sixamigos.sjsucanvasapp.canvas.CanvasConnector;
import com.sixamigos.sjsucanvasapp.courses.Course;
import com.sixamigos.sjsucanvasapp.login.canvas.CanvasLoginFailureException;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AssignmentsFragment extends Fragment {

    @Bind(R.id.list)
    LinearLayout mLinearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        showAssignments();

        return view;
    }

    public void showAssignments() {
        CanvasConnector canvasConnector = new CanvasConnector(getContext());
        canvasConnector.setCallback(new CanvasConnector.CanvasConnectorCallback() {
            @Override
            public <T> void onCoursesReceived(List<T> data) {
                for (final Course course : (List<Course>)data) {
                    CanvasConnector assignmentConnector = new CanvasConnector(getContext(), course.getId());
                    assignmentConnector.setCallback(new CanvasConnector.CanvasConnectorCallback() {
                        @Override
                        public <T> void onCoursesReceived(List<T> data) {
                            for (Assignment assignment : (List<Assignment>)data) {
                                View view =
                                    LayoutInflater.from(getContext()).inflate(R.layout.card_assignment_due, null);
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

                                TextView mTitleTextView = (TextView) view.findViewById(R.id.title);
                                mTitleTextView.setText(assignment.getName() + " | " + course.getCourseName().split(" ")[1]);

                                TextView mDescriptionTextView = (TextView) view.findViewById(R.id.description);
                                if (!assignment.getDescription().equals("null"))
                                    mDescriptionTextView.setText(Html.fromHtml(assignment.getDescription()));
                                else mDescriptionTextView.setText("No Description");
                                mDescriptionTextView.setMovementMethod(LinkMovementMethod.getInstance());

                                TextView mDueAtTextView = (TextView) view.findViewById(R.id.due_date);
                                if (!assignment.getDueDate().equals("null"))
                                    mDueAtTextView.setText("Due At: " + assignment.getDueDate());
                                else mDueAtTextView.setText("No Due Date");

                                mLinearLayout.addView(view);
                            }
                        }
                    });

                    HashMap<String, String> params = new HashMap<>();
                    params.put("bucket", "future");

                    try {
                        assignmentConnector.getAssignments(params);
                    } catch (CanvasLoginFailureException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        try {
            canvasConnector.getCourses();
        } catch (CanvasLoginFailureException e) {
            e.printStackTrace();
        }
    }
}