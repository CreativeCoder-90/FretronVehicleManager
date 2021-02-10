package com.fretron.vehicleManager.services

import com.fretron.vehicleManager.model.Vehicle

class VehicleService {
    fun createNewVehicle(readValue: Vehicle):Vehicle {
        return Vehicle()
    }


}
