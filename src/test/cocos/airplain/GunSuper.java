package test.cocos.airplain;

import test.cocos.airplain.Bullet.BulletType;

public class GunSuper extends Gun {

	@Override
	public boolean fire(GamLayer layer, int x, int y) {
		// TODO special handle, kill all
		// 一个透明的或者是看不见的，有屏幕那么大的精灵？ Yes, tested
//		sprite->setVisible(false);
//		Rect rect = mSp->getBoundingBox();
//		log(".................................................menuCloseCallback");
//		//	log("%d", rect.origin.x);
//		//	log("%d", rect.origin.y);
//		//	log("%d", rect.size.width);
//		//	log("%d", rect.size.height);
//			log("getMinX=%d", rect.getMinX());
//			log("getMinY=%d", rect.getMinY());
//			log("getMaxX=%d", rect.getMaxX());
//			log("getMaxY%d", rect.getMaxY());
//
//			 if(mSp3->getBoundingBox().intersectsRect(rect)){
//				 log("intersectsRect");
//			 }else{
//				 log("intersectsRect, no");
//			 }
		layer.addChild(Bullet.createOneBullet(BulletType.BulletTypeYellow), x, y);
		return true;
	}

}
