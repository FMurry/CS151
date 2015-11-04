package com.sixamigos.sjsucanvasapp.canvas;

/**
<<<<<<< HEAD
 * @author  Alex Heritier
=======
 * Created by Alex on 11/3/15.
>>>>>>> Added a package for the canvas code + added the canvas connector class
 */
public class CanvasConnector {
    private static final String TAG = "canvas.CanvasConnector";

<<<<<<< HEAD
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
=======
    public CanvasConnector() {
        System.out.println(TAG + ": yolo");
    }
>>>>>>> Added a package for the canvas code + added the canvas connector class
}
