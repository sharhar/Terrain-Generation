package game.uitls;

public class Time {
	
	public static float deltaTime = 0;
	
	private static long startTime = 0;
	private static long currentTime = 0;
	
	public static void init() {
		startTime = System.nanoTime();
		currentTime = System.nanoTime();
	}
	
	public static void tick() {
		currentTime = System.nanoTime();
		
		deltaTime = (float) ((currentTime - startTime) / (1000000.0f * 1000));
		
		startTime = System.nanoTime();
	}
}
