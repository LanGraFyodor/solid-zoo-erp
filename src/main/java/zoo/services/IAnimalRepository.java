package zoo.services;

import zoo.domain.Animal;
import java.util.List;
import java.util.Optional;

/**
 * Интерфейс репозитория животных.
 * Следует DIP - сервисы зависят от абстракции, а не от конкретной реализации.
 */
public interface IAnimalRepository {
    void add(Animal animal);

    void remove(Animal animal);

    Optional<Animal> findByName(String name);

    List<Animal> findAll();

    List<Animal> findBySpecies(String species);

    int count();
}
