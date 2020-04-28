package com.matthewkrueger.font_utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FontsTest {

    @Test
    void getFontFamily() {
        Fonts f = new Fonts();

        assertNotNull(f.getFontFamily("F"));

        FontManager.initFonts();
        FontManager.getFontFamily("F");

    }
}