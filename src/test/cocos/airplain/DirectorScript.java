package test.cocos.airplain;

/**
 * 负责剧本的导演，暂先简单实现，后期扩充
 */
public class DirectorScript {
	// 关卡配置
	class Level {
		int level;
		int enemyNum;
		int enemyHp;
		int heroNum;
		int heroHp;
		// many other
	}

	private Level mCurrentLevel;

	public Level getLevel(int level) {
		// load script
		// mCurrentLevel = loadScript(level);
		return mCurrentLevel;
	}

}
