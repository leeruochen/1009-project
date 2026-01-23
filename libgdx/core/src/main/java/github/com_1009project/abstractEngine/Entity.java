package github.com_1009project.abstractEngine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    // Properties, protected fields to be used by subclasses
    private static int idCounter = 0;
    protected int id; // Unique identifier for the entity
    protected Vector2 position; // Position of the entity in the logic
    protected Vector2 size; // Size of the entity
    protected float rotation; // Rotation angle of the entity
    protected Vector2 previousPosition; // Previous position for collision
    protected boolean active; // Active state of the entity

    public Entity() { // Constructor to initialize the entity
        this.id = idCounter++; // Generate a unique ID
        this.position = new Vector2(0, 0); // Default position at origin
        this.size = new Vector2(1, 1); // Default size
        this.rotation = 0; // Default rotation
        this.previousPosition = new Vector2(0, 0); // Initialize previous position
        this.active = true; // Entity is active by default
    }

    public abstract void render(SpriteBatch batch); // Abstract method to render the entity

    public abstract void update(float deltaTime); // Abstract method to update the entity's state

    public void setPosition(float x, float y) { // Setter for the position of the entity
        this.position.set(x, y);
    }

    public Vector2 getPosition() { // Getter for the position
        return position;
    }

    public void setSize(float width, float height) { // Setter for the size of the entity
        this.size.set(width, height);
    }

    public Vector2 getSize() { // Getter for the size
        return size;
    }

    public void setRotation(float rotation) { // Setter for the rotation of the entity
        this.rotation = rotation;
    }

    public float getRotation() { // Getter for the rotation
        return rotation;
    }

    public int getId() { // Getter for the entity ID
        return id;
    }

    public Vector2 getPreviousPosition() {
        return previousPosition;
    }

    public boolean isActive() { // Check if the entity is active
        return active;
    }

    public void setActive(boolean active) { // Setter for the active state
        this.active = false;
    }
}