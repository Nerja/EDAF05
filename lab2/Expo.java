import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Expo {
	public static void main(String[] args) {
		Random rand = new Random();
		Map<Integer, Integer> g = new HashMap<Integer, Integer>();
		ArrayList<Integer> ga = new ArrayList<Integer>();
		
		
		long t = System.nanoTime();
		for(int i = 0; i < 5000; i++) {
			ga.add(rand.nextInt(10000));
		}
		System.out.println(System.nanoTime() - t);
		
		long qt = System.nanoTime();
		for(int i = 0; i < 5000; i++) {
			g.put(rand.nextInt(10000), rand.nextInt(10000));
		}
		System.out.println(System.nanoTime() - qt);
		g.put(1337, 1337);
		long m = System.nanoTime();
		g.get(1337);
		System.out.println(System.nanoTime() - m);
		long ma = System.nanoTime();
		ga.get(1337);
		System.out.println(System.nanoTime() - ma);
	}
}
