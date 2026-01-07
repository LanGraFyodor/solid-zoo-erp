package zoo.domain;

/**
 * Абстрактный класс для птиц.
 * Следует OCP - можно расширять без изменения.
 */
public abstract class Bird extends AbstractAnimal {

    protected Bird(String name, String species, int age) {
        super(name, species, age);
    }

    public void fly() {
        System.out.println("🦅 " + getName() + " летит");
    }
}
