package com.engeto.projekt_01;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        CountryData countryData = new CountryData();
        countryData.loadFromFile("vat-eu.txt", "\t");//krok 1
        countryData.getDescription();//krok 2
        System.out.println("----------------------------------------------------------------------------");

        //krok 3
        List<Country> countryList = countryData.getAllCountries();
        CountryData countryOver20Dph = new CountryData();
        CountryData countryUnder20Dph = new CountryData();
        DecimalFormat format = new DecimalFormat("0.#");
        for (Country country: countryList) {
            if (country.getFullDph() > 20 && !country.isSpecialDph()){
                countryOver20Dph.addCountry(country);

            } else {
                countryUnder20Dph.addCountry(country);
            }
        }

        countryOver20Dph.getDescription();
        System.out.println("===================================================================================\n" +
                "Sazba VAT 20 % nebo nižší nebo používají speciální sazbu: AT, CY, CZ,... \n");
        countryUnder20Dph.getDescription();




    }
}
