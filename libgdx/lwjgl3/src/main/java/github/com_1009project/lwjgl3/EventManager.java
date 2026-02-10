package github.com_1009project.lwjgl3;

import java.util.HashMap;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Array;

public class EventManager extends InputAdapter{
    private HashMap<Integer, Event> keyMappings;
    private Array<EventObserver> observers;
    private void notifyObservers(Event event, Boolean up){
        for (int i = 0; i < observers.size; i++){
            observers.get(i).onNotify(event, up);
        }
    }

    public EventManager(){
        observers = new Array<EventObserver>();
        keyMappings = new HashMap<Integer, Event>();
    }
    
    @Override
    public boolean keyUp(int keycode) {
        if (keyMappings.containsKey(keycode)){
            notifyObservers(keyMappings.get(keycode), true);
            return true;
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keyMappings.containsKey(keycode)){
            notifyObservers(keyMappings.get(keycode), false);
            return true;
        }
        return false;
    }

    public void eventTrigger(Event event){
        notifyObservers(event, null);
    }

    public void mapKey(int keycode, Event event){
        keyMappings.put(keycode, event);
    }

    public void addObserver(EventObserver observer){
        observers.add(observer);
    }
}
