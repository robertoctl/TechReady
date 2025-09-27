package com.challenge.jdk17features;

public final class ElectricAuto implements Auto {

    private final String engineType = "electric";

    public String getEngineType() {
        return engineType;
    }

    @Override
    public void start() {
        System.out.println("ElectricAuto starts");
    }
}
