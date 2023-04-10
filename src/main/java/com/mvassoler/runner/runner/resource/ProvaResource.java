package com.mvassoler.runner.runner.resource;

import com.mvassoler.runner.runner.domain.Prova;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.mvassoler.runner.runner.grant.RolesGrant.CREATE_PROVA;
import static com.mvassoler.runner.runner.grant.RolesGrant.DELETE_PROVA;
import static com.mvassoler.runner.runner.grant.RolesGrant.SUPER_USER;
import static com.mvassoler.runner.runner.grant.RolesGrant.UPDATE_PROVA;

@RestController
@RequestMapping("/prova")
public class ProvaResource {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("(" + CREATE_PROVA + ") or (" + SUPER_USER + ")")
    public ResponseEntity<String> insertProva(@RequestBody Prova prova) {
        prova.setId(UUID.randomUUID());
        return ResponseEntity.ok("Prova de ID " + prova.getId() + " criada");
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("(" + UPDATE_PROVA + ") or (" + SUPER_USER + ")")
    public ResponseEntity<String> updateProva(@PathVariable UUID id, @RequestBody Prova prova) {
        prova.setId(id);
        return ResponseEntity.ok("Prova de ID " + prova.getId() + " atualizada");
    }


    @DeleteMapping(value = "/{id}")
    @PreAuthorize("(" + DELETE_PROVA + ") or (" + SUPER_USER + ")")
    public ResponseEntity<String> deleteProva(@PathVariable UUID id) {
        return ResponseEntity.ok("Prova de ID " + id + " excluída");
    }

}
