package test.cocos.airplain;

public abstract class Gun {
	// 开火， layer是子弹的容器
	// x, y 中心点，单排枪就从这个点发射， 双排枪的话这个就是双排子弹的中心点
	// 返回false表示没子弹了，需要换枪
	public abstract boolean fire(GamLayer layer, int x, int y);
}
