package test.cocos.airplain;

/**
 * 英雄，属性有：多把枪用于远程攻击；增加超级攻击的方法；可以获得各种补给等；
 */
public class RoleHero extends Role {
	public RoleHero() {
		super(RoleTeam.RoleTeamHero, 100, 100, 100);
	}

	private Gun mGunSingleInfinite;
	private Gun mGunDouble;
	private Gun mGunSupper;

	private Gun mGunCurrent;

	private final int MAX_HP = 100;

	public void switchGunSingleInflite() {
		mGunCurrent = mGunSingleInfinite;
	}

	public void switchGunDouble() {
		mGunCurrent = mGunDouble;
	}

	@Override
	public void fire(Role target) {
		// TODO Auto-generated method stub
		int x = 10;
		int y = 10;
		if (!mGunCurrent.fire(DirectorVice.getInstance().getGamLayer(), x, y)) {
			switchGunSingleInflite();
			mGunCurrent.fire(DirectorVice.getInstance().getGamLayer(), x, y);
		}
	}

	@Override
	public void fireSuper(Role target) {
		mGunSupper.fire(DirectorVice.getInstance().getGamLayer(), 0, 0);
	}

	@Override
	public void attack(Role target) {
		target.gotDamaged(mForce);
	}

	@Override
	public void gotDamaged(int damage) {
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
		mGunDouble = gun;
	}

	@Override
	public void gotGunSupper(Gun gun) {
		mGunSupper = gun;
	}

}
