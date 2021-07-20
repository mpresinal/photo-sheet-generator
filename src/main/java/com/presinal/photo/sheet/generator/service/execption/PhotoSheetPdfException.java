package com.presinal.photo.sheet.generator.service.execption;

/**
 * 
 * @author Miguel Presinal <presinal378@gmail.com>
 */
public class PhotoSheetPdfException extends Exception {

    public PhotoSheetPdfException() {
        this("Could not generate photo shoot sheet pdf file.");
    }

    public PhotoSheetPdfException(String message) {
        this(message, null);
    }

    public PhotoSheetPdfException(String message, Throwable cause) {
        super(message, cause);
    }

}
