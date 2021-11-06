package com.presinal.photo.sheet.generator.domain;

/**
 * 
 * @author Miguel Presinal <presinal378@gmail.com>
 */
public class PhotoSheetProperties {
    private int photosPerRow;
    private float imageQuality;

    public int getPhotosPerRow() {
        return photosPerRow;
    }

    public void setPhotosPerRow(int photosPerRow) {
        this.photosPerRow = photosPerRow;
    }

    /**
     * 
     * @return A value between 0.1 and 1.0
     */
    public float getImageQuality() {
        return imageQuality;
    }

    /**
     * 
     * @param imageQuality  A value between 0.1 and 1.0
     */
    public void setImageQuality(float imageQuality) {
        this.imageQuality = imageQuality;
    }
}
