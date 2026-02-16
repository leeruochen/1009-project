
package github.com_1009project.abstractEngine;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Scene {
    private int id;
    protected List<Layer> layers = new ArrayList<>();;
    protected AssetManager resourceManager;
    protected EntityManager entityManager;
    protected EventManager eventManager;
    protected SpriteBatch batch;

    public Scene(int id, AssetManager resourceManager, EntityManager entityManager, EventManager eventManager, SpriteBatch batch) {
        this.id = id;
        this.resourceManager = resourceManager;
        this.entityManager = entityManager;
        this.eventManager = eventManager;
        this.batch = batch;
    }
    public int getId() {
        return id;
    }
    public void init() {
        // add BackgroundLayer and EntityLayer
        layers.add(new BackgroundLayer(batch, resourceManager,"imgs/background.png"));
        layers.add(new EntityLayer(batch, eventManager, entityManager));
        layers.add(new UILayer(batch));
    }
    public void onEnter() {
        System.out.println("Scene " + getId() + " has been switched and now is active!");
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
