package github.com_1009project.abstractEngine;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class GameMaster extends ApplicationAdapter{
    // private EntityManager em;
    // private SceneManager sm;
    // private EventManager em;
    // private UIManager um;
    private CollisionManager cm;
    private ResourceManager rm;
    private SpriteBatch batch;

    private ArrayList<Entity> entities;

    // this is our set up, to initialize our managers and variables
    @Override
    public void create() {
        cm = new CollisionManager(128);
        rm = new ResourceManager();
        batch = new SpriteBatch();
        entities = new ArrayList<>();
    }

    // our main gameplay/simulation loop
    // the loop should be process input -> update -> collision -> render
    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);

        // time between each frame, this ensures same speed on different devices
        float deltaTime = Gdx.graphics.getDeltaTime();

        // input manager would go here

        // update all entities
        for (Entity e : entities) {
                e.update(deltaTime);
        }

        // update collisions
        cm.updateCollision(entities);

        // render all entities
        for (Entity e : entities) {
                e.render(batch);
        }
    }

    @Override
    public void dispose() {
        rm.dispose();
        batch.dispose();
    }
}
