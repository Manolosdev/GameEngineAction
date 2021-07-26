/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rpg.Core;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

/**
 *
 * @author Manolo
 */
public class FontManager {

    public static final int PLAIN = 0;
    public static final int BOLD = 1;
    public static final int ITALIC = 2;
    public static final int BOLD_ITALIC = BOLD | ITALIC;
    static private FontManager instance;
    private HashMap<String, Font> fonts;

    private FontManager() {
        this.fonts = new HashMap<String, Font>();
    }

    public static FontManager getInstance() {
        if (instance == null) {
            instance = new FontManager();
        }
        return instance;
    }

    public Font loadFont(String filneName, int size, int style) {
        URL url = getClass().getResource("/" + filneName);
        if (url == null) {
            throw new RuntimeException("Fonte /" + filneName + " não encontrada!");
        } else {
            try {
                Font font = this.fonts.get(filneName);
                if (font == null) {
                    File file = new File(url.toURI());
                    font = Font.createFont(Font.TRUETYPE_FONT, file);
                    this.fonts.put(filneName, font);
                }
                font = font.deriveFont((float) size);
                if ((style & BOLD) == BOLD) {
                    font = font.deriveFont(Font.BOLD);
                }
                if ((style & ITALIC) == ITALIC) {
                    font = font.deriveFont(Font.ITALIC);
                }
                return font;
            } catch (FontFormatException | IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
