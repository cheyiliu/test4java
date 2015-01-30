package test.cocos.airplain;

/**
 * 补给血包
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
