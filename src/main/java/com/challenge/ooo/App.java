package com.challenge.ooo;

public class App {
    public static void main(String[] args) {

        PolyCsvService polyCsvService = new PolyCsvService(new SimpleCsvFormatter());
        String result = polyCsvService.toCsv(new ResearchPaper("title", "author", 20));
        System.out.println(result);

        ResearchPaper paper = new ResearchPaper("AI", "Ada Lovelace", 2024);
        CsvService csvService = new CsvService();
        System.out.println(csvService.toCsv(paper));

        NewCsvService newCsvService = new NewCsvService(new CsvFormatterFactory().from("simple"));
        System.out.println(newCsvService.toCsv(paper));

        newCsvService = new NewCsvService(new CsvFormatterFactory().from("period"));
        System.out.println(newCsvService.toCsv(paper));

    }
}








//        NewCsvService s1 = new NewCsvService(new SimpleCsvFormatter());
//        System.out.println(s1.toCsv(paper));
//        NewCsvService s2 = new NewCsvService(new PeriodSeparatedFormatter());
//        System.out.println(s2.toCsv(paper));