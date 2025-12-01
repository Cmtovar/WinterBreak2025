package org.example.game;

import org.example.config.GameBalance;
import org.example.config.Material;
import org.example.engine.Input;
import org.example.engine.Renderer;
import org.example.engine.Vector3Axis;
import org.lwjgl.glfw.GLFW;

public class Player implements Movable{
    private Vector3Axis position;
    private World world;

    // todo: check, passing world as a reference, consider DTO later
    public Player(Vector3Axis startPosition, World world) {
        // center of the cube, todo: will overhang ledges
        this.position = startPosition;
        this.world = world;
    }

    public void update(double dt) {
        // if w-key press is read, walk forward
        if (Input.isKeyHeld(GLFW.GLFW_KEY_W)) {
            moveForward(GameBalance.PLAYER_SPEED * dt);
        }
        // if s-key press is read, walk backward
        if (Input.isKeyHeld(GLFW.GLFW_KEY_S)) {
            moveBackward(GameBalance.PLAYER_SPEED * dt);
        }
    }


    @Override
    public void moveForward(double distance) {
        Vector3Axis newPosition = this.position.add(new Vector3Axis(0, 0, distance));
        // todo: fix coupling to world
        if (world.canStandAt(newPosition)) {
            this.position = newPosition;
        }
    }

    @Override
    public void moveBackward(double distance) {
        Vector3Axis newPosition = this.position.add(new Vector3Axis(0, 0, -distance));
        // todo: fix coupling to world
        if (world.canStandAt(newPosition)) {
            this.position = newPosition;
        }
    }

    public Vector3Axis getPosition() {
        return this.position;
    }

    // todo: perhaps make Renderer a factory for different geometries instead of a monostate
    public void render() {
        Renderer.renderCube(new Vector3Axis(position.x, position.y, position.z), Material.PLAYER);
    }

}
