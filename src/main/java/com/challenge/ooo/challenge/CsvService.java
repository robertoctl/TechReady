package com.challenge.ooo.challenge;

class CsvService {
    public String toCsv(ResearchPaper paper) {
        // TODO: right now we just hardcode one format
        return paper.title() + "," + paper.author() + "," + paper.year();
    }

    public String toPsv(ResearchPaper paper) {
        // TODO: right now we just hardcode one format
        return paper.title() + "." + paper.author() + "." + paper.year();
    }
}
