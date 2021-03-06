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

package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.components.TextRenderComponent;
import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;

@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public abstract class TextBox extends TextElement {

    private final TextRenderComponent textRenderComponent;
    private float cursor = 1f;
    private float speed = 0.075f;

    public TextBox(final String text, final Vector2f position, final float width, final float height, final float offsetX, final float offsetY) {
        this(text, new Transform(position, new Dimensions(width, height)), offsetX, offsetY);
    }

    public TextBox(final String text, final Transform transform, final float offsetX, final float offsetY) {
        super(text, transform, TEXT_BOX);

        textRenderComponent = new TextRenderComponent(this, "text-renderer", getWidth() - offsetX, new Vector2f(offsetX, offsetY));
        addComponent(textRenderComponent);
    }

    @Override
    public void onFixedTick() {
        if (cursor < getText().length()) {
            textRenderComponent.setText(getText().substring(0, Math.round(cursor)));
        }
        cursor += speed;
    }

    @Override
    public void drawForeground(final SaltyGraphics graphics) {
    }

    public void start() {
        cursor = 1;
    }

    public void start(final String text) {
        setText(text);
        start();
    }

    @Override
    public void setText(final String text) {
        super.setText(text);
        start();
    }

    @Override
    public void setFont(final Font font) {
        super.setFont(font);
        textRenderComponent.setFont(font);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(final float speed) {
        this.speed = speed;
    }
}
