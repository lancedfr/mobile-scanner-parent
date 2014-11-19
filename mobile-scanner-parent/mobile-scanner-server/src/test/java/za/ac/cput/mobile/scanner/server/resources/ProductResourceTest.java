/**
 * EFD (Economic Freedom Developers) 
 * (Lance Reid, Nash Naidoo, Flippie van Schalkwyk, Tohhier Allie)
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package za.ac.cput.mobile.scanner.server.resources;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import za.ac.cput.mobile.scanner.repository.model.Product;
import za.ac.cput.mobile.scanner.service.product.ProductService;

public class ProductResourceTest {

  public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

  @Mock
  private ProductService productService;
  @InjectMocks
  private ProductResource productResource;

  private MockMvc mockMvc;
  private Product product1;
  private Product product2;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(productResource).build();

    product1 = new Product();
    product1.setId(1);
    product1.setBarcode("43214321");
    product1.setName("Lance");

    product2 = new Product();
    product2.setId(2);
    product2.setBarcode("1234123123");
    product2.setName("Reid");
  }

  @Test
  public void testGetDummyProduct() throws Exception {
    mockMvc.perform(get("/rest/product/dummy"))//
        .andExpect(status().isOk())//
        .andExpect(content().contentType(APPLICATION_JSON_UTF8))//
        .andExpect(jsonPath("$.name", is("Dummy")))//
        .andExpect(jsonPath("$.barcode", is("99999999")));

    verify(productService, times(1)).addProduct(any(Product.class));
    verifyNoMoreInteractions(productService);
  }

  @Test
  public void testGetProduct() throws Exception {
    when(productService.getProduct(1)).thenReturn(product1);

    mockMvc.perform(get("/rest/product/{id}".replace("{id}", "1")))//
        .andExpect(status().isOk())//
        .andExpect(content().contentType(APPLICATION_JSON_UTF8))//
        .andExpect(jsonPath("$.id", is(1)))//
        .andExpect(jsonPath("$.barcode", is("43214321")));

    verify(productService, times(1)).getProduct(1);
    verifyNoMoreInteractions(productService);
  }

  @Test
  public void testGetAllProducts() throws Exception {
    when(productService.getProducts()).thenReturn(Arrays.asList(product1, product2));

    mockMvc.perform(get("/rest/products"))//
        .andExpect(status().isOk())//
        .andExpect(content().contentType(APPLICATION_JSON_UTF8))//
        .andExpect(jsonPath("$", hasSize(2)))//
        .andExpect(jsonPath("$[0].id", is(1)))//
        .andExpect(jsonPath("$[0].barcode", is("43214321")))//
        .andExpect(jsonPath("$[1].barcode", is("1234123123")));

    verify(productService, times(1)).getProducts();
    verifyNoMoreInteractions(productService);
  }

  @Test
  public void testCreateProduct() throws Exception {
    mockMvc.perform(post("/rest/product")//
        .contentType(APPLICATION_JSON_UTF8)//
        .content("{\"id\":1,\"name\":\"Lance\",\"barcode\":\"43214321\"}"))//
        .andExpect(status().isOk())//
        .andExpect(content().contentType(APPLICATION_JSON_UTF8))//
        .andExpect(jsonPath("$.id", is(1)))//
        .andExpect(jsonPath("$.barcode", is("43214321")));

    verify(productService, times(1)).addProduct(any(Product.class));
    verifyNoMoreInteractions(productService);
  }

  @Test
  public void testDeleteProduct() throws Exception {
    mockMvc.perform(delete("/rest/product/1")//
        .contentType(APPLICATION_JSON_UTF8)//
        .content("{\"id\":1,\"name\":\"Lance\",\"barcode\":\"43214321\"}"))//
        .andExpect(status().isOk())//
        .andExpect(content().contentType(APPLICATION_JSON_UTF8))//
        .andExpect(jsonPath("$.id", is(Null.NULL)))//
        .andExpect(jsonPath("$.barcode", is(Null.NULL)));

    verify(productService, times(1)).deleteProduct(1);
    verifyNoMoreInteractions(productService);
  }
  
  @Test
  public void testGetProductByBarcode() throws Exception {
    when(productService.getProductByBarcode("anyBarcode")).thenReturn(product1);

    mockMvc.perform(get("/rest/product?barcode=anyBarcode"))//
        .andExpect(status().isOk())//
        .andExpect(content().contentType(APPLICATION_JSON_UTF8))//
        .andExpect(jsonPath("$.id", is(1)))//
        .andExpect(jsonPath("$.barcode", is("43214321")));

    verify(productService, times(1)).getProductByBarcode("anyBarcode");
    verifyNoMoreInteractions(productService);
  }

}