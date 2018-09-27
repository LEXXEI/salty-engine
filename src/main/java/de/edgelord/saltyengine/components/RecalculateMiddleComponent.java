/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.components;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;

public class RecalculateMiddleComponent extends Component<GameObject> {

    private Vector2f exactMiddle = new Vector2f(0, 0);


    public RecalculateMiddleComponent(GameObject parent, String name) {
        super(parent, name, Components.CORE_COMPONENT);
    }

    @Override
    public void onFixedTick() {

        calculateMiddle();
        getParent().setMiddle(exactMiddle);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

    }

    @Override
    public void onCollision(CollisionEvent e) {

    }

    private void calculateMiddle() {

        exactMiddle.setX(getParent().getX() + (getParent().getWidth() / 2));
        exactMiddle.setY(getParent().getY() + (getParent().getHeight() / 2));
    }
}