package com.engeto.projekt_01;

public class Country {

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

    public Double getFullDph() {
        return fullDph;
    }

    public void setFullDph(Double fullDph) {
        this.fullDph = fullDph;
    }

    public Double getReduceDph() {
        return reduceDph;
    }

    public void setReduceDph(Double reduceDph) {
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
}
