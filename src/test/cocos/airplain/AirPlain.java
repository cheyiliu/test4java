package test.cocos.airplain;

public class AirPlain implements Role {
	Gun mDefaultGun;
	Gun mGun;
	Gun mBomb;
	int mHP;

	@Override
	public void attack(Role target) {
		int x = 10;
		int y = 10;
		if (!mGun.fire(LevelManager.getGamLayer(), x, y)) {
			switchGun();
		}
		mGun.fire(LevelManager.getGamLayer(), x, y);
	}

	private void switchGun() {
		mGun = mDefaultGun;
	}

	@Override
	public void gotAttacked(int damage) {
		mHP -= damage;
		if (mHP <= 0) {
			// die
		}
	}

	@Override
	public void gotBlood(int blood) {
		mHP += blood;
	}

	@Override
	public void gotGun(Gun gun) {
		mGun = gun;
	}



}
