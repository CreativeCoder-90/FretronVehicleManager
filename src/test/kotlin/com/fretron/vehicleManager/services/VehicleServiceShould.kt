package com.fretron.vehicleManager.services

import com.fretron.vehicleManager.exceptions.vehicleExceptions.InvalidVehicleRegistrationException
import com.fretron.vehicleManager.helpers.TestDataSource
import com.fretron.vehicleManager.model.Vehicle
import com.fretron.vehicleManager.repositories.VehicleRepository
import org.junit.Test
import org.junit.Assert.*


//This class has been setup to test the VehicleService class in main folder
class VehicleServiceShould {

    private lateinit var vehicleRepository: VehicleRepository /*= mock(VehicleRepository::class.java)*/
    private var vehicleService: VehicleService = VehicleService(vehicleRepository)

//    @Before
//    fun set_result_to_required_methods_of_mocked_classes() {
//        whenever(vehicleRepository.createNewVehicle(any())).thenReturn(TestDataSource.getVehicle())
//    }

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
            vehicle!!.setRegistrationNumber("")
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
