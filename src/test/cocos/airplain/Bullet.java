package test.cocos.airplain;

/**
 * 子弹： 要么一直飞，直到掉地上； 要么遇到可攻击对象，炸了
 */
public class Bullet {
	private int mForce;

	public void kill(Role role) {
		if (role != null) {
			role.gotHurt(mForce);
		}
		// TODO for cocos, remove self or put self to cache pool
	}

}
