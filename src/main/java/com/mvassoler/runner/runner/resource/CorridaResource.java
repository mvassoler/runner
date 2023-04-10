package com.mvassoler.runner.runner.resource;

import com.mvassoler.runner.runner.domain.Corrida;
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
@RequestMapping("/corrida")
public class CorridaResource {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("(" + CREATE_CORRIDA + ") or (" + SUPER_USER + ")")
    public ResponseEntity<String> insertCorrida(@RequestBody Corrida corrida) {
        corrida.setId(UUID.randomUUID());
        return ResponseEntity.ok("Corrida de ID " + corrida.getId() + " criada");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("(" + DELETE_CORRIDA + ") or (" + SUPER_USER + ")")
    public ResponseEntity<String> deleteCorrida(@PathVariable UUID id) {
        return ResponseEntity.ok("Corrida de ID " + id + " excluída");
    }

}
