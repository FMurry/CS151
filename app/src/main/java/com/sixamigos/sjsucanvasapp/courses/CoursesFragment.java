package com.sixamigos.sjsucanvasapp.courses;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sixamigos.sjsucanvasapp.R;
import com.sixamigos.sjsucanvasapp.canvas.CanvasConnector;
import com.sixamigos.sjsucanvasapp.login.canvas.CanvasLoginFailureException;

import java.util.List;

/**
 * Created by Jason Safaiyeh
 **/

public class CoursesFragment extends Fragment {

    private LinearLayout mLinearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mLinearLayout = (LinearLayout) view.findViewById(R.id.list);
        showCourses();

        return view;
    }

    private void showCourses() {
        CanvasConnector canvasConnector = new CanvasConnector(getContext());
        canvasConnector.setCallback(new CanvasConnector.CanvasConnectorCallback() {
            @Override
            public void onCoursesReceived(List<Course> courses) {
                for (Course course : courses) {
                    TextView textView = new TextView(getContext());
                    textView.setText(course.getFullName());
                    mLinearLayout.addView(textView);
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
