package vehiclecorrelation.service;

import vehiclecorrelation.api.reporter.AbandonedVehicleReporter;
import vehiclecorrelation.api.reporter.AbandonedVehicleReporterFactory;
import vehiclecorrelation.api.verification.VerificationServiceClient;
import vehiclecorrelation.api.verification.VerificationServiceClientFactory;
import vehiclecorrelation.api.data.SuspectedVehiclesDAO;
import vehiclecorrelation.api.data.SuspectedVehiclesDAOFactory;
import vehiclecorrelation.api.image.ImageReader;
import vehiclecorrelation.api.image.ImageReaderFactory;
import vehiclecorrelation.model.*;

import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class VehicleCorrelationService {
    private final ImageReader imageReader = new ImageReaderFactory().createReader();

    private final SuspectedVehiclesDAO suspectedVehiclesDAO = new SuspectedVehiclesDAOFactory().createDao();

    private final VerificationServiceClient verificationService = new VerificationServiceClientFactory().createClient();

    private final AbandonedVehicleReporter reporter = new AbandonedVehicleReporterFactory().createReporter();

    private final int likehoodTreshold = Integer.parseInt(System.getenv("LIKEHOOD_TRESHOLD"));

    private final int abandonedDaysTreshold = Integer.parseInt(System.getenv("ABANDON_TRESHOLD"));

    private final String cameraUploadS3Bucket = System.getenv("CAMERA_S3_BUCKET");

    private final String clipImageS3Bucket = System.getenv("CLIP_S3_BUCKET");

    public void process(DetectedVehiclesEvent detectedVehicles) {
        String cameraId = detectedVehicles.getCamId();
        List<Vehicle> detected = detectedVehicles.getVehicles();
        List<SuspectedVehicle> suspected = suspectedVehiclesDAO.getSuspectedVehicles(cameraId);

        // Remove suspected vehicles that no more appear in detected
        suspected.removeIf(vehicle -> !detected.contains(vehicle));

        // Retrieve Source Image
        BufferedImage sourceImage = imageReader.getImage(cameraUploadS3Bucket, detectedVehicles.getSourceS3Object());

        // Process each Detected Vehicle in the Image
        detected.forEach(vehicle -> correlate(vehicle, suspected, detectedVehicles.getDateTime(), sourceImage));

        // Update Suspected Vehicles list
        suspectedVehiclesDAO.save(cameraId, suspected);
    }

    private void correlate(Vehicle vehicle, List<SuspectedVehicle> suspected, LocalDateTime captureDateTime, BufferedImage sourceImage) {
        // Create and Store clipped Vehicle image from original image
        BufferedImage clippedImage = clip(sourceImage, vehicle.getPositionOnSourceImage());
        String clipUrl = UUID.randomUUID().toString();
        imageReader.saveNewImage(clippedImage, clipImageS3Bucket, clipUrl);

        Optional<SuspectedVehicle> suspectedVehicleOpt = suspected.stream().filter(vehicle::equals).findAny();
        if(suspectedVehicleOpt.isPresent()) {
            // Check likehood score for being the same vehicle
            SuspectedVehicle suspectedVehicle = suspectedVehicleOpt.get();
            int likehoodScore = verificationService.getLikehoodScore(clipUrl, suspectedVehicle.getLastClipImageLocation());
            if(likehoodScore >= likehoodTreshold) {
                // Same Vehicle continue to appear
                // Replace Clipped Image for the next day comparison
                suspectedVehicle.setLastClipImageLocation(clipUrl);
                imageReader.deleteFromOrigin(clipImageS3Bucket, suspectedVehicle.getLastClipImageLocation());

                // Check how long the Vehicle in Suspected list
                long daysInSuspected = Duration.between(
                        suspectedVehicle.getDetectedSince().toLocalDate().atStartOfDay(),
                        captureDateTime.toLocalDate().atStartOfDay())
                        .toDays();
                if(daysInSuspected >= abandonedDaysTreshold) {
                    // Report to Municipal systems
                    AbandonedVehicleEvent event = new AbandonedVehicleEvent();
                    reporter.publish(event);
                }
            } else {
                // Assuming the old vehicle was gone and another (similar) is standing in same location
                // Remove old Suspected Vehicle and its Clipped Image
                imageReader.deleteFromOrigin(clipImageS3Bucket, suspectedVehicle.getLastClipImageLocation());
                suspected.remove(suspectedVehicle);

                // Add the detected vehicle to Suspected list
                suspected.add(new SuspectedVehicle(vehicle, captureDateTime, clipUrl));
            }
        } else {
            // Add the detected vehicle to Suspected list
            suspected.add(new SuspectedVehicle(vehicle, captureDateTime, clipUrl));
        }
    }

    private BufferedImage clip(BufferedImage sourceImage, VehiclePosition position) {
        return sourceImage.getSubimage(
                position.getTopLeft().getX(),
                position.getTopLeft().getY(),
                position.getBottomRight().getX() - position.getTopLeft().getX(),
                position.getBottomRight().getY() - position.getTopLeft().getY());
    }
}
