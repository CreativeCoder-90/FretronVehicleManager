package com.fretron.vehicleManager.repositories

import com.fretron.vehicleManager.helpers.TestDataSource
import com.fretron.vehicleManager.model.Vehicle
import org.junit.Test
import org.junit.Assert.*


class VehicleRepositoryShould {

    @Test
    fun create_new_vehicle_with_new_registration_number(){
         var vehicle:Vehicle = TestDataSource.getVehicle()
        var createdVehicleByRepository:Vehicle? = createNewVehicle(vehicle)
        assertNotNull(createdVehicleByRepository)
    }

    private fun createNewVehicle(vehicle: Vehicle):Vehicle? {
        return VehicleRepository().createNewVehicle(vehicle)
    }

}