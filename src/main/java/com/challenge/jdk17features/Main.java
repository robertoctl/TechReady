package com.challenge.jdk17features;

public class Main {

    public static void main(String[] args) {

        // Records (data carrier classes)
        User user = new User("Roberto", 37, "roberto@email.com", "male");
        System.out.println(user);

        // Sealed Classes (controlled inheritance)
        Auto hybridAuto = new HybridAuto();
        hybridAuto.start();
        hybridAuto.turnRight();

        // Pattern Matching for instanceof
        Auto electricAuto = new ElectricAuto();
        hybridAuto.start();
        hybridAuto.turnRight();

        printAutoType(hybridAuto);
        printAutoType(electricAuto);

        // Switch Expressions
        printEngineType((ElectricAuto)electricAuto);

        // Helpful NullPointerException (NPE) Messages
        GasAuto gasAuto = fakeBuilder();
        printAutoType(gasAuto);
        System.out.println(gasAuto.getEngineType());

    }

    private static GasAuto fakeBuilder() {
        return null;
    }

    private static void printAutoType(Auto auto) {
        if (auto instanceof HybridAuto hybridAuto) {
            System.out.println("This auto is hybrid and has an " + hybridAuto.getEngineType() + " engine");
        }
        if (auto instanceof ElectricAuto electricAuto) {
            System.out.println("This is an electric auto, engine type is " + electricAuto.getEngineType());
        }
    }

    public static void printEngineType(ElectricAuto electricAuto) {
        String message = switch (electricAuto.getEngineType()) {
            case "electric" -> "No wonder";
            case "other"  -> "??";
            default     -> "Unexpected value";
        };
        System.out.println(message);
    }

}