package pl.wnuk.demoapp.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import pl.wnuk.demoapp.DemoappApplicationTests;
import pl.wnuk.demoapp.domain.ProductFacade;
import pl.wnuk.demoapp.domain.ProductRequestDto;
import pl.wnuk.demoapp.domain.ProductResponseDto;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class ProductEndpointTest extends DemoappApplicationTests {

    @Autowired
    ProductFacade productFacade;

    private String productsUrl;

    @Before
    public void init() {
        productsUrl = "http://localhost:" + port + "/products/";
    }

    @Test
    public void shouldGetExistingProudct(){

        ProductRequestDto requestDto = new ProductRequestDto("produkt");
        ProductResponseDto existingProduct = productFacade.create(requestDto);

        final String url = productsUrl + existingProduct.getId();

        ResponseEntity<ProductResponseDto> result = httpClient.getForEntity(url, ProductResponseDto.class);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualToComparingFieldByField(existingProduct);
    }

    @Test
    public void shouldReturnThatTheProductDoesNotExist(){

        final String url = productsUrl + "notExistProduct";

        ResponseEntity<ProductResponseDto> result = httpClient.getForEntity(url, ProductResponseDto.class);

        assertThat(result.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void shouldCreateProduct(){

        final ProductRequestDto product = new ProductRequestDto("iphone");
        String productJson = mapToJson(product);

        ResponseEntity<ProductResponseDto> result = httpClient.postForEntity(productsUrl,
               getHttpRequest(productJson), ProductResponseDto.class);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo("iphone");

    }

    @Test
    public void shouldUpdateProduct(){

        ProductRequestDto requestDto = new ProductRequestDto("produkt");
        ProductResponseDto existingProduct = productFacade.create(requestDto);

        final String url = "http://localhost:"+ port + "/products/" + existingProduct.getId();

        ProductRequestDto updatedProduct = new ProductRequestDto("updated product");

        String productJson = mapToJson(updatedProduct);

        ResponseEntity<ProductResponseDto> result = httpClient.exchange(url,
                HttpMethod.PUT,
                getHttpRequest(productJson),
                ProductResponseDto.class);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getId()).isEqualTo(existingProduct.getId());
        assertThat(result.getBody().getName()).isEqualTo(updatedProduct.getName());
    }

    @Test
    public void shouldDeleteProduct(){

        ProductRequestDto requestDto = new ProductRequestDto("produkt");
        ProductResponseDto existingProduct = productFacade.create(requestDto);

        final String url = productsUrl + existingProduct.getId();

        ResponseEntity<Void> result = httpClient
                .exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);

        assertThat(result.getStatusCodeValue()).isEqualTo(204);
    }


    String mapToJson (ProductRequestDto product){
        try {
            return objectMapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpEntity<String> getHttpRequest(String json){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("content-type", "application/json");
        return new HttpEntity<>(json, httpHeaders);
    }
}
