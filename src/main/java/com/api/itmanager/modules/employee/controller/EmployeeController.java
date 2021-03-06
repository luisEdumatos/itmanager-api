package com.api.itmanager.modules.employee.controller;

import com.api.itmanager.modules.employee.dto.EmployeeRequest;
import com.api.itmanager.modules.employee.dto.EmployeeResponse;
import com.api.itmanager.modules.employee.service.EmployeeService;
import com.api.itmanager.util.exception.ClientNotFoundException;
import com.api.itmanager.util.exception.EmployeeNotFoundException;
import com.api.itmanager.util.response.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

    private EmployeeService employeeService;

    @ApiOperation(value = "Retorna a lista de colaboradores existentes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de colaboradores, caso não existir, retorna lista vazia")
    })
    @GetMapping(produces = "application/json")
    public List<EmployeeResponse> listAll() {
        return employeeService.listAll();
    }

    @ApiOperation(value = "Retorna o colaborador informado por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o colaborador referente ao ID informado"),
            @ApiResponse(code = 400, message = "Erro de passagem de parâmetro"),
            @ApiResponse(code = 404, message = "Colaborador não encontrado para o ID informado"),
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public EmployeeResponse findById(@PathVariable Long id) throws EmployeeNotFoundException {
        return employeeService.findById(id);
    }

    @ApiOperation(value = "Cria um novo colaborador")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Colaborador criado com sucesso"),
            @ApiResponse(code = 400, message = "Erro na validação dos campos informados"),
    })
    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createEmployee(@RequestBody @Valid EmployeeRequest request) throws ClientNotFoundException {
        return employeeService.createEmployee(request);
    }

    @ApiOperation(value = "Atualiza dados de um colaborador existente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Colaborador atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Erro de passagem de parâmetro ou na validação dos campos"),
            @ApiResponse(code = 405, message = "Falta de ID no parâmetro"),
            @ApiResponse(code = 404, message = "Colaborador não encontrado para o ID informado"),
            @ApiResponse(code = 500, message = "ID de entidade relacionada não encontrado"),
    })
    @PutMapping(value = "/{id}", produces = "application/json")
    public Response updateByID(@PathVariable Long id, @RequestBody @Valid EmployeeRequest request) throws EmployeeNotFoundException, ClientNotFoundException {
        return employeeService.updateByID(id, request);
    }

    @ApiOperation(value = "Deleta colaborador informado pelo ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Colaborador deletado com sucesso"),
            @ApiResponse(code = 405, message = "Falta de ID no parâmetro"),
            @ApiResponse(code = 404, message = "Colaborador não encontrado para o ID informado"),
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteById(@PathVariable Long id) throws EmployeeNotFoundException {
        return employeeService.delete(id);
    }
}
