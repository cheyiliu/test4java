package test.cocos.airplain;

public class DirectorVice {
	private GamLayer mGamLayer;
	private static DirectorVice sDirectorVice;

	private DirectorVice() {
	}

	public static synchronized DirectorVice getInstance() {
		if (sDirectorVice == null) {
			sDirectorVice = new DirectorVice();
		}
		return sDirectorVice;
	}

	public GamLayer getGamLayer() {
		return mGamLayer;
	}

	public void update() {

		// 碰撞监测，是否要补给，是否要爆炸，是否炸全屏

	}

	public void requestBullet(int bulletType, int x, int y, int direction) {

	}
}
