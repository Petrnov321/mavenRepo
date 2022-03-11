package com.engeto.projekt_01;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String INPUT = "vat-eu.txt";
    public static final String DELIMITER = "\t";

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        CountryData countryData = new CountryData();
        countryData.loadFromFile(INPUT, DELIMITER);//krok 1
        countryData.getDescription();//krok 2
        System.out.println("----------------------------------------------------------------------------");

        //kroky 3, 4, 5
        countryData.filterDphAndSpecialDph(20.0);
        //vypsání obou seznamů
        String data = countryData.getOver() + "\n" +
                "===================================================================================\n" +
                "Sazba VAT 20 % nebo nižší nebo používají speciální sazbu: AT, CY, CZ,... \n\n" +
                countryData.getUnder() + "\n";

        System.out.println("\n\n\n" + data);

        //uložení výpisu do souboru. krok 6
        countryData.saveOverToFile();

        //krok 7
        Double fullDph = 0.0;
        String inputDph = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zadejte výši DPH podle které se bude výběr filtrovat:");
        inputDph = scanner.nextLine();
        if (inputDph.equals("")) {
            System.out.println("Nezadal jste žádnou hodnotu, použila se výchozí hodnota 20% dph");
            fullDph = 20.0;
        } else {
            fullDph = Double.parseDouble(inputDph);
        }

        countryData.filterDph(fullDph);

        //výpis filtru
        System.out.println(countryData.getUnder());

        //uložení filtru
        countryData.saveOverToFile();
        }


    }

