package zoo.domain;

/**
 * Абстрактный базовый класс сотрудника.
 */
public abstract class AbstractEmployee implements Employee {
    protected final String name;
    protected final String role;

    protected AbstractEmployee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, role);
    }
}
