package nl.novi.backenddjdondiablo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoTest {
    private Demo demo;

    @BeforeEach
    void setUp() {
        this.demo = new Demo(
                1L,
                "songTitle",
                "artistName",
                "songName",
                "personalMessage",
                "username",
                "demoURL"
            );
    }

    @Test
    void getId() {
        Long expectedId = 1L;
        Long actualId = demo.getId();
        assertEquals(expectedId, actualId);
    }

    @Test
    void getSongTitle() {
        String expectedSongTitle ="songTitle";
        String actualSongTitle = demo.getSongTitle();
        assertEquals(expectedSongTitle, actualSongTitle);
    }

    @Test
    void getArtistName() {
        String expectedArtistName ="artistName";
        String actualArtistName = demo.getArtistName();
        assertEquals(expectedArtistName, actualArtistName);
    }

    @Test
    void getSongName() {
        String expectedSongName ="songName";
        String actualSongName = demo.getSongName();
        assertEquals(expectedSongName, actualSongName);
    }
}