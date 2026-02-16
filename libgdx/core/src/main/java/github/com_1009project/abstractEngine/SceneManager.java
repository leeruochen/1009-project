package github.com_1009project.abstractEngine;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SceneManager implements EventObserver {
    private Map<Integer, Scene> scenes = new HashMap<>();
    private Scene currentScene;
    private AssetManager resourceManager; // reference to resource manager to pass to scenes
    private EntityManager entityManager;
    private EventManager eventManager;
    private SpriteBatch batch;
    private boolean interactPressed = false;

    public SceneManager(AssetManager resourceManager, EntityManager entityManager, EventManager eventManager, SpriteBatch batch) {
        this.resourceManager = resourceManager;
        this.entityManager = entityManager;
        this.eventManager = eventManager;
        this.batch = batch;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    // Load a scene by ID (can be extended to load specific subclasses like TestScene)
    public void loadScene(int id) {
        if (scenes.containsKey(id)) {
            throw new IllegalStateException("Scene with ID " + id + " already loaded.");
        }

        Scene scene;
        if (id == 1) {
            // Example: load TestScene when ID = 1
            scene = new TestScene(id, resourceManager, entityManager, eventManager, batch);
        } else {
            // fallback generic scene
            scene = new TestScene(id, resourceManager, entityManager, eventManager, batch);
        }

        scenes.put(id, scene);
    }

    // Update the current scene
    public void updateScene(float deltaTime) {
        if (currentScene != null) {
            currentScene.update(deltaTime);
        }
    }

    // Render the current scene
    public void renderScene() {
        if (currentScene != null) {
            currentScene.render();
        }
    }

    // Unload a specific scene
    public void unloadScene(int id) {
        Scene scene = scenes.remove(id);
        if (scene != null) {
            scene.dispose();
        }
    }

    // Dispose all scenes
    public void dispose() {
        for (Scene scene : scenes.values()) {
            scene.dispose();
        }
        scenes.clear();
    }

    // Optional: get the current scene
    public Scene getCurrentScene() {
        return currentScene;
    }

    @Override
    public void onNotify(Event event, Boolean up) {
		// Only loop through entities that have explicitly flagged they want input
		for (Entity entity : entityManager.getEntities()) {
			if (entity.isActive()) {
				if (entity.isInputEnabled()) {
					this.changeScene(entity, event, up);
				}
			}
		}
	}

	@Override // this is for handling mouse events
	public void onNotify(Event event, Boolean up, int screenX, int screenY) {

	}
    
    public void changeScene(Entity entity, Event event, boolean isUp) {
        if (entity == null) return;

        // Update input state
        if (!isUp) { // Key Pressed
            switch (event) {
                case PlayerInteract:
                    // Handle interaction logic here if needed // testinggggggggggggggggggg
                    interactPressed = true;
                    break;
            }
        } else { // Key Released
            switch (event) {
                case PlayerInteract:
                    interactPressed = false;
                    break;
            }
        }
    }
}