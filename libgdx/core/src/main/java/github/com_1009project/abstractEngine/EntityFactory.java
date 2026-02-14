package github.com_1009project.abstractEngine;

public class EntityFactory {
    public Entity createEntity(EntityType type) {
        switch (type) {
            case COLLISION_BOX:
                return new CollisionBox(0, 0, 0, 0);
            // Add cases for other entity types as needed
            default:
                return null;
        }
    }
}
