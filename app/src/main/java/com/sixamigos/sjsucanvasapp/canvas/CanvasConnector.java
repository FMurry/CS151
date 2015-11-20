package com.sixamigos.sjsucanvasapp.canvas;

import android.util.Log;

import com.sixamigos.sjsucanvasapp.courses.Course;
import com.sixamigos.sjsucanvasapp.login.canvas.CanvasLoginFailureException;
import com.sixamigos.sjsucanvasapp.login.canvas.CanvasToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author Alex Heritier
 */
public class CanvasConnector {
    private static final String TAG = "canvas.CanvasConnector";
    private CanvasConnectorCallback callback;

    public CanvasConnector() {
    }

    /**
     * Set the callback to be used when the course data is received.
     * @param callback
     */
    public void setCallback(CanvasConnectorCallback callback) {
        this.callback = callback;
    }

    /**
     * Make the server request to get the course data and then pass it to the callback object.
     */
    public void getCourses() throws CanvasLoginFailureException {
        HashMap<String, String> data = new HashMap<>();

        data.put("access_token", CanvasToken.getCanvasToken());
        data.put("enrollment_type", "student");

        GetCoursesTask getCoursesTask = new GetCoursesTask();
        getCoursesTask.setCallback(new CanvasCallback() {
            @Override
            public void call(JSONObject data) {
                try {
                    JSONArray courseArray = data.getJSONArray("_wrapped_array");
                    int courseNum = 0;
                    for (int i = 0; i < courseArray.length(); i++) {
                        JSONObject courseData = courseArray.getJSONObject(i);
                        if (courseData.has("name")) courseNum++;
                    }
                    Course[] courses = new Course[courseNum];
                    for (int i = 0; i < courseArray.length(); i++) {
                        JSONObject courseData = courseArray.getJSONObject(i);
                        if (courseData.has("name")) {
                            Course course = new Course();
                            course.setCourseName(courseData.getString("name"));
                            course.setFullName(courseData.getString("name"));
                            courses[i] = course;
                        }
                    }
                    callback.onCoursesReceived(courses);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        getCoursesTask.execute(data);
    }

    public interface CanvasConnectorCallback {
        public void onCoursesReceived(Course[] courses);
    }
}
