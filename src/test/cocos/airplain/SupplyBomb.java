package test.cocos.airplain;
/**
 * 
 * @author housy
 * 补给炸弹
 *
 */
public class SupplyBomb extends Supply {
	Gun mGun;
	@Override
	public void supplyBomb(Role target) {
		target.gotGun(mGun);
	}

}
