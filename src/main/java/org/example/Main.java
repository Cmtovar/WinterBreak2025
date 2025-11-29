package org.example;

import org.example.engine.Input;
import org.example.engine.Vector3Axis;
import org.example.game.Player;
import org.example.game.World;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Main {
    public static long window;
    public static Player player;
    public static World world;

    public static void main(String[] args) {
        init();
        loop();
        cleanup();
    }

    public static void init() {
        // window
        GLFW.glfwInit();
        window = GLFW.glfwCreateWindow(800, 600, "Red Cube Game", 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        Input.init(window);

        // openGL
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClearColor(0.5f, 0.7f, 1.0f, 1.0f); // Sky blue

        // game objects
        world = new World();
        player = new Player(new Vector3Axis(0, 1, 0), world);
    }

    public static void loop() {
        double lastTime = GLFW.glfwGetTime();

        while (!GLFW.glfwWindowShouldClose(window)) {
            double currentTime = GLFW.glfwGetTime();
            double dt = currentTime - lastTime;
            lastTime = currentTime;

            Input.update();
            player.update(dt);

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            setupCamera();
            world.render();
            player.render();

            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }

    private static void setupCamera() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(-10, 10, -10, 10, -10, 50); // simple orthogonal view

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0, -5, -20); // camera position
        GL11.glRotatef(-30, 1, 0, 0); // tilt down
    }

    public static void cleanup() {
        GLFW.glfwTerminate();
    }
}
