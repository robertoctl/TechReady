package com.challenge.jdk17features;

public sealed interface Auto permits ElectricAuto, GasAuto, HybridAuto {

    default void turnRight() {
        System.out.println("Turning right");
    }

    void start();

}
