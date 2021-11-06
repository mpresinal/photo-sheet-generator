/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presinal.photo.sheet.generator.component;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 */
public interface ImageQualityProcessor {
    
    BufferedImage ajustQuality(Path image, float quality) throws IOException;
}
