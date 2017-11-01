package com.fashionmart.prodcat.controller.test;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fashionmart.prodcat.ProductCatalogueApplication;
import com.fashionmart.prodcat.entity.ProductEntity;
import com.fashionmart.prodcat.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductCatalogueApplication.class)
@WebAppConfiguration
public class ProductCatalogueControllerTest {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    
    private ProductEntity savedEntityA;
    private ProductEntity savedEntityB;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
    
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
    
    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.productRepository.deleteAllInBatch();
        
        ProductEntity entityA = new ProductEntity(
        		UUID.randomUUID().toString(),
				"liril",
				"soap",
				"hul",
				5,
				10.5,
				"SYSTEM",
				Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)),
				"SYSTEM",
				Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)),
				1
			);
		this.savedEntityA = productRepository.save(entityA);
		
		ProductEntity entityB = new ProductEntity(
        		UUID.randomUUID().toString(),
				"lays",
				"food",
				"itc",
				5,
				20.5,
				"SYSTEM",
				Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)),
				"SYSTEM",
				Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)),
				1
			);
		this.savedEntityB = productRepository.save(entityB);
    }
    
    @Test
    @Ignore
    public void productNotFound() throws Exception {
        mockMvc.perform(get("/product/get-by-id/2")
        .contentType(contentType))
        .andExpect(status().isOk())
        .andExpect(content().bytes("{a}".getBytes(Charset.defaultCharset())));
    }
}
