package test.cocos.airplain;

import java.util.ArrayList;

/**
 * 子弹基类，属性有威力，资源等；行为有杀伤；同时有静态工厂方法(后期开发看需求是否需要单独分离出工厂类)
 */
public class Bullet {
	// 子弹类型：单排黄，双排蓝和超级子弹(灭全屏)
	public enum BulletType {
		BulletTypeYellow, BulletTypeBlue, BulletTypeSuper
	}

	// 威力(杀伤力)
	protected int mForce;
	// 资源(后面用cocos实现肯定要改)
	protected int mRes;

	// 构造
	public Bullet(int mForce, int mRes) {
		this.mForce = mForce;
		this.mRes = mRes;
	}

	// 杀伤目标
	public void kill(Role target) {
		target.gotAttacked(mForce);
		// after kill, bullet die
	}

	// 工厂方法：批量生产子弹
	public static ArrayList<Bullet> createBullets(BulletType type, int num) {
		if (BulletType.BulletTypeBlue == type) {
			ArrayList<Bullet> bulltes = new ArrayList<Bullet>(num);
			for (int i = 0; i < num; i++) {
				bulltes.add(new BulletBlue());
			}
			return bulltes;
		} else if (BulletType.BulletTypeYellow == type) {
			ArrayList<Bullet> bulltes = new ArrayList<Bullet>(num);
			for (int i = 0; i < num; i++) {
				bulltes.add(new BulletYellow());
			}
			return bulltes;
		} else if (BulletType.BulletTypeSuper == type) {
			ArrayList<Bullet> bulltes = new ArrayList<Bullet>(num);
			for (int i = 0; i < num; i++) {
				bulltes.add(new BulletSupper());
			}
			return bulltes;
		}
		return null;
	}

	// 工厂方法：生产单个子弹
	public static Bullet createOneBullet(BulletType type) {
		if (BulletType.BulletTypeBlue == type) {
			return new BulletBlue();
		} else if (BulletType.BulletTypeYellow == type) {
			return new BulletYellow();
		} else if (BulletType.BulletTypeYellow == type) {
			return new BulletSupper();
		}
		return null;
	}
}
