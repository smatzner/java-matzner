package farm;

import animals.Chicken;
import animals.Cow;
import animals.Sheep;

public class Farm {
    private Chicken chicken;
    private Cow cow;
    private Sheep sheep;

    public Farm() {
    }

    public Chicken getChicken() {
        return chicken;
    }

    public void setChicken(Chicken chicken) {
        this.chicken = chicken;
    }

    public Cow getCow() {
        return cow;
    }

    public void setCow(Cow cow) {
        this.cow = cow;
    }

    public Sheep getSheep() {
        return sheep;
    }

    public void setSheep(Sheep sheep) {
        this.sheep = sheep;
    }
}
