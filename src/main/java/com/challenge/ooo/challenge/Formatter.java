package com.challenge.ooo.challenge;

public interface Formatter {
    String format(ResearchPaper paper);
}

class SimpleCsvFormatter implements Formatter {
    public String format(ResearchPaper p) {
        return "%s,%s,%d".formatted(p.title(), p.author(), p.year());
    }
}

class PeriodSeparatedFormatter implements Formatter {
    public String format(ResearchPaper p) {
        return "%s.%s.%d".formatted(p.title(), p.author(), p.year());
    }
}
