package com.fretron.vehicleManager.resources

import com.fretron.vehicleManager.di.components.DaggerVehicleAppComponent
import com.fretron.vehicleManager.helpers.TestDataSource
import com.fretron.vehicleManager.services.VehicleService
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import org.json.JSONObject
import org.junit.Test
import org.junit.Assert.assertTrue
import org.mockito.Mockito.*
import javax.ws.rs.client.Entity
import javax.ws.rs.core.Application
import javax.ws.rs.core.MediaType


//createVehicleRequest() provides a Json String which is needed to create vehicle
class VehicleResourceShould :JerseyTest(){
    private val baseURI:String="vehicle/v1"

    override fun configure(): Application{
       // vehicleService = mock(VehicleService::class.java)
        val vehicleService:VehicleService= DaggerVehicleAppComponent.builder().build().buildLayer().vehicleService
        return ResourceConfig().register(VehicleResource(vehicleService))
    }

//    @Before
//    fun mocked_class_required_methods_results(){
//        whenever(vehicleService.createNewVehicle(any())).thenReturn(TestDataSource.getVehicle())
//    }

    @Test
    fun return_200_after_create_vehicle(){
        var requestedVehicle = TestDataSource.createVehicleRequest()     //Json string of vehicle provided by user
        var response = target("$baseURI/vehicle").request().post(Entity.entity(requestedVehicle, MediaType.APPLICATION_JSON))
        println("responseStatus: ${response.status}")
        assertTrue(response.status==200)
        val responseJson = JSONObject(response.readEntity(String::class.java))  //To convert Json string into Json object
        var uuid = responseJson.get("uuid").toString()
        println("response uuid check: $uuid")
    }
}