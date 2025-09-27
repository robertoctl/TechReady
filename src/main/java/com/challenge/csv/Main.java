package com.challenge.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class Main {

    public static void main(String[] args) throws IOException {
        String outputFileName = "contacts.csv";
        String[] headers = new String[] {"First Name", "Last Name" };

        try(Writer writer = new FileWriter(outputFileName);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(headers))
        ) {
            csvPrinter.printRecord("Dannielle", "Wilks");
            csvPrinter.printRecord("Harvir", "Mathews");
            csvPrinter.printRecord("Sahil", "Rojas");
            csvPrinter.printRecord("Eileen", "Pike");
            csvPrinter.printRecord("Matias", "Moreno");
        }
    }

}
