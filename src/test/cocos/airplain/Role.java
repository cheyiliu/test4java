package test.cocos.airplain;

/**
 * 角色类定义，属性有：队伍，血量，攻击力，资源等； 行为有：判断是敌是友，攻击，被攻击，获得各种补给； 提供静态工厂方法
 */
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

	// 开火
	public abstract void fire(Role target);

	// 大招-秒全屏
	public abstract void fireSuper(Role target);

	// 撞击
	public abstract void attack(Role target);

	public abstract void gotDamaged(int damage);

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
