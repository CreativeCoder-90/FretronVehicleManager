package com.fretron.vehicleManager.di.components

import com.fretron.vehicleManager.di.modules.ConfigModule
import com.fretron.vehicleManager.di.modules.DatabaseModule
import com.fretron.vehicleManager.di.modules.HttpModule
import com.mongodb.client.MongoDatabase
import dagger.Component
import org.glassfish.grizzly.http.server.HttpServer
import javax.inject.Singleton

@Singleton
@Component(modules = [HttpModule::class, ConfigModule::class, DatabaseModule::class])
interface VehicleAppComponent {
    fun server(): HttpServer
    fun provideMongoDatabase(): MongoDatabase         //here dagger looks if there is a @Provides annotated method whose return type is same as server(),
                                                    //then it will inject the dependency needed in HttpServer
}
