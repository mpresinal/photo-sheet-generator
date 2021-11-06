package com.presinal.photo.sheet.generator.service;

import com.presinal.photo.sheet.generator.component.ImageQualityProcessor;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.imageio.ImageIO;

public abstract class AbstractPhotoSheetPdfService implements PhotoSheetPdfService {

    /**
     * @return A string containing a list of supported image format separated by
     * '|', ex: .jpg|.png
     */
    protected abstract String getSupportedImageFormat();
    
    protected abstract ImageQualityProcessor getImageQualityProcessor();

    protected List<Path> listImageFiles(String imagesDir) throws IOException {

        try ( Stream<Path> stream = Files.list(Paths.get(imagesDir))) {
            List<Path> files;
            if (getSupportedImageFormat() != null && !getSupportedImageFormat().isEmpty()) {
                Pattern regPattern = Pattern.compile(getSupportedImageFormat(), Pattern.CASE_INSENSITIVE);
                files = stream.filter(p -> regPattern.matcher(p.getFileName().toString()).find())
                        /*.map(p -> p.getFileName())*/.collect(Collectors.toList());
            } else {
                files = stream/*.map(p -> p.getFileName())*/.collect(Collectors.toList());
            }
            return files;
        }

    }

    /**
     * 
     * @param images
     * @param quality a value from 0.1 to 1.0
     * @param outpuDir
     * @return
     * @throws IOException 
     */
    protected List<Path> ajustImageQuality(List<Path> images, float quality, String outpuDir) throws IOException {

        if (images == null) {
            throw new NullPointerException("images is null");
        }
        
        if (quality < 0.1 || quality > 1.0) {
            throw new IllegalArgumentException("quality should be a value between 0.1 and 1.0");
        }
        
        if (outpuDir == null) {
            throw new NullPointerException("outpuDir is null");
        }
        
        Path outputPath = Paths.get(outpuDir, "processed");
        if (!Files.exists(outputPath)) {
            Files.createDirectory(outputPath);
        }
        
        List<Path> files = new ArrayList<>();        
        
        if (images != null) {
            ImageQualityProcessor qualityProcessor = getImageQualityProcessor();
            BufferedImage img;
            Path newImageFile;
            for (Path image : images) {
                img = qualityProcessor.ajustQuality(image, quality);
                newImageFile = outputPath.resolve(image.getFileName());
                ImageIO.write(img, getFileExtension(newImageFile), newImageFile.toFile());
                files.add(newImageFile);
            }            
        }

        return files;
    }
    
    private String getFileExtension(Path file) {
        String name = file.getFileName().toString();
        return name.substring(name.lastIndexOf(".")+1);        
    }
}
