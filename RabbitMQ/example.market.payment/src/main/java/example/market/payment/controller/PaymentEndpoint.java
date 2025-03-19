package example.market.payment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentEndpoint {

    @Value("${spring.application.name}")
    private String appName;

    // 큐를 바라보는 리스너 생성
    // 큐에 들어있는 Message를 가져와서 처리 가능
    @RabbitListener(queues = "${message.queue.payment}")
    public void receiveMessage(String orderId) { // 큐에 orderId 들어있음
        log.info("Received orderId: {}, appName: {}", orderId, appName);
    }
}
