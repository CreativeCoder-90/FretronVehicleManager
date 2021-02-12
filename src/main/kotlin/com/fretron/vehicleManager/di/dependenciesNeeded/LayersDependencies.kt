package com.fretron.vehicleManager.di.dependenciesNeeded

import com.fretron.vehicleManager.repositories.VehicleRepository
import com.fretron.vehicleManager.services.VehicleService
import javax.inject.Inject

class LayersDependencies @Inject constructor(var vehicleService: VehicleService, var vehicleRepository: VehicleRepository) {
}