package com.fretron.vehicleManager.di.modules

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ConfigModule {

    @Provides
    @Named("host")
    fun hostProvider():String{
        return "http://localhost"
    }

    @Provides
    @Named("port")
    fun portProvider():Int{
        return 8080
    }

}