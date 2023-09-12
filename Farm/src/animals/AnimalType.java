package animals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum AnimalType {
    // TODO
    SHEEP("Schaf","Schafe"),
    COW("Kuh","Kühe"),
    CHICKEN("Huhn","Hühner"),
    PIG("Schwein","Schweine");

    private String sing;
    private String plur;

    AnimalType(String sing, String plur) {
        this.sing = sing;
        this.plur = plur;
    }

    private String getSing() {
        return sing;
    }

    private String getPlur() {
        return plur;
    }

    public Map<String, String> getAnimalTypeLabels() {
        Map<String, String> animalTypeLabels = new HashMap<>();
        animalTypeLabels.put("sing",getSing());
        animalTypeLabels.put("plur",getPlur());
        return animalTypeLabels;
    }
}
