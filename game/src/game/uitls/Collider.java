package game.uitls;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import game.Main;
import game.Player;
import game.terrain.Block;
import game.terrain.Chunk;

public class Collider {
	
	public static Vector2f getCurrentChunks() {
		int index1 = -1;
		int index2 = -1;
		
		for(int i =0;i < Main.chunks.length;i++) {
			float x = Main.chunks[i].worldX;
			if(Block.playerPos.x > x) {
				index1 = i;
			}
		}
		
		for(int i =0;i < Main.chunks.length;i++) {
			float x = Main.chunks[i].worldX;
			if(Block.playerPos.x + 16 > x) {
				index2 = i;
			}
		}
		
		return new Vector2f(index1, index2);
	}
	
	public static boolean rectRectCol(Vector2f pos1, Vector2f size1, Vector2f pos2, Vector2f size2) {
		
		if(pos1.x + size1.x < pos2.x) {
			return false;
		}
		
		if(pos2.x + size2.x < pos1.x) {
			return false;
		}
		
		if(pos1.y + size1.y < pos2.y) {
			return false;
		}
		
		if(pos2.y + size2.y < pos1.y) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isHittingBlock(Block b) {
		Vector2f p1 = new Vector2f(Display.getWidth()/2 - Player.size.x/2,Display.getHeight()/2 - Player.size.y/2);
		Vector2f s1 = Player.size;
		Vector2f p2 = new Vector2f((b.pos.x - Block.playerPos.x)*Block.size.x + Display.getWidth()/2-Block.size.x/2,
				(b.pos.y - Block.playerPos.y)*Block.size.y + Display.getHeight()/2 - 76 + 2-Block.size.y/2);
		Vector2f s2 = Block.size;
		
		return rectRectCol(p1, s1, p2, s2);
	}
	
	public static boolean isHittingChunk(Vector2f indexs) {
		if(indexs.x == -1 && indexs.y == -1) {
			return false;
		}
		
		int index1 = (int) (indexs.x);
		int index2 = (int) (indexs.y);
		
		Chunk c = null;
		
		if(index1 != -1) {
			c = Main.chunks[index1];
			
			for(Block[] ba:c.blocks) {
				for(Block b: ba) {
					if(b != null) {
						if(Maths.inRange(Block.playerPos, b.pos)) {
							if(isHittingBlock(b)) {
								return true;
							}
						}
					}
				}
			}
		}
		
		if(index2 != -1) {
			c = Main.chunks[index2];
			
			for(Block[] ba:c.blocks) {
				for(Block b: ba) {
					if(b != null) {
						if(Maths.inRange(Block.playerPos, b.pos)) {
							if(isHittingBlock(b)) {
								return true;
							}
						}
					}
				}
			}
		
		}
		
		return false;
	}
	
}
