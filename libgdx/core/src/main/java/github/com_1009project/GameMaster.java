package github.com_1009project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.graphics.Texture;

import github.com_1009project.abstractEngine.CameraManager;
import github.com_1009project.abstractEngine.CollisionManager;
import github.com_1009project.abstractEngine.MapManager;
import github.com_1009project.abstractEngine.EntityManager;
import github.com_1009project.abstractEngine.Entity;
import github.com_1009project.abstractEngine.testEntity;

import java.util.ArrayList;
import java.util.List;

public class GameMaster extends ApplicationAdapter{
    private EntityManager entityManager;
    // private SceneManager sm;
    private EventManager eventManager;
    private MovementManager movementManager;
    // private UIManager um;
    private CollisionManager cm;
    private AssetManager am;
    private CameraManager camera;
    private MapManager mapManager;
    private SpriteBatch batch;
    private testEntity player;

    // camera properties
    private int width, height;

    public GameMaster(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // this is our set up, to initialize our managers and variables
    @Override
    public void create() {
        cm = new CollisionManager(128);
        batch = new SpriteBatch();
        am = new AssetManager();
        entityManager = new EntityManager(new EntityFactory());
        

        // set up camera with max world bounds
        camera = new CameraManager(width, height);
        camera.setBounds(4000, 4000);

        // load assets
        // update the asset manager to actually load the assets
        // finishLoading() makes sure all assets are loaded before proceeding
        loadAssets();
        am.update();
        am.finishLoading();

        // set up the map
        // scale the map if needed, if textures look small
        // load the map from file
        // parse collision layer and add collision boxes to entities list, "Collision" can be changed to how the developer wants to name it in Tiled
        mapManager = new MapManager(camera.getCamera());
        mapManager.setScale(4.0f); 
        mapManager.setMap(am.get("maps/test.tmx", TiledMap.class));
        List<Entity> collisionLayer = mapManager.parseCollisionLayer("Collision");
        entityManager.addEntities(collisionLayer);

        // example of creating an entity and making it the target of the camera
        player = new testEntity(200, 200, 50, 50, am.get("imgs/boy_down_1.png", Texture.class));
        entityManager.createEntity(player);
        // this makes the camera follow the player entity
        camera.setTarget(player);

        		//eventmanager adds entityManager as an event observer
		eventManager.addObserver(entityManager);
		
		//entitymanager and movementmanager connected (aggregation relationship)
		entityManager.setMovementManager(movementManager);

		//key mappings for eventManager
		eventManager.mapKey(Input.Keys.W, Event.PlayerUp);
		eventManager.mapKey(Input.Keys.S, Event.PlayerDown);
		eventManager.mapKey(Input.Keys.A, Event.PlayerLeft);
		eventManager.mapKey(Input.Keys.D, Event.PlayerRight);
		eventManager.mapKey(Input.Keys.RIGHT, Event.PlayerRight);
		eventManager.mapKey(Input.Keys.LEFT, Event.PlayerLeft);
		eventManager.mapKey(Input.Keys.SPACE, Event.PlayerJump);

		// Register input processor
		Gdx.input.setInputProcessor(eventManager);
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
        entityManager.update(deltaTime);

        // update collisions
        cm.updateCollision(entityManager.getEntities());

        // update camera position
        camera.cameraUpdate(deltaTime);

        // render entities and map based on camera position
        mapManager.render();

        if (player.hasCollided) {
            camera.shake(2f, 0.2f);
            player.hasCollided = false;
        }

        // batch will render entities according to cameraPosition
        batch.setProjectionMatrix(camera.getCamera().combined);
        batch.begin();
        for (Entity e : em.getEntities()) {e.render(batch);}
        //ruo chen can try use entityManager.render here thanks
        batch.end();
    }

    private void loadAssets() {
        // load textures
        am.load("imgs/boy_down_1.png", Texture.class);

        // load tmx maps, params required to prevent errors
        TmxMapLoader.Parameters params = new TmxMapLoader.Parameters();
        params.projectFilePath = "maps/test.tiled-project";
        am.load("maps/test.tmx", TiledMap.class, params);
    }

    @Override
    public void dispose() {
        am.dispose();
        batch.dispose();
    }
}

