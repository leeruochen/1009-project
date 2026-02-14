package github.com_1009project.abstractEngine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import java.util.ArrayList;
import java.util.List;
//https://libgdx.com/wiki/graphics/2d/tile-maps

public class MapManager extends Layer implements Disposable {

    private final EntityFactory entityFactory;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private float map_scale;

    public MapManager(OrthographicCamera camera, EntityFactory entityFactory) {
        this.map_scale = 1.0f;
        this.camera = camera;
        this.entityFactory = entityFactory;
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

    public void update(float deltaTime) {
    }

    // render the map based on the camera's position and zoom level, so that only the visible portion of the map is rendered
    @Override
    public void render() {
        if (renderer == null) return;
        renderer.setView(camera);
        renderer.render();
    }

    public List<Entity> loadCollisionLayer(String layerName) {
        // get "layerName" layer from the map
        MapLayer collisionLayer = map.getLayers().get(layerName);
        List<Entity> entities = new ArrayList<>();

        // gets every object in the layer, if the object is a rectangle, create a collision box.
        // developers should only use rectangle objects for collision layers.
        if (collisionLayer != null) {
            for (MapObject object : collisionLayer.getObjects()) {
                if (object instanceof RectangleMapObject) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();
                    Entity box = entityFactory.createEntity(EntityType.COLLISION_BOX);

                    // if scale is set, apply it to the rectangle dimensions, and set the position and size of the collision box accordingly
                    float scaledHeight = rect.height * map_scale;
                    float scaledWidth = rect.width * map_scale;
                    float scaledX = rect.x * map_scale;
                    float scaledY = rect.y * map_scale;

                    box.setPosition(scaledX, scaledY);
                    box.setSize(scaledWidth, scaledHeight);

                    // create entity and add to entities list
                    entities.add(box);
                }
            }
        }
        return entities;
    }

    public void dispose() {
        if (renderer != null) renderer.dispose();
    }
}