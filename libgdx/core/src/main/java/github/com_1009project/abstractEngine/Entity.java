package github.com_1009project.abstractEngine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    // properties fields to be used by subclasses
    private static int idCounter = 0;
    private int id; 
    private boolean active;
    private Vector2 position;
    private Vector2 size;
    private float rotation; 
    private Vector2 previousPosition;
    private Vector2 velocity;
    private Vector2 acceleration;
    private float speed;
    private float friction;
    private CollisionComponent collisionComponent;

    public Entity() { // constructor to initialize the entity with defaults
        this.id = idCounter++; // id's are sequentially assigned
        this.position = new Vector2(0, 0);
        this.size = new Vector2(1, 1); 
        this.rotation = 0;
        this.previousPosition = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);
        this.acceleration = new Vector2(0, 0);
        this.speed = 0;
        this.friction = 0; 
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
        // collision component creation
        // entities can call this in their constructor to make it collidable
        // protected so that only subclasses can create collision components
        this.collisionComponent = new CollisionComponent(position.x, position.y, width, height);
    }

    protected void createCollisionComponent(float width, float height, float offsetX, float offsetY) {
        // overloaded method to create collision component with offsets suitable for "Players"
        this.collisionComponent = new CollisionComponent(position.x + offsetX, position.y + offsetY, width, height);
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