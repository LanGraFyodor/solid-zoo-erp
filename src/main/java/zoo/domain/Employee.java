package zoo.domain;

/**
 * Базовый интерфейс сотрудника зоопарка.
 * Следует ISP - только базовые данные сотрудника.
 */
public interface Employee {
    String getName();

    String getRole();

    void performDuty();
}
