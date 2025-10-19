package com.projects.jpaTutorial;

import com.projects.jpaTutorial.entities.ProductEntity;
import com.projects.jpaTutorial.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaTutorialApplicationTests {

    @Autowired
    ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

    @Test
    void testRepository() {
        ProductEntity productEntity = ProductEntity.builder()
                .sku("nestle234")
                .title("Nestle Chocolate")
                .price(BigDecimal.valueOf(123.45))
                .quantity(12)
                .build();
        ProductEntity savedProductEntity = productRepository.save(productEntity);
        System.out.println(savedProductEntity);
    }

    @Test
    void getRepository() {
        List<ProductEntity> productEntities = productRepository.findAll();
        System.out.println(productEntities);
    }

    @Test
    void getEntityByTitle() {
        List<ProductEntity> productEntity = productRepository.findByTitleOrderByPrice("Pepsi");
        System.out.println(productEntity);
    }

    @Test
    void getEntityCreatedAfter() {
        List<ProductEntity> productEntities = productRepository.findByCreatedAtAfter(LocalDateTime.of(2025,1, 1, 0,0,0));
        System.out.println(productEntities);
    }

    @Test
    void getEntityByQuantityAndPrice() {
        List<ProductEntity> productEntities = productRepository.findByQuantityAndPrice(50,BigDecimal.valueOf(99.99));
        System.out.println(productEntities);
    }

    @Test
    void getEntityByQuantityGreaterAndPriceLess() {
        List<ProductEntity> productEntities = productRepository.findByQuantityGreaterThanAndPriceLessThan(5,BigDecimal.valueOf(1299.99));
        System.out.println(productEntities);
    }

    @Test
    void getEntityByTitleLike() {
        List<ProductEntity> productEntities = productRepository.findByTitleLike("%P%");
        System.out.println(productEntities);
    }

    @Test
    void getEntityByTitleContainingIgnoreCase() {
        List<ProductEntity> productEntities = productRepository.findByTitleContainingIgnoreCase("m", null);
        System.out.println(productEntities);
    }

    @Test
    void getSingleEntityByTitleAndPrice() {
        Optional<ProductEntity> productEntity = productRepository
                .findByTitleAndPrice("Dell XPS 15", BigDecimal.valueOf(1299.99));
        if(productEntity.isPresent()) {
            System.out.println(productEntity);
        } else {
            System.out.println("No Entity Present");
        }
    }
}
