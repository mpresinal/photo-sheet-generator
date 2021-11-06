/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presinal.photo.sheet.generator.component.impl;

import com.presinal.photo.sheet.generator.component.ImageQualityProcessor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Component;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 */
@Component
public class ImageQualityProcessorImpl implements ImageQualityProcessor {

    @Override
    public BufferedImage ajustQuality(Path image, float quality) throws IOException {
        BufferedImage source = ImageIO.read(image.toFile());
        int width = (int)(source.getWidth() * quality);
        int height = (int) (source.getHeight() * quality);
        BufferedImage destination =  new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = destination.createGraphics();
        graphic.drawImage(source.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        graphic.dispose();
        return destination;
    }    
}
