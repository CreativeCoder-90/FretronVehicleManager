package com.fretron.vehicleManager.resources

import com.fretron.vehicleManager.model.Vehicle
import com.fretron.vehicleManager.services.VehicleService
import org.codehaus.jackson.map.ObjectMapper
import java.lang.Exception
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("vehicle/v1")
class VehicleResource @Inject constructor(var vehicleService: VehicleService) {

    @Path("vehicle")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createNewVehicle(request: String): Response {
        var v: Vehicle = ObjectMapper().readValue(request, Vehicle::class.java)  //this is the vehicle converted from json String(provided by user) to java object
        var createdVehicle: Vehicle = vehicleService.createNewVehicle(v)
        return Response.ok(createdVehicle.toString()).build()
        return Response.ok(v.toString()).build()
    }

    @Path("vehicle/{uuid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getVehicleByUUID(@PathParam("uuid") id:String): Response{
        var vehicleFetchedById:Vehicle? = vehicleService.getVehicleById(id)
        return Response.ok().entity(vehicleFetchedById.toString()).build()
    }

    @Path("vehicles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllVehicles(): Response{
        var vehiclesFetchedFromRepo: ArrayList<Vehicle> = vehicleService.getAllVehicles()
        return Response.ok().entity(vehiclesFetchedFromRepo.toString()).build()
    }

    @Path("vehicle/{uuid}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    fun deleteVehicleById(@PathParam("uuid") id: String):Response{
          var vehicleDeletedFromRepo:Vehicle? = vehicleService.deleteVehicleById(id)
        return Response.ok().entity(vehicleDeletedFromRepo.toString()).build()
    }

    @Path("vehicle")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun updateVehicle(request: String):Response{
        var vehicleToBeUpdated:Vehicle = ObjectMapper().readValue(request,Vehicle::class.java)
       var vehicleUpdatedByRepository:Vehicle? = vehicleService.updateGivenVehicle(vehicleToBeUpdated)
        return Response.ok().entity(vehicleUpdatedByRepository.toString()).build()
    }

}