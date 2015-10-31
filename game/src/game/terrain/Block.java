package game.terrain;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import game.Player;

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
	
	public static Vector2f grassTex = new Vector2f(3, 0);
	public static Vector2f dirtTex = new Vector2f(2, 0);
	public static Vector2f stoneTex = new Vector2f(1, 0);
	
	public static void initTexture(int t, int x, int y) {
		Block.texture = t;
		texSize = new Vector2f(x, y);
		texBlockSize = new Vector2f(1.0f/texSize.x, 1.0f/texSize.y);
	}
	
	public boolean inScreen() {
		Vector2f s = new Vector2f();
		
		s.x = ((pos.x - playerPos.x) * size.x) + Display.getWidth()/2 + Player.size.x/2;
		s.y = ((pos.y - playerPos.y) * size.y) + Display.getHeight()/2 - Player.size.y/2;
		
		boolean inX = s.x > -50 && s.x < Display.getWidth() + 50;
		boolean inY = s.y > -50 && s.y < Display.getHeight() + 50;
		
		return inX && inY;
	}
	
	public Block(Vector2f pos, Vector2f texPos) {
		this.pos= pos;
		this.texPos = texPos;
	}
	
	public Block(Vector2f pos, int t) {
		this.pos= pos;
		
		int dif = (int) (t - pos.y);
		
		Vector2f tex = new Vector2f();
		
		if(dif >= 6) {
			tex = stoneTex;
		} else if(dif < 6 && dif > 0) {
			tex = dirtTex;
		} else {
			tex = grassTex;
		}
		
		this.texPos = tex;
	}
}
