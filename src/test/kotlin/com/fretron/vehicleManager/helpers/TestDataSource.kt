package com.fretron.vehicleManager.helpers

import com.fretron.vehicleManager.model.Vehicle
import org.codehaus.jackson.map.ObjectMapper

object TestDataSource {
    fun createVehicleRequest():String{      //This is a Json string which is needed to create a vehicle
        return "{" +
                "\"uuid\":\"1a1c5fe5-3ee0-453d-8425-5fec44961029\"," +
                "\"registrationNumber\":\"11\"," +
                "\"driverName\": \"Driver Name\"," +
                "\"carryingCapacity\":\"40\"," +
                "\"bodyType\": \"Type-1\"," +
                "\"chassisType\": \"Type-2\"" +
                " }"
    }

    fun getVehicle():Vehicle{
        var jsonString = "{\n" +
                "    \"uuid\": \"1a1c5fe5-3ee0-453d-8425-5fec44961029\",\n" +
                "    \"registrationNumber\": \"11\",\n" +
                "    \"driverName\": \"Driver Name\",\n" +
                "    \"bodyType\": \"Type-1\",\n" +
                "    \"chassisType\": \"Type-2\",\n" +
                "    \"carryingCapacity\": \"40\"\n" +
                "}"
        return ObjectMapper().readValue(jsonString,Vehicle::class.java)
    }

}
