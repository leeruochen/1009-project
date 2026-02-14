package github.com_1009project.abstractEngine;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.assets.AssetManager;

public class SceneManager {
    private Map<Integer, Scene> scenes = new HashMap<>();
    private Scene currentScene;
    private AssetManager resourceManager; // Reference to ResourceManager
    private EntityManager entityManager;
    private EventManager eventManager;

    public SceneManager(AssetManager resourceManager, EntityManager entityManager, EventManager eventManager) {
        this.resourceManager = resourceManager;
        this.entityManager = entityManager;
        this.eventManager = eventManager;
    }
    public void loadScene(int id) {
        Scene scene = new Scene(id, resourceManager, entityManager, eventManager);
        scene.init();
        scenes.put(id, scene);
    }
    public void switchScene(int id) {
        if (currentScene != null) {
            currentScene.dispose();
        }
        currentScene = scenes.get(id);
        if (currentScene != null) {
            currentScene.init();
        }
    }
    public void updateScene(float deltaTime) {
        if (currentScene != null) {
            currentScene.update(deltaTime);
        }
    }
    public void renderScene() {
        if (currentScene != null) {
            currentScene.render();
        }
    }

    public void dispose() {
        for (Scene scene : scenes.values()) {
            scene.dispose();
        }
        scenes.clear();
    }
}
