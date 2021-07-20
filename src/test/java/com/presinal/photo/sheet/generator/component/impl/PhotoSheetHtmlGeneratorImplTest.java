package com.presinal.photo.sheet.generator.component.impl;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PhotoSheetHtmlGeneratorImplTest {

    @Test
    public void whenValidInputs_generateValidHtml() {
        PhotoSheetHtmlGeneratorImpl generator = new PhotoSheetHtmlGeneratorImpl();
        final Path IMAGE_DIR = Paths.get("src", "test", "resources", "images");
        List<Path> images = new ArrayList<>();
        images.add(IMAGE_DIR.resolve("google.png"));
        images.add(IMAGE_DIR.resolve("facebook.png"));
        images.add(IMAGE_DIR.resolve("microsoft.png"));

        String rows = "<tr>" + String.format(generator.CELL_TEMPLATE, images.get(0))
                + String.format(generator.CELL_TEMPLATE, images.get(1))
                + "</tr>"
                + "<tr>"
                + String.format(generator.CELL_TEMPLATE, images.get(2))
                + "</tr>";
        String expectedResult = String.format(generator.HTML_TEMPLATE, rows);

        String result = generator.generateHtmlSheet(2, images);
        assertEquals(expectedResult, result, "invalid generated html");
    }

    @Test
    public void whenImagesIsEmpty_emptyTable() {
        final StringBuilder EXPECTED_RESULT = new StringBuilder("<html>");
        EXPECTED_RESULT.append("<body>")
                .append("<table>")
                .append("</table>")
                .append("</body>")
                .append("</html>");

        String result = new PhotoSheetHtmlGeneratorImpl().generateHtmlSheet(2, new ArrayList<>());
        assertEquals(EXPECTED_RESULT.toString(), result, "invalid generated html");
    }

    @Test
    public void whenImagesIsNull_throwNPE() {
        assertThrows(NullPointerException.class, () -> {
            new PhotoSheetHtmlGeneratorImpl().generateHtmlSheet(2, null);
        });
    }

    @Test
    public void whenColsIsZero_throwIAE() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PhotoSheetHtmlGeneratorImpl().generateHtmlSheet(0, new ArrayList<>());
        });
    }
}
