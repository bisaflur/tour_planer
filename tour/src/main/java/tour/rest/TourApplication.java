package tour.rest;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(info = @Info(title = "TourPlaner REST API", version = "0.01"))
public class TourApplication extends Application {
}
