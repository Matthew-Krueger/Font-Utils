/* *********************************************************************************************
 * This software is licensed under the BSD 3-Clause License                                    *
 *                                                                                             *
 * Copyright (c) 2020, Matthew Krueger                                                         *
 * All rights reserved.                                                                        *
 *                                                                                             *
 * Redistribution and use in source and binary forms, with or without                          *
 * modification, are permitted provided that the following conditions are met:                 *
 *                                                                                             *
 * 1. Redistributions of source code must retain the above copyright notice, this              *
 *    list of conditions and the following disclaimer.                                         *
 *                                                                                             *
 * 2. Redistributions in binary form must reproduce the above copyright notice,                *
 *    this list of conditions and the following disclaimer in the documentation                *
 *    and/or other materials provided with the distribution.                                   *
 *                                                                                             *
 * 3. Neither the name of the copyright holder nor the names of its                            *
 *    contributors may be used to endorse or promote products derived from                     *
 *    this software without specific prior written permission.                                 *
 *                                                                                             *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"                 *
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE                   *
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE              *
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE                *
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL                  *
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR                  *
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER                  *
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,               *
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE               *
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.                        *
 **********************************************************************************************/

package com.matthewkrueger.font_utils;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FontManager {

    private static final Map<String, FontFamily> FONTS = new HashMap<>();
    private static boolean init = false;


    /**
     * Load fonts shipped with this and system fonts to the fonts map
     */
    public static void initFonts(){

        GraphicsEnvironment ge  = GraphicsEnvironment.getLocalGraphicsEnvironment();

        for(String fontFamilyName : ge.getAvailableFontFamilyNames()){
            FONTS.put(fontFamilyName, new FontFamily(fontFamilyName));
        }
        init = true;

    }

    /**
     *
     * @param fontStream the {@link InputStream} to read the font from.
     * @param fontFormat A font format, such as Font.TRUETYPE_FONT
     * @throws IOException if it cannot read the font
     * @throws FontFormatException if it cannot create the fontformat. Generally you should use Font.TRUETYPE_FONT
     */
    public static void registerFamily(InputStream fontStream, int fontFormat) throws IOException, FontFormatException {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(Font.createFont(fontFormat, fontStream));

        FONTS.clear();
        initFonts();

    }

    /**
     * Get a font family from the FONTS that are maintained by this class
     * @param familyName the name of the font family
     * @return the FontFamily object. Call getFont(int fontSize, int fontStyle) to get the specific font.
     */
    public static FontFamily getFontFamily(String familyName){

        // guard clause for an uninitialized map
        if(!init)
            initFonts();

        if(FONTS.containsKey(familyName)){

            return FONTS.get(familyName);

        }else{

            System.out.println("Font family " + familyName + " not found. Using SansSerif");
            return FONTS.get("SansSerif");

        }

    }

    /**
     * Get a font family from the FONTS that are maintained by this class
     * @param familyName the name of the font family
     * @return the FontFamily object. Call getFont(int fontSize, int fontStyle) to get the specific font.
     */
    public static FontFamily getFontFamily(String familyName, FontLogging log){

        // guard clause for an uninitialized map
        if(!init)
            initFonts();

        if(FONTS.containsKey(familyName)){

            return FONTS.get(familyName);

        }else{

            log.log("Font family " + familyName + " not found. Using SansSerif");
            return FONTS.get("SansSerif");

        }

    }

}
