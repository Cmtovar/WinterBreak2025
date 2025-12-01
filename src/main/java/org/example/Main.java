package org.example;

import org.example.engine.Camera;
import org.example.engine.Input;
import org.example.engine.Vector3Axis;
import org.example.engine.Window;
import org.example.game.Player;
import org.example.game.World;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class Main {
    public static Window window;
    public static Camera camera;
    public static Player player;
    public static World world;

    public static void main(String[] args) {
        init();
        loop();
        cleanup();
    }

    public static void init() {
        window = Window.getInstanceOf();
        camera = Camera.getInstanceOf();

        window.init();
        camera.init();

        // game objects
        world = new World();
        player = new Player(new Vector3Axis(0, 1, 0), world);
    }

    public static void loop() {
        // todo: put glfw get time in... window? or maybe a new Timer class
        double lastTime = GLFW.glfwGetTime();

        while (window.shouldNotClose()) {
            double currentTime = GLFW.glfwGetTime();
            double dt = currentTime - lastTime;
            lastTime = currentTime;

            Input.update();
            player.update(dt);

            // opens buffer to contain rendering
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // todo: possible "Renderable" interface? (e.g. world, player, etc.)
            camera.setupCamera();
            world.render();
            player.render();

            window.swapBuffers();
        }
    }

    public static void cleanup() {
        window.close();
    }
}
