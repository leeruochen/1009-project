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
import github.com_1009project.abstractEngine.EventManager;
import github.com_1009project.abstractEngine.MovementManager;
import github.com_1009project.abstractEngine.SceneManager;
import github.com_1009project.abstractEngine.UIFactory;
import github.com_1009project.abstractEngine.Event;
import com.badlogic.gdx.Input;

public class GameMaster extends ApplicationAdapter{
    // all attributes and managers should be declared here, and initialized in the create() method, this is where we will manage all our game state and logic, and the main game loop will be in the render() method
    private EntityManager entityManager;
    private SceneManager sm;
    private EventManager eventManager;
    private MovementManager movementManager;
    private UIFactory uf;
    private CollisionManager collisionManager;
    private AssetManager assetManager;
    private CameraManager camera;
    private MapManager mapManager;
    private SpriteBatch batch;

    // camera properties
    private int width, height;

    public GameMaster(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // this is our set up, to initialize our managers and variables
    @Override
    public void create() {
        // initialize all managers and variables here
        collisionManager = new CollisionManager(128);
        batch = new SpriteBatch();
        assetManager = new AssetManager();
        entityManager = new EntityManager(assetManager);
        eventManager = new EventManager();
        movementManager = new MovementManager(entityManager);
        sm = new SceneManager(assetManager, entityManager, eventManager, batch);

        // camera initialization and set up
        // camera = new CameraManager(width, height);
        // camera.setBounds(4000, 4000);

        // an example of how to set up the asset manager
        // update the asset manager to actually load the assets
        // finishLoading() makes sure all assets are loaded before proceeding
        // loadAssets();
        // assetManager.update();
        // assetManager.finishLoading();

        // map manager set up
        // scale the map if needed, if textures look small
        // load the map from file
        // parse collision layer and add collision boxes to entities list, "Collision" can be changed to how the developer wants to name it in Tiled
        // mapManager = new MapManager(entityManager);
        // mapManager.setScale(4.0f); 
        // mapManager.render(camera.getCamera());
        // mapManager.loadEntities();

        // camera target set up, this can be optional if developer wants static camera.
        // an example of how to set up a player entity and set the camera to follow it
        // for (Entity entity : entityManager.getEntities()) {
        //     if (entity instanceof testEntity) {
        //         player = (testEntity) entity;
        //         break;
        //     }
        // }
        // camera.setTarget(player);

        // Event Manager initialization and set up
        // we also add the observers here
		// eventManager.addObserver(movementManager);
        // eventManager.addObserver(sm);

		// we can set key mappings here
		// eventManager.mapKey(Input.Keys.W, Event.PlayerUp);
		// eventManager.mapKey(Input.Keys.S, Event.PlayerDown);
		// eventManager.mapKey(Input.Keys.A, Event.PlayerLeft);
		// eventManager.mapKey(Input.Keys.D, Event.PlayerRight);
		// eventManager.mapKey(Input.Keys.RIGHT, Event.PlayerRight);
		// eventManager.mapKey(Input.Keys.LEFT, Event.PlayerLeft);
		// eventManager.mapKey(Input.Keys.SPACE, Event.PlayerJump);
        // eventManager.mapKey(Input.Keys.E, Event.PlayerInteract);
        // eventManager.mapKey(Input.Keys.ESCAPE, Event.GamePause);

		// Register input processor
        // this is required for the event manager to receive input events, the event manager will then notify its observers (like movementManager and SceneManager) of the events
		// Gdx.input.setInputProcessor(eventManager);
    }

    // the main gameplay/simulation loop should be in the render method, this is called continuously by libGDX, and is where we will update our game state and render everything to the screen
    // the loop should be process input -> update all managers/entities -> collision -> render
    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);

        // time between each frame, this ensures same speed on different devices
        // float deltaTime = Gdx.graphics.getDeltaTime();

        // update all entities
        // entityManager.update(deltaTime);

        // update collisions
        // collisionManager.updateCollision(entityManager.getEntities());

        // update camera position
        // camera.cameraUpdate(deltaTime);

        // render entities and map based on camera position
        // mapManager.render(camera.getCamera());

        // batch will render entities according to cameraPosition
        // batch.setProjectionMatrix(camera.getCamera().combined);
        // batch.begin();
        // entityManager.render(batch);
        // batch.end();
    }

    // an example of how to use the asset manager to load assets, this can be expanded to load more assets as needed
    // can also use a json file or other data file to specify assets to load, and parse that file in this method to load assets in bulk
    private void loadAssets() {
        // load textures
        // assetManager.load("example.jpg", Texture.class);
    }

    // resize is called whenever ApplicationAdapter detects a change in screen size, this can be used to adjust the camera viewport and other properties as needed
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
