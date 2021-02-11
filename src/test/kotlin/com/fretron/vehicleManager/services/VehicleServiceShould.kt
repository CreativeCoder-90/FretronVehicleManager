package com.fretron.vehicleManager.services

import com.fretron.vehicleManager.exceptions.vehicleExceptions.InvalidVehicleRegistrationException
import com.fretron.vehicleManager.helpers.TestDataSource
import com.fretron.vehicleManager.model.Vehicle
import com.fretron.vehicleManager.repositories.VehicleRepository
import org.junit.Test
import org.junit.Assert.*

//This class has been setup to test the VehicleService class in main folder
class VehicleServiceShould {

    private var vehicleService:VehicleService = VehicleService(VehicleRepository())

//    @Test
//    fun createNewVehicleTest(){
//        var vehicle = TestDataSource.getVehicle()   //we are assuming that this is the vehicle that will be provided by VehicalRespository class
//        //check for not null object
//        assertNotNull(vehicle)
//        //check registrationNumber and chassisType for null and blank safety
//        assertNotNull(vehicle.getRegistrationNumber())
//        assertFalse(vehicle.getRegistrationNumber().equals(""))
//        assertNotNull(vehicle.getChassisType())
//        assertFalse(vehicle.getChassisType().equals(""))
//    }

    @Test
    fun throw_exception_on_invalid_or_null_data(){
        var vehicle:Vehicle? = TestDataSource.getVehicle()  //vehicle provided by user/postman

        assertThrows(InvalidVehicleRegistrationException::class.java){
            vehicle!!.setRegistrationNumber(null)
            vehicleService.createNewVehicle(vehicle!!)
        }

//        assertThrows(InvalidVehicleChassisException::class.java) {
//            vehicle!!.setChassisType("")
//            vehicleService.createNewVehicle(vehicle!!)
//        }
//
//            assertThrows(InvalidVehicleException::class.java){
//                vehicle=null
//                vehicleService.createNewVehicle(vehicle!!)
//            }
       }
    }
