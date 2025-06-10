package back.vybz.comment_read_service.kafka.config;

import back.vybz.comment_read_service.kafka.event.CommentDeleteEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@RequiredArgsConstructor
public class CommentDeleteEventKafkaConfig {

    private final CommonKafkaConfig commonKafkaConfig;

    @Bean
    public ConsumerFactory<String, CommentDeleteEvent> commentDeleteEventConsumerFactory(){
        return new DefaultKafkaConsumerFactory<>(
                commonKafkaConfig.commonConsumerConfigs(),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(CommentDeleteEvent.class,false))
        );
    }

    @Bean(name = "commentDeleteEventKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, CommentDeleteEvent> commentDeleteEventConcurrentKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, CommentDeleteEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(commentDeleteEventConsumerFactory());
        return factory;
    }
}
