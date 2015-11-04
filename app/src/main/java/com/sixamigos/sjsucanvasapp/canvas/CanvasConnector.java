package com.sixamigos.sjsucanvasapp.canvas;

/**
 * @author  Alex Heritier
 */
public class CanvasConnector {
    private static final String TAG = "canvas.CanvasConnector";

    private CanvasConnectorCallback callback;

    public CanvasConnector() {
        System.out.println(TAG + ": yolo");
    }

    public void setCallback(CanvasConnectorCallback callback) {
        this.callback = callback;
    }

    public void getCourses() {
        // get courses
        Course[] courses = null;
        callback.onCoursesReceived(courses);
    }

    interface CanvasConnectorCallback {
        public void onCoursesReceived(Course[] courses);
    }
}
