package test.cocos.airplain;

/**
 * 补给-双排子弹的枪
 */
public class SupplyGunDouble extends Supply {
	Gun mGun;

	@Override
	public void supplyGunDouble(Role target) {
		target.gotGunDouble(mGun);
	}

}
