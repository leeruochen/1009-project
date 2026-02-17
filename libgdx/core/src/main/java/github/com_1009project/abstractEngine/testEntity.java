package github.com_1009project.abstractEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input;

public class testEntity extends Entity {
    public boolean hasCollided = false; // Flag for camera shake
    private Texture texture;
    public String mapToLoad = null;

    public testEntity(float x, float y, float w, float h, Texture texture) {
        super();
        this.setPosition(x, y);
        this.setSize(w, h);
        this.createCollisionComponent(44, 10, 10, 0);
        this.texture = texture;
        this.getMovementComponent().setMaxSpeed(1000f);
        this.getMovementComponent().setAcceleration(new Vector2(0,0));
        this.getMovementComponent().setFriction(0.85f);
        this.setOnGround(true);
    }

    @Override
    public void updateMovement(float deltaTime) {
        Vector2 vel = this.getMovementComponent().getVelocity();
        float playerMaxSpd = this.getMovementComponent().getMaxSpeed();
        
        //diagonal movement
        if (vel.len() > playerMaxSpd) {
            vel.setLength(playerMaxSpd);
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
            if (other instanceof Door) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                    String dest = ((Door) other).getDestination();
                    if (dest != null && !dest.isEmpty()) {
                        this.mapToLoad = dest; // Set the map to load
                    }
                }
            }
            else if (other instanceof CollisionBox) {
                this.setPosition(this.getPreviousPosition().x, this.getPreviousPosition().y);
                this.hasCollided = true;
            }
        }
        this.getCollisionComponent().updateBounds(getPosition());
    }
}
