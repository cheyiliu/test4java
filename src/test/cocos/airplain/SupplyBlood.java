package test.cocos.airplain;
/**
 * 
 * @author housy
 * 补给血
 */
public class SupplyBlood extends Supply {
	int mBlood;
	@Override
	public void supplyBlood(Role target) {
		target.gotBlood(mBlood);
	}

}
