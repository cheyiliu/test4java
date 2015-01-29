package test.cocos.airplain;

public abstract class Role {
	public enum RoleTeam {
		RoleTeamHero, RoleTeamMonster
	}

	protected RoleTeam mTeam;
	protected int mHP;
	protected int mForce;
	protected int mRes;

	public Role(RoleTeam mTeam, int mHP, int mForce, int mRes) {
		this.mTeam = mTeam;
		this.mHP = mHP;
		this.mForce = mForce;
		this.mRes = mRes;
	}

	public boolean isFriend(Role target) {
		if (target.mTeam == mTeam) {
			return true;
		}
		return false;
	}

	public abstract void attack(Role target);

	public abstract void gotAttacked(int damage);

	public abstract void gotBlood(int blood);

	public abstract void gotGunDouble(Gun gun);

	public abstract void gotGunSupper(Gun gun);
}
