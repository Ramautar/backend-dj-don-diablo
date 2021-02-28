package nl.novi.backenddjdondiablo.payload.request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class DemoUploadRequest {


    @Size(min = 3, max = 20)
    private String artistName;

    @Size(min = 3, max = 20)
    private String songName;

    @Size(min = 0, max = 200)
    private String personalMessage;

    @NotBlank
    private MultipartFile MP3DemoFile;



    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getPersonalMessage() {
        return personalMessage;
    }

    public void setPersonalMessage(String personalMessage) {
        this.personalMessage = personalMessage;
    }

    public MultipartFile getMP3DemoFile() {
        return MP3DemoFile;
    }

    public void setMP3DemoFile(MultipartFile MP3DemoFile) {
        this.MP3DemoFile = MP3DemoFile;
    }
}
