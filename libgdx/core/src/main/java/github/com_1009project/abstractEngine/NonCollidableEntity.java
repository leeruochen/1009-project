package github.com_1009project.abstractEngine;

public abstract class NonCollidableEntity extends Entity {
    public NonCollidableEntity(float x, float y) {
        super();
        this.position.set(x, y);
    }
}
