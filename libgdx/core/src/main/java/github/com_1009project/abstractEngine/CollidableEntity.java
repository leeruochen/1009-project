package github.com_1009project.abstractEngine;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class CollidableEntity extends Entity {
    protected Rectangle bounds;

    // Constructor to initialize the CollidableEntity with position and size
    public CollidableEntity(float x, float y, float width, float height) {
        super(); // sets default values from Entity
        this.position.set(x, y);
        this.size.set(width, height);
        this.bounds = new Rectangle(x, y, width, height);
        this.previousPosition = new Vector2(x, y);
    }

    protected void updateBounds() { // Update the bounding rectangle based on the entity's position and size
        this.bounds.setPosition(this.position.x, this.position.y);
        this.bounds.setSize(this.size.x, this.size.y);
    }

    public Rectangle getBounds() { // Getter for the bounding rectangle
        return bounds;
    }

    @Override
    public void update(float deltaTime) {
        this.previousPosition.set(this.position.x, this.position.y); // Store previous position
        updateMovement(deltaTime); // Update the entity's motion
        updateBounds(); // Update the bounding rectangle
    }

    public abstract void updateMovement(float deltaTime); // keep it abstract for subclasses to implement their own movement logic

    public abstract void onCollision(CollidableEntity other); // to handle collision with another CollidableEntity
}
