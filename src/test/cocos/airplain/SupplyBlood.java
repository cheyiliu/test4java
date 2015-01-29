package test.cocos.airplain;

/**
 * 
 * @author housy 补给血
 */
public class SupplyBlood extends Supply {
	int mBlood;
	int mRes;
	
	public SupplyBlood() {
		this.mBlood = 100;
		this.mRes = 100;
	}

	@Override
	public void supplyBlood(Role target) {
		target.gotBlood(mBlood);
	}

}
