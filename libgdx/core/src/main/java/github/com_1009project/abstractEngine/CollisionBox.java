package github.com_1009project.abstractEngine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// simple collision box entity
public class CollisionBox extends Entity {
    public CollisionBox(float x, float y, float w, float h) {
        super();
        this.position.set(x, y);
        this.size.set(w, h);
        this.createCollisionComponent(w, h);
    }

    @Override
    public void updateMovement(float deltaTime) {}

    @Override
    public void render(SpriteBatch batch) {}
    
    // no onCollision behavior, classes will implement their own collision logic if touching this box
    @Override
    public void onCollision(Entity other) {}
}