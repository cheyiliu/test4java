package test.cocos.airplain;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int start = 1;
		int count = 1;
		for (int i = start; i < start + count; i++) {
			System.out.println("-----------------");
			System.out.println(i);
			System.out.println(hash(i));
			System.out.println(secondaryHash(i));
		}

	}

	static int hash(int h) {
		// This function ensures that hashCodes that differ only by
		// constant multiples at each bit position have a bounded
		// number of collisions (approximately 8 at default load factor).
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

	private static int secondaryHash(int h) {
		// Spread bits to regularize both segment and index locations,
		// using variant of single-word Wang/Jenkins hash.
		h += (h << 15) ^ 0xffffcd7d;		System.out.println(h);
		h ^= (h >>> 10);		System.out.println(h);
		h += (h << 3);		System.out.println(h);
		h ^= (h >>> 6);		System.out.println(h);
		h += (h << 2) + (h << 14);		System.out.println(h);
		return h ^ (h >>> 16);
	}
}
