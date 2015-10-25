package game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import game.terrain.Block;

public class Player {
	
	public static Vector2f size = new Vector2f(45, 95);
	public static float speed = 0.01f;
	
	public static void init() {
		
	}
	
	public static void render() {
		
	}
	
	public static void tick() {
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			speed = 0.1f;
		}else {
			speed = 0.01f;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			Block.playerPos.y += speed;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			Block.playerPos.y -= speed;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			Block.playerPos.x -= speed;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			Block.playerPos.x += speed;
		}
	}
}
