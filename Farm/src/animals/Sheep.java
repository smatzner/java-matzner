package animals;

public class Sheep extends Animal {
    private int wool;

    public Sheep(String name, int hunger, int age, int wool) {
        super(name, hunger, age);
        this.wool = wool;
    }

    public Sheep() {
    }

    public int getWool() {
        return wool;
    }

    public void setWool(int wool) {
        this.wool = wool;
    }

    @Override
    public void feed(){
        super.feed();
        wool++;
    }
}
