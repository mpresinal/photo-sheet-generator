package com.presinal.photo.sheet.generator.service;

import com.presinal.photo.sheet.generator.domain.PhotoSheetProperties;
import com.presinal.photo.sheet.generator.service.execption.PhotoSheetPdfException;

/**
 * 
 * @author Miguel Presinal <presinal378@gmail.com>
 */
public interface PhotoSheetPdfService {

    void doPdf(String imagesDir, String outputFile, PhotoSheetProperties props) throws PhotoSheetPdfException;
}
