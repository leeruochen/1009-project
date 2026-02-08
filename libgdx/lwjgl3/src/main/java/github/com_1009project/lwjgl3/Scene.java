package github.com_1009project.lwjgl3;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;

import github.com_1009project.abstractEngine.ResourceManager;

public class Scene {
    private int id;
    private List<Layer> layers = new ArrayList<>();;
    private ResourceManager resourceManager;

    public Scene(int id, ResourceManager resourceManager) {
        this.id = id;
        this.resourceManager = resourceManager;
    }
    public int getId() {
        return id;
    }
    public void init() {
        // add BackgroundLayer and EntityLayer
        layers.add(new BackgroundLayer(resourceManager));
        layers.add(new EntityLayer(resourceManager));
        layers.add(new UILayer());
    }
    public void onEnter() {
        // called when scene becomes active
        for (Layer layer : layers) {
            if (layer instanceof UILayer) {
                Gdx.input.setInputProcessor(((UILayer) layer).getUIManager().getStage());
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
