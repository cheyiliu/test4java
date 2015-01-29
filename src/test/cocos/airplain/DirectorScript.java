package test.cocos.airplain;

public class DirectorScript {
	// 管卡配置
	class Level {
		int level;
		int enemyNum;
		int enemyHp;
		int heroNum;
		int heroHp;
	}

	private Level mCurrentLevel;

	public Level getLevel(int level) {
		// load script
		// mCurrentLevel = loadScript(level);
		return mCurrentLevel;
	}

}
