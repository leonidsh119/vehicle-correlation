package vehiclecorrelation;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import vehiclecorrelation.model.DetectedVehiclesEvent;
import vehiclecorrelation.service.VehicleCorrelationService;

import java.util.Map;

public class LambdaHandler implements RequestHandler<Map<String, Object>, String> {

    @Override
    public String handleRequest(Map<String, Object> event, Context context) {
        Gson gson = new Gson();
        DetectedVehiclesEvent detectedVehicles = gson.fromJson(gson.toJson(event), DetectedVehiclesEvent.class);
        VehicleCorrelationService service = new VehicleCorrelationService();
        service.process(detectedVehicles);
        return "OK";
    }
}