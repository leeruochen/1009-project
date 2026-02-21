package github.com_1009project.logicEngine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;

import github.com_1009project.abstractEngine.Entity;

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

    public Entity createEntity(MapObject object, float mapScale, Entity existingPlayer) {
        String type = object.getProperties().get("type", String.class);
        Entity entity = null;

        // Create collision box for the map object
        RectangleMapObject rectObj = (RectangleMapObject) object;
        Rectangle rect = rectObj.getRectangle();

        float x = rect.x * mapScale;
        float y = rect.y * mapScale;
        float width = rect.width * mapScale;
        float height = rect.height * mapScale;

        // Demo code as most of the map objects do not have classes
        if (type == null){
            type = "CollisionBox"; // default to CollisionBox if no type is specified
        }

        if ("Player".equals(type) && existingPlayer != null) {
            existingPlayer.setPosition(rect.x * mapScale, rect.y * mapScale);
            existingPlayer.setSize(rect.width * mapScale, rect.height * mapScale);
            return existingPlayer;
        } else {
            switch (type) {
                case "Player":
                    if ("Player".equals(type) && existingPlayer != null) {
                    existingPlayer.setPosition(rect.x * mapScale, rect.y * mapScale);
                    existingPlayer.setSize(rect.width * mapScale, rect.height * mapScale);
                    return existingPlayer;
                    } else {
                        entity = new testEntity(x, y, width, height, assetManager.get("imgs/boy_down_1.png", Texture.class));
                    }
                    
                    System.out.println("Creating entity of type: " + type);
                    break;

                case "Door":
                    entity = new Door(x, y, width, height);
                    if (object.getProperties().containsKey("targetMap")) {
                        ((Door) entity).setDestination(object.getProperties().get("targetMap", String.class));
                    }
                    System.out.println("Creating entity of type: " + type);
                    break;

                case "CollisionBox":
                    entity = new CollisionBox(x, y, width, height);
                    System.out.println("Creating entity of type: " + type);
                    break;
                // Add cases for other entity types as needed
                default:
                    System.out.println("Unknown entity type: " + type);
                    break;
            }
        }
        return entity;
    }
}