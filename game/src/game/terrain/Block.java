package game.terrain;

import org.lwjgl.util.vector.Vector2f;

public class Block {
	
	public Vector2f pos;
	public Vector2f texPos;
	public static Vector2f texSize;
	public static Vector2f texBlockSize;
	public static int texture;
	public static Vector2f size = new Vector2f(50,50);
	public static Vector2f playerPos = new Vector2f(0,0);
	public float r = 1;
	public float g = 1;
	public float b = 1;
	
	
	public static void initTexture(int t, int x, int y) {
		Block.texture = t;
		texSize = new Vector2f(x, y);
		texBlockSize = new Vector2f(1.0f/texSize.x, 1.0f/texSize.y);
	}
	
	public Block(Vector2f pos, Vector2f texPos) {
		this.pos= pos;
		this.texPos = texPos;
	}
	
}
