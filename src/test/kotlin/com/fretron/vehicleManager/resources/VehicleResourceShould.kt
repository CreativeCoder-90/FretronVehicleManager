package com.fretron.vehicleManager.resources

import com.fretron.vehicleManager.helpers.TestDataSource
import com.fretron.vehicleManager.services.VehicleService
import org.codehaus.jackson.map.ObjectMapper
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import org.junit.Test
import javax.ws.rs.client.Entity
import javax.ws.rs.core.Application
import javax.ws.rs.core.MediaType


//createVehicleRequest() provides a Json String which is needed to create vehicle
class VehicleResourceShould :JerseyTest(){
    private val baseURI:String="vehicle/v1"

    override fun configure(): Application{
        return ResourceConfig().register(VehicleResource(VehicleService(),ObjectMapper()))
    }

    @Test
    fun return_200_after_create_vehicle(){
        val requestedVehicle = TestDataSource.createVehicleRequest()
        val response = target("$baseURI/vehicle").request().post(Entity.entity(requestedVehicle, MediaType.APPLICATION_JSON))
        println("responseStatus: ${response.status}")
    }
}