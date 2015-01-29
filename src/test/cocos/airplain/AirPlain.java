package test.cocos.airplain;

public class AirPlain implements Role {
	Gun mDefaultGun;
	Gun mGun;
	Gun mBomb;
	int mHP;
	final int MAX_HP = 100;

	@Override
	public void attack(Role target) {
		int x = 10;
		int y = 10;
		if (!mGun.fire(GameLogic.getGamLayer(), x, y)) {
			switchGun();
		}
		mGun.fire(GameLogic.getGamLayer(), x, y);
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
		if (MAX_HP < mHP) {
			mHP = MAX_HP;
		}
	}

	@Override
	public void gotGun(Gun gun) {
		mGun = gun;
	}

	@Override
	public void gotBomb(Gun gun) {
		mBomb = gun;
	}

}
