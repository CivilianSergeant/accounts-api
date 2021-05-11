package technology.grameen.gaccounting.responses;

public class EntityResponse<T> implements IResponse {
    private int status;
    private T object;

    public EntityResponse(int status, T object) {
        this.status = status;
        this.object = object;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
