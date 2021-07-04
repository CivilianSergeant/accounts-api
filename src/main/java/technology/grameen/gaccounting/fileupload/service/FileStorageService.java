package technology.grameen.gaccounting.fileupload.service;

import org.springframework.web.multipart.MultipartFile;
import technology.grameen.gaccounting.fileupload.response.UploadResponse;

public interface FileStorageService {

    UploadResponse storeFile(MultipartFile file);

}
