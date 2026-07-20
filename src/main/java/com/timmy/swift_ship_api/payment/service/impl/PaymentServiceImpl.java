package com.timmy.swift_ship_api.payment.service.impl;
import com.timmy.swift_ship_api.auth.AuthUtils;
import com.timmy.swift_ship_api.escrow.Escrow;
import com.timmy.swift_ship_api.escrow.service.EscrowService;
import com.timmy.swift_ship_api.payment.dto.InitiatePaymentRequest;
import com.timmy.swift_ship_api.payment.dto.InitiatePaymentResponse;
import com.timmy.swift_ship_api.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final RestClient restClient;
    private final EscrowService escrowService;
    private final AuthUtils authUtils;

    @Value("${paystack.secret}")
    private String secretKey;
    @Value("${paystack.initialize}")
    private String paystackInitializationUrl;

    @Override
    public InitiatePaymentResponse initiatePayment(UUID escrowId) {
        Escrow escrow= escrowService.getEscrowById(escrowId);
        var serviceCharge = escrow.getPrice().multiply(BigDecimal.valueOf(0.15));
        var totalPrice = serviceCharge.add(escrow.getPrice());
        var price = totalPrice.multiply(BigDecimal.valueOf(100)).longValueExact();

        InitiatePaymentRequest request = InitiatePaymentRequest
                .builder()
                .email(authUtils.getLoggedInUser().getEmail())
                .amount(price)
                .build();
        try{
            InitiatePaymentResponse initiatePaymentResponse = restClient.post()
                    .uri(paystackInitializationUrl)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer "+secretKey)
                    .body(request)
                    .retrieve()
                    .body(InitiatePaymentResponse.class);
        }
        catch(Exception ex){

        }



        return null;
    }
}
