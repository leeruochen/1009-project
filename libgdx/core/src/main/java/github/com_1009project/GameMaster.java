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
import github.com_1009project.abstractEngine.EntityType;
import github.com_1009project.abstractEngine.Entity;
import github.com_1009project.abstractEngine.testEntity;
import github.com_1009project.abstractEngine.EntityFactory;
import github.com_1009project.abstractEngine.EventManager;
import github.com_1009project.abstractEngine.MovementManager;
import github.com_1009project.abstractEngine.SceneManager;
import github.com_1009project.abstractEngine.UIFactory;
import github.com_1009project.abstractEngine.Event;
import com.badlogic.gdx.Input;

import java.util.List;

public class GameMaster extends ApplicationAdapter{
    private EntityManager entityManager;
    private SceneManager sm;
    private EventManager eventManager;
    private MovementManager movementManager;
    private UIFactory uf;
    private CollisionManager collisionManager;
    private AssetManager assetManager;
    private CameraManager camera;
    private MapManager mapManager;
    private EntityFactory entityFactory;
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
        collisionManager = new CollisionManager(128);
        batch = new SpriteBatch();
        assetManager = new AssetManager();
        entityFactory = new EntityFactory();
        entityManager = new EntityManager(entityFactory, assetManager);
        eventManager = new EventManager();
        movementManager = new MovementManager();
        sm = new SceneManager(assetManager, entityManager, eventManager);
        

        // set up camera with max world bounds
        camera = new CameraManager(width, height);
        camera.setBounds(4000, 4000);

        // load assets
        // update the asset manager to actually load the assets
        // finishLoading() makes sure all assets are loaded before proceeding
        loadAssets();
        assetManager.update();
        assetManager.finishLoading();

        // set up the map
        // scale the map if needed, if textures look small
        // load the map from file
        // parse collision layer and add collision boxes to entities list, "Collision" can be changed to how the developer wants to name it in Tiled
        mapManager = new MapManager(entityManager);
        mapManager.setScale(4.0f); 
        mapManager.setMap(assetManager.get("maps/test.tmx", TiledMap.class));
        mapManager.loadLayer("COLLISION_BOX");
        mapManager.loadLayer("DOOR");

        // example of creating an entity and making it the target of the camera
        player = (testEntity) entityManager.createEntity(EntityType.PLAYER);
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
        collisionManager.updateCollision(entityManager.getEntities());

        // update camera position
        camera.cameraUpdate(deltaTime);

        // render entities and map based on camera position
        mapManager.render(camera.getCamera());

        if (player.hasCollided) {
            camera.shake(2f, 0.2f);
            player.hasCollided = false;
        }

        // batch will render entities according to cameraPosition
        batch.setProjectionMatrix(camera.getCamera().combined);
        batch.begin();
        entityManager.render(batch);
        batch.end();
    }

    private void loadAssets() {
        // load textures
        assetManager.load("imgs/boy_down_1.png", Texture.class);

        // load tmx maps, params required to prevent errors
        assetManager.setLoader(TiledMap.class, new TmxMapLoader());
        TmxMapLoader.Parameters params = new TmxMapLoader.Parameters();
        params.projectFilePath = "maps/test.tiled-project";
        assetManager.load("maps/test.tmx", TiledMap.class, params);
    }

    @Override
    public void resize(int width, int height) {
        camera.resize(width, height);
        mapManager.resize(width, height);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        batch.dispose();
        mapManager.dispose();
        collisionManager.dispose();
        sm.dispose();
    }
}

