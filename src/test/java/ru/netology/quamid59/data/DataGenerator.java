package ru.netology.quamid59.data;


import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int addDays, String dateFormat) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(dateFormat));
    }

    public static String generateCity() {
        String[] cities = new String[]{"Горно-Алтайск", "Петропавловск-Камчатский", "Санкт-Петербург", "Нижний Новгород", "Ростов-на-Дону", "Южно-Сахалинск", "Великий Новгород", "Йошкар-Ола", "Нарьян-Мар", "Ханты-Мансийск", "Салехард"};
        int itemIndex = (int) (Math.random() * cities.length);
        return cities[itemIndex];
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String randomName = (faker.name().firstName() + " " + faker.name().lastName() + "-" + faker.name().lastName());
        return randomName.replace('ё', 'е');
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateName(locale), generatePhone(locale));
        }

        public static UserInfoFull generateUserWithCity(String locale) {
            return new UserInfoFull(generateCity(), generateName(locale), generatePhone(locale));
        }
    }

    @Value
    public static class UserInfo {
        String name;
        String phone;
    }

    @Value
    public static class UserInfoFull {
        String city;
        String name;
        String phone;
    }
}