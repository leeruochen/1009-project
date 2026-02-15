package github.com_1009project.abstractEngine;

public class Door extends CollisionBox {

    public Door(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void onCollision(Entity other) {
        // Implement door-specific collision behavior here, e.g., transition to another map or level
        System.out.println("Door collided with: " + other);
        // Code for map change
    }
}
