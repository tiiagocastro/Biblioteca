package br.com.fuctura.biblioteca.profiles;

import br.com.fuctura.biblioteca.services.DBSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevProfile {
    @Autowired
    private DBSerice dbSerice;

    @Bean
    public void instanciaDB() {
        this.dbSerice.instanciaDB();
    }
}
