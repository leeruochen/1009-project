package github.com_1009project.abstractEngine;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackgroundLayer extends Layer {
    
    private Texture background;
    private SpriteBatch batch;

    public BackgroundLayer(SpriteBatch batch, AssetManager resourceManager, String filepath) {
        this.batch = batch;
        if (!resourceManager.isLoaded(filepath)) {
            resourceManager.load(filepath, Texture.class);
            resourceManager.finishLoading();
        }
        this.background = resourceManager.get(filepath, Texture.class);
    }

    @Override
    public void update(float deltaTime) {
        
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        if (background != null) {
            background.dispose();
        }
    }

}