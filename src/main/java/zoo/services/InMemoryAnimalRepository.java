package zoo.services;

import zoo.domain.Animal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * In-memory реализация репозитория животных.
 * Следует DIP - конкретная реализация интерфейса IAnimalRepository.
 */
public class InMemoryAnimalRepository implements IAnimalRepository {
    private final Map<String, Animal> animals = new HashMap<>();

    @Override
    public void add(Animal animal) {
        animals.put(animal.getName(), animal);
    }

    @Override
    public void remove(Animal animal) {
        animals.remove(animal.getName());
    }

    @Override
    public Optional<Animal> findByName(String name) {
        return Optional.ofNullable(animals.get(name));
    }

    @Override
    public List<Animal> findAll() {
        return new ArrayList<>(animals.values());
    }

    @Override
    public List<Animal> findBySpecies(String species) {
        return animals.values().stream()
                .filter(a -> a.getSpecies().equals(species))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return animals.size();
    }
}
