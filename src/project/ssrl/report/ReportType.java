package project.ssrl.report;

public enum ReportType {

    CHEATING("%player% has been reported for cheating"),
    CHATABUSE("%player% has been reported for chatabusing"),
    TOXICITY("%player% has been reported for toxicity");

    protected String string;

    ReportType(String string) {
        this.string = string;
    }

    public String getMessage() {
        return this.string;
    }

    public String getName() {
        return this.name();
    }

    public static ReportType getType(String message) {
        for(ReportType reportType : values()) if(reportType.getName().equalsIgnoreCase(message)) return reportType;
        return null;
    }
}
