package github.com_1009project.abstractEngine;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Scene {
    private int id;
    private List<Layer> layers = new ArrayList<>();;
    private AssetManager resourceManager;
    private EntityManager entityManager;
    private EventManager eventManager;

    public Scene(int id, AssetManager resourceManager, EntityManager entityManager, EventManager eventManager) {
        this.id = id;
        this.resourceManager = resourceManager;
        this.entityManager = entityManager;
        this.eventManager = eventManager;
    }
    public int getId() {
        return id;
    }
    public void init() {
        // add BackgroundLayer and EntityLayer
        layers.add(new BackgroundLayer("imgs/background.png"));
        layers.add(new EntityLayer(eventManager, entityManager));
        layers.add(new UILayer());
    }
    public void onEnter() {
        // called when scene becomes active
        for (Layer layer : layers) {
            if (layer instanceof UILayer) {
                Gdx.input.setInputProcessor(((UILayer) layer).getStage());
            }
        }
    }
    public void onExit() {
        // used to stop music or other things when scene is no longer active
    }
    public void update(float deltaTime) {
        for (Layer layer : layers) {
            layer.update(deltaTime);
        }
    }
    public void render() {
        for (Layer layer : layers) {
            layer.render(); 
        }
    }

    public void dispose() {
        for (Layer layer : layers) {
            layer.dispose();
        }
    }
}
