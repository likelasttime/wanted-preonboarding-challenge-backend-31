package com.likelasttime.commerse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelasttime.commerse.dto.request.*;
import com.likelasttime.commerse.dto.response.CreateProductResponse;
import com.likelasttime.commerse.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureDataJpa
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("product 등록 성공")
    public void testCreateProduct() throws Exception {
        // Given
        Map<String, Integer> dimensions = new HashMap();
        dimensions.put("width", 200);
        dimensions.put("height", 85);
        dimensions.put("depth", 90);

        Map<String, Object> additional_info = new HashMap();
        additional_info.put("assembly_required", true);
        additional_info.put("assembly_time", "30분");

        CreateProductDetailsRequest createProductDetailsRequest = CreateProductDetailsRequest.builder()
                .weight(new BigDecimal(25.5))
                .dimensions(dimensions)
                .materials("가죽, 목재, 폼")
                .country_of_origin("대한민국")
                .warranty_info("2년 품질 보증")
                .care_instructions("마른 천으로 표면을 닦아주세요")
                .additional_info(additional_info)
                .build();

        CreateProductPricesRequest createProductPricesRequest = CreateProductPricesRequest.builder()
                .base_price(new BigDecimal(599000))
                .sale_price(new BigDecimal(499000))
                .cost_price(new BigDecimal(350000))
                .currency("KRW")
                .tax_rate(new BigDecimal(10))
                .build();

        List<CreateCategoriesRequest> categories = new ArrayList();
        categories.add(CreateCategoriesRequest.builder()
                .category_id(5L)
                .is_primary(true)
                .build());
        categories.add(CreateCategoriesRequest.builder()
                .category_id(8L)
                .is_primary(false)
                .build());

        List<CreateProductOptionGroupsRequest> option_groups = new ArrayList();
        List<CreateOptionsRequest> createOptionsRequests1 = new ArrayList();
        createOptionsRequests1.add(CreateOptionsRequest.builder()
                .name("브라운")
                .additional_price(new BigDecimal(0))
                .sku("SOFA-BRN")
                .stock(10)
                .display_order(1)
                .build());
        createOptionsRequests1.add(CreateOptionsRequest.builder()
                .name("블랙")
                .additional_price(new BigDecimal(0))
                .sku("SOFA-BLK")
                .stock(15)
                .display_order(2)
                .build());
        option_groups.add(CreateProductOptionGroupsRequest.builder()
                .name("색상")
                .display_order(1)
                .options(createOptionsRequests1)
                .build());

        List<CreateOptionsRequest> createOptionsRequests2 = new ArrayList();
        createOptionsRequests2.add(CreateOptionsRequest.builder()
                        .name("천연 가죽")
                        .additional_price(new BigDecimal(100000))
                        .sku("SOFA-LTHR")
                        .stock(5)
                        .display_order(1)
                        .build());
        createOptionsRequests2.add(CreateOptionsRequest.builder()
                .name("인조 가죽")
                .additional_price(new BigDecimal(0))
                .sku("SOFA-FAKE")
                .stock(20)
                .display_order(2)
                .build());
        option_groups.add(CreateProductOptionGroupsRequest.builder()
                .name("색상")
                .display_order(2)
                .options(createOptionsRequests2)
                .build());

        List<CreateProductImagesRequest> images = new ArrayList();
        images.add(CreateProductImagesRequest.builder()
                .url("https://example.com/images/sofa1.jpg")
                .alt_text("브라운 소파 정면")
                .is_primary(true)
                .display_order(1)
                .option_id(null)
                .build());
        images.add(CreateProductImagesRequest.builder()
                .url("https://example.com/images/sofa2.jpg")
                .alt_text("브라운 소파 측면")
                .is_primary(false)
                .display_order(2)
                .option_id(null)
                .build());

        List<Long> tags = new ArrayList<>();
        tags.add(1L);
        tags.add(4L);
        tags.add(7L);

        CreateProductRequest createProductRequest = CreateProductRequest.builder()
                .name("슈퍼 편안한 소파")
                .slug("super-comfortable-sofa")
                .short_description("최고급 소재로 만든 편안한 소파")
                .full_description("<p>이 소파는 최고급 소재로 제작되었으며...</p>")
                .seller_id(1L)
                .brand_id(2L)
                .status("ACTIVE")
                .detail(createProductDetailsRequest)
                .price(createProductPricesRequest)
                .categories(categories)
                .option_groups(option_groups)
                .images(images)
                .tags(tags)
                .build();

        CreateProductResponse createProductResponse = new CreateProductResponse(
                123L, "슈퍼 편안한 소파", "super-comfortable-sofa",
                LocalDateTime.now(), LocalDateTime.now()
        );

        // when
        when(productService.createProduct(Mockito.any(CreateProductRequest.class)))
                .thenReturn(createProductResponse);

        // then
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + "{token}")
                        .content(objectMapper.writeValueAsString(createProductRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("상품이 성공적으로 등록되었습니다."))
                .andExpect(jsonPath("$.data.name").value(createProductResponse.getName()))
                .andExpect(jsonPath("$.data.slug").value(createProductResponse.getSlug()))
                .andExpect(jsonPath("$.data.id").value(createProductResponse.getId()));
    }
}
