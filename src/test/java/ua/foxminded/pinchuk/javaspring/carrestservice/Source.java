package ua.foxminded.pinchuk.javaspring.carrestservice;

import org.junit.jupiter.params.provider.Arguments;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Brand;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Model;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Type;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.User;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Source {

    public static Brand brand1 = new Brand(1, "Mercedes");
    public static Brand brand2 = new Brand(2, "BMW");
    public static Brand brand3 = new Brand(3, "Audi");

    public static List<Brand> brands = new ArrayList<>() {{
        add(brand1);
        add(brand2);
        add(brand3);
    }};

    public static Type type1 = new Type(1, "Sedan");
    public static Type type2 = new Type(2, "Hatchback");
    public static Type type3 = new Type(3, "Coupe");
    public static List<Type> types = new ArrayList<>() {{
        add(type1);
        add(type2);
        add(type3);
    }};

    public static User user1 = new User(1, "admin@car.com", "1111", "John", "Smith", User.Role.ADMIN, "138765487");
    public static User user2 = new User(2, "staff@car.com", "1111", "Tom", "Green", User.Role.STAFF, "135481314");
    public static User user3 = new User(3, "antonynewman@gmail.com", "1111", "Anthony", "Newman", User.Role.CUSTOMER, "846321871");
    public static List<User> users = new ArrayList<>() {{
        add(user1);
        add(user2);
        add(user3);
    }};

    public static Model model1 = new Model("1", brand1, 2020, "C-class", new ArrayList<>() {{
        add(type1);
        add(type3);
    }});
    public static Model model2 = new Model("2", brand1, 2021, "C-class", new ArrayList<>() {{
        add(type1);
    }});

    public static Model model3 = new Model("3", brand1, 2021, "E-class", new ArrayList<>() {{
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
    public static List<Model> models = new ArrayList<>() {{
        add(model1);
        add(model2);
        add(model3);
        add(model4);
        add(model5);
        add(model6);
        add(model7);
    }};

    public static Stream<Arguments> provideModels() throws ParseException {
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

}
