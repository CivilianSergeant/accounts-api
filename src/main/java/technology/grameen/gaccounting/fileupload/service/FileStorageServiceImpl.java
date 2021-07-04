package technology.grameen.gaccounting.fileupload.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import technology.grameen.gaccounting.fileupload.exception.FileStorageException;
import technology.grameen.gaccounting.fileupload.property.FileStorageProperties;
import technology.grameen.gaccounting.fileupload.response.UploadResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    FileStorageServiceImpl(FileStorageProperties properties){
        this.fileStorageLocation = Paths.get(properties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception ex){
            throw new FileStorageException("Could not create directory where the uploaded file will be stored.", ex);
        }
    }

    @Override
    public UploadResponse storeFile(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        UUID uuid = UUID.nameUUIDFromBytes((String.valueOf(Instant.now().getEpochSecond())+originalName).getBytes());
        String fileName = uuid.toString()+'.'+originalName.substring(originalName.lastIndexOf('.')+1);

        try {
            if(fileName.contains("..")){
                throw new FileStorageException("Sorry! File Contains Invalid Path"+fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(),targetLocation);


            return new UploadResponse(fileName,file.getContentType(),file.getSize());
        }catch (IOException ex){
            throw new FileStorageException("Could not store file "+ fileName + ". Please try again.",ex);
        }

    }
}
