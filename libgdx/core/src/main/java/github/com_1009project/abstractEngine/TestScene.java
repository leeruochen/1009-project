package github.com_1009project.abstractEngine;

import com.badlogic.gdx.assets.AssetManager;

public class TestScene extends Scene {
    public TestScene(int id, AssetManager resourceManager, EntityManager entityManager, EventManager eventManager) {
        super(id, resourceManager, entityManager, eventManager);
    }

    @Override
    public void init() {
        super.init(); // keep default layers
        System.out.println("TestScene initialized with default layers");
        // Add extra test-specific layers or entities here if needed
    }

    @Override
    public void onEnter() {
        super.onEnter();
        System.out.println("Entered TestScene");
    }

    @Override
    public void render() {
        super.render();
        System.out.println("Rendering TestScene");
    }
}