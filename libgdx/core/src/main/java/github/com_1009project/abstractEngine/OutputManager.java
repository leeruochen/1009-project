// package github.com_1009project.abstractEngine;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;

// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.audio.Sound;
// import com.badlogic.gdx.audio.Music;

// public class OutputManager implements EventObserver{
//     private ResourceManager resourceManager;
//     private HashMap<Event, String> soundMap;
//     private HashMap<Event, String> musicMap;
//     private Music curMusic;
//     private void playMusic(Event event){
//         if (curMusic != null) curMusic.stop();
//         curMusic = resourceManager.getMusic(musicMap.get(event));
//         curMusic.setLooping(true);
//         curMusic.play();
//     }

//     private void playSound(Event event){
//         resourceManager.getSound(soundMap.get(event)).play(1.0f);
//     }

//     public void loadSound(Event event, String filePath){
//         resourceManager.loadSound(filePath);
//         soundMap.put(event, filePath);
//     }

//     public void loadMusic(Event event, String filePath){
//         resourceManager.loadMusic(filePath);
//         musicMap.put(event, filePath);
//     }
    
//     public void dispose(){
//         // all other disposal is offloaded to ResourceManager
//         if (curMusic != null){curMusic.dispose();}
//     }

//     public OutputManager(ResourceManager resourceManager){
//         soundMap = new HashMap<Event, String>();
//         musicMap = new HashMap<Event, String>();
//     }

//     @Override
//     public void onNotify(Event event, Boolean up){
//         if (soundMap.containsKey(event)){playSound(event);}
//         playMusic(event);
//     }

//     @Override
//     public void onNotify(Event event, Boolean up, int screenX, int screenY){onNotify(event, up);}
// }
