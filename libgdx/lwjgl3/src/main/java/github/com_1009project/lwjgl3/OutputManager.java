package github.com_1009project.lwjgl3;

import java.util.HashMap;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.lwjgl3.audio.Mp3.Music;

public class OutputManager implements EventObserver{
    private HashMap<Event, Sound> soundMap;
    private Music curMusic;
    private void playMusic(String path){
        return;
    }
    
    @Override
    public void onNotify(Event event, Boolean up){return;}
    @Override
    public void onNotify(Event event, Boolean up, int screenX, int screenY){return;}
}
