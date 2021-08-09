package com.presinal.photo.sheet.generator.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractPhotoSheetPdfService implements PhotoSheetPdfService {

    /**
     * @return A string containing a list of supported image format separated by '|', ex: .jpg|.png
     */
    protected abstract String getSupportedImageFormat();

    protected List<Path> listImageFiles(String imagesDir) throws IOException {

        try(Stream<Path> stream = Files.list(Paths.get(imagesDir))) {
            List<Path> files;
            if (getSupportedImageFormat() != null && !getSupportedImageFormat().isEmpty()) {
                Pattern regPattern = Pattern.compile(getSupportedImageFormat(), Pattern.CASE_INSENSITIVE);
                files = stream.filter(p ->  regPattern.matcher(p.getFileName().toString()).find())
                        .map(p -> p.getFileName()).collect(Collectors.toList());
            } else {
                files = stream.map(p -> p.getFileName()).collect(Collectors.toList());
            }
            return files;
        }

    }
}
