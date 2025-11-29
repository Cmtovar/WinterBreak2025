package org.example.engine;

import org.lwjgl.glfw.GLFW;

import java.util.HashSet;
import java.util.Set;

public class Input {
    public static long window;
    public static Set<Integer> pressed = new HashSet<>();

    public static void init(long windowHandle) {
        window = windowHandle;
    }

    public static void update() {
        // on update, allow in new set of inputs
        pressed.clear();

        // when a press occurs, add w-key to pressed if the game window owns a w-key press
        if (org.lwjgl.glfw.GLFW.glfwGetKey(window, GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS) {
            pressed.add(GLFW.GLFW_KEY_W);
        }

        // when a press occurs, add s-key to pressed if the game window owns a s-key press
        if (org.lwjgl.glfw.GLFW.glfwGetKey(window, GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS) {
            pressed.add(GLFW.GLFW_KEY_S);
        }
    }

    public static boolean isKeyHeld(int key) {
        return pressed.contains(key);
    }
}
