package zoo.services;

import zoo.domain.Animal;
import zoo.domain.FoodType;
import zoo.interfaces.IFeedable;

import java.time.LocalTime;
import java.util.*;

/**
 * Сервис кормления животных.
 * Следует SRP - только логика кормления и расписание.
 * Следует DIP - зависит от IAnimalRepository.
 */
public class FeedingService {
    private final IAnimalRepository repository;
    private final Map<String, List<LocalTime>> feedingSchedule;

    public FeedingService(IAnimalRepository repository) {
        this.repository = repository;
        this.feedingSchedule = new HashMap<>();
    }

    public void setFeedingSchedule(Animal animal, List<LocalTime> times) {
        feedingSchedule.put(animal.getName(), new ArrayList<>(times));
        System.out.println("📅 Расписание кормления для " + animal.getName() + ": " + times);
    }

    public List<LocalTime> getFeedingSchedule(Animal animal) {
        return feedingSchedule.getOrDefault(animal.getName(), Collections.emptyList());
    }

    public void feedAnimal(Animal animal) {
        if (animal instanceof IFeedable feedable) {
            feedable.feed(feedable.getPreferredFood());
        }
    }

    public void feedAnimal(Animal animal, FoodType food) {
        if (animal instanceof IFeedable feedable) {
            feedable.feed(food);
        }
    }

    public void feedAllAnimals() {
        System.out.println("\n🍽️ === МАССОВОЕ КОРМЛЕНИЕ ===");
        for (Animal animal : repository.findAll()) {
            if (animal instanceof IFeedable feedable) {
                feedAnimal(animal);
            }
        }
    }

    public Map<String, List<LocalTime>> getAllSchedules() {
        return Collections.unmodifiableMap(feedingSchedule);
    }

    public void printFeedingSchedule() {
        System.out.println("\n📋 === РАСПИСАНИЕ КОРМЛЕНИЯ ===");
        if (feedingSchedule.isEmpty()) {
            System.out.println("Расписание пусто");
            return;
        }
        for (Map.Entry<String, List<LocalTime>> entry : feedingSchedule.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
