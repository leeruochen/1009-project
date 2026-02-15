package github.com_1009project.abstractEngine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.assets.AssetManager;

public class EntityFactory {

    private final AssetManager assetManager;

    public EntityFactory(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public Entity createEntity(EntityType type) {
        switch (type) {
            // Add cases for other entity types as needed
            case PLAYER:
                return new testEntity(200, 200, 50, 50, assetManager.get("imgs/boy_down_1.png", Texture.class));
            default:
                return null;
        }
    }

    public Entity createEntity(MapObject object, float mapScale) {

        String type = object.getProperties().get("type", String.class);

        if (type!= null) {
            System.out.println("Creating entity of type: " + type);
        } 

        RectangleMapObject rectObj = (RectangleMapObject) object;
        Rectangle rect = rectObj.getRectangle();

        float x = rect.x * mapScale;
        float y = rect.y * mapScale;
        float width = rect.width * mapScale;
        float height = rect.height * mapScale;

        if (type == null){
            type = "CollisionBox"; // default to CollisionBox if no type is specified
        }

        switch (type) {

            case "Player":
                return new testEntity(x, y, width, height, assetManager.get("imgs/boy_down_1.png", Texture.class));

            case "Door":
                // String targetMap = object.getProperties().get("targetMap", String.class);
                // int spawnX = object.getProperties().get("spawnX", Integer.class);
                // int spawnY = object.getProperties().get("spawnY", Integer.class);

                return new Door(x, y, width, height);

            case "CollisionBox":
                return new CollisionBox(x, y, width, height);
            // Add cases for other entity types as needed
            default:
                System.out.println("Unknown entity type: " + type);
                return null;
        }
    }
}
