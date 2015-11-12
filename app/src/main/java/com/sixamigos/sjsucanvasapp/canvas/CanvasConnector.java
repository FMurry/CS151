package com.sixamigos.sjsucanvasapp.canvas;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author Alex Heritier
 */
public class CanvasConnector {
    public static final String DEBUG_TOKEN = "12~wpJplnfyzvsMkcA1Bd4ccOvTfKN5ydOl0TXs663mVF3aYyc7zXo6rJ8JwoihJYOC";
    private static final String TAG = "canvas.CanvasConnector";
    private CanvasConnectorCallback callback;

    public CanvasConnector() {
    }

    public void setCallback(CanvasConnectorCallback callback) {
        this.callback = callback;
    }

    public void getCourses() {
        HashMap<String, String> data = new HashMap<>();
        data.put("access_token", CanvasConnector.DEBUG_TOKEN);
        data.put("enrollment_type", "student");

        GetCoursesTask getCoursesTask = new GetCoursesTask();
        getCoursesTask.setCallback(new CanvasCallback() {
            @Override
            public void call(JSONObject data) {
                //Course[] courses = null;
                //callback.onCoursesReceived(courses);
                try {
                    System.out.println(data.toString(4));
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
