package test.cocos.airplain;

/**
 * 
 * @author housy 补给炸弹
 * 
 */
public class SupplyGunSuper extends Supply {
	GunSuper mGun;

	@Override
	public void supplyGunSuper(Role target) {
		target.gotGunSupper(mGun);
	}

}
