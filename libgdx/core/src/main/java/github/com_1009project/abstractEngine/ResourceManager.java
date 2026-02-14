// package github.com_1009project.abstractEngine;

// will be deleted, still considering, kinda useless

// import com.badlogic.gdx.assets.AssetManager;
// import com.badlogic.gdx.maps.tiled.TiledMap;
// import com.badlogic.gdx.maps.tiled.TmxMapLoader;
// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.audio.Sound;
// import com.badlogic.gdx.audio.Music;
// import com.badlogic.gdx.utils.Disposable;
// //https://libgdx.com/wiki/managing-your-assets

// public class ResourceManager implements Disposable {
//     private final AssetManager manager;

//     public ResourceManager() {
//         manager = new AssetManager();
//         // set loader for tmx maps
//         manager.setLoader(TiledMap.class, new TmxMapLoader());
//     }

//     // loaders queues the resources for loading
//     // when loading the resources, we have to manually type the file path for every resource
//     // we can use a txt file to list all resources if there are too many resources
//     // or we can use a loop to load resources if they follow a naming convention
//     public void loadTexture(String filePath) {
//         manager.load(filePath, Texture.class);
//     }

//     public void loadSound(String filePath) {
//         manager.load(filePath, Sound.class);
//     }

//     public void loadMusic(String filePath) {
//         manager.load(filePath, Music.class);
//     }

//     public void loadTiledMap(String filePath) {
//         TmxMapLoader.Parameters params = new TmxMapLoader.Parameters();
//         params.projectFilePath = "maps/test.tiled-project";
//         manager.load(filePath, TiledMap.class, params);
//     }

//     // getters retrieve the loaded resources
//     public Texture getTexture(String filePath) {
//         return manager.get(filePath, Texture.class);
//     }

//     public Sound getSound(String filePath) {
//         return manager.get(filePath, Sound.class);
//     }

//     public Music getMusic(String filePath) {
//         return manager.get(filePath, Music.class);
//     }

//     public TiledMap getTiledMap(String filePath) {
//         return manager.get(filePath, TiledMap.class);
//     }

//     // update downloads the queued resources
//     public boolean update() {
//         return manager.update();
//     }

//     // getProgress returns the loading progress as a float between 0 and 1
//     public float getProgress() {
//         return manager.getProgress();
//     }

//     public AssetManager getManager() {
//         return manager;
//     }

//     // to dispose of the AssetManager and free resources
//     @Override
//     public void dispose() {
//         manager.dispose();
//     }
// }
