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
    private boolean isPersistent;

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
        this.previousPosition.set(this.getCollisionComponent().getBounds().x, this.getCollisionComponent().getBounds().y); // Store previous position
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

    public void onCollision(Entity collidedEntity){} // classes to override this if they want to handle collision

    // Getters and Setters
    public CollisionComponent getCollisionComponent() {return this.collisionComponent;}
    public void setPosition(float x, float y) {this.position.set(x, y);}
    public Vector2 getPosition() { return position;}
    public void setSize(float width, float height) {this.size.set(width, height);}
    public Vector2 getSize() { return size;}
    public void setRotation(float rotation) {this.rotation = rotation;}
    public float getRotation() {return rotation;}
    public int getId() {return id;}
    public Vector2 getPreviousPosition() {return previousPosition;}
    public Vector2 getVelocity() { return velocity; }
    public void setVelocity(Vector2 velocity) { this.velocity = velocity; }
    public Vector2 getAcceleration() { return acceleration; }
    public void setAcceleration(Vector2 acceleration) { this.acceleration = acceleration; }
    public float getSpeed() { return speed; }
    public void setSpeed(float speed) { this.speed = speed; }
    public float getFriction() { return friction; }
    public void setFriction(float friction) { this.friction = friction;}
    public boolean isActive() { return active;}
    public void setActive(boolean active) { this.active = active;}

    public void onDestroy(){} // method to be called when the entity is destroyed, can be overridden by subclasses for cleanup
    
    public boolean getPersistent() {
        return isPersistent;
    }

    public void setPersistent(boolean persistent) {
        this.isPersistent = persistent;
    }
}