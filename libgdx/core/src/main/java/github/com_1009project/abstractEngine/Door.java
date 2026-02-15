package github.com_1009project.abstractEngine;


public class Door extends CollisionBox {

    public Door(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void onCollision(Entity other) {
    }
}
