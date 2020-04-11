package Wilson.ProblemWilson.configuracion;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracion {
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}