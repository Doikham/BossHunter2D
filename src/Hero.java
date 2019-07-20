public class Hero {
    private int health;
    private int atk;

    public void attack(){

    }

    public void damaged(int dmg)
    {
        this.health = this.health - dmg;
    }

    public int getHealth(){
        if(this.health <=0) return 0;
        return health;
    }

    public void setHealth(int h){
        this.health = h;
    }
}
