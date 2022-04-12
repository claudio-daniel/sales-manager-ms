package com.my.admin.app.commerce.controller;

import com.my.admin.app.commerce.model.document.Department;
import com.my.admin.app.commerce.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping({"/", "my_administration/departments"})
@RestController
@Api(tags = "Department Controller", value = "For department manage")
public class DepartmentController {

    private static final Logger LOG = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "Get all cashes", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Full authentication is required to access this resource"),
    })
    @GetMapping
    public Flux<?> getAllDepartments() {
        Flux<?> response = null;

        try {

            response = departmentService.findAll();

            Flux<Department> response2 = departmentService.findAll();

            var algo = response2.flatMap(dep -> {
                if (dep.getName().equalsIgnoreCase("Claudio")) {
                    return Mono.just(dep);
                }
                return Mono.empty();
            });

        } catch (Exception e) {
            Map<String, Object> res = new HashMap<>();

            res.put("mensaje", "Ha ocurrido un error al consultar la lista de departamentos");
            res.put("error", Objects.requireNonNull(e.getMessage()).concat(" : ").concat(e.getMessage()));

            return Flux.just(res);
        }
        LOG.info("get all departments");

        return response;
    }

    @GetMapping(value = "/{id}")
    public Mono<?> getDepartment(@PathVariable(value = "id") String id) {
        Map<String, Object> response = new HashMap<>();

        Mono<Department> departmentView = null;

        if (!StringUtils.isEmpty(id)) {
            try {
                departmentView = departmentService.findDepartmentById(id);
            } catch (DataAccessException e) {
                response.put("mensaje", "error al reaizar la consulta a la base de datos");
                response.put("error", Objects.requireNonNull(e.getMessage()).concat(" : ").concat(e.getMostSpecificCause().getMessage()));

                return Mono.just(new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR));
            }
        }

        if (departmentView == null) {
            response.put("mensaje", "El inquilino con el id " + id + " no se encuentra registrado");

            return Mono.just(new ResponseEntity<>(response, HttpStatus.NOT_FOUND));
        }

        return departmentView;
    }

    @PostMapping
    public Mono<?> create(@Valid @RequestBody Department departmentView) {
        Mono<Department> departmentNew;

        try {
            departmentNew = departmentService.saveDepartment(departmentView);
        } catch (ConstraintViolationException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "error al reaizar el insert a la base de datos");
            response.put("error", e.getMessage().concat(" : ").concat(e.getLocalizedMessage()));

            return Mono.just(new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR));
        }
        return departmentNew;
    }

    @PutMapping(value = "/{id}")
    public Mono<?> update(@Valid @RequestBody Department departmentView, @PathVariable String id) {

        Mono<Department> departmentCurrent = departmentService.findDepartmentById(id);
        Mono<Department> departmentEdit;

        Map<String, Object> response = new HashMap<>();

        if (departmentCurrent == null) {
            response.put("mensaje", "Error : no es posible editar, El departamento con el id " + id + " no se encuentra registrado");

            return Mono.just(new ResponseEntity<>(response, HttpStatus.NOT_FOUND));
        }

        try {
            departmentEdit = departmentCurrent.flatMap(department -> {
                department.setName(departmentView.getName());
                department.setQuantityRooms(departmentView.getQuantityRooms());
                department.setRenter(departmentView.getRenter());

                return departmentService.saveDepartment(department);
            });

        } catch (DataAccessException e) {
            response.put("mensaje", "error al editar el departamento en la base de datos");
            response.put("error", Objects.requireNonNull(e.getMessage()).concat(" : ").concat(e.getMostSpecificCause().getMessage()));

            return Mono.just(new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR));
        }
        departmentCurrent.subscribe();

        return departmentEdit;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
        Map<String, Object> response = new HashMap<>();

        try {
            departmentService.deleteDepartmentById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "error al eliminar departamento en la base de datos");
            response.put("error", Objects.requireNonNull(e.getMessage()).concat(" : ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("Mensaje ", "operación exitosa : el departamento ha sido eliminado de la base de datos");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
