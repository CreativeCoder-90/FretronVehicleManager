package com.fretron.vehicleManager.resources

import com.fretron.vehicleManager.di.components.DaggerVehicleAppComponent
import com.fretron.vehicleManager.helpers.TestDataSource
import com.fretron.vehicleManager.model.Vehicle
import com.fretron.vehicleManager.services.VehicleService
import com.nhaarman.mockito_kotlin.whenever
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import org.json.JSONObject
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.*
import javax.ws.rs.client.Entity
import javax.ws.rs.core.Application
import javax.ws.rs.core.MediaType
import com.nhaarman.mockito_kotlin.any
import org.codehaus.jackson.map.ObjectMapper
import org.junit.Assert.assertNotNull
import javax.inject.Inject
import javax.json.JsonObject


//createVehicleRequest() provides a Json String which is needed to create vehicle
class VehicleResourceShould :JerseyTest(){
    private val uuid: String="1a1c5fe5-3ee0-453d-8425-5fec44961029"
    private val baseURI:String="vehicle/v1"

     lateinit var vehicleService:VehicleService

    override fun configure(): Application{
        vehicleService = mock(VehicleService::class.java)
        return ResourceConfig().register(VehicleResource(vehicleService))
    }

    @Before
    fun mocked_class_required_methods_results(){
        whenever(vehicleService.createNewVehicle(any())).thenReturn(TestDataSource.getVehicle())
        whenever(vehicleService.getVehicleById(any())).thenReturn(TestDataSource.getVehicle())
        whenever(vehicleService.getAllVehicles()).thenReturn(arrayListOf(TestDataSource.getVehicle()))
        whenever(vehicleService.deleteVehicleById(any())).thenReturn(TestDataSource.getVehicle())
        whenever(vehicleService.updateGivenVehicle(any())).thenReturn(TestDataSource.getUpdatedVehicle())
    }

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

    @Test
    fun return_200_after_getting_vehicle_using_uuid(){
        var response = target("$baseURI/vehicle/$uuid").request().get()
        println(response.status)
        var responseJson = JSONObject(response.readEntity(String::class.java))
        assertNotNull(responseJson)
        println("response uuid : ${responseJson.get("uuid").toString()}")
    }

    @Test
    fun return_200_after_getting_list_of_all_vehicles(){
        val response = target("$baseURI/vehicles").request().get()
        println("response status: ${response.status}")
        assertTrue(response.status==200)
        assertNotNull(response)
    }

    @Test
    fun return_200_after_deleting_vehicle_by_uuid(){
        val response = target("$baseURI/vehicle/$uuid").request().delete()
        println(response.status)
        assertTrue(response.status==200)
        var responseJson = JSONObject(response.readEntity(String::class.java))
        assertNotNull(responseJson)
        println("response uuid: ${responseJson.get("uuid")}")
    }

    @Test
    fun return_200_after_updating_vehicle(){
        var updateVehicleRequest = TestDataSource.updateVehicleRequest()
        var updatedVehicle = TestDataSource.getUpdatedVehicle()
        var response = target("$baseURI/vehicle").request().put(Entity.entity(updateVehicleRequest, MediaType.APPLICATION_JSON))
        println("response status: ${response.status}")
        assertTrue(response.status==200)
        var jsonResponse = JSONObject(response.readEntity(String::class.java))
        println("uuid of requested vehicle to be updated:${updatedVehicle.getUuid()}")
        println("uuid of updated vehicle :${jsonResponse.get("uuid")}")
        println("requested vehicle to be updated: ${updatedVehicle.getBodyType()}")
        println("updated vehicle :${jsonResponse.get("bodyType")}")
        assertNotNull(jsonResponse)
        assertTrue(updatedVehicle.getUuid()==jsonResponse.get("uuid"))
    }

}