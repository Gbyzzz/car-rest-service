package ua.foxminded.pinchuk.javaspring.carrestservice;

import org.junit.jupiter.params.provider.Arguments;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Source {

    public static Brand brand1 = new Brand(1L, "Mercedes");
    public static Brand brand2 = new Brand(2L, "BMW");
    public static Brand brand3 = new Brand(3L, "Audi");

    public static Brand brand4 = new Brand(4L, "Porsche");

    public static List<Brand> brands = new ArrayList<>() {{
        add(brand1);
        add(brand2);
        add(brand3);
    }};

    public static Type type1 = new Type(1L, "Sedan");
    public static Type type2 = new Type(2L, "Hatchback");
    public static Type type3 = new Type(3L, "Coupe");
    public static Type type4 = new Type(4L, "SUV");
    public static List<Type> types = new ArrayList<>() {{
        add(type1);
        add(type2);
        add(type3);
    }};

    public static Model model1 = new Model("1", brand1, 2020, "C-Class", new ArrayList<>() {{
        add(type1);
        add(type3);
    }});
    public static Model model2 = new Model("2", brand1, 2021, "C-Class", new ArrayList<>() {{
        add(type1);
    }});

    public static Model model3 = new Model("3", brand1, 2021, "E-Class", new ArrayList<>() {{
        add(type1);
        add(type3);
    }});
    public static Model model4 = new Model("4", brand2, 2020, "420d", new ArrayList<>() {{
        add(type1);
        add(type2);
    }});
    public static Model model5 = new Model("5", brand2, 2022, "530d", new ArrayList<>() {{
        add(type1);
    }});

    public static Model model6 = new Model("6", brand2, 2021, "420d", new ArrayList<>() {{
        add(type1);
        add(type3);
    }});
    public static Model model7 = new Model("7", brand3, 2019, "A4", new ArrayList<>() {{
        add(type1);
        add(type3);
    }});
    public static Model model8 = new Model("8", brand3, 2018, "A6", new ArrayList<>() {{
        add(type1);
    }});
    public static Model model9 = new Model("9", brand3, 2019, "A6", new ArrayList<>() {{
        add(type1);
    }});
    public static Model model10 = new Model("2131", brand1, 2017, "S-Class", new ArrayList<>() {{
        add(type1);
    }});
    public static List<Model> models = new ArrayList<>() {{
        add(model1);
        add(model2);
        add(model3);
        add(model4);
        add(model5);
        add(model6);
        add(model7);
        add(model8);
        add(model9);
    }};

    public static Car car1 = new Car(1L, new CarModelType(1L, model1, type1),
            "Silver","2765AV");
    public static Car car2 = new Car(2L, new CarModelType(11L, model7, type1),
            "Black","2711AW");
    public static Car car3 = new Car(3L, new CarModelType(3L, model2, type1),
            "Green","2768AV");
    public static Car car4 = new Car(4L, new CarModelType(8L, model5, type1),
            "White","3765AQ");

    public static Car car5 = new Car(5L, new CarModelType(3L, model2, type1),
            "White","3768AQ");
    public static List<Car> cars = new ArrayList<>() {{
        add(car1);
        add(car2);
        add(car3);
        add(car4);
    }};

    public static Stream<Arguments> provideModelsByBrand() {
        return Stream.of(
                Arguments.of("/api/v1/manufacturers/" + brand1.getName(),
                        new ArrayList<Model>() {{
                            add(model1);
                            add(model2);
                            add(model3);
                        }}),
                Arguments.of("/api/v1/manufacturers/" + brand1.getName() +
                                "?year_min=2021",
                        new ArrayList<Model>() {{
                            add(model2);
                            add(model3);
                        }}),
                Arguments.of("/api/v1/manufacturers/" + brand1.getName() +
                                "?year_max=2020",
                        new ArrayList<Model>() {{
                            add(model1);
                        }}),
                Arguments.of("/api/v1/manufacturers/" + brand1.getName() +
                                "?type=coupe",
                        new ArrayList<Model>() {{
                            add(model1);
                            add(model3);
                        }}),
                Arguments.of("/api/v1/manufacturers/" + brand1.getName() +
                                "?type=coupe&year_min=2021",
                        new ArrayList<Model>() {{
                            add(model3);
                        }}),
                Arguments.of("/api/v1/manufacturers/" + brand2.getName() +
                                "/" + model4.getName(),
                        new ArrayList<Model>() {{
                            add(model4);
                            add(model6);
                        }}),
                Arguments.of("/api/v1/manufacturers/" + brand2.getName() +
                                "/" + model4.getName() + "?type=coupe",
                        new ArrayList<Model>() {{
                            add(model6);
                        }}),
                Arguments.of("/api/v1/manufacturers/" + brand2.getName() +
                                "/" + model4.getName() + "?year_max=2020",
                        new ArrayList<Model>() {{
                            add(model4);
                        }}));
    }


    public static Stream<Arguments> provideModelsByBrandAndName() {
        return Stream.of(
                Arguments.of("/api/v1/manufacturers/" + brand1.getName() + "/C-Class",
                        new ArrayList<Model>() {{
                            add(model1);
                            add(model2);
                        }}),
                Arguments.of("/api/v1/manufacturers/" + brand2.getName() + "/420d",
                        new ArrayList<Model>() {{
                            add(model4);
                            add(model6);
                        }}),
                Arguments.of("/api/v1/manufacturers/" + brand3.getName() + "/A6",
                        new ArrayList<Model>() {{
                            add(model8);
                            add(model9);
                        }}));
    }

    public static Stream<Arguments> provideModelByBrandAndNameAndYear() {
        return Stream.of(
                Arguments.of("/api/v1/manufacturers/" + model1.getBrand().getName() + "/"
                        + model1.getName() + "/" + model1.getYear(), model1),
                Arguments.of("/api/v1/manufacturers/" + model2.getBrand().getName() + "/"
                        + model2.getName() + "/" + model2.getYear(), model2),
                Arguments.of("/api/v1/manufacturers/" + model3.getBrand().getName() + "/"
                        + model3.getName() + "/" + model3.getYear(), model3),
                Arguments.of("/api/v1/manufacturers/" + model4.getBrand().getName() + "/"
                        + model4.getName() + "/" + model4.getYear(), model4),
                Arguments.of("/api/v1/manufacturers/" + model5.getBrand().getName() + "/"
                        + model5.getName() + "/" + model5.getYear(), model5),
                Arguments.of("/api/v1/manufacturers/" + model6.getBrand().getName() + "/"
                        + model6.getName() + "/" + model6.getYear(), model6),
                Arguments.of("/api/v1/manufacturers/" + model7.getBrand().getName() + "/"
                        + model7.getName() + "/" + model7.getYear(), model7),
                Arguments.of("/api/v1/manufacturers/" + model8.getBrand().getName() + "/"
                        + model8.getName() + "/" + model8.getYear(), model8),
                Arguments.of("/api/v1/manufacturers/" + model9.getBrand().getName() + "/"
                        + model9.getName() + "/" + model9.getYear(), model9));
    }


    public static Stream<Arguments> provideBrandById() {
        return IntStream.range(0, brands.size())
                .mapToObj(index -> Arguments.of(brands.get(index), index + 1));
    }


    public static Stream<Arguments> provideTypesById() {
        return IntStream.range(0, types.size())
                .mapToObj(index -> Arguments.of(types.get(index), index + 1));
    }

    public static Stream<Arguments> provideCars() {
        return Stream.of(
                Arguments.of("/api/v1/cars" + "?manufacturer=mercedes",
                        new ArrayList<Car>() {{
                            add(car1);
                            add(car3);
                        }}),
                Arguments.of("/api/v1/cars",
                        cars),
                Arguments.of("/api/v1/cars" + "?color=silver",
                        new ArrayList<Car>() {{
                            add(car1);
                        }}),
                Arguments.of("/api/v1/cars" + "?type=sedan",
                        cars),
                Arguments.of("/api/v1/cars" + "?year_max=2021",
                        new ArrayList<Car>() {{
                            add(car1);
                            add(car2);
                            add(car3);
                        }}),
                Arguments.of("/api/v1/cars" + "?page_size=2&page=2",
                        new ArrayList<Car>() {{
                            add(car3);
                            add(car4);
                        }})
                );
    }

    public static Stream<Arguments> provideCarById() {
        return IntStream.range(0, cars.size())
                .mapToObj(index -> Arguments.of(cars.get(index), index + 1));
    }
}
