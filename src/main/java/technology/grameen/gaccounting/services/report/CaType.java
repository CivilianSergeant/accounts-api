package technology.grameen.gaccounting.services.report;

import java.util.ArrayList;
import java.util.List;

public class CaType {

    String type;
    String finYearStart;
    String finYearEnd;

    List<PrimaryGroup> primaryGroups = new ArrayList<>();

    public CaType(String type) {
        this.type = type;
    }

    public List<PrimaryGroup> getPrimaryGroups() {
        return primaryGroups;
    }

    public void setPrimaryGroups(List<PrimaryGroup> primaryGroups) {
        this.primaryGroups = primaryGroups;
    }

    public String getType() {
        return type;
    }

    public String getFinYearStart() {
        return finYearStart;
    }

    public void setFinYearStart(String finYearStart) {
        this.finYearStart = finYearStart;
    }

    public String getFinYearEnd() {
        return finYearEnd;
    }

    public void setFinYearEnd(String finYearEnd) {
        this.finYearEnd = finYearEnd;
    }
}
