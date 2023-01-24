package it.peppe.customer.service;

import it.peppe.clients.fraud.FraudCheckResponse;
import it.peppe.clients.fraud.FraudClient;
import it.peppe.clients.notification.NotificationClient;
import it.peppe.clients.notification.NotificationRequest;
import it.peppe.customer.model.Customer;
import it.peppe.customer.repository.CustomerRepository;
import it.peppe.customer.request.CustomerRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FraudClient fraudClient;

    @Autowired
    private NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        // SEND NOTIFICATION UPON REGISTRATION
        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, welcome to Peppeservices...",customer.getFirstName())
                )
        );

    }
}
