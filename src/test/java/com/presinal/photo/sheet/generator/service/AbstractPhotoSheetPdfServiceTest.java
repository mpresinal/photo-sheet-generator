package com.presinal.photo.sheet.generator.service;

import com.presinal.photo.sheet.generator.domain.PhotoSheetProperties;
import com.presinal.photo.sheet.generator.service.execption.PhotoSheetPdfException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbstractPhotoSheetPdfServiceTest {
    private final Logger LOGGER = LoggerFactory.getLogger(AbstractPhotoSheetPdfServiceTest.class);
    private static final Path IMAGE_DIR = Paths.get("src", "test", "resources", "images");

    @Test
    public void testListImageFiles_WhenNoSupportedFormatNotFilter() throws IOException {
        AbstractPhotoSheetPdfService service =  create(null);
        List<Path> result = service.listImageFiles(IMAGE_DIR.toString());
        assertFalse(result.isEmpty(), "Invalid result");
        assertSame(result.size(), 5, "Invalid result size");
    }

    @Test
    public void testListImageFiles_WhenSupportedFormatDoFilter() throws IOException {
        AbstractPhotoSheetPdfService service =  create(".jpg|.jfif");
        List<Path> result = service.listImageFiles(IMAGE_DIR.toString());
        assertFalse(result.isEmpty(), "Invalid result");
        assertSame(result.size(), 2, "Invalid result size");
    }

    @Test
    public void testListImageFiles_WhenNoInput() throws IOException {
        assertThrows(NullPointerException.class, ()-> {
            AbstractPhotoSheetPdfService service =  create(".jpg|.jfif");
            List<Path> result = service.listImageFiles(null);
        });
    }

    @Test
    public void testListImageFiles_WhenEmptyInput() throws IOException {
        AbstractPhotoSheetPdfService service =  create(".jpg|.jfif");
        List<Path> result = service.listImageFiles("");
        assertTrue(result.isEmpty(), "Invalid result");
    }

    private AbstractPhotoSheetPdfService create(final String supportedFormat) {
        return new AbstractPhotoSheetPdfService() {
            @Override
            protected String getSupportedImageFormat() {
                return supportedFormat;
            }

            @Override
            public void doPdf(String imagesDir, String outputFile, PhotoSheetProperties props) throws PhotoSheetPdfException {

            }
        };
    }
}
