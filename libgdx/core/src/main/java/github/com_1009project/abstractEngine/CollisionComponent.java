package github.com_1009project.abstractEngine;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CollisionComponent {
    private Rectangle bounds;
    private boolean active = true;

    // creates a collision box that will follow the entity
    public CollisionComponent(float x, float y, float width, float height) {
        this.bounds = new Rectangle(x, y, width, height);
    }

    protected void updateBounds(Vector2 position) { 
        // update the bounding rectangle based on the entity's position and size
        this.bounds.setPosition(position.x, position.y);
    }

    public Rectangle getBounds() { 
        // getter for the bounding rectangle
        return bounds;
    }

    public void setActive(boolean active) { 
        // choose to activate or deactivate collision detection for this entity
        this.active = active;
    }

    public boolean isActive() { 
        // getter for the active state
        return active;
    }
}
