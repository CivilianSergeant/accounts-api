package technology.grameen.gaccounting.services.report;

import java.util.ArrayList;
import java.util.List;

public class PrimaryGroup {


    String title;
    List<SubGroup> subGroups = new ArrayList<>();

    public PrimaryGroup( String title) {

        this.title = title;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubGroup> getSubGroups() {
        return subGroups;
    }
}
