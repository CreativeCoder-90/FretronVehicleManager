package com.fretron.vehicleManager.di.modules

import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideMongoDatabase():MongoDatabase{
        return MongoClient("localhost",27017).getDatabase("vehicles")
    }

}