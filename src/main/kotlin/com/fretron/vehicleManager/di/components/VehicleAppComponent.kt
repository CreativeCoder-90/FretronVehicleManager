package com.fretron.vehicleManager.di.components

import com.fretron.vehicleManager.di.dependenciesNeeded.LayersDependencies
import com.fretron.vehicleManager.di.modules.ConfigModule
import com.fretron.vehicleManager.di.modules.HttpModule
import com.fretron.vehicleManager.di.modules.LayersModule
import dagger.Component
import org.glassfish.grizzly.http.server.HttpServer
import javax.inject.Singleton

@Singleton
@Component(modules = [LayersModule::class, HttpModule::class, ConfigModule::class])
interface VehicleAppComponent {
    fun buildLayer():LayersDependencies
    fun server():HttpServer     //here dagger looks if there is a @Provides annotated method whose return type is same as server(),
                                //then it will inject the dependency needed in HttpServer
}