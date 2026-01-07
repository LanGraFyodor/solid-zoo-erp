package zoo.domain;

import zoo.interfaces.IFeedable;
import zoo.interfaces.ICleanable;

/**
 * Сотрудник-смотритель, отвечающий за кормление животных.
 * Следует SRP - только кормление.
 */
public class Zookeeper extends AbstractEmployee {

    public Zookeeper(String name) {
        super(name, "Смотритель");
    }

    @Override
    public void performDuty() {
        System.out.println("👷 " + name + " выполняет обход вольеров");
    }

    public void feedAnimal(IFeedable animal, FoodType food) {
        System.out.println("👷 " + name + " кормит животное...");
        animal.feed(food);
    }

    public void clean(ICleanable target) {
        if (target.needsCleaning()) {
            System.out.println("👷 " + name + " начал уборку...");
            target.clean();
        }
    }
}
