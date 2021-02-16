package com.fretron.vehicleManager.services

import com.fretron.vehicleManager.exceptions.vehicleExceptions.InvalidVehicleChassisException
import com.fretron.vehicleManager.exceptions.vehicleExceptions.InvalidVehicleException
import com.fretron.vehicleManager.exceptions.vehicleExceptions.InvalidVehicleRegistrationException
import com.fretron.vehicleManager.helpers.TestDataSource
import com.fretron.vehicleManager.model.Vehicle
import com.fretron.vehicleManager.repositories.VehicleRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.mock


//This class has been setup to test the VehicleService class in main folder
class VehicleServiceShould {

    private var vehicleRepository: VehicleRepository = mock(VehicleRepository::class.java)
    private var vehicleService: VehicleService = VehicleService(vehicleRepository)
    private val uuid:String = "1a1c5fe5-3ee0-453d-8425-5fec44961029"

    @Before
    fun set_result_to_required_methods_of_mocked_classes() {
        whenever(vehicleRepository.createNewVehicle(any())).thenReturn(TestDataSource.getVehicle())
    }

    @Test
    fun createNewVehicleTest(){
        var vehicle = TestDataSource.getVehicle()   //we are assuming that this is the vehicle that will be provided by VehicalRespository class
        var vehicleReturnedFromRepo = vehicleRepository.createNewVehicle(vehicle)
        if(vehicleReturnedFromRepo!=null){
            assertTrue(vehicle.get("uuid")==vehicleReturnedFromRepo.get("uuid"))
        }
    }

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

    @Test
    fun get_vehicle_by_uuid(){
        var vehicleFromRepo:Vehicle = vehicleRepository.getVehicleById(uuid)
        assertNotNull(vehicleFromRepo)
        var sampleVehicle:Vehicle = TestDataSource.getVehicle()
        assertTrue(sampleVehicle.getUuid().equals(vehicleFromRepo.getUuid()))
        println("sampleVehicle uuid: ${sampleVehicle.getUuid()}")

    }



    }
