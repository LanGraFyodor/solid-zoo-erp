package zoo.domain;

/**
 * Конкретная реализация попугая.
 * Следует LSP - может использоваться везде, где ожидается Animal.
 */
public class Parrot extends Bird {

    private final String color;

    public Parrot(String name, int age, String color) {
        super(name, "Попугай", age);
        this.color = color;
    }

    @Override
    public FoodType getPreferredFood() {
        return FoodType.SEEDS;
    }

    @Override
    public String makeSound() {
        return "🦜 " + getName() + " говорит: Привет! Привет!";
    }

    public String getColor() {
        return color;
    }

    public void talk() {
        System.out.println(makeSound());
    }
}
