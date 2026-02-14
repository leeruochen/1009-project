// package github.com_1009project.abstractEngine;

// import java.util.HashMap;
// import java.util.Map;

// public class SceneManager {
//     private Map<Integer, Scene> scenes = new HashMap<>();
//     private Scene currentScene;
//     private ResourceManager resourceManager; // Reference to ResourceManager

//     public SceneManager(ResourceManager resourceManager) {
//         this.resourceManager = resourceManager;
//     }
//     public void loadScene(int id) {
//         Scene scene = new Scene(id, resourceManager);
//         scene.init();
//         scenes.put(id, scene);
//     }
//     public void switchScene(int id) {
//         if (currentScene != null) {
//             currentScene.dispose();
//         }
//         currentScene = scenes.get(id);
//         if (currentScene != null) {
//             currentScene.init();
//         }
//     }
//     public void updateScene(float deltaTime) {
//         if (currentScene != null) {
//             currentScene.update(deltaTime);
//         }
//     }
//     public void renderScene() {
//         if (currentScene != null) {
//             currentScene.render();
//         }
//     }
//     public void dispose() {
//         for (Scene scene : scenes.values()) {
//             scene.dispose();
//         }
//         scenes.clear();
//     }
// }
