package org.example.engine;

import java.util.Objects;

/**
 * Purpose: 3D position/vector math.
 */
public class Vector3Axis {
    public double x;
    public double y;
    public double z;

    /**
     * constructor for a vector with three axis components.
     * @param x
     * @param y
     * @param z
     */
    public Vector3Axis(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    /**
     * sum components of this vector with another vector
     * @param other
     * @return
     */
    public Vector3Axis add(Vector3Axis other) {
        return new Vector3Axis(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    /**
     * multiply components of this vector with a scalar
     * @param scalar
     * @return
     */
    public Vector3Axis multiply(double scalar) {
        return new Vector3Axis(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    /**
     * used in constructor
     * @param x
     */
    private void setX(double x) {
        // primitive defaults to 0.0
        this.x = x;
    }

    public double getX() {
        return x;
    }

    /**
     * used in constructor
     * @param y
     */
    private void setY(double y) {
        // primitive defaults to 0.0
        this.y = y;
    }

    public double getY() {
        return y;
    }

    /**
     * used in constructor
     * @param z
     */
    private void setZ(double z) {
        // primitive defaults to 0.0
        this.z = z;
    }

    public double getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vector3Axis that = (Vector3Axis) o;
        return Double.compare(x, that.x) == 0 && Double.compare(y, that.y) == 0 && Double.compare(z, that.z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
