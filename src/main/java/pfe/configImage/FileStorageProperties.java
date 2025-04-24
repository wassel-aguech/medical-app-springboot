package pfe.configImage;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "file")
//@NoArgsConstructor
//@AllArgsConstructor
@Component
public class FileStorageProperties {
    private String uploadImgUsersDir;


    public String getUploadImgUsersDir() {
        return uploadImgUsersDir;
    }


    public void setUploadImgUsersDir(String uploadImgUsersDir) {
        this.uploadImgUsersDir = uploadImgUsersDir;
    }
}
