package settings;

import converting.Converting;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettingsTest {

    @Test
    void testGetSetting() {
        String expected = "storage/producers/";
        assertEquals(expected, Settings.getSetting("storagePathProducers"),"getSetting is incorrect");
    }
}