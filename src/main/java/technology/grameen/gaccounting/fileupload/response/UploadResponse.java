package technology.grameen.gaccounting.fileupload.response;

public class UploadResponse {

    String filename;
    String type;
    Long size;

    public UploadResponse(String filename, String type, Long size) {
        this.filename = filename;
        this.type = type;
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
