package github.com_1009project.abstractEngine;

public abstract class Layer {
    public abstract void update(float deltaTime);
    public abstract void render();
    public abstract void dispose();
}
