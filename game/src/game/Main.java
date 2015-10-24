package game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import game.graphics.Renderer;
import game.graphics.Texture;
import game.terrain.Block;
import game.terrain.Chunk;
import game.uitls.OpenSimplexNoise;

import static org.lwjgl.opengl.GL11.*;

public class Main {
	
	public static OpenSimplexNoise noise;
	
	public static void main(String[] args) {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setTitle("Terrain");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		noise = new OpenSimplexNoise(2350978);
		
		Block.initTexture(new Texture("/textures.png").getID(), 16, 16);
		Chunk chunk = new Chunk(0);
		
		glClearColor(0.2f, 0.3f, 0.9f, 1);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 800, 0, 600, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		
		while(!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT);
			
			chunk.render();
			
			Renderer.renderBatch();
			
			Display.update();
		}
		
		Display.destroy();
	}
}
