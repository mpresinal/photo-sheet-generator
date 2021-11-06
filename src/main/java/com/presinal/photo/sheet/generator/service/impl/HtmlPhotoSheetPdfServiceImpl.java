package com.presinal.photo.sheet.generator.service.impl;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.presinal.photo.sheet.generator.component.PhotoSheetHtmlGenerator;
import com.presinal.photo.sheet.generator.domain.PhotoSheetProperties;
import com.presinal.photo.sheet.generator.service.AbstractPhotoSheetPdfService;
import com.presinal.photo.sheet.generator.service.execption.PhotoSheetPdfException;
import com.presinal.photo.sheet.generator.service.PhotoSheetPdfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.itextpdf.html2pdf.HtmlConverter;
import com.presinal.photo.sheet.generator.component.ImageQualityProcessor;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author Miguel Presinal <presinal378@gmail.com>
 */
@Service
public class HtmlPhotoSheetPdfServiceImpl extends AbstractPhotoSheetPdfService implements PhotoSheetPdfService {

    private final Logger LOGGER = LoggerFactory.getLogger(HtmlPhotoSheetPdfServiceImpl.class);
    private static final String SUPPORTED_IMAGE_FORMATS = ".jpg|.jpeg|.jpe|.pjpeg|.pjp|.jif|.jfif|.jfi|.gif|.png";
    @Value("${images.supported.formats:" + SUPPORTED_IMAGE_FORMATS + "}")
    private String supportedImageFormat;
    private final PhotoSheetHtmlGenerator htmlGenerator;
    private final ImageQualityProcessor qualityProcessor;

    @Autowired
    public HtmlPhotoSheetPdfServiceImpl(PhotoSheetHtmlGenerator htmlGenerator, ImageQualityProcessor qualityProcessor) {
        this.htmlGenerator = htmlGenerator;
        this.qualityProcessor = qualityProcessor;
    }

    @Override
    public void doPdf(String imagesDir, String outputFile, PhotoSheetProperties sheetProps) throws PhotoSheetPdfException {
        try (PdfWriter pdfWriter = new PdfWriter(Files.newOutputStream(Paths.get(outputFile), StandardOpenOption.WRITE, StandardOpenOption.CREATE), new WriterProperties().setFullCompressionMode(true))) {
            ConverterProperties props = new ConverterProperties();
            String baseUri = imagesDir.endsWith(File.separator) ? imagesDir : imagesDir + File.separator;
            props.setBaseUri(baseUri);
            List<Path> files = listImageFiles(imagesDir);
            if (files != null && !files.isEmpty()) {
                if (sheetProps.getImageQuality() < 1.0) {
                    files = ajustImageQuality(files, sheetProps.getImageQuality(), imagesDir);
                }
            }
            String html = htmlGenerator.generateHtmlSheet(sheetProps.getPhotosPerRow(), files.stream().map(p -> p.getFileName()).collect(Collectors.toList()));
            HtmlConverter.convertToPdf(html, pdfWriter, props);
        } catch (Exception e) {
            LOGGER.error("Error generating the pdf.", e);
            throw new PhotoSheetPdfException("Could not generate pdf.", e);
        }

    }

    @Override
    protected String getSupportedImageFormat() {
        return supportedImageFormat;
    }

    @Override
    protected ImageQualityProcessor getImageQualityProcessor() {
        return qualityProcessor;
    }
    
    
}
