package com.matthewkrueger.font_utils;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FontManagerTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        FontManager.initFonts();
    }

    @Test
    void registerFamily() {

        try {
            FontManager.registerFamily(FontManagerTest.class.getResourceAsStream("/Courier Prime.ttf"), Font.TRUETYPE_FONT);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            fail();
        }

    }

    @org.junit.jupiter.api.Test
    void getFontFamily() {

        try {
            FontManager.registerFamily(FontManagerTest.class.getResourceAsStream("/Courier Prime.ttf"), Font.TRUETYPE_FONT);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            fail();
        }

        assertNotNull(FontManager.getFontFamily("Courier Prime").getFont(12, Font.PLAIN));

    }

    @Test
    void testGetFontFamilyNull(){

    }

    @org.junit.jupiter.api.Test
    void testGetFontFamily() {

        try {
            FontManager.registerFamily(FontManagerTest.class.getResourceAsStream("/Courier Prime.ttf"), Font.TRUETYPE_FONT);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            fail();
        }

        assertNotNull(FontManager.getFontFamily("Courier Prime.ttf").getFont(12, Font.PLAIN));

    }
}