package zoo.domain;

import zoo.interfaces.IFeedable;
import zoo.interfaces.IHealable;

/**
 * Абстрактный базовый класс для всех животных.
 * Реализует общую логику для Animal, IFeedable, IHealable.
 */
public abstract class AbstractAnimal implements Animal, IFeedable, IHealable {
    protected final String name;
    protected final String species;
    protected final int age;
    protected boolean healthy;
    protected int hungerLevel;

    protected AbstractAnimal(String name, String species, int age) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.healthy = true;
        this.hungerLevel = 50;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSpecies() {
        return species;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public boolean isHealthy() {
        return healthy;
    }

    @Override
    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }

    @Override
    public void feed(FoodType food) {
        if (food == getPreferredFood()) {
            hungerLevel = Math.max(0, hungerLevel - 30);
            System.out.println("🍖 " + name + " с удовольствием съел " + food.getDisplayName());
        } else {
            hungerLevel = Math.max(0, hungerLevel - 10);
            System.out.println("🍽️ " + name + " съел " + food.getDisplayName() + " без энтузиазма");
        }
    }

    @Override
    public void heal() {
        this.healthy = true;
        System.out.println("💊 " + name + " прошёл лечение и теперь здоров");
    }

    @Override
    public boolean examineHealth() {
        System.out.println("🩺 Осмотр " + name + ": " + (healthy ? "здоров" : "нуждается в лечении"));
        return healthy;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

    public void increaseHunger(int amount) {
        hungerLevel = Math.min(100, hungerLevel + amount);
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %d лет, %s)",
                name, species, age, healthy ? "здоров" : "болен");
    }
}
