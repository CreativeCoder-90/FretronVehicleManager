package com.fretron.vehicleManager.repositories

import com.fretron.vehicleManager.exceptions.vehicleExceptions.VehicleNotFoundException
import com.fretron.vehicleManager.helpers.TestDataSource
import com.fretron.vehicleManager.model.Vehicle
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import org.junit.Assert.*
import org.mockito.Mockito.mock



class VehicleRepositoryShould {

    private var vehicleRepository: VehicleRepository= mock(VehicleRepository::class.java)
    private val uuid:String = "1a1c5fe5-3ee0-453d-8425-5fec44961029"

    @Test
    fun create_new_vehicle_with_new_registration_number(){
         var vehicle:Vehicle = TestDataSource.getVehicle()   //this is the vehicle provided by user
        var createdVehicleByRepository:Vehicle? = createNewVehicle(vehicle)
        assertNotNull(createdVehicleByRepository)
    }

    private fun createNewVehicle(vehicle: Vehicle):Vehicle? {
        return vehicleRepository.createNewVehicle(vehicle)
    }

    @Test
    fun get_vehicle_by_uuid(){
        assertThrows(VehicleNotFoundException::class.java){
            vehicleRepository.getVehicleById(uuid)
        }
        var sampleVehicle= TestDataSource.getVehicle()
        var createdVehicle= createNewVehicle(sampleVehicle)
        assertTrue(sampleVehicle!=null)
        println("createdVehicle: $createdVehicle")
        if(createdVehicle!=null){
            assertTrue(sampleVehicle.getRegistrationNumber()==createdVehicle.getRegistrationNumber())
        }

    }

}