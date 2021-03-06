package game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import game.graphics.Renderer;
import game.graphics.Texture;
import game.input.KeyInput;
import game.terrain.Block;
import game.terrain.Chunk;
import game.uitls.Collider;
import game.uitls.OpenSimplexNoise;
import game.uitls.Time;

import static org.lwjgl.opengl.GL11.*;

public class Main {
	
	public static OpenSimplexNoise noise;
	public static Chunk[] chunks;
	public static boolean col = false;
	
	public static void main(String[] args) {
		try {
			Display.setDisplayMode(new DisplayMode(1600, 900));
			Display.setTitle("Terrain");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		noise = new OpenSimplexNoise(2350978);
		
		Block.initTexture(new Texture("/textures.png").getID(), 16, 16);
		//Chunk chunk = new Chunk(0);
		
		chunks = new Chunk[12];
		chunks[0] = new Chunk(0);
		chunks[1] = new Chunk(1);
		chunks[2] = new Chunk(2);
		chunks[3] = new Chunk(3);
		chunks[4] = new Chunk(4);
		chunks[5] = new Chunk(5);
		chunks[6] = new Chunk(6);
		chunks[7] = new Chunk(7);
		chunks[8] = new Chunk(8);
		chunks[9] = new Chunk(9);
		chunks[10] = new Chunk(10);
		chunks[11] = new Chunk(11);
		
		glClearColor(0.2f, 0.3f, 0.9f, 1);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
		glMatrixMode(GL_MODELVIEW);
		
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		Time.init();
		while(!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);
			
			Time.tick();
			KeyInput.update();
			
			for(Chunk c:chunks) {
				c.render();
			} 
			
			Player.tick();
			
			col = Collider.isHittingChunk(Collider.getCurrentChunks());
			
			Renderer.renderBatch();
			Renderer.renderPlayer();
			
			Display.update();
			Display.sync(120);
		}
		
		Display.destroy();
	}
}
