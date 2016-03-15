package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoExampleGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "de.greenrobot.daogenerator.Schema");
        addFood(schema);
        addFoodType(schema);
        addEpisode(schema);
        addFoodsEpisode(schema);
        new DaoGenerator().generateAll(schema, "../GreenDao/app/src/main/java_gen");
    }

    private static void addFood(Schema schema) {
        Entity food = schema.addEntity("Food");
        food.addIdProperty().primaryKey();
        food.addIntProperty("typeId");
        food.addStringProperty("name").notNull();
    }
    private static void addFoodType(Schema schema) {
        Entity foodType = schema.addEntity("FoodType");
        foodType.addIdProperty().primaryKey();
        foodType.addStringProperty("name").notNull();
    }
    private static void addEpisode(Schema schema){
        Entity episode = schema.addEntity("Episode");
        episode.addIdProperty().primaryKey();
        episode.addStringProperty("name");
    }
    private static void addFoodsEpisode(Schema schema){
        Entity foodsEpisodes = schema.addEntity("FoodsEpisode");
        foodsEpisodes.addIntProperty("foodId");
        foodsEpisodes.addIntProperty("episodeId");
    }
}
