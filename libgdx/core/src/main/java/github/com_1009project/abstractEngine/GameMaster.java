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
    private CameraManager camera;
    private SpriteBatch batch;

    // camera properties
    private int width, height;

    private ArrayList<Entity> entities;

    public GameMaster(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // this is our set up, to initialize our managers and variables
    @Override
    public void create() {
        cm = new CollisionManager(128);
        rm = new ResourceManager();
        batch = new SpriteBatch();
        entities = new ArrayList<>();

        // set up camera
        camera = new CameraManager(width, height);

        // example of creating an entity and making it the target of the camera
        // Entity player = new PlayerEntity(100, 100, 32, 32, rm);
        // entities.add(player);
        // camera.setTarget(player);
        // this makes the camera follow the player entity
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

        // update camera position
        camera.cameraUpdate(deltaTime);
        // batch will render entities according to cameraPosition
        batch.setProjectionMatrix(camera.camera.combined);

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
