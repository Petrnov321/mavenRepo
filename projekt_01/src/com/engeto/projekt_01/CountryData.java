package com.engeto.projekt_01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CountryData {

    private List<Country> countryData = new ArrayList<>();

    public void loadFromFile(String filename, String delimiter) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                inputLine = inputLine.replaceAll(",", ".");
                String[] parts = inputLine.split(delimiter);//rozdělím řádek na části
                /*for (int i = 0; i < parts.length; i++) {
                    if (parts[i].contains(",")){
                        parts[i].replace(",", ".");
                    }
                }*/

                //jednotlivé části uložím do proměnných
                String stateAbbreviation = parts[0];
                String stateName = parts[1];
                double fullDph = Double.parseDouble(parts[2]);
                double reduceDph = Double.parseDouble(parts[3]);
                boolean specialDph = Boolean.parseBoolean(parts[4]);

                Country country = new Country(stateAbbreviation, stateName, fullDph, reduceDph, specialDph);
                countryData.add(country);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getDescription(){
        DecimalFormat format = new DecimalFormat("0.#");
        for (Country country: countryData) {
            System.out.println(country.getStateName() + " (" + country.getStateAbbreviation() + "): " +
                    format.format(country.getFullDph()) + " % (" + format.format(country.getReduceDph()) + " %) ");
        }
        }

        public int getSize(){
            return countryData.size();
        }

    public List<Country> getAllCountries(){
        return new ArrayList<>(countryData);
    }

    public void addCountry(Country newCountry){
        countryData.add(newCountry);
    }

    

}

