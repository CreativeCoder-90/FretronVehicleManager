package com.fretron.vehicleManager.services

import com.fretron.vehicleManager.helpers.TestDataSource
import org.junit.Test
import org.junit.Assert.*


class VehicleServiceShould {
    @Test
    fun createNewVehicleTest(){
        var vehicle = TestDataSource.getVehicle()   //we are assuming that this is the vehicle that will be provided by VehicalRespository class
        //check for not null object
        assertNotNull(vehicle)
        //check registrationNumber and chassisType for null and blank safety
        assertNotNull(vehicle.getRegistrationNumber())
        assertFalse(vehicle.getRegistrationNumber().equals(""))
        assertNotNull(vehicle.getChassisType())
        assertFalse(vehicle.getChassisType().equals(""))
    }
}