package zoo.domain;

/**
 * Конкретная реализация змеи.
 * Следует LSP - может использоваться везде, где ожидается Animal.
 */
public class Snake extends Reptile {

    private final boolean venomous;

    public Snake(String name, int age, boolean venomous) {
        super(name, "Змея", age);
        this.venomous = venomous;
    }

    @Override
    public FoodType getPreferredFood() {
        return FoodType.MEAT;
    }

    @Override
    public String makeSound() {
        return "🐍 " + getName() + " шипит: Шшшш!";
    }

    public boolean isVenomous() {
        return venomous;
    }

    public void hiss() {
        System.out.println(makeSound());
    }
}
