package com.challenge.ooo;

public class CsvService {

    public String toCsv(ResearchPaper paper) {
        return paper.title() + "," + paper.author() + "," + paper.year();
    }

}

class CsvFormatterFactory {

    Formatter from(String style) {
        return switch (style) {
            case "simple" -> new SimpleCsvFormatter();
            case "period" -> new PeriodSeparatedFormatter();
            default -> throw new IllegalArgumentException("Unknown style");
        };
    }

}