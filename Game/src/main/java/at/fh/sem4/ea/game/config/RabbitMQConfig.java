package at.fh.sem4.ea.game.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("battleship-exchange");
    }

    @Bean
    public Queue playerQueue() {
        return new Queue("player-queue", false);
    }

    @Bean
    public Binding binding(Queue playerQueue, DirectExchange exchange) {
        return BindingBuilder.bind(playerQueue).to(exchange).with("player.created");
    }
}

