/*
 * Copyright 2018 Malte Dostal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.edgelord.saltyengine.transform;

import java.util.Random;

public class Vector2f {

    private float x;
    private float y;

    public Vector2f(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(final Vector2f position) {
        this(position.getX(), position.getY());
    }

    public static Vector2f zero() {
        return new Vector2f(0, 0);
    }

    public static Vector2f max() {
        return new Vector2f(Float.MAX_VALUE, Float.MAX_VALUE);
    }

    public static Vector2f min() {
        return new Vector2f(Float.MIN_VALUE, Float.MIN_VALUE);
    }

    public static Vector2f random(final int min, final int max) {
        final Random random = new Random();
        return new Vector2f(random.nextInt(max - min) + min, random.nextInt(max - min) + min);
    }

    public static Vector2f one() {
        return new Vector2f(1, 1);
    }

    /**
     * Returns the distance between this and the given point.
     *
     * @param point a point
     * @return teh distance between this and the given point
     */
    public float distance(final Vector2f point) {
        final double ac = Math.abs(point.y - y);
        final double cb = Math.abs(point.x - x);

        return (float) Math.hypot(ac, cb);
    }

    public float getX() {
        return x;
    }

    public void setX(final float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(final float y) {
        this.y = y;
    }

    public Vector2f add(final float x1, final float y1) {
        x += x1;
        y += y1;

        return this;
    }

    public Vector2f add(final Vector2f pos1) {
        return add(pos1.getX(), pos1.getY());
    }

    public Vector2f multiply(final float x1, final float y1) {
        x *= x1;
        y *= y1;

        return this;
    }

    public Vector2f multiply(final Vector2f pos1) {
        return multiply(pos1.getX(), pos1.getY());
    }

    public Vector2f divide(final float x1, final float y1) {
        x /= x1;
        y /= y1;

        return this;
    }

    public Vector2f divide(final Vector2f pos1) {
        return divide(pos1.getX(), pos1.getY());
    }

    public Vector2f subtract(final float x1, final float y1) {
        x -= x1;
        y -= y1;

        return this;
    }

    public Vector2f subtract(final Vector2f pos1) {
        return subtract(pos1.getX(), pos1.getY());
    }

    public Coordinates convertToCoordinates() {

        return new Coordinates((int) getX(), (int) getY());
    }

    public void parseCoordinates2f(final Coordinates coordinates) {

        setX((float) coordinates.getX());
        setY((float) coordinates.getY());
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Vector2f) {
            final Vector2f other = (Vector2f) obj;
            return other.getX() == getX() && other.getY() == getY();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Vector2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Returns a new <code>Vector2f</code> with the same {@link #x} and {@link #y} as this one.
     *
     * @return a "copy" of this <code>Vector2f</code>
     */
    @Override
    protected Object clone() {
        return new Vector2f(x, y);
    }
}
