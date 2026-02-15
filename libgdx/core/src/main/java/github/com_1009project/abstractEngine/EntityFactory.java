package github.com_1009project.abstractEngine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.assets.AssetManager;

public class EntityFactory {

    private AssetManager assetManager;

    public EntityFactory(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    // public Entity createEntity(EntityType type) {
    //     switch (type) {
    //         // Add cases for other entity types as needed
    //         case PLAYER:
    //             return new testEntity(200, 200, 50, 50, assetManager.get("imgs/boy_down_1.png", Texture.class));
    //         default:
    //             return null;
    //     }
    // }

    // public Entity createEntity(EntityType type, float x, float y, float width, float height) { //overloaded method for collision boxes that don't need asset manager
    //     switch (type) {
    //         case COLLISION_BOX:
    //             return new CollisionBox(x, y, width, height);
    //         // Add cases for other entity types as needed
    //         default:
    //             return null;
    //     }
    // }

    public Entity createEntity(MapObject object) {

        String type = object.getProperties().get("type", String.class);

        if (type!= null) {
            System.out.println("Creating entity of type: " + type);
        } 

        // if (type == null) {
        //     System.err.println("ERROR: MapObject has no 'class' property. Object name: " + object.getName());
        //     System.err.println("Available properties: " + object.getProperties().getKeys());
        //     return null;
        // }

        RectangleMapObject rectObj = (RectangleMapObject) object;
        Rectangle rect = rectObj.getRectangle();

        float x = rect.x;
        float y = rect.y;
        float width = rect.width;
        float height = rect.height;

        if (type == null){
            type = "CollisionBox"; // default to CollisionBox if no type is specified
        }

        switch (type) {

            case "Player":
                return new testEntity(x, y, 50, 50, assetManager.get("imgs/boy_down_1.png", Texture.class));

            case "Door":
                // String targetMap = object.getProperties().get("targetMap", String.class);
                // int spawnX = object.getProperties().get("spawnX", Integer.class);
                // int spawnY = object.getProperties().get("spawnY", Integer.class);

                return new Door(x, y, width, height);

            case "CollisionBox":
                return new CollisionBox(x, y, width, height);

            default:
                System.out.println("Unknown entity type: " + type);
                return null;
        }
    }
}
