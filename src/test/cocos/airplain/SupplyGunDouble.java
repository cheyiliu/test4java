package test.cocos.airplain;

/**
 * 
 * @author housy 补给枪
 * 
 */
public class SupplyGunDouble extends Supply {
	Gun mGun;

	@Override
	public void supplyGunDouble(Role target) {
		target.gotGunDouble(mGun);
	}

}
