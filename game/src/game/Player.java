package game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import game.input.KeyInput;
import game.terrain.Block;
import game.uitls.Collider;
import game.uitls.Time;

public class Player {
	
	public static Vector2f size = new Vector2f(45, 95);
	public static float speed = 10;
	
	public static float gForce = 30f;
	public static float yVel = 0;
	
	public static boolean inAir = false;
	
	public static void moveX(float amount) {
		Block.playerPos.x += (amount * Time.deltaTime);
	}
	
	public static void moveY(float amount) {
		Block.playerPos.y += (amount * Time.deltaTime);
	}
	
	public static void moveColX(float amount) {
		moveX(amount);
		
		if(Collider.isHittingChunk(Collider.getCurrentChunks())) {
			moveX(-amount);
		}
	}
	
	public static void moveColY(float amount) {
		moveY(amount);
		
		if(Collider.isHittingChunk(Collider.getCurrentChunks())) {
			moveY(-amount);
			if(amount < 0) {
				inAir = false;	
			}
		} else if(amount != 0) {
			inAir = true;
		}
	}
	
	public static void tick() {
		if(!inAir) {
			yVel = 0;
		}
		
		yVel -= (gForce * Time.deltaTime);
		
		if(inAir) {
			KeyInput.keys[Keyboard.KEY_W] = false;
		}
		
		if(KeyInput.isKeyPressed(Keyboard.KEY_W) && !inAir) {
			yVel = 10;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			moveColX(-speed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			moveColX(speed);
		}
		
		moveColY(yVel);
	}
}
