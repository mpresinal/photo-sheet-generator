package com.presinal.photo.sheet.generator.component;

import java.nio.file.Path;
import java.util.List;

/**
 * 
 * @author Miguel Presinal <presinal378@gmail.com>
 */
public interface PhotoSheetHtmlGenerator {

    /**
     * Generate a html document containing at least a html table.
     * @param cols the amount of columns that the table will have
     * @param images A list of image path.
     * @throws NullPointerException when images is null
     * @throws IllegalArgumentException when cols is less than 1
     * @return
     */
    String generateHtmlSheet(int cols, List<Path> images);
}
