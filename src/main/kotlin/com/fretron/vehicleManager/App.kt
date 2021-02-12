import com.fretron.vehicleManager.di.components.DaggerVehicleAppComponent
import org.glassfish.grizzly.http.server.HttpServer

//This is the entry point of our application, from here the server is started
fun main(){
var httpServer:HttpServer = DaggerVehicleAppComponent.builder().build().server()
    httpServer.start()
}