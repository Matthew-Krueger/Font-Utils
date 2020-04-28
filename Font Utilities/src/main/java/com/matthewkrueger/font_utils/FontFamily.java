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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class FontFamily {

    /**
     * Contains a map of onts, mapped by
     */
    private final Map<Integer, Map<Integer, Font>> fontCache;
    private final String fontFamilyName;

    /**
     * Construct a font family from a name (do not create fonts)
     */
    public FontFamily(String name){
        this.fontFamilyName = name;
        this.fontCache = new HashMap<>();
    }

    /**
     * Gets a font and caches it. This function will return a newly created font that will be accessible in the future
     * @param fontSize the size of the font
     * @param fontStyle the style of the font, given by the Font.STYLE
     * @return the new font
     */
    public Font getFont(int fontSize, int fontStyle){

        if(fontCache.containsKey(fontSize)){

            Map<Integer,Font> fontSizeSubmap = fontCache.get(fontSize);

            // since the font size exists, test for the individual style
            if(fontSizeSubmap.containsKey(fontStyle)){
                return fontCache.get(fontSize).get(fontStyle);
            }else{
                fontSizeSubmap.put(fontStyle, new Font(fontFamilyName, fontStyle, fontSize));
                return fontSizeSubmap.get(fontStyle);
            }

        }

        fontCache.put(fontSize, new HashMap<>());
        Map<Integer, Font> fontSizeSubmap = fontCache.get(fontSize);
        fontSizeSubmap.put(fontStyle, new Font(fontFamilyName, fontStyle, fontSize));
        return fontSizeSubmap.get(fontStyle);

    }

    /**
     * Gets a font and caches it. This function will return a newly created font that will be accessible in the future
     * @param fontSize the size of the font
     * @param fontStyle the style of the font, given by the Font.STYLE
     * @param log the Logging function, use a lambda. (s){ System.out.println(s) }
     * @return the new font
     */
    public Font getFont(int fontSize, int fontStyle, FontLogging log){

        if(fontCache.containsKey(fontSize)){

            Map<Integer,Font> fontSizeSubmap = fontCache.get(fontSize);

            // since the font size exists, test for the individual style
            if(fontSizeSubmap.containsKey(fontStyle)){
                return fontCache.get(fontSize).get(fontStyle);
            }else{

                // cache miss of the font size
                log.log(
                        "Font Family " + fontFamilyName + " at size " + fontSize + " and type "+ fontStyle + " cache miss."
                );
                fontSizeSubmap.put(fontStyle, new Font(fontFamilyName, fontStyle, fontSize));
                return fontSizeSubmap.get(fontStyle);
            }

        }

        log.log("Font Family " + fontFamilyName + " at size " + fontSize + " cache miss.");
        fontCache.put(fontSize, new HashMap<>());
        Map<Integer, Font> fontSizeSubmap = fontCache.get(fontSize);
        fontSizeSubmap.put(fontStyle, new Font(fontFamilyName, fontStyle, fontSize));
        return fontSizeSubmap.get(fontStyle);

    }

}
