package org.bmw.api;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bmw.domain.Student;
import org.bmw.domain.University;
import org.bmw.domaininteraction.StudentService;

import java.util.List;

@Transactional
@Path("/api/student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentController {

    @Inject
    private StudentService studentService;


    @Path("/create")
    @POST
    public Response createStudent(@Valid CreateStudentRequest request) {
        Long studentId = studentService.createStudent(request.firstName(), request.lastName(), request.cnp(), request.email(), request.university());
        return Response.status(Response.Status.CREATED).entity(studentId).build();
    }

    @Path("/getByFirstName")
    @GET
    public List<Student> findByFirstName(@QueryParam("firstName") String firstName) {
        return studentService.findByFirstName(firstName);
    }

    @Path("/getAllStudents")
    @GET
    public List<Student> findAllStudents(){
        return studentService.findAllStudents();
    }

    public record CreateStudentRequest(
            @NotBlank(message = "FirstName cannot be blank")
            String firstName,

            @NotBlank(message = "LastName cannot be blank")
            String lastName,

            @Size(min = 13 , max = 13)
            @NotBlank(message = "CNP must contain 13 characters")
            String cnp,

            @Email
            @NotBlank(message = "Email cannot be blank")
            String email,

            University university
    ) {}


}
