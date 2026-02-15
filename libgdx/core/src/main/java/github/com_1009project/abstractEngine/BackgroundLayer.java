package github.com_1009project.abstractEngine;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackgroundLayer extends Layer {
    //private final AssetManager resourceManager;
    private Texture background;
    private SpriteBatch batch;

    // public BackgroundLayer(AssetManager resourceManager, String filePath) {
    //     this.resourceManager = resourceManager;

    //     if (!resourceManager.isLoaded(filePath)) {
    //         resourceManager.load("background.png", Texture.class);
    //         resourceManager.finishLoading();
    //     }

    //     this.background = resourceManager.get("background.png", Texture.class);
    //     this.batch = new SpriteBatch();
    // }

    public BackgroundLayer(String filepath) {
        background = new Texture("imgs/background.png");
        batch = new SpriteBatch();

    }

    @Override
    public void update(float deltaTime) {
        // Optional: animate background
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
    }

}