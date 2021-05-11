package technology.grameen.gaccounting.responses;

public enum ResponseEnum {

    SERVICE_CATEGORY_NOT_UNIQUE("800"),
    SERVICE_CATEGORY_NOT_AVAILABLE("801");


    private String action;

    // getter method
    public String getCode()
    {
        return this.action;
    }

    // enum constructor - cannot be public or protected
    private ResponseEnum(String action)
    {
        this.action = action;
    }

}
