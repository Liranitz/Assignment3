public abstract class Unit extends Tile {
	protected String name;
    protected Resource health;
    protected int attackPoints;
    protected int defensePoints;

    protected Unit(char tile, String name, int healthCapacity, int attack, int defense) {
        super(tile);
        this.name = name;
        this.health = new Resource(healthCapacity);
        this.attackPoints = attack;
        this.defensePoints = defense;
    }

    protected void initialize(Position position, MessageCallback messageCallback){
        ...
    }
	
    protected int attack(){
		...
    }

    public int defend(){
        ...
    }

	// Should be automatically called once the unit finishes its turn
    public abstract void processStep();
	
	// What happens when the unit dies
    public abstract void onDeath();

	// This unit attempts to interact with another tile.
    public void interact(Tile tile){
		...
    }

    public void visit(Empty e){
		...
    }

    public void visit(Wall e){
		...
    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);


	// Combat against another unit.
    protected void battle(Unit u){
        ...
    }


    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", name, health.healthAmount, attackPoints, defensePoints);
    }
}
