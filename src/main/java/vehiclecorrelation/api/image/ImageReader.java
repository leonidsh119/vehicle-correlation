package vehiclecorrelation.api.image;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface ImageReader {
    BufferedImage getImage(String sourceName, String uri);

    void deleteFromOrigin(String sourceName, String uri);

    void saveNewImage(BufferedImage image, String sourceName, String url);
}
