package de.koegler85;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gabriel Kögler
 */
@RestController
@SpringBootApplication
public class Main
{
    @RequestMapping( "/" )
    public String home()
    {
        return "hello world";
    }

    public static void main ( String[] args ) throws Exception
    {
        SpringApplication.run ( Main.class, args );
    }
}