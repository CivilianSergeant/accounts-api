package technology.grameen.gaccounting.fileupload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import technology.grameen.gaccounting.fileupload.response.UploadResponse;
import technology.grameen.gaccounting.fileupload.service.FileStorageService;
import technology.grameen.gaccounting.responses.EntityResponse;
import technology.grameen.gaccounting.responses.IResponse;


@RestController
@RequestMapping("/api/v1/file-upload")
public class FileUploadController {

    FileStorageService fileStorageService;

    FileUploadController(FileStorageService fileStorageService){
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<IResponse> uploadFile(@RequestParam("file") MultipartFile file){
        UploadResponse fileUploaded = fileStorageService.storeFile(file);
        return new ResponseEntity<>(new EntityResponse<>(HttpStatus.OK.value(),fileUploaded), HttpStatus.OK);
    }
}
