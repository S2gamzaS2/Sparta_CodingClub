package example.market.order;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderApplicationQueueConfig {

    @Value("${message.exchange}")
    private String exchange;

    @Value(("${message.queue.product}"))
    private String queueProduct;

    @Value(("${message.queue.payment}"))
    private String queuePayment;

    // Exchange 생성
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    // Queue 생성
    @Bean public Queue queueProduct() { return new Queue(queueProduct); }
    @Bean public Queue queuePayment() { return new Queue(queuePayment); }

    // 바인딩 생성 - 연결
    @Bean
    public Binding bindingProduct(){ return BindingBuilder.bind(queueProduct()).to(exchange()).with(queueProduct); }
    @Bean
    public Binding bindingPayment(){ return BindingBuilder.bind(queuePayment()).to(exchange()).with(queuePayment); }
}
