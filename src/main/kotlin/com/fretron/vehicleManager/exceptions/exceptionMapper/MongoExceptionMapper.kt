package com.fretron.vehicleManager.exceptions.exceptionMapper

import com.fretron.vehicleManager.exceptions.dbExceptions.MongoException
import com.fretron.vehicleManager.model.ErrorResponse
import org.codehaus.jackson.map.ObjectMapper
import java.time.LocalDateTime
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper

class MongoExceptionMapper: ExceptionMapper<MongoException?> {

    override fun toResponse(exception: MongoException?): Response {
        var errorResponse = ErrorResponse()
        errorResponse.timeStamp= LocalDateTime.now().toString()
        errorResponse.message= exception?.message

        when(exception){
            is MongoException->{
                errorResponse.errorCode="500"
                return Response.ok().entity(ObjectMapper().writeValueAsString(errorResponse.toString())).build()
            }
        }
        return Response.ok().entity("something went wrong").build()
    }
}