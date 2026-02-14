package github.com_1009project.abstractEngine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class testEntity extends Entity {
    public boolean hasCollided = false; // Flag for camera shake
    private Texture texture;

    public testEntity(float x, float y, float w, float h, Texture texture) {
        super();
        this.setPosition(x, y);
        this.setSize(w, h);
        this.createCollisionComponent(20, 10, 15, 0);
        this.texture = texture;
        this.setMaxSpeed(300f);
        this.setAcceleration(new Vector2(0,0));
        this.setFriction(0.85f);
        this.setOnGround(true);
    }

    @Override
    public void updateMovement(float deltaTime) {
        Vector2 vel = this.getVelocity();
        
        // for diagonal movement
        if (vel.len() > this.getMaxSpeed()) {
            vel.setLength(this.getMaxSpeed());
        }
        
        // Update position
        this.getPosition().add(vel.x * deltaTime, vel.y * deltaTime);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, this.getPosition().x, this.getPosition().y, this.getSize().x, this.getSize().y);
    }

    @Override
    public void onCollision(Entity other) {
        Rectangle playerRect = this.getCollisionComponent().getBounds();
        Rectangle otherRect = other.getCollisionComponent().getBounds();

        if (playerRect.overlaps(otherRect)) {
            this.setPosition(this.getPreviousPosition().x, this.getPreviousPosition().y);
        }
        this.getCollisionComponent().updateBounds(this.getPosition());

        this.hasCollided = true;
    }
}
