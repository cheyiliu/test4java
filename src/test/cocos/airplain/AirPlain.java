package test.cocos.airplain;

public class AirPlain extends Role {

	// die state, just empty impl
	public class AirPlainStateDie implements RoleState {
		@Override
		public int onEnter() {
			System.out.println(this + ", onEnter");
			return 0;
		}

		@Override
		public int onExit() {
			System.out.println(this + ", onExit");
			return 0;
		}

		@Override
		public int gotHurt(int hurtHP) {
			System.out.println(this + ", gotHurt, " + hurtHP);
			return 0;
		}

		@Override
		public int gotCure(int cureHP) {
			System.out.println(this + ", gotCure, " + cureHP);
			return 0;
		}

		@Override
		public int atack(Role target) {
			System.out.println(this + ", atack, " + target);
			return 0;
		}

		@Override
		public int cure(Role target) {
			System.out.println(this + ", cure, " + target);
			return 0;
		}

	}

	// normal状态
	public class AirPlainStateNormal implements RoleState {
		// 不同状态下，攻击力不一样，故放在状态比较合适
		int mTheAttackForce = 100;

		public AirPlainStateNormal(int mTheAttackForce) {
			super();
			this.mTheAttackForce = mTheAttackForce;
		}

		@Override
		public int onEnter() {
			System.out.println(this + ", onEnter");
			return 0;
		}

		@Override
		public int onExit() {
			System.out.println(this + ", onExit");
			return 0;
		}

		@Override
		public int gotHurt(int hurtHP) {
			System.out.println(this + ", gotHurt, " + hurtHP);
			return 0;
		}

		@Override
		public int gotCure(int cureHP) {
			System.out.println(this + ", gotCure, " + cureHP);
			return 0;
		}

		@Override
		public int atack(Role target) {
			System.out.println(this + ", atack, " + target);
			return 0;
		}

		@Override
		public int cure(Role target) {
			System.out.println(this + ", cure, " + target);
			return 0;
		}

	}

	public AirPlain(Camp camp, RoleState state, int hp) {
		super(camp, state, hp);
	}

	public AirPlain() {
//		super(Camp.CampBlue, new AirPlainStateNormal(mHP), Config.AirPlainLife_small);
	}
}
