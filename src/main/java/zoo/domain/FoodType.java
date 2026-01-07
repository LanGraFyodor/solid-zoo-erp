package zoo.domain;

/**
 * Типы корма для животных.
 */
public enum FoodType {
    MEAT("Мясо"),
    FISH("Рыба"),
    SEEDS("Семена"),
    INSECTS("Насекомые"),
    VEGETABLES("Овощи"),
    FRUITS("Фрукты");

    private final String displayName;

    FoodType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
