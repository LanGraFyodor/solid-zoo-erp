package zoo.interfaces;

import zoo.domain.FoodType;

/**
 * Интерфейс для кормления животных.
 * Следует принципу ISP - отделён от других аспектов ухода.
 */
public interface IFeedable {
    void feed(FoodType food);

    FoodType getPreferredFood();
}
