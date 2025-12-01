package org.example.engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

public class Window {
    private static Window instance;
    private long window;

    private Window() {
    }

    public static Window getInstanceOf() {
        if (instance == null) {
            instance = new Window();
        }
        return instance;
    }

    public void init() {
        // window
        GLFW.glfwInit();
        window = GLFW.glfwCreateWindow(800, 600, "Red Cube Game", 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        Input.init(window);
    }

    public boolean shouldNotClose() {
        return !GLFW.glfwWindowShouldClose(window);
    }

    public void swapBuffers() {
        // load buffer into display window
        GLFW.glfwSwapBuffers(window);
        // process remaining OS events in event queue
        GLFW.glfwPollEvents();
    }

    public void close() {
        GLFW.glfwTerminate();
    }
}
