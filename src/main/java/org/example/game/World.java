package org.example.game;

import org.example.config.Material;
import org.example.engine.Renderer;
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
        for (Vector3Axis position : grassPositions) {
            Renderer.renderCube(position, Material.GRASS);
        }

        // render water cubes (blue)
        for (Vector3Axis position : waterPositions) {
            Renderer.renderCube(position, Material.WATER);
        }
    }
}
