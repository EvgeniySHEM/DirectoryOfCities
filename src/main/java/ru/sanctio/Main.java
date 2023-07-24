package ru.sanctio;

import java.util.List;

public class Main {
    public static void main( String[] args ) {
        List<City> cities = CityUtils.parseFile();

        CityUtils.numberOfCitiesInTheRegion(cities);
    }
}
