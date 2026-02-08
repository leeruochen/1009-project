package github.com_1009project.lwjgl3;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import github.com_1009project.abstractEngine.ResourceManager;

public class BackgroundLayer extends Layer {
    private Texture background;
    private SpriteBatch batch;

    public BackgroundLayer(ResourceManager resourceManager) {
        background = resourceManager.getTexture("background.png");
        batch = new SpriteBatch();
    }

    @Override
    public void update(float deltaTime) {
        // can remove if not needed, if we want our background to be dynamic
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
    }
}