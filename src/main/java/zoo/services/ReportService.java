package zoo.services;

import zoo.domain.Animal;
import zoo.domain.Enclosure;

import java.util.List;

/**
 * Сервис отчётов.
 * Следует SRP - только генерация отчётов.
 * Следует DIP - зависит от IAnimalRepository.
 */
public class ReportService {
    private final IAnimalRepository animalRepository;
    private final FeedingService feedingService;
    private final VetService vetService;

    public ReportService(IAnimalRepository animalRepository,
            FeedingService feedingService,
            VetService vetService) {
        this.animalRepository = animalRepository;
        this.feedingService = feedingService;
        this.vetService = vetService;
    }

    public void generateFullReport() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("          📊 ПОЛНЫЙ ОТЧЁТ ПО ЗООПАРКУ");
        System.out.println("=".repeat(50));

        generateAnimalSummary();
        feedingService.printFeedingSchedule();
        vetService.printHealthStatus();

        System.out.println("\n" + "=".repeat(50));
    }

    public void generateAnimalSummary() {
        List<Animal> animals = animalRepository.findAll();

        System.out.println("\n🦁 === СВОДКА ПО ЖИВОТНЫМ ===");
        System.out.println("Общее количество: " + animals.size());

        if (!animals.isEmpty()) {
            System.out.println("\nСписок животных:");
            for (Animal animal : animals) {
                System.out.println("  • " + animal);
            }
        }

        // Группировка по видам (Optimizied)
        System.out.println("\nПо видам:");
        java.util.Map<String, Long> speciesCount = animals.stream()
                .collect(java.util.stream.Collectors.groupingBy(Animal::getSpecies,
                        java.util.stream.Collectors.counting()));

        speciesCount.forEach((species, count) -> System.out.println("  • " + species + ": " + count));
    }

    public void generateEnclosureReport(List<Enclosure> enclosures) {
        System.out.println("\n🏠 === ОТЧЁТ ПО ВОЛЬЕРАМ ===");
        System.out.println("Всего вольеров: " + enclosures.size());

        for (Enclosure enclosure : enclosures) {
            System.out.println("  • " + enclosure);
            if (enclosure.needsCleaning()) {
                System.out.println("    ⚠️ Требуется уборка");
            }
        }
    }

    public int getTotalAnimalCount() {
        return animalRepository.count();
    }

    public int getHealthyAnimalCount() {
        return (int) animalRepository.findAll().stream()
                .filter(Animal::isHealthy)
                .count();
    }

    public int getSickAnimalCount() {
        return (int) animalRepository.findAll().stream()
                .filter(a -> !a.isHealthy())
                .count();
    }
}
