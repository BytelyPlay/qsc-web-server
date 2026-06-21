package net.bytelyplay.qscwebbackend;

import lombok.extern.slf4j.Slf4j;
import net.bytelyplay.qscwebbackend.utils.tokens.AccessToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class QscWebBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(QscWebBackendApplication.class, args);
    }

}
