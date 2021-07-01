package com.goudourasv.http.kostas;

import com.goudourasv.data.instructors.InstructorEntity;
import com.goudourasv.domain.instructors.Instructor;
import io.smallrye.common.annotation.Blocking;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;


@ApplicationScoped
@Path("/abc")
public class AbcExampleResource {
    private final EntityManager entityManager;

    public AbcExampleResource(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Transactional
    @Blocking
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public AbcExample getAbcExample() {
        String oldId = "774e3fdb-ee12-49e1-b517-a951854a13d7";

        AbcExample abcExample = new AbcExample();
        UUID costasUuid = UUID.fromString(oldId);
        System.out.println("#1,1");
//        InstructorEntity costas = new InstructorEntity();
        InstructorEntity costas = entityManager.getReference(InstructorEntity.class,costasUuid);
        System.out.println("#2");
        costas.setId(costasUuid);
        costas.setFirstName("nikos");
        System.out.println("#3");

//        System.out.println("#31: " + costas.getLastName());

//        System.out.println("#32");

//
//        costas.setLastName("maistrelis");
        try {
            entityManager.merge(costas);
            System.out.println("#4");
            entityManager.flush();
            System.out.println("#5");
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return abcExample;

    }
}
