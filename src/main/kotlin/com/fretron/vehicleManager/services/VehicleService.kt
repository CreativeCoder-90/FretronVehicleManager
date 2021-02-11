package com.fretron.vehicleManager.services

import com.fretron.vehicleManager.exceptions.vehicleExceptions.InvalidVehicleChassisException
import com.fretron.vehicleManager.exceptions.vehicleExceptions.InvalidVehicleException
import com.fretron.vehicleManager.exceptions.vehicleExceptions.InvalidVehicleRegistrationException
import com.fretron.vehicleManager.model.Vehicle
import com.fretron.vehicleManager.repositories.VehicleRepository

class VehicleService constructor(var vehicleRepository: VehicleRepository) {
    fun createNewVehicle(vehicle: Vehicle):Vehicle? {
        validateVehicle(vehicle)
       return vehicleRepository.createNewVehicle(vehicle)
    }

    fun validateVehicle(vehicle: Vehicle){
        if (vehicle==null)
            throw InvalidVehicleException("vehicle can not be null")
        else if (vehicle.getRegistrationNumber().equals("") || vehicle.getRegistrationNumber().equals(null))
            throw InvalidVehicleRegistrationException("Vehicle registration number can't be blank")
        else if (vehicle.getChassisType().equals("") || vehicle.getChassisType().equals(null))
            throw InvalidVehicleChassisException("Vehicle chassis type can't be blank")
    }
}
