package test.cocos.airplain;

/**
 * 
 * @author housy 补给枪
 * 
 */
public class SupplyGun extends Supply {
	Gun mGun;

	@Override
	public void supplyGun(Role target) {
		target.gotGun(mGun);
	}

}
