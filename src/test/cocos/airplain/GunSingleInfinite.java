package test.cocos.airplain;

import test.cocos.airplain.Bullet.BulletType;

/**
 * 单排子弹的枪，默认的，一次发射一枚，子弹无限
 */
public class GunSingleInfinite extends Gun {

	@Override
	public boolean fire(GameLayer layer, int x, int y) {
		layer.addChild(Bullet.createOneBullet(BulletType.BulletTypeYellow), x,
				y);
		return true;
	}

}
