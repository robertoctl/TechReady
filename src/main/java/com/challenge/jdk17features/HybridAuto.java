package com.challenge.jdk17features;

public final class HybridAuto implements Auto {

    private final String engineType = "hybrid";

    public String getEngineType() {
        return engineType;
    }

    @Override
    public void start() {
        System.out.println("HybridAuto starts");
    }
}

