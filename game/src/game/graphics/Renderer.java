package game.graphics;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import game.terrain.Block;

public class Renderer {
	
	public static List<Block> blocks = new ArrayList<Block>();
	
	public static void addBlock(Block b) {
		if(b == null) {
			return;
		}
		blocks.add(b);
	}
	
	public static void renderBatch(){
		glBindTexture(GL_TEXTURE_2D, Block.texture);
		
		for(Block b: blocks) {
			batchRenderBlock(b);
		}
		
		glBindTexture(GL_TEXTURE_2D, 0);
		
		blocks.clear();
	}
	
	public static void batchRenderBlock(Block b) {
		glPushMatrix();
		
		glTranslatef(b.pos.x*Block.size.x + Display.getWidth()/2, b.pos.y*Block.size.y + Display.getHeight()/2, 0);
		
		glBegin(GL_QUADS);
		{
			glTexCoord2f(Block.texBlockSize.x * (b.texPos.x+1), Block.texBlockSize.y * (b.texPos.y+1));
			glVertex2f(b.pos.x + Block.size.x/2 + 1, b.pos.y - Block.size.y/2);
			
			glTexCoord2f(Block.texBlockSize.x * (b.texPos.x+1), Block.texBlockSize.y * (b.texPos.y));
			glVertex2f(b.pos.x + Block.size.x/2 + 1, b.pos.y + Block.size.y/2 + 1);
			
			glTexCoord2f(Block.texBlockSize.x * (b.texPos.x), Block.texBlockSize.y * (b.texPos.y));
			glVertex2f(b.pos.x - Block.size.x/2, b.pos.y + Block.size.y/2 + 1);
			
			glTexCoord2f(Block.texBlockSize.x * (b.texPos.x), Block.texBlockSize.y * (b.texPos.y+1));
			glVertex2f(b.pos.x - Block.size.x/2, b.pos.y - Block.size.y/2);
		}
		glEnd();
		
		glPopMatrix();
	}
	
	public static void renderBlock(Block b) {
		glPushMatrix();
		
		glTranslatef(b.pos.x*Block.size.x + Display.getWidth()/2, b.pos.y*Block.size.y + Display.getHeight()/2, 0);
		
		glBindTexture(GL_TEXTURE_2D, Block.texture);
		
		glBegin(GL_QUADS);
		{
			glTexCoord2f(Block.texBlockSize.x * (b.texPos.x+1), Block.texBlockSize.y * (b.texPos.y+1));
			glVertex2f(b.pos.x + Block.size.x/2, b.pos.y - Block.size.y/2);
			
			glTexCoord2f(Block.texBlockSize.x * (b.texPos.x+1), Block.texBlockSize.y * (b.texPos.y));
			glVertex2f(b.pos.x + Block.size.x/2, b.pos.y + Block.size.y/2);
			
			glTexCoord2f(Block.texBlockSize.x * (b.texPos.x), Block.texBlockSize.y * (b.texPos.y));
			glVertex2f(b.pos.x - Block.size.x/2, b.pos.y + Block.size.y/2);
			
			glTexCoord2f(Block.texBlockSize.x * (b.texPos.x), Block.texBlockSize.y * (b.texPos.y+1));
			glVertex2f(b.pos.x - Block.size.x/2, b.pos.y - Block.size.y/2);
		}
		glEnd();
		
		glBindTexture(GL_TEXTURE_2D, 0);
		
		glPopMatrix();
	}
	
}
