package de.edgelord.saltyengine.components.gfx;

import de.edgelord.saltyengine.core.stereotypes.ComponentParent;
import de.edgelord.saltyengine.cosmetic.light.Light;
import de.edgelord.saltyengine.cosmetic.light.LightSystem;
import de.edgelord.saltyengine.cosmetic.light.PointLight;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.transform.RelationMode;
import de.edgelord.saltyengine.utils.TransformRelationUtils;

/**
 * This {@link de.edgelord.saltyengine.core.Component} makes a {@link Light} follow its parent using the rule
 * described in {@link #relationToParent} and {@link TransformRelationUtils}.
 * The light and the mode can be set in one of the constructors, if not the defaults are:
 * {@code RelationMode.CENTRE}
 * and
 * {@code new PointLight(parent.getTransform())}
 */
public class LightComponent extends GFXComponent {

    private RelationMode relationToParent;
    private Light light;

    public LightComponent(ComponentParent parent, String name, RelationMode relationToParent, Light light) {
        super(parent, name);

        this.light = light;
        this.relationToParent = relationToParent;

        addToLightSystem();
    }

    public LightComponent(ComponentParent parent, String name, Light light) {
        this(parent, name, RelationMode.CENTRE, light);
    }

    public LightComponent(ComponentParent parent, String name, RelationMode relationToParent) {
        this(parent, name, relationToParent, new PointLight(parent.getTransform()));
    }

    public LightComponent(ComponentParent parent, String name) {
        this(parent, name, RelationMode.CENTRE, new PointLight(parent.getTransform()));
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
    }

    @Override
    public void onFixedTick() {
        TransformRelationUtils.positionRelativeTo(relationToParent, getParent().getTransform(), light.getTransform());
    }

    public void addToLightSystem() {
        LightSystem currentLightSystem = SceneManager.getCurrentScene().getLightSystem();

        if (currentLightSystem == null) {
            throw new NullPointerException("Can't add a LightComponent when the current scene has no LightSystem! Set one by using Scene#setLightSystem!");
        }

        currentLightSystem.addLight(light);
    }
}
