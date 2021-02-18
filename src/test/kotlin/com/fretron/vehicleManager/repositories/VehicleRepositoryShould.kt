package com.fretron.vehicleManager.repositories

import com.fretron.vehicleManager.exceptions.vehicleExceptions.VehicleNotFoundException
import com.fretron.vehicleManager.helpers.EmbeddedMongoDb
import com.fretron.vehicleManager.helpers.TestDataSource
import com.fretron.vehicleManager.model.Vehicle
import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import com.nhaarman.mockito_kotlin.mock
import org.codehaus.jackson.map.ObjectMapper
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.mock
import kotlin.jvm.Throws
import java.util.*


class VehicleRepositoryShould {

    private lateinit var embeddedMongoDb: EmbeddedMongoDb
    private lateinit var database: MongoDatabase
    private val objectMapper = ObjectMapper()
    private lateinit var  vehicleRepository: VehicleRepository
    private val uuid: String = "1a1c5fe5-3ee0-453d-8425-5fec44961029"

    @Before
    fun configure() {

        startMongoDb()
        val mongoClient = MongoClient("localhost", embeddedMongoDb.port)
        database = mongoClient.getDatabase("vehicle")
        vehicleRepository = VehicleRepository(database)
    }

    @After
    fun closeConnections() {
        embeddedMongoDb.stop()
    }

    private fun startMongoDb() {
        val rand = Random()
        val n = rand.nextInt(99) + 9900
        embeddedMongoDb = EmbeddedMongoDb(n)
        embeddedMongoDb.start()
    }

    @Test
    fun create_new_vehicle_with_new_registration_number() {
        var vehicle: Vehicle = TestDataSource.getVehicle()   //this is the vehicle provided by user
        var createdVehicleByRepository: Vehicle? = createNewVehicle(vehicle)
        assertNotNull(createdVehicleByRepository)
    }

    private fun createNewVehicle(vehicle: Vehicle): Vehicle? {
        return vehicleRepository.createNewVehicle(vehicle)
    }

    @Test
    fun get_vehicle_by_uuid() {
        assertThrows(VehicleNotFoundException::class.java) {
            vehicleRepository.getVehicleById(uuid)
        }
        var sampleVehicle = TestDataSource.getVehicle()
        var createdVehicle = createNewVehicle(sampleVehicle)
        assertTrue(sampleVehicle != null)
        println("createdVehicle: $createdVehicle")
        if (createdVehicle != null) {
            assertTrue(sampleVehicle.getRegistrationNumber() == createdVehicle.getRegistrationNumber())
        }
    }

    @Test
    fun get_list_of_all_vehicles() {
        var vehicle_list_size_before_adding_any = vehicleRepository.getAllVehicles().size
        assertTrue(vehicle_list_size_before_adding_any == 0)
        println("vehicle_list_size_before_adding_any: $vehicle_list_size_before_adding_any")

        vehicleRepository.createNewVehicle(TestDataSource.getVehicle())

        var vehicle_list_size_after_adding_any = vehicleRepository.getAllVehicles().size
        println("vehicle_list_size_after_adding_any: $vehicle_list_size_after_adding_any")
        assertTrue(vehicle_list_size_after_adding_any == 1)
    }

    @Test
    fun delete_vehicle_by_id_Test() {
        assertThrows(VehicleNotFoundException::class.java) {
            vehicleRepository.deleteVehicleById(uuid)    //uuid from TestDataSource
        }
        createNewVehicle(TestDataSource.getVehicle())
        val deleteVehicleById = vehicleRepository.deleteVehicleById(uuid)
        println("deleted vehicle uuid --> ${deleteVehicleById?.getUuid()}")
        assertTrue(deleteVehicleById?.getUuid() == uuid)
    }

    @Test
    fun update_given_vehicle_Test(){
        var oldVehicle = TestDataSource.getVehicle()        //vehicle to be updated
        var updatedVehicle = TestDataSource.getUpdatedVehicle()      //new vehicle to be updated in db
        assertTrue(oldVehicle.getUuid()==updatedVehicle.getUuid())
        assertTrue(oldVehicle.getDriverName()!=updatedVehicle.getDriverName())

        createNewVehicle(oldVehicle)

        var vehicleUpdatedInDb:Vehicle? = vehicleRepository.updateGivenVehicle(updatedVehicle)    //to update updated vehicle in database
        println("vehicle updated in db: ${vehicleUpdatedInDb?.getUuid()}")
        assertNotNull(vehicleUpdatedInDb)
        assertTrue(vehicleUpdatedInDb?.getUuid()==oldVehicle.getUuid())
        assertTrue(vehicleUpdatedInDb?.getDriverName()!=oldVehicle.getDriverName())
    }


}