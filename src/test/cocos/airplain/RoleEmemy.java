package test.cocos.airplain;

public class RoleEmemy implements Role {
	public enum Team {
		TeamHero, TeamMonster, TeamMiddle
	}

	public Team mTeam;
	private Gun mDefaultGun;
	private Gun mGun;
	private Gun mBomb;
	private int mHP;
	private final int MAX_HP = 100;

	public boolean isFrient(RoleEmemy target){
		if (target.mTeam == mTeam) {
			return true;
		}
		return false;
	}
	public void superAttack() {
		mBomb.fire(DirectorVice.getInstance().getGamLayer(), 0, 0);
	}

	@Override
	public void attack(Role target) {
		int x = 10;
		int y = 10;
		if (!mGun.fire(DirectorVice.getInstance().getGamLayer(), x, y)) {
			switchGun();
		}
		mGun.fire(DirectorVice.getInstance().getGamLayer(), x, y);
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
	public void gotGunDouble(Gun gun) {
		mGun = gun;
	}

	@Override
	public void gotGunSupper(Gun gun) {
		mBomb = gun;
	}

}
