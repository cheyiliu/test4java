package test.cocos.airplain;

/**
 * 枪，功能是开火发射子弹；包含静态工厂方法。
 */
public abstract class Gun {
	// 开火， layer是子弹的容器
	// x, y 中心点，单排枪就从这个点发射， 双排枪的话这个就是双排子弹的中心点
	// 返回false表示没子弹了，需要换枪
	public abstract boolean fire(GameLayer layer, int x, int y);

	public enum GunType {
		GunTypeSuper, GunTypeDouble, GunTYpeSingleInfinite
	}

	// 工厂方法
	public static Gun createGun(GunType type) {
		if (GunType.GunTypeDouble == type) {
			return new GunDouble();
		} else if (GunType.GunTYpeSingleInfinite == type) {
			return new GunSingleInfinite();
		} else if (GunType.GunTypeSuper == type) {
			return new GunSuper();
		} else {
			return null;
		}
	}
}
