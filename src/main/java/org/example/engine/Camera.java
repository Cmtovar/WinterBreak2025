package org.example.engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class Camera {
    private static Camera instance;

    private Camera() {
    }

    public static Camera getInstanceOf() {
        if (instance == null) {
            instance = new Camera();
        }
        return instance;
    }

    public void init() {
        // openGL
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClearColor(0.5f, 0.7f, 1.0f, 1.0f); // Sky blue
    }

    public void setupCamera() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        // play space size, default orientation (behavior is independent of glRotatef)
        // v , v1 - includes more spaces to the left and right respectively
        // v2, v3 - includes more spaces to the bottom and top respectively
        // v4, v5 - includes more spaces out of the screen and out of the screen respectively
        //
        // if geometry looks squished on an axis, reduce the number of spaces on that axis (zooms in)
        //  or increase the number of spaces on its perpendicular axis (zooms out)
        //
        // if more space is needed in a direction, couple its increase/decrease with its neighboring axis to mitigate stretching
        // to pan the camera, apply increment/decrement to both ends of an axis
        GL11.glOrtho(-9, 9, -12, 2, -20, 20); // simple orthogonal view

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        // v  - left/right shift
        // v1 - up/down shift
        // v2 -
        GL11.glTranslatef(0, 0, 0); // camera position

        // todo: in setup, associate camera tilt and camera position

        // glRotate produces a rotation of angle degrees around the vector (x,y,z)
        // https://registry.khronos.org/OpenGL-Refpages/gl2.1/xhtml/glRotate.xml
        // I still don't understand it after an hour of playing with it
        // if the cube is going off-screen, consider increasing the magnitude in GL11.glOrtho(...) above ^^
        //
        // (x, y, z) gets normalized if magnitude is not equal to 1
        // this is always a vector through the origin, compensate off-screen geometry by increasing/decreasing
        //  space size in glOrtho(...) above ^^
        //
        // config that looks nice: << for basic demo world and cube sizes
        // GL11.glOrtho(-2, 12, -5, 5, -20, 20); for GL11.glRotatef(-90, 0, -1, 0);
        // GL11.glOrtho(-2, 12, -5, 5, -20, 20); for GL11.glRotatef(90, 0, 1, 0);
        // GL11.glOrtho(-9, 9, -12, 2, -20, 20); for GL11.glRotatef(90, 1, 0, 0);
        // GL11.glOrtho(-9, 9, -12, 2, -20, 20); for GL11.glRotatef(-90, -1, 0, 0);
        // GL11.glOrtho(-4, 1, -2, 2, -20, 20); for GL11.glRotatef(90, 0, 0, 1);
        // GL11.glOrtho(-4, 1, -2, 2, -20, 20); for GL11.glRotatef(-90, 0, 0, -1);
        /*
           I just noticed by this point that glTranslate(...) existed and I forgot about it
           at least now I can decouple the world scope from the rotation.
           but, glOrtho(...) still matters because of squash/stretch.
         */
        // GL11.glOrtho(-3, 9, -4, 8, -20, 20); for GL11.glRotatef(90, 0, 1, 1);
        // GL11.glOrtho(-2, 10, -8, 4, -20, 20); for GL11.glRotatef(90, 0, 1, -1);
        //  ^- swapped magnitudes of v2/v3, incremented v/v1
        // GL11.glOrtho(-10, 2, -9, 3, -20, 20); for GL11.glRotatef(90, 0, -1, 1);
        //  ^- swapped magnitudes of v/v1 and v2/v3, decremented v/v1 and v2/v3
        // GL11.glOrtho(-3, 9, -4, 8, -20, 20); for GL11.glRotatef(-90, 0, -1, -1);
        //
        // GL11.glOrtho(-3, 8, -9, 2, -20, 20); for GL11.glRotatef(90, 1, 0, 1);
        // GL11.glOrtho(-8, 3, -9, 2, -20, 20); for GL11.glRotatef(90, 1, 0, -1);
        //  ^- swapped magnitudes of v/v1
        // GL11.glOrtho(-8, 3, -2, 9, -20, 20); for GL11.glRotatef(90, -1, 0, 1);
        //  ^- swapped magnitudes of v/v1 and v2/v3
        // GL11.glOrtho(-3, 8, -9, 2, -20, 20); for GL11.glRotatef(90, -1, 0, -1);
        //
        // GL11.glOrtho(-2, 9, -9, 2, -20, 20); for GL11.glRotatef(90, 1, 1, 0);
        // GL11.glOrtho(-9, 2, -9, 2, -20, 20); for GL11.glRotatef(90, 1, -1, 0);
        //  ^- swapped magnitudes of v/v1
        // GL11.glOrtho(-2, 9, -2, 9, -20, 20); for GL11.glRotatef(90, -1, 1, 0);
        //  ^- swapped magnitudes of v2/v3
        // GL11.glOrtho(-2, 9, -9, 2, -20, 20); for GL11.glRotatef(90, -1, -1, 0);
        //
        // GL11.glOrtho(-10, 11, -12, 9, -20, 20); for all GL11.glRotate(...);

        GL11.glRotatef(90, 1, 0, 0); // tilt down
    }
}
