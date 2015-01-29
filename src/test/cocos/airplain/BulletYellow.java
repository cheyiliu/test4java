package test.cocos.airplain;

public class BulletYellow extends Bullet {
	int mForce;

	public void kill(Role target) {
		target.gotAttacked(mForce);
		// after kill, bullet die
	}
}
