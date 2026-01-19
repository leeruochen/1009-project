package github.com_1009project.abstractEngine;

import java.util.ArrayList;

//https://hypersphere.blog/blog/quad-trees/ for future optimization

public class CollisionManager {
    public void update(ArrayList<CollidableEntity> entities) { // naive solution for collision detection
        for (int i = 0; i < entities.size(); i++) { // Iterate through all entities
            CollidableEntity entityA = entities.get(i);

            for (int j = i + 1; j < entities.size(); j++) { // Check against other entities
                CollidableEntity entityB = entities.get(j);

                if (entityA.getBounds().overlaps(entityB.getBounds())) { // Check for collision
                    entityA.onCollision(entityB);
                    entityB.onCollision(entityA);
                }
            }
        }
    }
}
