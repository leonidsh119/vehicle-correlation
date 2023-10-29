package vehiclecorrelation.api.image;

public class ImageReaderFactory {
    public ImageReader createReader() {
        return new S3ImageReader();
    }
}
