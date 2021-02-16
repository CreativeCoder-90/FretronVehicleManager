package com.fretron.vehicleManager.repositories

import com.fretron.vehicleManager.exceptions.vehicleExceptions.DuplicateRegistrationNumException
import com.fretron.vehicleManager.exceptions.vehicleExceptions.VehicleNotFoundException
import com.fretron.vehicleManager.model.Vehicle
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import com.mongodb.util.JSON
import org.bson.Document
import org.codehaus.jackson.map.ObjectMapper
import javax.inject.Inject

class VehicleRepository @Inject constructor(private val database: MongoDatabase) {

//    private val  collectionName:String = ""

    @Throws(DuplicateRegistrationNumException::class)
    fun createNewVehicle(v: Vehicle): Vehicle {

        //database.createCollection("Vehicle_Details")
        var collection = database.getCollection("Vehicle_Details")       //creating a collection/table
        var document =
            Document.parse(v.toString())  //creating a document //here parse() method converts json string to corresponding Document object
        if (isRegistrationNumDuplicate(v)) {
            throw DuplicateRegistrationNumException("duplicate registration number: ${v.getRegistrationNumber()}")
        }
            collection.insertOne(document)
        println(v.toString())
        return v
    }

    private fun isRegistrationNumDuplicate(v: Vehicle): Boolean {
        var cursor =
            database.getCollection("Vehicle_Details").find(Filters.eq("registrationNumber", v.getRegistrationNumber()))
                .limit(1).iterator()
        return cursor.hasNext()
    }

    fun getVehicleById(id:String): Vehicle {
        var collection = database.getCollection("Vehicle_Details")
        var cursor = collection.find(Filters.eq("uuid",id)).limit(1).iterator()

        if(cursor.hasNext()){
            var document:Document = cursor.next()
            document.remove("_id")
            val jsonString = JSON.serialize(document)   // To convert bson into Json
          var vehicleFetchedFromDb = ObjectMapper().readValue(jsonString,   Vehicle::class.java)
            return vehicleFetchedFromDb
        }
        throw VehicleNotFoundException("Vehicle not found with id: $id")
    }

}
