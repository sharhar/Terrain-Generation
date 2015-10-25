package game.uitls;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import game.Main;
import game.Player;
import game.terrain.Block;
import game.terrain.Chunk;

public class Collider {
	
	public static int getCurrentChunk() {
		int index = -1;
		
		for(int i =0;i < Main.chunks.length;i++) {
			float x = Main.chunks[i].worldX;
			if(Block.playerPos.x > x) {
				index = i;
			}
		}
		
		return index;
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
	
	public static boolean isHittingChunk(int index) {
		if(index == -1) {
			return false;
		}
		
		Chunk c = Main.chunks[index];
		
		List<Block> blocks = new ArrayList<Block>();
		
		for(Block[] ba:c.blocks) {
			for(Block b: ba) {
				if(b != null) {
					if(Maths.inRange(Block.playerPos, b.pos)) {
						b.r = 0;
						//if(isHittingBlock(b)) {
						//	return true;
						//}
						blocks.add(b);
					} else {
						b.r = 1;
					}
				}
			}
		}
		
		for(Block b:blocks) {
			if(isHittingBlock(b)) {
				return true;
			}
		}
		
		return false;
	}
	
}
