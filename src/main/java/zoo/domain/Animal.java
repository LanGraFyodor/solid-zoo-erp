package zoo.domain;

/**
 * Базовый интерфейс для всех животных зоопарка.
 * Следует принципу ISP - содержит только базовые характеристики животного.
 */
public interface Animal {
    String getName();

    String getSpecies();

    int getAge();

    boolean isHealthy();

    void setHealthy(boolean healthy);

    String makeSound();
}
