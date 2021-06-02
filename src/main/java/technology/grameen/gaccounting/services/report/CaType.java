package technology.grameen.gaccounting.services.report;

import java.util.ArrayList;
import java.util.List;

public class CaType {

    String type;

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
}
