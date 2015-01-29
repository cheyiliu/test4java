package test.cocos.airplain;

import test.cocos.airplain.Bullet.BulletType;

public class GunSuper extends Gun {

	@Override
	public boolean fire(GamLayer layer, int x, int y) {
		// TODO special handle, kill all
		// 一个透明的或者是看不见的，有屏幕那么大的精灵？
		layer.addChild(Bullet.createOneBullet(BulletType.BulletTypeYellow), x, y);
		return true;
	}

}
