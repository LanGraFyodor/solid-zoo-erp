package zoo.domain;

import zoo.interfaces.IHealable;

/**
 * Ветеринар зоопарка.
 * Следует SRP - только лечение и осмотры.
 */
public class Veterinarian extends AbstractEmployee {

    public Veterinarian(String name) {
        super(name, "Ветеринар");
    }

    @Override
    public void performDuty() {
        System.out.println("👨‍⚕️ " + name + " проводит плановые осмотры");
    }

    public boolean examineAnimal(IHealable animal) {
        System.out.println("👨‍⚕️ " + name + " осматривает животное...");
        return animal.examineHealth();
    }

    public void healAnimal(IHealable animal) {
        System.out.println("👨‍⚕️ " + name + " лечит животное...");
        animal.heal();
    }

    public void conductMedicalExam(Animal animal) {
        System.out.println("🏥 Медицинский осмотр при поступлении: " + animal.getName());
        if (animal instanceof IHealable healable) {
            if (!healable.examineHealth()) {
                healable.heal();
            }
        }
        System.out.println("✅ Осмотр " + animal.getName() + " завершён. Готов к размещению.");
    }
}
