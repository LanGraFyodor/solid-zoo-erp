package zoo.services;

import zoo.domain.Animal;
import zoo.interfaces.IHealable;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Ветеринарный сервис.
 * Следует SRP - только медицинские осмотры и лечение.
 * Следует DIP - зависит от IAnimalRepository.
 */
public class VetService {
    private final IAnimalRepository repository;
    private final Map<String, List<LocalDateTime>> medicalHistory;

    public VetService(IAnimalRepository repository) {
        this.repository = repository;
        this.medicalHistory = new HashMap<>();
    }

    public boolean conductIntakeExam(Animal animal) {
        System.out.println("\n🏥 === ВХОДНОЙ МЕДОСМОТР ===");
        System.out.println("Пациент: " + animal.getName() + " (" + animal.getSpecies() + ")");

        boolean isHealthy = true;
        if (animal instanceof IHealable healable) {
            isHealthy = healable.examineHealth();
            if (!isHealthy) {
                System.out.println("⚠️ Обнаружены проблемы со здоровьем. Требуется лечение.");
                healable.heal();
            }
        }

        recordExam(animal);
        System.out.println("✅ Медосмотр завершён. " + animal.getName() + " допущен к размещению.");
        return true;
    }

    public void examineAnimal(Animal animal) {
        if (animal instanceof IHealable healable) {
            healable.examineHealth();
            recordExam(animal);
        }
    }

    public void healAnimal(Animal animal) {
        if (animal instanceof IHealable healable) {
            healable.heal();
            recordExam(animal);
        }
    }

    public void examineAllAnimals() {
        System.out.println("\n🩺 === ПЛАНОВЫЙ ОСМОТР ВСЕХ ЖИВОТНЫХ ===");
        for (Animal animal : repository.findAll()) {
            examineAnimal(animal);
        }
    }

    private void recordExam(Animal animal) {
        medicalHistory.computeIfAbsent(animal.getName(), k -> new ArrayList<>())
                .add(LocalDateTime.now());
    }

    public List<LocalDateTime> getMedicalHistory(Animal animal) {
        return medicalHistory.getOrDefault(animal.getName(), Collections.emptyList());
    }

    public List<Animal> getSickAnimals() {
        List<Animal> sick = new ArrayList<>();
        for (Animal animal : repository.findAll()) {
            if (!animal.isHealthy()) {
                sick.add(animal);
            }
        }
        return sick;
    }

    public void printHealthStatus() {
        System.out.println("\n🏥 === СТАТУС ЗДОРОВЬЯ ===");
        for (Animal animal : repository.findAll()) {
            String status = animal.isHealthy() ? "✅ здоров" : "❌ болен";
            System.out.println("  " + animal.getName() + ": " + status);
        }
    }
}
