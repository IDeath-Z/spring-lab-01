package com.spring.project.spring_lab.application.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.spring.project.spring_lab.adapters.web.dto.TransactionAuthDTO;

@Service
public class TransactionService {

    private boolean isAuth() {

        try {

            String apiUrl = "https://util.devi.tools/api/v2/authorize";
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<TransactionAuthDTO> response = restTemplate.getForEntity(
                    apiUrl,
                    TransactionAuthDTO.class);

            if (response.getStatusCode().is2xxSuccessful() && response.hasBody()) {

                TransactionAuthDTO body = response.getBody();
                return body != null &&
                        "success".equals(body.status()) &&
                        body.data().authorization();
            }

            return false;
        } catch (RestClientException e) {

            return false;
        }
    }

    public String testeAPI() {

        if (isAuth()) {
            return "Ta autorizado chefe";
        } else {
            return "Mio";
        }
    }

}
