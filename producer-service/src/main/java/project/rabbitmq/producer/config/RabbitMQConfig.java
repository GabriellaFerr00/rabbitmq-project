package project.rabbitmq.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static constants.RabbitMQConstants.EXG_NAME_MARKETPLACE;
import static constants.RabbitMQConstants.QUEUE_PRODUCT_LOG;
import static constants.RabbitMQConstants.RK_PRODUCT_LOG;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue queue(){
        return new Queue(QUEUE_PRODUCT_LOG, false, false, false);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(EXG_NAME_MARKETPLACE, false, false);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(this.queue())
                .to(directExchange())
                .with(RK_PRODUCT_LOG);
    }
}
