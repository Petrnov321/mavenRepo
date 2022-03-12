package com.engeto.projekt_01;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CountryData {

    private List<Country> countryData = new ArrayList<>();
    private List<Country> countryOver = new ArrayList<>();
    private List<Country> countryUnder = new ArrayList<>();
    private double fullDph;
    private DecimalFormat format = new DecimalFormat("0.#");
    private static final String DELIMITER = "\t";

    /**
     * Načítání ze souboru
     * @param filename
     * @param delimiter
     */
    public void loadFromFile(String filename, String delimiter) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                inputLine = inputLine.replaceAll(",", ".");
                String[] parts = inputLine.split(delimiter);//rozdělím řádek na části

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


    /**
     * Uložení do souboru
     * @param inputFilename
     * @param delimiter
     * @param listName
     */
    public void saveToFile(String inputFilename, String delimiter, List<Country> listName) {
        try(PrintWriter writer =
                    new PrintWriter(new FileWriter(inputFilename))
        ) {
            for (Country country : listName) {
                String outputLine = country.getStateAbbreviation() + delimiter;
                outputLine += country.getStateName() + delimiter;
                outputLine += format.format(country.getFullDph()) + delimiter;
                outputLine += format.format(country.getReduceDph()) + delimiter;
                outputLine += country.isSpecialDph();
                writer.println(outputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Vypsání přímo do konzole celého seznamu zemí
     */
    public void getDescription(){
        for (Country country: countryData) {
            System.out.println(country.getStateName() + " (" + country.getStateAbbreviation() + "): " +
                    format.format(country.getFullDph()) + " % ");
        }
    }

    /**
     * String do kterého je uložen celý seznam zemí
     * @return
     */
    public String getList(){
        String list = "";
        for (Country country: countryData) {
            list += country.getStateName() + " (" + country.getStateAbbreviation() + "): " +
                    format.format(country.getFullDph()) + " % (" + format.format(country.getReduceDph()) + " %)\n";
        }
        return list;
    }


    public int getSize(){
        return countryData.size();
    }

    public List<Country> getAllCountries(){
        return new ArrayList<>(countryData);
    }

    /**
     * přidání země do hlavního seznamu
     * @param newCountry
     */
    public void addCountry(Country newCountry){
        countryData.add(newCountry);
    }

    /**
     * Metoda, která seřadí a filtruje seznam podle nastavené proměnné (DPH danné země)
     * @param fullDph
     */
    public void filterDph(double fullDph){
        this.fullDph = fullDph;
        countryOver.clear();//před filtrací je třeba pomocné seznamy vyčistit
        countryUnder.clear();
        Collections.sort(countryData);
        Collections.reverse(countryData);
        for (Country country: countryData){
            if(country.getFullDph() > fullDph){
                countryOver.add(country);
            } else {
                countryUnder.add(country);
            }
        }
    }

    /**
     * Dělá to co předchozí metoda, ale je přidána podmínka jestli má speciální DPH
     * @param fullDph
     */
    public void filterDphAndSpecialDph(double fullDph){
        this.fullDph = fullDph;
        countryOver.clear();//před filtrací je třeba pomocné seznamy vyčistit
        countryUnder.clear();
        Collections.sort(countryData);
        Collections.reverse(countryData);
        for (Country country: countryData){
            if(country.getFullDph() > fullDph && !country.isSpecialDph()){
                countryOver.add(country);
            } else {
                countryUnder.add(country);
            }
        }
    }

    /**
     * Do stringu vypíše filtrováný seznam - města nad dannou hodnotou
     * @return
     */
    public String getOver(){
        String overCountries = "";
        for (Country country: countryOver) {
            overCountries += country.getStateName() + " (" + country.getStateAbbreviation() + "): " +
                    format.format(country.getFullDph()) + " % (" + format.format(country.getReduceDph()) + " %)\n";
        }
        return overCountries;
    }

    /**
     * Do stringu vypíše filtrováný seznam - města pod dannou hodnotou
     * @return
     */
    public String getUnder(){
        String underCountries = "";
        for (Country country: countryUnder) {
            underCountries += country.getStateAbbreviation() + ", ";
        }
        return underCountries;
    }

    /**
     * Uloží filtrovaný seznam (města nad dannou hodnotou) do souboru
     */
    public void saveOverToFile(){
            String fileName = "vat-over-" + format.format(fullDph) + ".txt";
        saveToFile(fileName, DELIMITER, countryOver);
    }

    /**
     * Uloží filtrovaný seznam (města pod dannou hodnotou) do souboru
     */
    public void saveUnderToFile(){
        String fileName = "vat-under-" + format.format(fullDph) + ".txt";
        saveToFile(fileName, DELIMITER, countryUnder);
    }




}


