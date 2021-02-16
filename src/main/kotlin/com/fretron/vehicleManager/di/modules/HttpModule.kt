package com.fretron.vehicleManager.di.modules

import com.fretron.vehicleManager.exceptions.exceptionMapper.VehicleExceptionMapper
import com.fretron.vehicleManager.resources.VehicleResource
import dagger.Module
import org.glassfish.jersey.server.ResourceConfig
import dagger.Provides
import org.codehaus.jackson.map.ObjectMapper
import org.glassfish.grizzly.http.server.HttpServer
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory
import javax.inject.Named
import javax.sound.sampled.Port
import javax.ws.rs.core.UriBuilder

@Module
class HttpModule {

    @Provides
    fun provideResource(vehicleResource: VehicleResource):ResourceConfig{
       return ResourceConfig().register(vehicleResource).register(VehicleExceptionMapper())
    }

    @Provides
    fun provideServer(@Named("host")host:String, @Named("port")port: Int, config: ResourceConfig):HttpServer{
        val url = UriBuilder.fromUri(host).port(port).build()
        return GrizzlyHttpServerFactory.createHttpServer(url,config)
    }

}