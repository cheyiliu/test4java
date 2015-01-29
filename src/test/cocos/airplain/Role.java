package test.cocos.airplain;

public interface Role {
	public void attack(Role target);

	public void gotAttacked(int damage);

	public void gotBlood(int blood);

	public void gotGun(Gun gun);

//	public void gotBomb(Gun gun);
}
