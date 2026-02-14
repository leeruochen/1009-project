package github.com_1009project.abstractEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EntityManager implements EventObserver {

    private final List<Entity> entities = new ArrayList<>();
    private final List<Entity> toRemove = new ArrayList<>();

    private final EntityFactory factory;
    private MovementManager movementManager;
    private AssetManager assetManager;

    public EntityManager(EntityFactory factory, AssetManager assetManager) {
        this.factory = factory;
        this.assetManager = assetManager;
    }

    // Creates a new entity of the specified type, adds it to the manager, and returns it
    public Entity createEntity(EntityType type) {
        Entity entity = factory.createEntity(type, assetManager);

        entities.add(entity);
        return entity;
    }

    // Marks an entity for removal at the end of the current update cycle
    public void markForRemoval(Entity entity) {
        if (!toRemove.contains(entity)) {
            toRemove.add(entity);
        }
    }

    // Immediately removes an entity from the manager and calls its onDestroy method
    public void processRemovals() {
        for (Entity entity : toRemove) {
            entity.onDestroy(); // Call onDestroy for cleanup
            entities.remove(entity);
        }
        toRemove.clear();
    }

    // Updates all active entities and processes removals at the end of the update cycle
    public void update(float deltaTime){

        // Update all active entities
        for (Entity entity : entities) {
            if (entity.isActive()) {
                entity.update(deltaTime);
            }
        }
        processRemovals(); // Process removals after updates
    }

    // getter for entities
    public List<Entity> getEntities() {
        return Collections.unmodifiableList(entities);
    }

    // add entities to the manager
    public void addEntities(List<Entity> newEntities) {
        for (Entity e : newEntities) {
            entities.add(e.copy());
        }
    }

    public void clear() {
        for (Entity entity : entities) {
            if (!entity.getPersistent()) {
                markForRemoval(entity);
            }
        }
        processRemovals();
    }
    public void render(SpriteBatch batch) {
		for (Entity entity : entities) {
			if (entity.isActive()) {
				entity.render(batch);
			}
		}
	}
	
	//reference to movementManager in GameMaster
	public void setMovementManager(MovementManager movementManager) {
		this.movementManager = movementManager;
	}

	@Override
	public void onNotify(Event event, Boolean up) {
		// Only loop through entities that have explicitly flagged they want input
		for (Entity entity : entities) {
			if (entity.isActive()) {
				if (entity.isInputEnabled()) {
					movementManager.handlePlayerInput(entity, event, up);
				}
			}
		}
	}

	@Override // this is for handling mouse events
	public void onNotify(Event event, Boolean up, int screenX, int screenY) {

	}
}
