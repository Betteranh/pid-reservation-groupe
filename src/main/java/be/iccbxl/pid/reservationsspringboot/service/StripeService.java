package be.iccbxl.pid.reservationsspringboot.service;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StripeService {

    public StripeService(@Value("${stripe.secret.key}") String secretKey) {
        Stripe.apiKey = secretKey;
    }

    public Session createCheckoutSession(List<SessionCreateParams.LineItem> items) throws Exception {
        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("https://pid-reservation-groupe.onrender.com/payment/success") // 🔵 redirection après paiement OK
                .setCancelUrl("https://pid-reservation-groupe.onrender.com/cart/view")                 // 🔴 redirection si annulation
                .addAllLineItem(items)
                .build();

        return Session.create(params);
    }
}
