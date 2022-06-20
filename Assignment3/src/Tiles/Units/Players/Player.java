package Tiles.Units.Players;

import CallBacks.EnemyDeathCallback;
import CallBacks.PlayerDeathCallback;
import MainProject.Action;
import MainProject.InputProvider;
import MainProject.InputQuery;
import Tiles.Units.Enemy.Enemy;
import Tiles.Units.Resource;
import Tiles.Units.Unit;

public class Player extends Unit{
    Resource resource;
    Integer experience;
    Integer level;
    protected PlayerDeathCallback edCallback;
    private InputProvider inputProvider;



    public Player (Resource resource , Integer experience, Integer level) {
        super('@',);
        this.experience = experience;
        this.resource = resource;
        this.level = level;
        inputProvider = new InputProvider();
    }
    public void SetDeathCallback(PlayerDeathCallback edCallback){
        edCallback.call();
    }


    public void Levelup(){
        return;
    }
    public void GameTick(){
        return;
    }
    public void AbilityCast(){
        return;
    }


    public Action GetInput() {
        return inputProvider.getInput();
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }

    @Override
    public void processStep() {

    }

    @Override
    public void onDeath() {
        SetDeathCallback(edCallback);
    }

    @Override
    public void visit(Player p) {
        //empty implementation
    }



    @Override
    public void visit(Enemy e) {
    battle(e);
    }
}
