package test.cocos.airplain;

public abstract class RoleEmemy extends Role {

	public RoleEmemy(RoleTeam mTeam, int mHP, int mForce, int mRes) {
		super(mTeam, mHP, mForce, mRes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(Role target) {
		if (!isFriend(target)) {
			target.gotAttacked(mForce);
		}
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
	}

	@Override
	public void gotGunDouble(Gun gun) {
	}

	@Override
	public void gotGunSupper(Gun gun) {
	}

}
