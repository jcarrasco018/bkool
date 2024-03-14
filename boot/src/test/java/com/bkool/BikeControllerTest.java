package com.bkool;

import com.bkool.domain.model.Bike;
import com.bkool.domain.model.BikeBuilder;
import com.bkool.domain.model.Item;
import com.bkool.domain.model.ItemBuilder;
import com.bkool.infraestructure.config.LoginRequest;
import com.bkool.infraestructure.dto.BikeDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BikeControllerTest {

    @LocalServerPort
    private int port;

    @Value("${server.contextPath}")
    private String contextPath;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Value("${security.user}")
    private String user;
    @Value("${security.pass}")
    private String pass;

    public static String token = null;

    @Test
    void login_KO() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:".concat(String.valueOf(port)).concat(contextPath))
                .pathSegment("login");
        LoginRequest login = new LoginRequest();
        login.setUser("1");
        login.setPass("2");
        ResponseEntity<String> response = testRestTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, new HttpEntity<>(login), new ParameterizedTypeReference<>() {
        });

        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void login_OK() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:".concat(String.valueOf(port)).concat(contextPath))
                .pathSegment("login");
        LoginRequest login = new LoginRequest();
        login.setUser(user);
        login.setPass(pass);
        ResponseEntity<String> response = testRestTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, new HttpEntity<>(login), new ParameterizedTypeReference<>() {
        });

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void save_201() {
        List<Item> itemList = Stream.of(
                ItemBuilder.builder().model("quater").type("cuadro").description("cuadro de tipo digital").build(),
                ItemBuilder.builder().model("Ultraflex").type("sillin").description("sillinAdaptable").build(),
                ItemBuilder.builder().model("bongo").type("ruedas").description("ruedas ergonomicas").build(),
                ItemBuilder.builder().model("Extreme").type("pedales").description("Repuesto compatible con Extreme/Extreme 20/Extreme 25/Ultraflex 25/Poweractive").build()
        ).toList();

        Bike bike = BikeBuilder.builder()
                .name("DrumFit")
                .description("Bicicleta indoor con volante de inercia de 10 kg")
                .price(129.00)
                .manufacturer("Teseo")
                .items(itemList).build();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:".concat(String.valueOf(port)).concat(contextPath))
                .pathSegment("bike");

        ResponseEntity<BikeDTO> response = testRestTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, new HttpEntity<>(bike,getHeaders()), new ParameterizedTypeReference<>() {
        });
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void save_500_without_name() {
        List<Item> itemList = Stream.of(
                ItemBuilder.builder().model("quater").type("cuadro").description("cuadro de tipo digital").build(),
                ItemBuilder.builder().model("Ultraflex").type("sillin").description("sillinAdaptable").build(),
                ItemBuilder.builder().model("bongo").type("ruedas").description("ruedas ergonomicas").build(),
                ItemBuilder.builder().model("Extreme").type("pedales").description("Repuesto compatible con Extreme/Extreme 20/Extreme 25/Ultraflex 25/Poweractive").build()
        ).toList();

        Bike bike = BikeBuilder.builder()
                .description("Bicicleta indoor con volante de inercia de 10 kg")
                .price(129.00)
                .manufacturer("Teseo")
                .items(itemList).build();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:".concat(String.valueOf(port)).concat(contextPath))
                .pathSegment("bike");

        ResponseEntity<BikeDTO> response = testRestTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, new HttpEntity<>(bike,getHeaders()), new ParameterizedTypeReference<>() {
        });
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void save_500_without_item_type() {
        List<Item> itemList = Stream.of(
                ItemBuilder.builder().model("quater").description("cuadro de tipo digital").build()
        ).toList();

        Bike bike = BikeBuilder.builder()
                .name("DrumFit")
                .description("Bicicleta indoor con volante de inercia de 10 kg")
                .price(129.00)
                .manufacturer("Teseo")
                .items(itemList).build();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:".concat(String.valueOf(port)).concat(contextPath))
                .pathSegment("bike");

        ResponseEntity<BikeDTO> response = testRestTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, new HttpEntity<>(bike,getHeaders()), new ParameterizedTypeReference<>() {
        });
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void save_500_without_item_model() {

        List<Item> itemList = Stream.of(
                ItemBuilder.builder().type("cuadro").description("cuadro de tipo digital").build()
        ).toList();

        Bike bike = BikeBuilder.builder()
                .name("DrumFit")
                .description("Bicicleta indoor con volante de inercia de 10 kg")
                .price(129.00)
                .manufacturer("Teseo")
                .items(itemList).build();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:".concat(String.valueOf(port)).concat(contextPath))
                .pathSegment("bike");

        ResponseEntity<BikeDTO> response = testRestTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, new HttpEntity<>(bike,getHeaders()), new ParameterizedTypeReference<>() {
        });
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


    @Test
    void search() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:".concat(String.valueOf(port)).concat(contextPath))
                .pathSegment("bike");
        uriBuilder.queryParam("name", "DrumFit");

        ResponseEntity<List<BikeDTO>> response = testRestTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, new HttpEntity<>(null,getHeaders()), new ParameterizedTypeReference<>() {
        });

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());

    }

    public HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getToken());
        return headers;
    }

    private String getToken() {
        if(Objects.isNull(token)){
            LoginRequest login = new LoginRequest();
            login.setUser(user);
            login.setPass(pass);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:".concat(String.valueOf(port)).concat(contextPath))
                    .pathSegment("login");
            ResponseEntity<String> response = testRestTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, new HttpEntity<>(login), new ParameterizedTypeReference<>() {
            });
            token = response.getBody();
        }
        return token;
    }


}
