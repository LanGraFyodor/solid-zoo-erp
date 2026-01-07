package zoo.domain;

import zoo.interfaces.ICleanable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс вольера для содержания животных.
 * Следует SRP - только управление вольером и его содержимым.
 */
public class Enclosure implements ICleanable {
    private final String id;
    private final String type;
    private final int capacity;
    private final List<Animal> animals;
    private boolean needsCleaning;

    public Enclosure(String id, String type, int capacity) {
        this.id = id;
        this.type = type;
        this.capacity = capacity;
        this.animals = new ArrayList<>();
        this.needsCleaning = false;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }

    public boolean addAnimal(Animal animal) {
        if (animals.size() < capacity) {
            animals.add(animal);
            needsCleaning = true;
            System.out.println("🏠 " + animal.getName() + " помещён в вольер " + id);
            return true;
        }
        System.out.println("❌ Вольер " + id + " полон!");
        return false;
    }

    public boolean removeAnimal(Animal animal) {
        if (animals.remove(animal)) {
            System.out.println("🚪 " + animal.getName() + " убран из вольера " + id);
            return true;
        }
        return false;
    }

    public int getAnimalCount() {
        return animals.size();
    }

    public boolean hasSpace() {
        return animals.size() < capacity;
    }

    @Override
    public void clean() {
        needsCleaning = false;
        System.out.println("🧹 Вольер " + id + " убран");
    }

    @Override
    public boolean needsCleaning() {
        return needsCleaning;
    }

    public void setNeedsCleaning(boolean needsCleaning) {
        this.needsCleaning = needsCleaning;
    }

    @Override
    public String toString() {
        return String.format("Вольер %s (%s): %d/%d животных", id, type, animals.size(), capacity);
    }
}
