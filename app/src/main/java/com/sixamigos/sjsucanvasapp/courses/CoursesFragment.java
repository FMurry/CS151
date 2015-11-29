package com.sixamigos.sjsucanvasapp.courses;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.sixamigos.sjsucanvasapp.R;
import com.sixamigos.sjsucanvasapp.canvas.CanvasConnector;
import com.sixamigos.sjsucanvasapp.color.Colors;
import com.sixamigos.sjsucanvasapp.login.canvas.CanvasLoginFailureException;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jason Safaiyeh
 **/

public class CoursesFragment extends Fragment {

    @Bind(R.id.list) LinearLayout mLinearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        showCourses();

        return view;
    }

    private void showCourses() {
        CanvasConnector canvasConnector = new CanvasConnector(getContext());
        canvasConnector.setCallback(new CanvasConnector.CanvasConnectorCallback() {
            @Override
            public void onCoursesReceived(List<Course> courses) {

                LinearLayout linearLayout = null;

                int colorCount = 0;
                for (int i = 0; i < courses.size(); i++) {

                    if (i % 2 == 0) {
                        linearLayout = createLinearLayout();
                    }

                    linearLayout.addView(createCard(courses.get(i), colorCount));
                    colorCount++;

                    if (i % 2 == 0) {
                        mLinearLayout.addView(linearLayout);
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

    public LinearLayout createLinearLayout() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(0, 0, 0,
            (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics()));
        return linearLayout;
    }

    public View createCard(Course course, int colorCount) {
        View view =
            LayoutInflater.from(getContext()).inflate(R.layout.card_course, null);
        CardView cardView = (CardView) view.findViewById(R.id.course_card_view);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1.0f
        );
        layoutParams.setMargins(
            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
            0,
            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()),
            0);
        cardView.setLayoutParams(layoutParams);
        cardView.setBackgroundColor(getResources().getColor(Colors.getColor(colorCount)));

        String[] courseName = course.getFullName().split(" ");

        TextView mSemesterTextView = (TextView) view.findViewById(R.id.semester_text);
        mSemesterTextView.setText(courseName[0]);

        TextView mCourseSymbolTextView = (TextView) view.findViewById(R.id.course_symbol);
        mCourseSymbolTextView.setText(courseName[1]);

        TextView mCourseNameTextView = (TextView) view.findViewById(R.id.course_name);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 5; i < courseName.length; i++) {
            stringBuilder.append(courseName[i]);
            stringBuilder.append(" ");
        }
        mCourseNameTextView.setText(stringBuilder.toString());

        RelativeLayout mGradeRelativeLayout = (RelativeLayout) view.findViewById(R.id.grades_layout);
        mGradeRelativeLayout.setBackgroundColor(getResources().getColor(Colors.getDarkColor(colorCount)));

        TextView mGradeTextView = (TextView) view.findViewById(R.id.grade_text);
        mGradeTextView.setText(course.getGrade() + "%");

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return cardView;
    }
}