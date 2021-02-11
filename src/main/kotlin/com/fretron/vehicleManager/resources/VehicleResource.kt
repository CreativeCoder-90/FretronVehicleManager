package com.fretron.vehicleManager.resources

import com.fretron.vehicleManager.model.Vehicle
import com.fretron.vehicleManager.services.VehicleService
import org.codehaus.jackson.map.ObjectMapper
import java.lang.Exception
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("vehicle/v1")
class VehicleResource constructor(private var vehicleService: VehicleService, private val objectMapper: ObjectMapper) {

    @Path("vehicle")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createNewVehicle(request: String): Response {
        var createdVehicle: Vehicle? =
            vehicleService.createNewVehicle(ObjectMapper().readValue(request, Vehicle::class.java))
        return Response.ok(createdVehicle.toString()).build()

    }

}