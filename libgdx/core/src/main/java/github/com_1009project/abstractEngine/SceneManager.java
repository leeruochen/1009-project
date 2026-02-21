package github.com_1009project.abstractEngine;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// import github.com_1009project.logicEngine.Door;
// import github.com_1009project.logicEngine.PauseScene;
// import github.com_1009project.logicEngine.TestScene;
// import github.com_1009project.logicEngine.testEntity;

public class SceneManager implements EventObserver {
    private Map<Integer, Scene> scenes = new HashMap<>();
    private Scene currentScene;
    private AssetManager resourceManager;
    private EntityManager entityManager;
    private EventManager eventManager;
    private SpriteBatch batch;
    private int previousSceneId = 1;

    public SceneManager(AssetManager resourceManager, EntityManager entityManager, EventManager eventManager, SpriteBatch batch) {
        this.resourceManager = resourceManager;
        this.entityManager = entityManager;
        this.eventManager = eventManager;
        this.batch = batch;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void loadScene(int id) {
        if (scenes.containsKey(id)) {
            currentScene = scenes.get(id);
        } else {
            if (id == 99) {
                //currentScene = new PauseScene(id, resourceManager, entityManager, eventManager, batch, this);
            } else if (id == 1) {
                //currentScene = new TestScene(id, resourceManager, entityManager, eventManager, batch);
            } else {
                throw new IllegalArgumentException("Unknown scene ID: " + id);
            }
            scenes.put(id, currentScene);
        }
        currentScene.onEnter();
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

    public void unloadScene(int id) {
        Scene scene = scenes.remove(id);
        if (scene != null) {
            scene.dispose();
        }
    }

    public void dispose() {
        for (Scene scene : scenes.values()) {
            scene.dispose();
        }
        scenes.clear();
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    @Override
    public void onNotify(Event event, Boolean up) {
        if (currentScene == null) return;

        // if (event == Event.GamePause && !up) {
        //     if (currentScene instanceof PauseScene) { 
        //         loadScene(previousSceneId); 
        //     } else {
        //         previousSceneId = currentScene.getId(); 
        //         loadScene(99); 
        //     }
        // }
		// for (Entity entity : entityManager.getEntities()) {
		// 	if (entity.isActive()) {
		// 		if (entity.isInputEnabled()) {
		// 			this.changeScene(entity, event, up);
		// 		}
		// 	}
		// }
	}

	@Override
	public void onNotify(Event event, Boolean up, int screenX, int screenY) {

	}
    
    public void changeScene(Entity entity, Event event, boolean isUp) {
        if (entity == null) return;

        // if (!isUp) {
        //     if (event == Event.PlayerInteract) {
        //         if (entity instanceof testEntity) {
        //             testEntity player = (testEntity) entity;
        //             Door door = player.nearbyDoor;
        //             if (door != null) {
        //                 String dest = door.getDestination();
        //                 if (dest != null && !dest.isEmpty()) {
        //                     player.mapToLoad = dest;
        //                 }
        //             }
        //         }
        //     }
        // } else { // Key Released
        //     if (event == Event.PlayerInteract) {
        //     }
        // }
    }
}