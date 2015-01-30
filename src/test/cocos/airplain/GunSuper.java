package test.cocos.airplain;

import test.cocos.airplain.Bullet.BulletType;

/**
 * 超级枪(游戏里看到的炸弹)，发射的子弹可以全屏灭怪。 顺便提下实现思路，将游戏背景的图片作为超级子弹的图片，但设置为不可见的，
 * 这样碰撞监测的时候，所有的怪物全部都和他碰撞了。这样的话保证了游戏处理逻辑和普通枪支的逻辑一致。
 */
public class GunSuper extends Gun {

	@Override
	public boolean fire(GameLayer layer, int x, int y) {
		// TODO special handle, kill all
		// 一个透明的或者是看不见的，有屏幕那么大的精灵？ Yes, tested
		// sprite->setVisible(false);
		// Rect rect = mSp->getBoundingBox();
		// log(".................................................menuCloseCallback");
		// // log("%d", rect.origin.x);
		// // log("%d", rect.origin.y);
		// // log("%d", rect.size.width);
		// // log("%d", rect.size.height);
		// log("getMinX=%d", rect.getMinX());
		// log("getMinY=%d", rect.getMinY());
		// log("getMaxX=%d", rect.getMaxX());
		// log("getMaxY%d", rect.getMaxY());
		//
		// if(mSp3->getBoundingBox().intersectsRect(rect)){
		// log("intersectsRect");
		// }else{
		// log("intersectsRect, no");
		// }
		layer.addChild(Bullet.createOneBullet(BulletType.BulletTypeSuper), x, y);
		return true;
	}

}
