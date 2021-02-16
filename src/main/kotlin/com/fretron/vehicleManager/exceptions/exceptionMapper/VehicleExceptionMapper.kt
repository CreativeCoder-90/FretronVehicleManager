package com.fretron.vehicleManager.exceptions.exceptionMapper

import com.fretron.vehicleManager.exceptions.vehicleExceptions.*
import com.fretron.vehicleManager.model.ErrorResponse
import org.codehaus.jackson.map.ObjectMapper
import java.time.LocalDateTime

import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper

class VehicleExceptionMapper :ExceptionMapper<VehicleException>{
    override fun toResponse(exception: VehicleException?): Response {
        var errorResponse = ErrorResponse()
        errorResponse.timeStamp=LocalDateTime.now().toString()
        errorResponse.message=exception?.message

        when(exception) {

            is InvalidVehicleException -> {
                errorResponse.errorCode="400"
                return Response.ok().entity(ObjectMapper().writeValueAsString(errorResponse.toString())).build()
            }

            is InvalidVehicleRegistrationException -> {
                errorResponse.errorCode="400"
                return Response.ok().entity(ObjectMapper().writeValueAsString(errorResponse.toString())).build()
            }

            is InvalidVehicleChassisException -> {
                errorResponse.errorCode="400"
                return Response.ok().entity(ObjectMapper().writeValueAsString(errorResponse.toString())).build()
            }

            is DuplicateRegistrationNumException -> {
                errorResponse.errorCode="404"
                return Response.ok().entity(ObjectMapper().writeValueAsString(errorResponse.toString())).build()
            }

            is VehicleNotFoundException -> {
                errorResponse.errorCode="404"
                return Response.ok().entity(ObjectMapper().writeValueAsString(errorResponse.toString())).build()
            }
        }
        return Response.ok().entity("something went wrong").build()
    }

}