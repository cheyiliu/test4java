package test.cocos.airplain;

public abstract class Role {
	public enum RoleTeam {
		RoleTeamHero, RoleTeamMonster
	}

	public enum RoleType {
		RoleTypeHero, RoleTypeMonsterBig, RoleTypeMonsterSmall, RoleTypeMonsterMiddle
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

	public static Role createRole(RoleType type) {
		if (RoleType.RoleTypeHero == type) {
			return new RoleHero();
		} else if (RoleType.RoleTypeMonsterBig == type) {
			return new RoleEmemyBig();
		} else if (RoleType.RoleTypeMonsterMiddle == type) {
			return new RoleEmemyMiddle();
		} else if (RoleType.RoleTypeMonsterSmall == type) {
			return new RoleEmemySmall();
		} else {
			return null;
		}
	}
}
