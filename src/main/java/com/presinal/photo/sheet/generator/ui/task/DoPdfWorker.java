/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presinal.photo.sheet.generator.ui.task;

import com.presinal.photo.sheet.generator.domain.PhotoSheetProperties;
import com.presinal.photo.sheet.generator.service.PhotoSheetPdfService;
import com.presinal.photo.sheet.generator.service.execption.PhotoSheetPdfException;
import com.presinal.photo.sheet.generator.ui.MainJFrame;
import javax.swing.SwingWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 */
public class DoPdfWorker extends SwingWorker<Void, Void> {
    
    private static final Logger logger = LoggerFactory.getLogger(DoPdfWorker.class);

    private String imagesDir;
    private String outputFile;
    private PhotoSheetProperties props;
    private PhotoSheetPdfService photoSheetPdfService;

    public DoPdfWorker(String imagesDir, String outputFile, PhotoSheetProperties props, PhotoSheetPdfService photoSheetPdfService) {
        this.imagesDir = imagesDir;
        this.outputFile = outputFile;
        this.props = props;
        this.photoSheetPdfService = photoSheetPdfService;
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            photoSheetPdfService.doPdf(imagesDir, outputFile, props);
            firePropertyChange("success", false, true);
        } catch (PhotoSheetPdfException ex) {
            logger.error("Error generating pdf. " + ex.getMessage());
            firePropertyChange("success", false, false);
        }
        return null;
    }

}
