package com.sixamigos.sjsucanvasapp.canvas;

import android.content.Context;
import android.util.Log;

import com.sixamigos.sjsucanvasapp.assignments.Assignment;
import com.sixamigos.sjsucanvasapp.courses.Course;
import com.sixamigos.sjsucanvasapp.login.canvas.CanvasLoginFailureException;
import com.sixamigos.sjsucanvasapp.login.canvas.CanvasToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Alex Heritier
 */
public class CanvasConnector {
    private static final String TAG = "canvas.CanvasConnector";
    private CanvasConnectorCallback callback;
    private Context context;
    private int id;

    public CanvasConnector(Context context) {
        this(context, -1);
    }

    public CanvasConnector(Context context, int id) {
        this.context = context;
        this.id = id;
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
        data.put("include", "total_scores");

        GetCoursesTask getCoursesTask = new GetCoursesTask(context);
        getCoursesTask.setCallback(new CanvasCallback() {
            @Override
            public void call(JSONObject data) {
                try {
                    JSONArray courseArray = data.getJSONArray("_wrapped_array");

                    List<Course> courses = new ArrayList<>();
                    for (int i = 0; i < courseArray.length(); i++) {
                        JSONObject courseData = courseArray.getJSONObject(i);
                        Log.e("Course Data", courseData.toString());
                        if (courseData.has("name")) {
                            Course course = new Course();
                            course.setCourseName(courseData.getString("course_code"));
                            course.setFullName(courseData.getString("name"));
                            course.setId(courseData.getInt("id"));

                            JSONArray enrollments = courseData.getJSONArray("enrollments");
                            JSONObject enrollmentObject = (JSONObject) enrollments.get(0);
                            course.setGrade(enrollmentObject.getDouble("computed_current_score"));

                            courses.add(course);
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

    /**
     * Make the server request to get the assignment data and then pass it to the callback object;
     */
    public void getAssignments(HashMap<String, String> data) throws CanvasLoginFailureException {
        GetAssignmentsTask getAssignmentsTask = new GetAssignmentsTask(context, id);
        getAssignmentsTask.setCallback(new CanvasCallback() {
            @Override
            public void call(JSONObject data) {
                try {

                    Log.e("Assignments", data.toString());
                    JSONArray assignmentArray = data.getJSONArray("_wrapped_array");

                    List<Assignment> assignments = new ArrayList<>();
                    for (int i = 0; i < assignmentArray.length(); i++) {
                        JSONObject assignmentData = assignmentArray.getJSONObject(i);
                        if (assignmentData.has("name")) {
                            Assignment assignment = new Assignment();
                            assignment.setName(assignmentData.getString("name"));
                            assignment.setDescription(assignmentData.getString("description"));
                            if (assignmentData.has("points_possible"))
                                try {
                                assignment.setTotalPoints(assignmentData.getDouble("points_possible"));
                                    } catch (Exception e) {
                                    assignment.setTotalPoints(0);
                                }
                            else assignment.setTotalPoints(0);
                            assignment.setDueDate(assignmentData.getString("due_at"));
                            assignments.add(assignment);
                        }
                    }
                    callback.onCoursesReceived(assignments);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        data.put("access_token", CanvasToken.getCanvasToken());
        getAssignmentsTask.execute(data);
    }

    public interface CanvasConnectorCallback {
        <T> void onCoursesReceived(List<T> data);
    }
}
