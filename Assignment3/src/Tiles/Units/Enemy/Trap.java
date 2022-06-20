package Tiles.Units.Enemy;

import MainProject.Action;
import Tiles.Units.Players.Player;

public class Trap extends Enemy {

    protected int visibilityTime;
    protected int invisibilityTime;
    protected int ticksCount;
    protected boolean visible;

    public Trap(char tile,String name,int healthPool,int attackPoints,int defensePoints,int experienceValue, int visibilityTime,int invisibiltyTime){
        super(tile,name,healthPool,attackPoints,defensePoints,experienceValue);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibiltyTime;
        visible=true;
        ticksCount =0;
    }

    @Override
    public String toString() {
        if(visible == true)
             return super.toString();
        else return ".";
    }

    @Override
    public Action EnemyTurn(Player player) {
        if (ticksCount < visibilityTime)
            visible = true;
        else
            visible = false;
        if (ticksCount == visibilityTime + invisibilityTime)
            ticksCount = 0;
        else
            ticksCount++;
        if (position.Range(player.getPosition()) < 2)
            player.accept(this);
        return null;
    }

}
