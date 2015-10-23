package game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

import game.blocks.Block;

public class Renderer {
	
	public static void renderBlock(Block b) {
		glPushMatrix();
		
		glTranslatef(b.pos.x*Block.size.x + Display.getWidth()/2, b.pos.y*Block.size.y + Display.getHeight()/2, 0);
		
		glBindTexture(GL_TEXTURE_2D, b.texture);
		
		glBegin(GL_QUADS);
		{
			glTexCoord2f(1, 1);
			glVertex2f(b.pos.x + Block.size.x/2, b.pos.y - Block.size.y/2);
			glTexCoord2f(1, 0);
			glVertex2f(b.pos.x + Block.size.x/2, b.pos.y + Block.size.y/2);
			glTexCoord2f(0, 0);
			glVertex2f(b.pos.x - Block.size.x/2, b.pos.y + Block.size.y/2);
			glTexCoord2f(0, 1);
			glVertex2f(b.pos.x - Block.size.x/2, b.pos.y - Block.size.y/2);
		}
		glEnd();
		
		glBindTexture(GL_TEXTURE_2D, 0);
		
		glPopMatrix();
	}
	
}
