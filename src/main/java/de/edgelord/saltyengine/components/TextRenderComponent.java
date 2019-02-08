/*
 * Copyright 2019 Malte Dostal
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

package de.edgelord.saltyengine.components;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.transform.Coordinates2f;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TextRenderComponent extends Component<ComponentContainer> {

    private Font font = SaltySystem.defaultFont;
    private float leading = font.getSize() * 1.3f;
    private String text;
    private List<String> lines = new ArrayList<>();
    private boolean recalculate = true;
    private String wordSeparator = " ";
    private Coordinates2f offset;
    private float lineLength;

    public TextRenderComponent(ComponentContainer parent, String name, float lineLength, Coordinates2f offset) {
        super(parent, name, Components.RENDER_COMPONENT);

        this.lineLength = lineLength;
        this.offset = offset;
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        saltyGraphics.setFont(font);
        FontMetrics fontMetrics = saltyGraphics.getFontMetrics();

        if (recalculate) {
            lines.clear();
            String[] words = text.split(wordSeparator);
            //leading = fontMetrics.getLineMetrics(getText(), saltyGraphics.getGraphics2D()).getLeading();
            float currentX = 0;
            StringBuilder lineBuilder = new StringBuilder();

            for (String word : words) {
                float length = fontMetrics.stringWidth(word + wordSeparator);

                if (currentX + length <= lineLength) {
                    lineBuilder.append(word).append(wordSeparator);
                    currentX += length;
                } else {
                    lines.add(lineBuilder.toString());
                    lineBuilder = new StringBuilder();
                    lineBuilder.append(word).append(wordSeparator);
                    currentX = length;
                }
            }

            lines.add(lineBuilder.toString());

            recalculate = false;
        }

        float currentY = 0;
        for (String line : lines) {
            saltyGraphics.drawText(line, getParent().getPosition().getX() + offset.getX(), currentY + getParent().getY() + offset.getY());

            currentY += leading;
        }
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void onCollision(CollisionEvent e) {

    }

    public void setText(String text) {
        this.text = text;
        recalculate = true;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
        leading = font.getSize() * 1.3f;
    }

    public String getText() {
        return text;
    }

    public String getWordSeparator() {
        return wordSeparator;
    }

    public void setWordSeparator(String wordSeparator) {
        this.wordSeparator = wordSeparator;
    }

    public Coordinates2f getOffset() {
        return offset;
    }

    public void setOffset(Coordinates2f offset) {
        this.offset = offset;
    }

    public float getLineLength() {
        return lineLength;
    }

    public void setLineLength(float lineLength) {
        this.lineLength = lineLength;
    }
}
