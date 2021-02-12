package com.fretron.vehicleManager.di.modules

import com.fretron.vehicleManager.repositories.VehicleRepository
import com.fretron.vehicleManager.services.VehicleService
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class LayersModule {

    @Provides
    fun vehicleRepositoryProvider():VehicleRepository{
        return VehicleRepository()
    }

//    @Provides
//    fun vehicleServiceProvider():VehicleService{
//        return VehicleService(vehicleRepository)
//    }

}