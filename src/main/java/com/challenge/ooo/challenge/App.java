package com.challenge.ooo.challenge;

public class App {
    public static void main(String[] args) {

        CsvService csvService = new CsvService();
        String result = csvService.toPsv(
                new ResearchPaper("title", "author", 20));
        System.out.println(result);


        NewCsvService newCsvService = new NewCsvService(new PeriodSeparatedFormatter());
        result = newCsvService.toCsv(new ResearchPaper("title", "author", 20));
        System.out.println(result);
    }
}





