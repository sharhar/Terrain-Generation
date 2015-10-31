package game.terrain;

import org.lwjgl.util.vector.Vector2f;

import game.Main;
import game.graphics.Renderer;

public class Chunk {
	
	public Block[][] blocks;
	public int x;
	public float worldX;
	public int[] heightMap;
	public static int WIDTH = 16;
	public static int HEIGHT = 256; 
	public static int MIDDLE = 96;
	
	public static final float SPREAD = 100;
	public static final float CAVE_SPREAD = 10;
	public static final float AMPLIFY = 50f;
	
	public Chunk(int x) {
		heightMap = new int[WIDTH];
		
		for(int i =0;i < WIDTH; i++) {
			heightMap[i] = (int) Math.floor(Main.noise.eval(((i+(x*WIDTH)) / SPREAD), 0) * AMPLIFY);
		}
		
		blocks = new Block[WIDTH][HEIGHT];
		
		for(int i = 0;i < WIDTH;i++) {
			for(int j = 0;j < HEIGHT;j++) {
				float noise = (float) Main.noise.eval(((i+(x*WIDTH)) / CAVE_SPREAD), (j - MIDDLE)/CAVE_SPREAD);
				if(j- MIDDLE <= heightMap[i] && noise < 0.5) {
					blocks[i][j] = new Block(new Vector2f((x*WIDTH) + i, j - MIDDLE), heightMap[i]);
				}
			}
		}
		
		this.x = x;
		this.worldX = (x*WIDTH) + 1f;
	}
	
	public void render() {
		for(Block[] ba: blocks) {
			for(Block b:ba) {
				Renderer.addBlock(b);
			}
		}
	}
}
