package github.com_1009project.abstractEngine;

import com.badlogic.gdx.math.Vector2;

public class MovementManager implements EventObserver {
    private final float playerSpeed = 300f;
    private EntityManager entityManager;
    
    // Track which keys are currently pressed
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean spacePressed = false; //jump logic is in game logic
    
    public MovementManager(EntityManager entityManager) {
    	this.entityManager = entityManager;
    }

    public void handlePlayerInput(Entity entity, Event event, boolean isUp) {
        if (entity == null) return;

        // Update input state
        if (!isUp) { // Key Pressed
            switch (event) {
                case PlayerLeft: 
                	leftPressed = true; 
                	break;
                case PlayerRight: 
                	rightPressed = true; 
                	break;
                case PlayerUp: 
                	upPressed = true; 
                	break;
                case PlayerDown: 
                	downPressed = true; 
                	break;
                case PlayerJump: 
                	spacePressed = true;
                	break;
            }
        } else { // Key Released
            switch (event) {
                case PlayerLeft: 
                	leftPressed = false; 
                	break;
                case PlayerRight: 
                	rightPressed = false; 
                	break;
                case PlayerUp: 
                	upPressed = false; 
                	break;
                case PlayerDown: 
                	downPressed = false; 
                	break;
                case PlayerJump: 
                	spacePressed = false;
                	break;
            }
        }

        // Update velocity based on current input state
        updatePlayerVelocity(entity);
    }

    private void updatePlayerVelocity(Entity entity) {
        Vector2 vel = entity.getMovementComponent().getVelocity();
        
        // Horizontal movement
        if (leftPressed && !rightPressed) {
            vel.x = -playerSpeed;
        } else if (rightPressed && !leftPressed) {
            vel.x = playerSpeed;
        } else {
            vel.x = 0; // Both or neither pressed
        }
        
        // Vertical movement
        if (upPressed && !downPressed) {
            vel.y = playerSpeed;
        } else if (downPressed && !upPressed) {
            vel.y = -playerSpeed;
        } else {
            vel.y = 0; // Both or neither pressed
        }
        
    }
    
    // Call this when player dies, pauses, or something that doesnt involve user input
    public void resetInput() {
        leftPressed = false;
        rightPressed = false;
        upPressed = false;
        downPressed = false;
    }
    
	@Override
	public void onNotify(Event event, Boolean up) {
		// Only loop through entities that have explicitly flagged they want input
		for (Entity entity : entityManager.getEntities()) {
			if (entity.isActive()) {
				if (entity.isInputEnabled()) {
					handlePlayerInput(entity, event, up);
				}
			}
		}
	}

	@Override // this is for handling mouse events
	public void onNotify(Event event, Boolean up, int screenX, int screenY) {

	}
}
