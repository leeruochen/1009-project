package github.com_1009project.abstractEngine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.assets.AssetManager;

public class EntityFactory {
    public Entity createEntity(EntityType type, AssetManager assetManager) {
        switch (type) {
            // Add cases for other entity types as needed
            case PLAYER:
                return new testEntity(200, 200, 50, 50, assetManager.get("imgs/boy_down_1.png", Texture.class));
            default:
                return null;
        }
    }

    public Entity createEntity(EntityType type) { //overloaded method for collision boxes that don't need asset manager
        switch (type) {
            case COLLISION_BOX:
                return new CollisionBox(0, 0, 0, 0);
            // Add cases for other entity types as needed
            default:
                return null;
        }
    }
}
