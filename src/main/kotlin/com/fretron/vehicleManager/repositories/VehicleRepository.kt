package com.fretron.vehicleManager.repositories

import com.fretron.vehicleManager.model.Vehicle

class VehicleRepository {
    fun createNewVehicle(v: Vehicle?) :Vehicle?{
        return Vehicle()
    }
}
