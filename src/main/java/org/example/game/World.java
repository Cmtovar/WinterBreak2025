package org.example.game;

import org.example.engine.Vector3Axis;
import org.lwjgl.opengl.GL11;

import java.util.HashSet;
import java.util.Set;

public class World {
    private Set<Vector3Axis> grassPositions = new HashSet<>();
    private Set<Vector3Axis> waterPositions = new HashSet<>();
    private Vector3Axis goalPosition;

    public World() {
        // straight path of grass
        for (int z = 0; z <= 10; z++) {
            grassPositions.add(new Vector3Axis(0, 0, z));
        }

        // water cubes next to path (just for visuals)
        waterPositions.add(new Vector3Axis(1, 0, 3));
        waterPositions.add(new Vector3Axis(1, 0, 4));
        waterPositions.add(new Vector3Axis(-1, 0, 6));
        waterPositions.add(new Vector3Axis(-1, 0, 7));

        goalPosition = new Vector3Axis(0, 0, 10);
    }

    public boolean canStandAt(Vector3Axis position) {
        // round to nearest integer coordinates
        Vector3Axis rounded = new Vector3Axis(Math.round(position.x), 0, Math.round(position.z));
        return grassPositions.contains(rounded);
    }

    public void render() {

    }

    private void renderCube(Vector3Axis position) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) position.x, (float) position.y, (float) position.z);

        float size = 0.5f;
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
