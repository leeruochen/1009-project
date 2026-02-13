package github.com_1009project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import github.com_1009project.abstractEngine.CameraManager;
import github.com_1009project.abstractEngine.CollisionManager;
import github.com_1009project.abstractEngine.Entity;
import github.com_1009project.abstractEngine.MapManager;
import github.com_1009project.abstractEngine.ResourceManager;
import github.com_1009project.abstractEngine.testEntity;

import java.util.ArrayList;

public class GameMaster extends ApplicationAdapter{
    // private EntityManager em;
    // private SceneManager sm;
    // private EventManager em;
    // private UIManager um;
    private CollisionManager cm;
    private ResourceManager rm;
    private CameraManager camera;
    private MapManager mapManager;
    private SpriteBatch batch;
    private testEntity player;

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
        mapManager = new MapManager();

        // set up camera with max world bounds
        camera = new CameraManager(width, height);
        camera.setBounds(4000, 4000);

        // load assets
        loadAssets();
        rm.update();
        rm.getManager().finishLoading();

        // set up the map
        // scale the map if needed, if textures look small
        // load the map from file
        // parse collision layer and add collision boxes to entities list, "Collision" can be changed to how the developer wants to name it in Tiled
        mapManager.setScale(4.0f); 
        mapManager.setMap(rm.getTiledMap("maps/test.tmx"));
        mapManager.render();
        mapManager.parseCollisionLayer(entities, "Collision");

        // example of creating an entity and making it the target of the camera
        player = new testEntity(200, 200, 50, 50, rm.getTexture("imgs/boy_down_1.png"));
        entities.add(player);
        camera.setTarget(player);
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
        for (Entity e : entities) {e.update(deltaTime);}

        // update collisions
        cm.updateCollision(entities);

        // update camera position
        camera.cameraUpdate(deltaTime);

        // render entities and map based on camera position
        mapManager.cameraView(camera.camera);

        if (player.hasCollided) {
            camera.shake(2f, 0.2f);
            player.hasCollided = false;
        }

        // batch will render entities according to cameraPosition
        batch.setProjectionMatrix(camera.camera.combined);
        batch.begin();
        for (Entity e : entities) {e.render(batch);}
        batch.end();
    }

    private void loadAssets() {
        // load textures
        rm.loadTexture("imgs/boy_down_1.png");

        // load tmx maps
        rm.loadTiledMap("maps/test.tmx");
    }

    @Override
    public void dispose() {
        rm.dispose();
        batch.dispose();
    }
}

