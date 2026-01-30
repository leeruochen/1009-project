package github.com_1009project.abstractEngine;

import java.util.ArrayList;

//https://hypersphere.blog/blog/quad-trees/ for future optimization

public class CollisionManager {
    public void updateCollision(ArrayList<Entity> entities) { // naive solution for collision detection
        for (int i = 0; i < entities.size(); i++) { // Iterate through all entities
            CollisionComponent c1 = entities.get(i).getCollisionComponent();
            Entity e1 = entities.get(i);

            for (int j = i + 1; j < entities.size(); j++) { // Check against other entities
                CollisionComponent c2 = entities.get(j).getCollisionComponent();
                Entity e2 = entities.get(j);

                if (c1.getBounds().overlaps(c2.getBounds())) { // Check for collision
                    e1.onCollision(e2);
                    e2.onCollision(e1);
                }
            }
        }
    }
}
