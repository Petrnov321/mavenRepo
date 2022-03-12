package com.engeto.projekt_01;

import java.util.Objects;

public class Country implements Comparable<Country> {

    private String stateAbbreviation, stateName;
    private double fullDph, reduceDph;
    private boolean specialDph;

    public Country(String stateAbbreviation, String stateName, Double fullDph, Double reduceDph, boolean specialDph) {
        this.stateAbbreviation = stateAbbreviation;
        this.stateName = stateName;
        this.fullDph = fullDph;
        this.reduceDph = reduceDph;
        this.specialDph = specialDph;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public double getFullDph() {
        return fullDph;
    }

    public void setFullDph(double fullDph) {
        this.fullDph = fullDph;
    }

    public double getReduceDph() {
        return reduceDph;
    }

    public void setReduceDph(double reduceDph) {
        this.reduceDph = reduceDph;
    }

    public boolean isSpecialDph() {
        return specialDph;
    }

    public void setSpecialDph(boolean specialDph) {
        this.specialDph = specialDph;
    }

    @Override
    public String toString() {
        return "Country: " +
                "stateAbbreviation= " + stateAbbreviation +
                ", stateName= " + stateName +
                ", fullDph= " + fullDph +
                ", reduceDph= " + reduceDph +
                ", specialDph= " + specialDph;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Double.compare(country.getFullDph(), getFullDph()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFullDph());
    }

    @Override
    public int compareTo(Country o) {
        int compare = Integer.compare((int)fullDph, (int)o.fullDph);
        return compare;
    }
}

