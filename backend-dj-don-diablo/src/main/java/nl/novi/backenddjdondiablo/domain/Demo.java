package nl.novi.backenddjdondiablo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;


@Entity
@Table(name="demo")
public class Demo {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @Column(columnDefinition = "serial")
    private long id;
    private String songTitle;
    private String artistName;
    private String songName;
    private String personalMessage;
    private String username;
    private String demoURL;
    private String status = "pending";
    private String comment = "Waiting for review ....";
    @JsonFormat(pattern = "dd-mm-yyyy",shape = JsonFormat.Shape.STRING)
    @Column(name = "upload_date")
    private String uploadDate;

    public Demo() {
    }

    public Demo(long id, String songTitle, String artistName, String songName, String personalMessage, String username, String demoURL) {
        this.id = id;
        this.songTitle = songTitle;
        this.artistName = artistName;
        this.songName = songName;
        this.personalMessage = personalMessage;
        this.username = username;
        this.demoURL = demoURL;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDemoURL() {
        return demoURL;
    }

    public void setDemoURL(String demoURL) {
        this.demoURL = demoURL;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
