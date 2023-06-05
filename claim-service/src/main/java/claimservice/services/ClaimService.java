package claimservice.services;

import claimservice.models.Claim;
import claimservice.repositories.ClaimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import utilities.dtos.UserResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaimService {
    private final ClaimRepository claimRepository;
    private final WebClient.Builder webClientBuilder;

    public List<Claim> getAll(){
        return claimRepository.findAll() ;
    }

    public Claim createClaim (Claim claim , Long userId){
        UserResponse userResponse = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/users/"+userId)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        if (userResponse == null) {
            throw new RuntimeException("User not found");
        } else {
            claim.setUserId(userId);
            return claimRepository.save(claim);
        }
    }

    public void deleteClaim (Long id) {
        claimRepository.deleteById(id);
    }

    public Claim updateClaim (Claim claim, Long id) {
        Claim requestedClaim = claimRepository.findById(id).orElse(null);
        if (requestedClaim != null) {
            claim.setId(id);
            return claimRepository.saveAndFlush(claim);
        } else {
            throw new RuntimeException("fail");
        }
    }

}
