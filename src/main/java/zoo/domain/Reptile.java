package zoo.domain;

/**
 * Абстрактный класс для рептилий.
 * Следует OCP - можно расширять без изменения.
 */
public abstract class Reptile extends AbstractAnimal {

    protected Reptile(String name, String species, int age) {
        super(name, species, age);
    }

    public void bask() {
        System.out.println("☀️ " + getName() + " греется на солнце");
    }
}
