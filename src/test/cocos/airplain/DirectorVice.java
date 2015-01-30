package test.cocos.airplain;

/**
 * 负责招兵买马的副导演，负责安排出场顺序，负责驱动场上的逻辑-碰撞监测等。
 */
public class DirectorVice {
	private GameLayer mGamLayer;
	private static DirectorVice sDirectorVice;

	private DirectorVice() {
	}

	public static synchronized DirectorVice getInstance() {
		if (sDirectorVice == null) {
			sDirectorVice = new DirectorVice();
		}
		return sDirectorVice;
	}

	public GameLayer getGamLayer() {
		return mGamLayer;
	}

	public void update() {

		// 碰撞监测，是否要补给，是否要爆炸，是否炸全屏

	}

	public void requestBullet(int bulletType, int x, int y, int direction) {

	}
}
