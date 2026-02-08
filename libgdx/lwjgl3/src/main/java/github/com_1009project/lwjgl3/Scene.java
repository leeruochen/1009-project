package github.com_1009project.lwjgl3;

import java.util.ArrayList;
import java.util.List;

import github.com_1009project.abstractEngine.ResourceManager;

public class Scene {
    private int id;
    private List<Layer> layers;
    private ResourceManager resourceManager;

    public Scene(int id, ResourceManager resourceManager) {
        this.id = id;
        this.resourceManager = resourceManager;
        this.layers = new ArrayList<>();
    }
    public int getId() {
        return id;
    }
    public void init() {
        // add BackgroundLayer and EntityLayer
        layers.add(new BackgroundLayer(resourceManager));
        layers.add(new EntityLayer(resourceManager));
    }
    public void onEnter() {
        // called when scene becomes active
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
