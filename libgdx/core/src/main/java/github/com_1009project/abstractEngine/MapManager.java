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
//https://libgdx.com/wiki/graphics/2d/tile-maps

public class MapManager implements Disposable {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private float map_scale;

    public MapManager() {
        this.map_scale = 1.0f;
    }

    public void setScale(float scale) {
        // set scale if needed
        this.map_scale = scale;
    }

    public void loadMap(TiledMap map) {
        // load map given
        this.map = map;

        // OrthogonalTiledMapRenderer renders top-down maps
        renderer = new OrthogonalTiledMapRenderer(map, map_scale);
    }

    public void parseCollisionLayer(ArrayList<Entity> entities, String layerName) {
        // get "layerName" layer from the map
        MapLayer collisionLayer = map.getLayers().get(layerName);

        // gets every object in the layer, if the object is a rectangle, create a collision box.
        // developers should only use rectangle objects for collision layers.
        if (collisionLayer != null) {
            for (MapObject object : collisionLayer.getObjects()) {
                if (object instanceof RectangleMapObject) {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();

                    // if scale is set, apply it to the rectangle dimensions
                    float scaledWidth = rect.width * map_scale;
                    float scaledHeight = rect.height * map_scale;
                    float scaledX = rect.x * map_scale;
                    float scaledY = rect.y * map_scale;

                    // create entity and add to entities list
                    entities.add(new CollisionBox(scaledX, scaledY, scaledWidth, scaledHeight));
                }
            }
        }
    }

    public void render(OrthographicCamera camera) {
        if (renderer == null) return;
        renderer.setView(camera);
        renderer.render();
    }

    public void dispose() {
        if (map != null) map.dispose();
        if (renderer != null) renderer.dispose();
    }
}