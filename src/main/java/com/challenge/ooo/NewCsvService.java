package com.challenge.ooo;

public class NewCsvService {

    private final Formatter csvFormatter;

    public NewCsvService(Formatter csvFormatter) {
        this.csvFormatter = csvFormatter;
    }

    public String toCsv(ResearchPaper paper) {
        return csvFormatter.format(paper);
    }

}
