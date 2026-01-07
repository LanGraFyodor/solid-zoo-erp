package zoo.interfaces;

/**
 * Интерфейс для уборки вольера.
 * Следует принципу ISP - отделён от других аспектов ухода.
 */
public interface ICleanable {
    void clean();

    boolean needsCleaning();
}
