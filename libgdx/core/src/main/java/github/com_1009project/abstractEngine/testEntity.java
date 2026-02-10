package github.com_1009project.abstractEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class testEntity extends Entity {
    private float speed = 400f;
    public boolean hasCollided = false; // Flag for camera shake
    private Texture texture;

    public testEntity(float x, float y, float w, float h, Texture texture) {
        super();
        this.setPosition(x, y);
        this.setSize(w, h);
        this.createCollisionComponent(20, 10, 15, 0);
        this.texture = texture;
    }

    @Override
    public void updateMovement(float deltaTime) {
        if (Gdx.input.isKeyPressed(Keys.W)) { this.getPosition().y += speed * deltaTime; }
        if (Gdx.input.isKeyPressed(Keys.S)) { this.getPosition().y -= speed * deltaTime; }
        if (Gdx.input.isKeyPressed(Keys.A)) { this.getPosition().x -= speed * deltaTime; }
        if (Gdx.input.isKeyPressed(Keys.D)) { this.getPosition().x += speed * deltaTime; }
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