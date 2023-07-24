package ru.sanctio;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class CityUtils {

    public static List<City> parseFile() {
        List<City> cities = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(Path.of("cities.csv"));
            while (scanner.hasNextLine()) {
                cities.add(parseLine(scanner.nextLine()));
            }
            scanner.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return cities;
    }

    private static City parseLine(String line) {
        String[] values = line.split(";", 6);

        if (values[5].trim().isEmpty()) {
            values[5] = "Неизвестно";
        }

        return new City(values[1], values[2], values[3], Integer.parseInt(values[4]), values[5]);
    }

    /**
     * Сортировка списка городов по наименованию
     * в алфавитном порядке по убыванию без учета регистра
     */
    public static List<City> sortingListOfCitiesByName(List<City> cities) {
        List<City> sortedList = new ArrayList<>(cities);
        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        return sortedList;
    }

    /**
     * Сортировка списка городов по федеральному округу и наименованию города
     * внутри каждого федерального округа
     * в алфавитном порядке по убыванию с учетом регистра;
     */
    public static List<City> sortingListOfCitiesByDistrictAndName(List<City> cities) {
        List<City> sortCitiesDistrictAndName = cities.stream()
                .sorted(Comparator.comparing(City::getDistrict)
                        .thenComparing(City::getName).reversed())
                .collect(Collectors.toList());
        return sortCitiesDistrictAndName;
    }

    /**
     * Поиск города с наибольшим количеством жителей
     *
     * @param cities
     */
    public static void searchСityMaxPopulation(List<City> cities) {
        City[] citiesArray = cities.toArray(City[]::new);
        int max = 0;
        int index = 0;
        for (int i = 0; i < citiesArray.length; i++) {
            if (citiesArray[i].getPopulation() > max) {
                max = citiesArray[i].getPopulation();
                index = i;
            }
        }
        System.out.println("[" + index + "] = " + max);

    }

    /**
     * Поиск количества городов в разрезе регионов
     *
     * @param cities массив городов
     */
    public static void numberOfCitiesInTheRegion(List<City> cities) {
        cities.stream()
                .collect(Collectors.groupingBy(
                        City::getRegion, Collectors.counting()))
                .forEach((s, count) -> System.out.println(s + " - " + count));
    }
}
