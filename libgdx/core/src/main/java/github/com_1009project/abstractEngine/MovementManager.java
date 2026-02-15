package github.com_1009project.abstractEngine;

import com.badlogic.gdx.math.Vector2;

public class MovementManager {
    private final float playerSpeed = 300f;
    
    // Track which keys are currently pressed
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean spacePressed = false;
    private boolean interactPressed = false;

    public void handlePlayerInput(Entity entity, Event event, boolean isUp) {
        if (entity == null) return;

        // Update input state
        if (!isUp) { // Key Pressed
            switch (event) {
                case PlayerLeft: 
                	leftPressed = true; 
                	break;
                case PlayerRight: 
                	rightPressed = true; break;
                case PlayerUp: 
                	upPressed = true; 
                	break;
                case PlayerDown: 
                	downPressed = true; 
                	break;
                case PlayerJump: 
                	spacePressed = true;
                	break;
                case PlayerInteract:
                    // Handle interaction logic here if needed // testinggggggggggggggggggg
                    interactPressed = true;
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
                case PlayerInteract:
                    interactPressed = false;
                    break;
            }
        }

        // Update velocity based on current input state
        updatePlayerVelocity(entity);
    }

    private void updatePlayerVelocity(Entity entity) {
        Vector2 vel = entity.getVelocity();
        
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
        spacePressed = false;
    }
}
