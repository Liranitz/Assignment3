package Tiles.Units.Players;

public class Mage extends Player {
    private Integer manaPool;
    private Integer currentMana;
    private Integer manaCost;
    private Integer spellPower;
    private Integer hitsCount;
    private Integer abilityRange;
    public Mage(Resource resource, Integer experience, Integer level) {
        super(resource, experience, level);
    }


    public void LevelUp(){
        super(LevelUp());
        // adding the specific level
    }
}
