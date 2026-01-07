package zoo;

import zoo.core.Zoo;
import zoo.domain.*;
import zoo.interfaces.*;
import zoo.services.*;

import java.time.LocalTime;
import java.util.List;

/**
 * Демонстрационный класс для ERP-системы зоопарка.
 * Показывает основные сценарии использования.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║        🦁 ERP-СИСТЕМА МОСКОВСКОГО ЗООПАРКА 🦁                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

        // === 1. Инициализация зоопарка с DI ===
        IAnimalRepository repository = new InMemoryAnimalRepository();
        Zoo zoo = new Zoo("Московский Зоопарк", repository);

        // === 2. Создание вольеров ===
        System.out.println("\n📦 === СОЗДАНИЕ ИНФРАСТРУКТУРЫ ===");
        Enclosure lionEnclosure = new Enclosure("L-01", "Хищники", 3);
        Enclosure birdEnclosure = new Enclosure("B-01", "Птицы", 10);
        Enclosure reptileEnclosure = new Enclosure("R-01", "Рептилии", 5);

        zoo.addEnclosure(lionEnclosure);
        zoo.addEnclosure(birdEnclosure);
        zoo.addEnclosure(reptileEnclosure);

        // === 3. Приём сотрудников ===
        System.out.println("\n👥 === ПРИЁМ СОТРУДНИКОВ ===");
        Zookeeper keeper1 = new Zookeeper("Иван Петров");
        Zookeeper keeper2 = new Zookeeper("Мария Сидорова");
        Veterinarian vet = new Veterinarian("Алексей Козлов");

        zoo.hireEmployee(keeper1);
        zoo.hireEmployee(keeper2);
        zoo.hireEmployee(vet);

        // === 4. Поступление животных (с обязательным медосмотром) ===
        System.out.println("\n🐾 === ПОСТУПЛЕНИЕ ЖИВОТНЫХ ===");

        Lion simba = new Lion("Симба", 5);
        Lion nala = new Lion("Нала", 4);
        Parrot kesha = new Parrot("Кеша", 2, "Зелёный");
        Parrot rio = new Parrot("Рио", 3, "Синий");
        Snake nagini = new Snake("Нагини", 7, true);

        // Демонстрация: Нала болеет - будет вылечена при осмотре
        nala.setHealthy(false);

        zoo.admitAnimal(simba, lionEnclosure);
        zoo.admitAnimal(nala, lionEnclosure);
        zoo.admitAnimal(kesha, birdEnclosure);
        zoo.admitAnimal(rio, birdEnclosure);
        zoo.admitAnimal(nagini, reptileEnclosure);

        // === 5. Настройка расписания кормления ===
        System.out.println("\n📅 === НАСТРОЙКА РАСПИСАНИЯ КОРМЛЕНИЯ ===");
        FeedingService feedingService = zoo.getFeedingService();

        feedingService.setFeedingSchedule(simba, List.of(
                LocalTime.of(9, 0),
                LocalTime.of(18, 0)));
        feedingService.setFeedingSchedule(kesha, List.of(
                LocalTime.of(8, 0),
                LocalTime.of(12, 0),
                LocalTime.of(17, 0)));
        feedingService.setFeedingSchedule(nagini, List.of(
                LocalTime.of(10, 0) // Змеи едят реже
        ));

        // === 6. Демонстрация кормления ===
        System.out.println("\n🍖 === ДЕМОНСТРАЦИЯ КОРМЛЕНИЯ ===");
        keeper1.feedAnimal(simba, FoodType.MEAT);
        keeper2.feedAnimal(kesha, FoodType.SEEDS);

        // Кормление неподходящей едой
        System.out.println("\n⚠️ Попытка накормить льва овощами:");
        keeper1.feedAnimal(simba, FoodType.VEGETABLES);

        // === 7. Плановый ветеринарный осмотр ===
        System.out.println("\n🩺 === ВЕТЕРИНАРНЫЙ ОСМОТР ===");
        vet.performDuty();
        zoo.getVetService().examineAllAnimals();

        // === 8. Демонстрация поведения животных ===
        System.out.println("\n🎭 === ДЕМОНСТРАЦИЯ ПОВЕДЕНИЯ ===");
        simba.roar();
        kesha.talk();
        nagini.hiss();
        rio.fly();

        // === 9. Уборка вольеров ===
        System.out.println("\n🧹 === УБОРКА ВОЛЬЕРОВ ===");
        for (Enclosure enclosure : zoo.getEnclosures()) {
            if (enclosure.needsCleaning()) {
                // Используем сотрудника для уборки (DIP implementation)
                keeper1.clean(enclosure);
            }
        }

        // === 10. Полный отчёт ===
        zoo.printFullStatus();

        // === 11. Демонстрация LSP ===
        System.out.println("\n🔬 === ДЕМОНСТРАЦИЯ LSP ===");
        System.out.println("Все животные через единый интерфейс Animal:");
        for (Animal animal : repository.findAll()) {
            System.out.println("  " + animal.makeSound());
        }

        // === 12. Демонстрация расширяемости (OCP) ===
        System.out.println("\n🔧 === ДЕМОНСТРАЦИЯ OCP ===");
        System.out.println("Для добавления нового вида (например, Слон) достаточно:");
        System.out.println("  1. Создать класс Elephant extends Mammal");
        System.out.println("  2. Реализовать getPreferredFood() и makeSound()");
        System.out.println("  3. Никаких изменений в существующих классах!");

        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              ✅ ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
