package claimservice.controllers;

import claimservice.models.Claim;
import claimservice.services.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Reclamation")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Claim> getAll() {
        return claimService.getAll();
    }


    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Claim createClaim(@RequestBody Claim claim, @PathVariable Long userId) {
        return claimService.createClaim(claim, userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Claim updateClaim(@RequestBody Claim claim, @PathVariable Long id) {
        return claimService.updateClaim(claim,id);
    }
}
