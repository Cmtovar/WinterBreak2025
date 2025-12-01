package org.example.game;

import org.example.engine.Vector3Axis;
import org.lwjgl.opengl.GL11;

import java.util.HashSet;
import java.util.Set;

public class World {
    private Set<Vector3Axis> grassPositions = new HashSet<>();
    private Set<Vector3Axis> waterPositions = new HashSet<>();

    public World() {
        // y is up
        // straight path of grass
        for (int z = 0; z <= 10; z++) {
            grassPositions.add(new Vector3Axis(0, 0, z));
        }

        // water cubes next to path (just for visuals)
        waterPositions.add(new Vector3Axis(1, 0, 3));
        waterPositions.add(new Vector3Axis(1, 0, 4));
        waterPositions.add(new Vector3Axis(-1, 0, 6));
        waterPositions.add(new Vector3Axis(-1, 0, 7));
    }

    public boolean canStandAt(Vector3Axis position) {
        // round to nearest integer coordinates
        Vector3Axis rounded = new Vector3Axis(Math.round(position.x), 0, Math.round(position.z));
        return grassPositions.contains(rounded);
    }

    public void render() {
        // render grass cubes (green)
        GL11.glColor3f(0.2f, 0.8f, 0.2f);
        for (Vector3Axis position : grassPositions) {
            renderCube(position);
        }

        // render water cubes (blue)
        GL11.glColor3f(0.2f, 0.4f, 1.0f);
        for (Vector3Axis position : waterPositions) {
            renderCube(position);
        }
    }

    private void renderCube(Vector3Axis position) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) position.x, (float) position.y, (float) position.z);

        float size = 0.5f;
        GL11.glBegin(GL11.GL_QUADS);

        // these render six faces by their vertices to see the cube

        // front face (normal facing +Z)
        GL11.glVertex3f(-size, -size,  size);  // bottom-left (from front view)
        GL11.glVertex3f( size, -size,  size);  // bottom-right
        GL11.glVertex3f( size,  size,  size);  // top-right
        GL11.glVertex3f(-size,  size,  size);  // top-left

        // back face (normal facing -Z)
        GL11.glVertex3f( size, -size, -size);  // bottom-left (from back view)
        GL11.glVertex3f(-size, -size, -size);  // bottom-right
        GL11.glVertex3f(-size,  size, -size);  // top-right
        GL11.glVertex3f( size,  size, -size);  // top-left

        // top face (normal facing +Y)
        GL11.glVertex3f(-size,  size, -size);  // back-left (from front view)
        GL11.glVertex3f(-size,  size,  size);  // front-left
        GL11.glVertex3f( size,  size,  size);  // front-right
        GL11.glVertex3f( size,  size, -size);  // back-right

        // bottom face (normal facing -Y)
        GL11.glVertex3f(-size, -size,  size);  // front-left (from front view)
        GL11.glVertex3f(-size, -size, -size);  // back-left
        GL11.glVertex3f( size, -size, -size);  // back-right
        GL11.glVertex3f( size, -size,  size);  // front-right

        // right face (normal facing +X)
        GL11.glVertex3f( size, -size,  size);  // front-bottom (from front view)
        GL11.glVertex3f( size, -size, -size);  // back-bottom
        GL11.glVertex3f( size,  size, -size);  // back-top
        GL11.glVertex3f( size,  size,  size);  // front-top

        // left face (normal facing -X)
        GL11.glVertex3f(-size, -size, -size);  // back-bottom  (from front view)
        GL11.glVertex3f(-size, -size,  size);  // front-bottom
        GL11.glVertex3f(-size,  size,  size);  // front-top
        GL11.glVertex3f(-size,  size, -size);  // back-top

        GL11.glEnd();
        GL11.glPopMatrix();
    }
}
