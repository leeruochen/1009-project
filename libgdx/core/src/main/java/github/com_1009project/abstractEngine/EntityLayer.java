package github.com_1009project.abstractEngine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// [waiting for EntityManager implementation cause will cause errors]
public class EntityLayer extends Layer {
    private EntityManager entityManager;
    private SpriteBatch batch;

    public EntityLayer(EventManager eventManager) {
        entityManager = new EntityManager();
        entityManager.setEventManager(eventManager);
    }

    @Override
    public void update(float deltaTime) {
        entityManager.update(deltaTime);
    }

    @Override
    public void render() {
        entityManager.render(batch);
    }

    @Override
    public void dispose() {
        entityManager.dispose();
        batch.dispose();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    
}
