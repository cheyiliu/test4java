package test.cocos.airplain;

public class Supply {
	public enum SupplyType {
		SupplyTypeBlood, SupplyTypeGunDouble, SupplyTypeGunSupper
	}

	public void supplyBlood(Role target) {

	}

	public void supplyGunDouble(Role target) {

	}

	public void supplyGunSuper(Role target) {

	}

	public static Supply createSupply(SupplyType type) {
		if (SupplyType.SupplyTypeBlood == type) {
			return new SupplyBlood();
		} else if (SupplyType.SupplyTypeGunDouble == type) {
			return new SupplyGunDouble();
		} else if (SupplyType.SupplyTypeGunSupper == type) {
			return new SupplyGunSuper();
		} else {
			return null;
		}
	}

}
