package github.com_1009project.lwjgl3;

public abstract class Layer {
    public abstract void update(float deltaTime);
    public abstract void render();
    public abstract void dispose();
}
