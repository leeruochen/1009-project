package github.com_1009project.abstractEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {

    // --- ANIMATION FIELDS ---
    private Animation<TextureRegion> walkDown, walkUp, walkLeft, walkRight;
    private float stateTime = 0f;
    
    private enum Direction { UP, DOWN, LEFT, RIGHT }
    private Direction currentDirection = Direction.DOWN;
    private boolean isMoving = false;
    
    private float speed = 400f;
    public boolean hasCollided = false; // Flag for camera shake

    public Player(float x, float y, float w, float h) {
        super();
        this.setPosition(x, y);
        this.setSize(w, h);
        this.createCollisionComponent(20, 10, 15, 0);
        
        loadAnimations();
    }

    private void loadAnimations() {
        // Load textures (Ensure your folder is named 'imgs' inside 'assets')
        Texture d1 = new Texture(Gdx.files.internal("imgs/boy_down_1.png"));
        Texture d2 = new Texture(Gdx.files.internal("imgs/boy_down_2.png"));
        Texture u1 = new Texture(Gdx.files.internal("imgs/boy_up_1.png"));
        Texture u2 = new Texture(Gdx.files.internal("imgs/boy_up_2.png"));
        Texture l1 = new Texture(Gdx.files.internal("imgs/boy_left_1.png"));
        Texture l2 = new Texture(Gdx.files.internal("imgs/boy_left_2.png"));
        Texture r1 = new Texture(Gdx.files.internal("imgs/boy_right_1.png"));
        Texture r2 = new Texture(Gdx.files.internal("imgs/boy_right_2.png"));

        // Create Animations (0.15f is a good walking speed)
        walkDown = new Animation<>(0.15f, new TextureRegion(d1), new TextureRegion(d2));
        walkUp = new Animation<>(0.15f, new TextureRegion(u1), new TextureRegion(u2));
        walkLeft = new Animation<>(0.15f, new TextureRegion(l1), new TextureRegion(l2));
        walkRight = new Animation<>(0.15f, new TextureRegion(r1), new TextureRegion(r2));

        walkDown.setPlayMode(Animation.PlayMode.LOOP);
        walkUp.setPlayMode(Animation.PlayMode.LOOP);
        walkLeft.setPlayMode(Animation.PlayMode.LOOP);
        walkRight.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void updateMovement(float deltaTime) {
        float moveX = 0;
        float moveY = 0;
        isMoving = false;

        if (Gdx.input.isKeyPressed(Keys.W)) { moveY = 1; currentDirection = Direction.UP; isMoving = true; }
        if (Gdx.input.isKeyPressed(Keys.S)) { moveY = -1; currentDirection = Direction.DOWN; isMoving = true; }
        if (Gdx.input.isKeyPressed(Keys.A)) { moveX = -1; currentDirection = Direction.LEFT; isMoving = true; }
        if (Gdx.input.isKeyPressed(Keys.D)) { moveX = 1; currentDirection = Direction.RIGHT; isMoving = true; }

        if (isMoving) {
            stateTime += deltaTime;
            Vector2 movement = new Vector2(moveX, moveY).nor().scl(speed * deltaTime);
            this.setPosition(this.getPosition().x + movement.x, this.getPosition().y + movement.y);
        } else {
            // Optional: Reset to specific frame when stopped
             stateTime = 0; 
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        // 1. Get correct animation
        Animation<TextureRegion> currentAnim;
        switch (currentDirection) {
            case UP:    currentAnim = walkUp; break;
            case LEFT:  currentAnim = walkLeft; break;
            case RIGHT: currentAnim = walkRight; break;
            default:    currentAnim = walkDown; break;
        }

        // 2. Get Frame (Loop if moving, Stand still if not)
        TextureRegion currentFrame = isMoving ? currentAnim.getKeyFrame(stateTime, true) : currentAnim.getKeyFrames()[0];

        // 3. Draw
        batch.draw(currentFrame, this.getPosition().x, this.getPosition().y, this.getSize().x, this.getSize().y);
    }

    @Override
    public void onCollision(Entity other) {
        // 1. GET THE HITBOXES
        // We stop looking at "this.position" directly for calculations.
        // We look at the separate component we created.
        Rectangle myRect = this.getCollisionComponent().getBounds();
        
        // We assume the other entity also has a component (e.g., the Wall)
        // If 'other' is a generic Entity, you might need to check if component exists first
        if (other.getCollisionComponent() == null) return; 
        Rectangle otherRect = other.getCollisionComponent().getBounds();

        // 2. CALCULATE CENTERS (Based on Hitboxes, not Sprites)
        float myCenterX = myRect.x + myRect.width / 2;
        float myCenterY = myRect.y + myRect.height / 2;
        
        float otherCenterX = otherRect.x + otherRect.width / 2;
        float otherCenterY = otherRect.y + otherRect.height / 2;

        float dx = myCenterX - otherCenterX;
        float dy = myCenterY - otherCenterY;

        // 3. CALCULATE EXTENTS (Based on Hitboxes)
        float combinedHalfWidth = (myRect.width / 2) + (otherRect.width / 2);
        float combinedHalfHeight = (myRect.height / 2) + (otherRect.height / 2);

        // 4. CALCULATE OVERLAP
        float overlapX = combinedHalfWidth - Math.abs(dx);
        float overlapY = combinedHalfHeight - Math.abs(dy);

        // Safety check: If we aren't actually overlapping, stop.
        if (overlapX <= 0 || overlapY <= 0) return;

        // 5. RESOLUTION (Crucial Step)
        if (overlapX < overlapY) {
            // Resolve X
            if (dx > 0) this.setPosition(this.getPosition().x + overlapX, this.getPosition().y);
            else        this.setPosition(this.getPosition().x - overlapX, this.getPosition().y);
        } else {
            // Resolve Y
            if (dy > 0) this.setPosition(this.getPosition().x, this.getPosition().y + overlapY);
            else        this.setPosition(this.getPosition().x, this.getPosition().y - overlapY);
        }

        // 6. SYNC IMMEDIATELY
        // We moved the 'position' (the sprite), so we must update the hitbox 
        // immediately so it matches the new location.
        this.getCollisionComponent().updateBounds(this.getPosition());
        // Report collision for Camera Shake
        this.hasCollided = true;
    }
}