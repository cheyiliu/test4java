package test.cocos.airplain;

/**
 * 敌人：只能有攻击和被攻击行为
 */
public abstract class RoleEmemy extends Role {

	public RoleEmemy(RoleTeam mTeam, int mHP, int mForce, int mRes) {
		super(mTeam, mHP, mForce, mRes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(Role target) {
		if (!isFriend(target)) {
			target.gotDamaged(mForce);
		}
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
	}

	@Override
	public void gotGunDouble(Gun gun) {
	}

	@Override
	public void gotGunSupper(Gun gun) {
	}

	@Override
	public void fire(Role target) {
	}

	@Override
	public void fireSuper(Role target) {
	}

}
