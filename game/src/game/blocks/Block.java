package game.blocks;

import org.lwjgl.util.vector.Vector2f;

public class Block {
	
	public Vector2f pos;
	public int texture;
	public static Vector2f size = new Vector2f(50,50);
	public static Vector2f playerPos = new Vector2f(0,0);
	
	public Block(Vector2f pos, int texture) {
		this.pos= pos;
		this.texture = texture;
	}
	
}
