package com.presinal.photo.sheet.generator.component.impl;

import com.presinal.photo.sheet.generator.component.PhotoSheetHtmlGenerator;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

/**
 * 
 * @author Miguel Presinal <presinal378@gmail.com>
 */
@Component
public class PhotoSheetHtmlGeneratorImpl implements PhotoSheetHtmlGenerator {

    final String CELL_TEMPLATE = "<td style=\"padding: 0 10px\">" +
            "<img src=\"%1$s\" alt=\"%1$s\" style=\"width: 200px; height: 300px; object-fit: contain;\">" +
            "<h5 style=\"text-align: center\">%1$s</h5>" +
            "</td>";

    final String HTML_TEMPLATE = "<html><body><table>%s</table></body></html>";

    @Override
    public String generateHtmlSheet(int cols, List<Path> images) {

        if (cols < 1) {
            throw new IllegalArgumentException("invalid cols value: " + cols +".It must be >= 1 ");
        }

        if (images == null) {
            throw new NullPointerException("images cannot be null");
        }

        return String.format(HTML_TEMPLATE, generateRows(cols, images));
    }

    private String generateRows(int cols, List<Path> images) {
        StringBuilder buffer = new StringBuilder();
        int length = images.size();
        StringBuilder tdHtml = new StringBuilder();
        String imgFile;
        for (int i = 1; i <= length; i++) {
            tdHtml.append(String.format(CELL_TEMPLATE, images.get(i-1).toString()));

            if (i % cols == 0 || i == length) {
                buffer.append("<tr>").append(tdHtml.toString()).append("</tr>");
                tdHtml.setLength(0); // clear buffer
            }
        }
        return buffer.toString();
    }
}
