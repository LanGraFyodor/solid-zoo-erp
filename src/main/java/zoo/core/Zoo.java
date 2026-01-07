package zoo.core;

import zoo.domain.*;
import zoo.services.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Главный класс зоопарка.
 * Координирует работу всех подсистем.
 */
public class Zoo {
    private final String name;
    private final IAnimalRepository animalRepository;
    private final FeedingService feedingService;
    private final VetService vetService;
    private final ReportService reportService;
    private final List<Enclosure> enclosures;
    private final List<Employee> employees;

    public Zoo(String name, IAnimalRepository animalRepository) {
        this.name = name;
        this.animalRepository = animalRepository;
        this.feedingService = new FeedingService(animalRepository);
        this.vetService = new VetService(animalRepository);
        this.reportService = new ReportService(animalRepository, feedingService, vetService);
        this.enclosures = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // === Управление животными ===

    public void admitAnimal(Animal animal, Enclosure enclosure) {
        System.out.println("\n🎉 === ПОСТУПЛЕНИЕ НОВОГО ЖИВОТНОГО ===");
        System.out.println("Животное: " + animal.getName() + " (" + animal.getSpecies() + ")");

        // Обязательный медосмотр при поступлении
        vetService.conductIntakeExam(animal);

        // Размещение в вольере
        if (enclosure.addAnimal(animal)) {
            animalRepository.add(animal);
            System.out.println("🎊 " + animal.getName() + " успешно принят в зоопарк!");
        }
    }

    public void removeAnimal(Animal animal) {
        animalRepository.remove(animal);
        for (Enclosure enclosure : enclosures) {
            enclosure.removeAnimal(animal);
        }
    }

    // === Управление вольерами ===

    public void addEnclosure(Enclosure enclosure) {
        enclosures.add(enclosure);
        System.out.println("🏠 Добавлен вольер: " + enclosure.getId());
    }

    public List<Enclosure> getEnclosures() {
        return Collections.unmodifiableList(enclosures);
    }

    // === Управление сотрудниками ===

    public void hireEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("👔 Принят сотрудник: " + employee);
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    // === Доступ к сервисам ===

    public FeedingService getFeedingService() {
        return feedingService;
    }

    public VetService getVetService() {
        return vetService;
    }

    public ReportService getReportService() {
        return reportService;
    }

    public IAnimalRepository getAnimalRepository() {
        return animalRepository;
    }

    // === Отчёты ===

    public void printFullStatus() {
        System.out.println("\n🦁🦁🦁 " + name.toUpperCase() + " 🦁🦁🦁");
        reportService.generateFullReport();
        reportService.generateEnclosureReport(enclosures);
    }
}
