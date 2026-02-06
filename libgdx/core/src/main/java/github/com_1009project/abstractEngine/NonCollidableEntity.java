package github.com_1009project.abstractEngine;

public abstract class NonCollidableEntity extends Entity {
    public NonCollidableEntity(float x, float y) {
        super();
        this.position.set(x, y);
    }

    @Override
    public void update(float deltaTime) {
        updateMovement(deltaTime); // Update the entity's movement
    }

    public abstract void updateMovement(float deltaTime); // Abstract method to update the entity's movement
}
