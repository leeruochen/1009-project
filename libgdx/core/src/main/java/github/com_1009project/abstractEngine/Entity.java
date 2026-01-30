package github.com_1009project.abstractEngine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    // properties fields to be used by subclasses
    private static int idCounter = 0;
    protected int id; 
    protected Vector2 position;
    protected Vector2 size;
    protected float rotation; 
    protected Vector2 previousPosition;
    protected boolean active;
    protected CollisionComponent collisionComponent;

    public Entity() { // constructor to initialize the entity with defaults
        this.id = idCounter++; // id's are sequentially assigned
        this.position = new Vector2(0, 0);
        this.size = new Vector2(1, 1); 
        this.rotation = 0;
        this.previousPosition = new Vector2(0, 0); 
        this.active = true; 
        this.collisionComponent = null; 
    }

    // to render the entity
    public abstract void render(SpriteBatch batch);
    // to update the entity's movement
    public abstract void updateMovement(float deltaTime);

    // this will be called every frame to update the entity
    public void update(float deltaTime){
        this.previousPosition.set(this.position.x, this.position.y); // Store previous position
        updateMovement(deltaTime); // Update the entity's movement
        
        // if entity is collidable, update its bounds
        if (this.collisionComponent != null) {
            this.collisionComponent.updateBounds(this.position);
        }
    }

    protected void createCollisionComponent(float width, float height) { 
        // entities can call this in their constructor to make it collidable
        this.collisionComponent = new CollisionComponent(position.x, position.y, width, height);
    }

    public void setCollisionActive(boolean active) {
        // Enable or disable collision detection for this entity
        if (this.collisionComponent != null) {
            this.collisionComponent.setActive(active);
        }
    }

    public boolean isCollidable() { 
        // check if entity is collidable and active
        return this.collisionComponent != null && this.collisionComponent.isActive();
    }

    public CollisionComponent getCollisionComponent() { // Getter for the collision component
        return this.collisionComponent;
    }

    public void onCollision(Entity collidedEntity){} // classes to override this if they want to handle collision

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
        this.active = active;
    }
}