package zoo.domain;

/**
 * Абстрактный класс для млекопитающих.
 * Следует OCP - можно расширять без изменения.
 */
public abstract class Mammal extends AbstractAnimal {

    protected Mammal(String name, String species, int age) {
        super(name, species, age);
    }

    public void sleep() {
        System.out.println("😴 " + getName() + " спит");
    }
}
