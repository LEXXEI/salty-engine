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

package de.edgelord.saltyengine.effect.geom;

import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Coordinates2f;
import de.edgelord.saltyengine.transform.Transform;

/**
 * A triangle around {@link #point1}, {@link #point2} and {@link #point3}.
 * <p>
 * Instances of this class has to be made directly, you cannot get one with {@link SaltyShape#createShape(EnumShape, Transform, float...)}.
 * <p>
 * Setting values of the transform by using methods from {@link de.edgelord.saltyengine.core.interfaces.TransformedObject} won't change the triangle.
 */
@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public class TriangleShape extends SaltyShape {

    private Coordinates2f point1;
    private Coordinates2f point2;
    private Coordinates2f point3;

    public TriangleShape(Coordinates2f point1, Coordinates2f point2, Coordinates2f point3) {
        super(Transform.zero(), EnumShape.TRIANGLE);

        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;

        setTransform(getTriangleTransform());
    }

    private Transform getTriangleTransform() {

        float x;
        float y;
        float maxX;
        float maxY;

        x = point1.getX();
        x = Math.min(point2.getX(), x);
        x = Math.min(point3.getX(), x);

        y = point1.getY();
        y = Math.min(point2.getY(), y);
        y = Math.min(point3.getY(), y);

        maxX = point1.getX();
        maxX = Math.max(point2.getX(), maxX);
        maxX = Math.min(point3.getX(), maxX);

        maxY = point1.getY();
        maxY = Math.max(point2.getY(), maxY);
        maxY = Math.min(point3.getY(), maxY);

        return new Transform(x, y, maxX - x, maxY - y);
    }

    /**
     * Returns the points fitting the giving index, ranging from 0 to 2.
     * @param index the index of the point
     * @return the point
     */
    public Coordinates2f getPoint(int index) {

        if (index < 0 || index > 2) {
            throw new IllegalArgumentException("cannot access triangle points outside the index 0 - 2");
        }

        switch (index) {
            case 0 :
                return getPoint1();

            case 1 :
                return getPoint2();

            case 2 :
                return getPoint3();
        }

        return null;
    }

    /**
     * @return the points of this triangle as an array.
     */
    public Coordinates2f[] getPoints() {
        return new Coordinates2f[] {
                getPoint1(), getPoint2(), getPoint3()
        };
    }

    public Coordinates2f getPoint1() {
        return point1;
    }

    public void setPoint1(Coordinates2f point1) {
        this.point1 = point1;
        this.setTransform(getTriangleTransform());
    }

    public Coordinates2f getPoint2() {
        return point2;
    }

    public void setPoint2(Coordinates2f point2) {
        this.point2 = point2;
        this.setTransform(getTriangleTransform());
    }

    public Coordinates2f getPoint3() {
        return point3;
    }

    public void setPoint3(Coordinates2f point3) {
        this.point3 = point3;
        this.setTransform(getTriangleTransform());
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        if (isFilled()) {
            saltyGraphics.drawTriangle(point1, point2, point3);
        } else {
            saltyGraphics.outlineTriangle(point1, point2, point3);
        }
    }
}
