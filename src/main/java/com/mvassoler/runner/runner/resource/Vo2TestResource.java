package com.mvassoler.runner.runner.resource;

import com.mvassoler.runner.runner.domain.Vo2Test;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.mvassoler.runner.runner.grant.RolesGrant.CREATE_CORRIDA;
import static com.mvassoler.runner.runner.grant.RolesGrant.DELETE_CORRIDA;
import static com.mvassoler.runner.runner.grant.RolesGrant.SUPER_USER;

@RestController
@RequestMapping("/vo2test")
@Tag(name = "Vo2Test")
public class Vo2TestResource {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("(" + CREATE_CORRIDA + ") or (" + SUPER_USER + ")")
    @Operation(
            summary = "Create Vo2 Test", description = "Enter payload data correctly to create a new record",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Create record success")
            }
    )
    public ResponseEntity<String> insertVo2Test(@RequestBody Vo2Test vo2Test) {
        vo2Test.setId(UUID.randomUUID());
        return ResponseEntity.ok("Teste criado com ID " + vo2Test.getId() + " - Resultado Vo2 : 45.9.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("(" + DELETE_CORRIDA + ") or (" + SUPER_USER + ")")
    @Operation(
            summary = "Delete street race", description = "Enter a valid identifier to delete the record",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Update record success")
            }
    )
    public ResponseEntity<String> deleteVo2Test(@PathVariable UUID id) {
        return ResponseEntity.ok("Vo2Test de ID " + id + " excluído");
    }

}
