
package test.design.pattern.behavior;

import java.util.Date;

/**
 * http://blog.chinaunix.net/uid-29140694-id-4127905.html
 * http://alaric.iteye.com/blog/1931253
 */
public class testMemento {
    public static void main(String[] args) {
        Game game = new Game();
        HeroState hState = new HeroState();
        hState.setHP(100);
        hState.setMP(100);
        SceneState sState = new SceneState();
        sState.setCoin(1000);
        sState.setWood(1000);
        game.sethState(hState);
        game.setsState(sState);
        System.out.println("游戏状态备份开始");
        GameMemento memento = game.createMemento();
        Caretaker ct = new Caretaker();
        ct.setMemento(memento);
        System.out.println("游戏状态备份完成");
        System.out.println("开始游戏，当前英雄生命值：" + game.gethState().getHP());
        game.play();
        System.out.println("游戏结束，当前英雄生命值：" + game.gethState().getHP());
        System.out.println("游戏状态还原开始");
        game.restore(ct.getMemento());
        System.out.println("游戏状态还原结束");
        System.out.println("当前英雄生命值：" + game.gethState().getHP());
    }
}

/**
 * 游戏自身（备忘录模式中的发起人，备份的是游戏的状态）
 */
class Game {

    /**
     * 英雄状态属性
     */
    private HeroState hState;

    /**
     * 场景状态属性
     */
    private SceneState sState;

    public HeroState gethState() {
        return hState;
    }

    public void sethState(HeroState hState) {
        this.hState = hState;
    }

    public SceneState getsState() {
        return sState;
    }

    public void setsState(SceneState sState) {
        this.sState = sState;
    }

    /**
     * 备份游戏
     */
    public GameMemento createMemento() {
        return new GameMemento(hState, sState);
    }

    /**
     * 玩游戏
     * 
     * @throws InterruptedException
     */
    public void play() {
        hState.setHP(0);
        hState.setMP(0);
        sState.setCoin(0);
        sState.setWood(0);
    }

    /**
     * 游戏还原
     */
    public void restore(GameMemento memento) {
        this.hState = memento.gethState();
        this.sState = memento.getsState();
    }
}

/**
 * 游戏英雄人物状态实体
 */
class HeroState implements Cloneable {

    /**
     * 英雄生命值
     */
    private int HP;

    /**
     * 英雄魔法值
     */
    private int MP;

    /**
     * 状态保存时间
     */
    private Date stateDate;

    public int getHP() {
        return HP;
    }

    public void setHP(int hP) {
        HP = hP;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int mP) {
        MP = mP;
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }

    public HeroState clone() {
        try {
            return (HeroState) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}

/**
 * 游戏场景状态实体
 */
class SceneState implements Cloneable {

    /**
     * 金币数量
     */
    private int coin;

    /**
     * 木材数量
     */
    private int wood;

    /**
     * 地图名称
     */
    private String mapName;

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public SceneState clone() {
        try {
            return (SceneState) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}

/**
 * 游戏备忘录角色
 */
class GameMemento {

    /**
     * 英雄状态
     */
    private HeroState hState;

    /**
     * 场景状态
     */
    private SceneState sState;

    /**
     * 构造方法
     * 
     * @param hState
     * @param sState
     */
    public GameMemento(HeroState hState, SceneState sState) {
        this.hState = hState.clone();
        this.sState = sState.clone();
    }

    /**
     * 获取备份状态
     * 
     * @return
     */
    public HeroState gethState() {
        return hState;
    }

    /**
     * 获取备份状态
     * 
     * @return
     */
    public SceneState getsState() {
        return sState;
    }
}

/**
 * 备忘录管理器
 */
class Caretaker {

    /**
     * 备忘录实体
     */
    private GameMemento memento;

    public GameMemento getMemento() {
        return memento;
    }

    public void setMemento(GameMemento memento) {
        this.memento = memento;
    }
}
