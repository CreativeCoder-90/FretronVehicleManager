package com.fretron.vehicleManager.services

import com.fretron.vehicleManager.exceptions.dbExceptions.MongoException
import com.fretron.vehicleManager.exceptions.vehicleExceptions.InvalidVehicleChassisException
import com.fretron.vehicleManager.exceptions.vehicleExceptions.InvalidVehicleException
import com.fretron.vehicleManager.exceptions.vehicleExceptions.InvalidVehicleRegistrationException
import com.fretron.vehicleManager.exceptions.vehicleExceptions.VehicleNotFoundException
import com.fretron.vehicleManager.model.Vehicle
import com.fretron.vehicleManager.repositories.VehicleRepository
import javax.inject.Inject
import kotlin.jvm.Throws

class VehicleService @Inject constructor(var vehicleRepository: VehicleRepository) {

    fun createNewVehicle(v: Vehicle):Vehicle {          //here v is the vehicle provided by user/postman
        validateVehicle(v)
       return vehicleRepository.createNewVehicle(v)
    }

    fun validateVehicle(v: Vehicle){
        if (v==null)
            throw InvalidVehicleException("vehicle can not be null")
        else if (v.getRegistrationNumber().equals("") || v.getRegistrationNumber().equals(null))
            throw InvalidVehicleRegistrationException("Vehicle registration number can't be blank")
        else if (v.getChassisType().equals("") || v.getChassisType().equals(null))
            throw InvalidVehicleChassisException("Vehicle chassis type can't be blank")
    }

    @Throws(VehicleNotFoundException::class)
    fun getVehicleById(id: String): Vehicle? {
        var vehicleFromRepo:Vehicle? = null
        if(id!=null) {
            vehicleFromRepo = vehicleRepository.getVehicleById(id)
        }
        return vehicleFromRepo
        }

    fun getAllVehicles(): ArrayList<Vehicle> {
        return vehicleRepository.getAllVehicles()
    }

    fun deleteVehicleById(id: String): Vehicle? {
        var vehicleDeletedFromRepo:Vehicle? = vehicleRepository.deleteVehicleById(id)
        return vehicleDeletedFromRepo
    }

    fun updateGivenVehicle(vehicleToBeUpdated: Vehicle): Vehicle? {
        var vehicleUpdatedByRepository:Vehicle? = vehicleRepository.updateGivenVehicle(vehicleToBeUpdated)
        if(vehicleUpdatedByRepository== null){
            throw VehicleNotFoundException("vehicle not found with id: ${vehicleToBeUpdated.getUuid()}, so can't update")
        }
        return vehicleUpdatedByRepository
    }

}
