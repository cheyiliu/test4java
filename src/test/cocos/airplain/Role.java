package test.cocos.airplain;

/**
 * 角色类，我们的‘飞机大战’显然是一部武打片， 所以这里的抽象也就重点这对武打类的了 角色行为有攻击，被攻击，治愈，被治愈
 * 角色有状态，他的行为取决于当前的状态 角色的状态可以有正常状态，眩晕状态，狂乱状态，死亡状态等 NOTE: 角色行为的返回值含义暂未定义 NOTE:
 * 代码暂不作防御处理
 */

public abstract class Role {
	// 阵营，红队，蓝队
	public enum Camp {
		CampRed, CampBlue
	}

	// 状态接口定义
	public interface RoleState {

		// 进入状态，可进行攻击力重计算，播放状态动画等
		public int onEnter();

		// 退出状态，作相应清理
		public int onExit();

		// 同角色类说明
		public int gotHurt(int hurtHP);

		// 同角色类说明
		public int gotCure(int cureHP);

		// 同角色类说明
		public int atack(Role target);

		// 同角色类说明
		public int cure(Role target);
	}

	// 角色阵营，是敌是友
	protected Camp mCamp = Camp.CampBlue;

	// 角色当前状态
	protected RoleState mState = null;

	// 血量，不随状态变化，故作为Role的成员比较合适
	protected int mHP;

	// // 不同状态下，攻击力不一样，故放在状态比较合适
	// int mTheAttackForce = 100;

	// 构造
	public Role() {
	}

	// 构造
	public Role(Camp camp, RoleState state, int hp) {
		this.mCamp = camp;
		this.mState = state;
		this.mHP = hp;
	}

	// set/get

	public int getHP() {
		return mHP;
	}

	public void setHP(int hp) {
		this.mHP = hp;
	}

	public Camp getCamp() {
		return mCamp;
	}

	public void setCamp(Camp camp) {
		this.mCamp = camp;
	}

	public RoleState getState() {
		return mState;
	}

	// 改变状态
	public void setState(RoleState state) {
		RoleState preState = mState;
		mState = state;
		mState.onEnter();
		preState.onExit();
	}

	// 角色受伤
	public int gotHurt(int hurtHP) {
		return mState.gotHurt(hurtHP);
	}

	// 角色被治疗
	public int gotCure(int cureHP) {
		return mState.gotCure(cureHP);
	}

	// 角色攻击目标，这里不判断是敌是友，交给状态处理
	public int atack(Role target) {
		return mState.atack(target);
	}

	// 角色治疗目标，这里不判断是敌是友，交给状态处理
	public int cure(Role target) {
		return mState.cure(target);
	}

	// 判断是敌是友
	public boolean isFriend(Role target) {
		return mCamp == target.mCamp;
	}

}
