public class Trap extends Enemy{
    protected int visibilityTime;
    protected int invisibiltyTime;
    protected int ticksCount;
    protected boolean visible;
    public Trap(int visibilityTime,int invisibiltyTime){
        this.visibilityTime = visibilityTime;
        this.invisibiltyTime= invisibiltyTime;
        visible=true;
        ticksCount =0;
    }

}
