package technology.grameen.gaccounting.responses;

import java.util.List;

public class EntityCollectionResponse<T> implements IResponse {

    private int status;
    private List<T> collection;

    public EntityCollectionResponse(int status, List<T> collection) {
        this.status = status;
        this.collection = collection;
    }

    public int getStatus() {
        return status;
    }

    public List<T> getCollection() {
        return collection;
    }
}
