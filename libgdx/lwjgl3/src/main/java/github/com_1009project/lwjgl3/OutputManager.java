package github.com_1009project.lwjgl3;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

public class OutputManager implements EventObserver{
    private HashMap<Event, Sound> soundMap;
    private Music curMusic;
    private void playMusic(String path){
        if (curMusic != null) curMusic.stop();
        curMusic = Gdx.audio.newMusic(Gdx.files.internal(path));
        curMusic.setLooping(true);
        curMusic.play();
    }
    
    public void loadSound(Event event, String filePath){
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(filePath));
        soundMap.put(event, sound);
    }

    public void dispose(){
        for (Sound sound: soundMap.values()){
            sound.dispose();
        }
        if (curMusic != null){curMusic.dispose();}
    }

    public OutputManager(){
        soundMap = new HashMap<Event, Sound>();
    }
    @Override
    public void onNotify(Event event, Boolean up){
        if (soundMap.containsKey(event)){soundMap.get(event).play(1.0f);}
        switch (event) {
            //Add misc sound cases here
            case PlayerJump:
                playMusic("temppath"); //change music path
                break;
        
            default:

                break;
        }
    }
    @Override
    public void onNotify(Event event, Boolean up, int screenX, int screenY){onNotify(event, up);}
}
