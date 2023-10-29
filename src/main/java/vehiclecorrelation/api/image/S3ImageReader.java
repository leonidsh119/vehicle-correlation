package vehiclecorrelation.api.image;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.awt.image.BufferedImage;

public class S3ImageReader implements ImageReader {
    final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
    @Override
    public BufferedImage getImage(String sourceName, String uri) {
        throw new UnsupportedOperationException("S3ImageReader::getImage not implemented yet.");
    }

    public void deleteFromOrigin(String sourceName, String uri) {
        throw new UnsupportedOperationException("S3ImageReader::deleteFromOrigin not implemented yet.");
    }

    @Override
    public void saveNewImage(BufferedImage image, String sourceName, String url) {
        throw new UnsupportedOperationException("S3ImageReader::deleteFromOrigin not implemented yet.");
    }
}
