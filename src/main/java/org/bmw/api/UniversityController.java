package org.bmw.api;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bmw.domain.Student;
import org.bmw.domain.University;
import org.bmw.domaininteraction.UniversityService;

import java.util.List;

@Transactional
@Path("/api/university")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UniversityController {

    @Inject
    private UniversityService universityService;

    @Path("/create")
    @POST
    public Response createUniversity(@Valid CreateUniversityRequest request) {
        Long universityId = universityService.create(request.name(), request.location(), request.students());
        return Response.status(Response.Status.CREATED).entity(universityId).build();
    }

    @Path("/getAllUniversities")
    @GET
    public List<University> getAllUniversities(){
        return universityService.findAllUniversities();
    }

    @Path("/findByName")
    @GET
    public University findByName(@QueryParam("name") String name){
        return universityService.findByName(name);
    }

    public record CreateUniversityRequest(
            @NotBlank(message = "Name cannot be blank")
            String name,

            @NotBlank(message = "Location cannot be blank")
            String location,

            List<University.StudentInner> students
    ) {}

}
