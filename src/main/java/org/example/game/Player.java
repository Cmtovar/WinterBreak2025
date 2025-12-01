package org.example.game;

import org.example.config.GameBalance;
import org.example.engine.Input;
import org.example.engine.Vector3Axis;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

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

    public void render() {
        GL11.glColor3f(1.0f, 0.2f, 0.2f); // red
        GL11.glPushMatrix();
        GL11.glTranslatef((float) position.x, (float) position.y, (float) position.z);

        float size = 0.4f;
        // todo: add impl for Renderable interface?
        GL11.glBegin(GL11.GL_QUADS);

        // these render six faces by their vertices to see the cube

        //  right
        /*  + - -
            + + -
            + + +
            + - +
         */
        GL11.glVertex3f( size, -size, -size);
        GL11.glVertex3f( size,  size, -size);
        GL11.glVertex3f( size,  size,  size);
        GL11.glVertex3f( size, -size,  size);

        //  top
        /*  - + -
            + - -
            + - +
            - - +
         */
        GL11.glVertex3f(-size,  size, -size);
        GL11.glVertex3f( size, -size, -size);
        GL11.glVertex3f( size, -size,  size);
        GL11.glVertex3f(-size, -size,  size);

        //  front
        /*  - - +
            + - +
            + + +
            - + +
         */
        GL11.glVertex3f(-size, -size, size);
        GL11.glVertex3f( size, -size, size);
        GL11.glVertex3f( size,  size, size);
        GL11.glVertex3f(-size,  size, size);


        //  back
        /*  - - -
            - + -
            + + -
            + - -
         */
        GL11.glVertex3f(-size, -size, -size);
        GL11.glVertex3f(-size,  size, -size);
        GL11.glVertex3f( size,  size, -size);
        GL11.glVertex3f( size, -size, -size);

        //  bottom
        /*  - - -
            + - -
            + - +
            - - +
         */
        GL11.glVertex3f(-size, -size, -size);
        GL11.glVertex3f( size, -size, -size);
        GL11.glVertex3f( size, -size,  size);
        GL11.glVertex3f(-size, -size,  size);

        //  left
        /*  - - -
            - - +
            - + +
            - + -
         */
        GL11.glVertex3f(-size, -size, -size);
        GL11.glVertex3f(-size, -size,  size);
        GL11.glVertex3f(-size,  size,  size);
        GL11.glVertex3f(-size,  size, -size);


        GL11.glEnd();
        GL11.glPopMatrix();
    }

}
