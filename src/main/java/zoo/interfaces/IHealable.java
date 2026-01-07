package zoo.interfaces;

/**
 * Интерфейс для лечения животных.
 * Следует принципу ISP - отделён от других аспектов ухода.
 */
public interface IHealable {
    void heal();

    boolean examineHealth();
}
