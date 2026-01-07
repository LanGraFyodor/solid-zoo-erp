package zoo.domain;

/**
 * Конкретная реализация льва.
 * Следует LSP - может использоваться везде, где ожидается Animal.
 */
public class Lion extends Mammal {

    public Lion(String name, int age) {
        super(name, "Лев", age);
    }

    @Override
    public FoodType getPreferredFood() {
        return FoodType.MEAT;
    }

    @Override
    public String makeSound() {
        return "🦁 " + getName() + " рычит: РРРР!";
    }

    public void roar() {
        System.out.println(makeSound());
    }
}
