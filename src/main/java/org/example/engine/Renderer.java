package org.example.engine;

import org.example.config.Material;
import org.lwjgl.opengl.GL11;

public class Renderer {
    // todo: consider making Player and World implement render() in Renderable interface, then call it here
    public static void renderCube(Vector3Axis position, Material material) {
        // todo: something to this selection system, visitor design pattern?
        float size;
        if (material == Material.PLAYER) {
            GL11.glColor3f(1.0f, 0.2f, 0.2f); // red
            size = 0.5f;
        } else if (material == Material.GRASS) {
            GL11.glColor3f(0.2f, 0.8f, 0.2f); // green
            size = 0.5f;
        } else if (material == Material.WATER) {
            GL11.glColor3f(0.2f, 0.4f, 1.0f); // blue
            size = 0.5f;
        } else {
            GL11.glColor3f(1.0f, 1.0f, 0.0f); // yellow
            size = 0.5f;
        }


        GL11.glPushMatrix();
        GL11.glTranslatef((float) position.x, (float) position.y, (float) position.z);

        // todo: add impl for Renderable interface?
        GL11.glBegin(GL11.GL_QUADS);

        // these render six faces by their vertices to see the cube

        // front face (facing +z)
        GL11.glVertex3f(-size, -size,  size); // bottom-left
        GL11.glVertex3f( size, -size,  size); // bottom-right
        GL11.glVertex3f( size,  size,  size); // top-right
        GL11.glVertex3f(-size,  size,  size); // top-left

        // back face (facing -z)
        GL11.glVertex3f( size, -size, -size); // bottom-left (from back view)
        GL11.glVertex3f(-size, -size, -size); // bottom-right
        GL11.glVertex3f(-size,  size, -size); // top-right
        GL11.glVertex3f( size,  size, -size); // top-left

        // top face (facing +y)
        GL11.glVertex3f(-size,  size, -size); // back-left
        GL11.glVertex3f(-size,  size,  size); // front-left
        GL11.glVertex3f( size,  size,  size); // front-right
        GL11.glVertex3f( size,  size, -size); // back-right

        // bottom face (facing -y)
        GL11.glVertex3f(-size, -size,  size); // front-left
        GL11.glVertex3f(-size, -size, -size); // back-left
        GL11.glVertex3f( size, -size, -size); // back-right
        GL11.glVertex3f( size, -size,  size); // front-right

        // right face (facing +x)
        GL11.glVertex3f( size, -size,  size); // front-bottom
        GL11.glVertex3f( size, -size, -size); // back-bottom
        GL11.glVertex3f( size,  size, -size); // back-top
        GL11.glVertex3f( size,  size,  size); // front-top

        // left face (facing -x)
        GL11.glVertex3f(-size, -size, -size); // back-bottom
        GL11.glVertex3f(-size, -size,  size); // front-bottom
        GL11.glVertex3f(-size,  size,  size); // front-top
        GL11.glVertex3f(-size,  size, -size); // back-top


        GL11.glEnd();
        GL11.glPopMatrix();
    }
}
