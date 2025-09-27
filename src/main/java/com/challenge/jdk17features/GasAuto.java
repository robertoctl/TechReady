package com.challenge.jdk17features;

public final class GasAuto implements Auto {

    private final String engineType = "gas";

    public String getEngineType() {
        return engineType;
    }

    @Override
    public void start() {
        System.out.println("GasAuto starts");
    }
}
