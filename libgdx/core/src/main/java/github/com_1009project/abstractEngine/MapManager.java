package github.com_1009project.abstractEngine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.Gdx;
//https://libgdx.com/wiki/graphics/2d/tile-maps

public class MapManager extends Layer implements Disposable {

    private EntityManager entityManager;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private float map_scale;
    private OrthographicCamera staticCam;

    public MapManager(EntityManager entityManager) {
        this.map_scale = 1.0f;
        this.entityManager = entityManager;
        this.staticCam = new OrthographicCamera();
        this.staticCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void setScale(float scale) {
        // set scale if needed
        this.map_scale = scale;
    }

    public void setMap(TiledMap map) {
        // set the map
        this.map = map;
        this.renderer = new OrthogonalTiledMapRenderer(map, map_scale);
    }
    
    public TiledMap getMap() {
        return this.map;
    }

    public void update(float deltaTime) {
    }

    // render static map
    @Override
    public void render() {
        if (renderer == null) return;
        renderer.setView(staticCam);
        renderer.render();
    }

    // render dynamic map with camera
    public void render(OrthographicCamera camera) {
        if (renderer == null) return;
        renderer.setView(camera);
        renderer.render();
    }

    public void loadEntities(Entity existingPlayer) {
        Iterable<MapLayer> mapLayers = map.getLayers();
        
        for (MapLayer layer : mapLayers) {
            for (MapObject object : layer.getObjects()) {
                String type = object.getProperties().get("type", String.class);
                
                // If this is a player spawn point and we have an existing player, reposition it
                if ("Player".equals(type) && existingPlayer != null) {
                    RectangleMapObject rectObj = (RectangleMapObject) object;
                    Rectangle rect = rectObj.getRectangle();
                    existingPlayer.setPosition(rect.x * map_scale, rect.y * map_scale);
                    existingPlayer.setSize(rect.width * map_scale, rect.height * map_scale);
   
                } 
                else {
                
                    Entity entity = entityManager.createEntity(object, map_scale);
                    
                    if (entity instanceof Door) {
                        if (object.getProperties().containsKey("targetMap")) {
                            ((Door) entity).setDestination(object.getProperties().get("targetMap", String.class));
                        }
                    }
                    
                    if (entity instanceof testEntity) {
                        existingPlayer = entity;
                    }
                }
            }
        }
    }

    public OrthographicCamera getCamera() {
        return staticCam;
    }

    public void resize(int width, int height) {
        // update camera viewport on window resize
        this.staticCam.setToOrtho(false, width, height);
        this.staticCam.update();
    }

    public void dispose() {
        if (renderer != null) renderer.dispose();
    }
}