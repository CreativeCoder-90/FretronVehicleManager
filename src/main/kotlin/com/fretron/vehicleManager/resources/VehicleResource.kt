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
class VehicleResource @Inject constructor(var vehicleService: VehicleService) {

    @Path("vehicle")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createNewVehicle(request: String): Response {
       var v:Vehicle = ObjectMapper().readValue(request,Vehicle::class.java)  //this is the vehicle converted from json String(provided by user)
        var createdVehicle: Vehicle? =                                        // to java object
            vehicleService.createNewVehicle(v)
        return Response.ok(createdVehicle.toString()).build()
    }

}