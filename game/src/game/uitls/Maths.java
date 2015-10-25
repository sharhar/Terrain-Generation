package game.uitls;

import org.lwjgl.util.vector.Vector2f;

public class Maths {
	
	
	public static boolean inRange(Vector2f p1, Vector2f p2) {
		float distance = 6;
		
		boolean inX = p2.x <= p1.x+distance && p2.x >= p1.x-distance;
		boolean inY = p2.y <= p1.y+distance && p2.y >= p1.y-distance;
		
		
		return inX && inY;
		//return distance(p1, p2) <= distance;
	}
	
	public static float distance(Vector2f p1, Vector2f p2) {
		if(p2.x > p1.x) {
			//System.out.println("1:\t" + p1);
			//System.out.println("2:\t" + p2);
		}
		float X = Math.abs(p1.x-p2.x);
		float Y = Math.abs(p1.y-p2.y);
		return (float) Math.sqrt(X*X + Y*Y);
	}
	
}
