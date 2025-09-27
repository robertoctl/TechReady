package com.challenge.ooo;

public class PolyCsvService {

        private final Formatter formatter;

        public PolyCsvService(Formatter formatter) { this.formatter = formatter; }
        public String toCsv(ResearchPaper p) { return formatter.format(p); }
}
