package test.cocos.airplain;

/**
 * 补给，超级枪
 */
public class SupplyGunSuper extends Supply {
	GunSuper mGun;

	@Override
	public void supplyGunSuper(Role target) {
		target.gotGunSupper(mGun);
	}

}
