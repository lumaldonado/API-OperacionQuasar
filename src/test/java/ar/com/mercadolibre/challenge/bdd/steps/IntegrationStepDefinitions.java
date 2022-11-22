package ar.com.mercadolibre.challenge.bdd.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;


import static ar.com.mercadolibre.challenge.drivers.util.Jsons.asJsonString;
import static ar.com.mercadolibre.challenge.drivers.util.Jsons.objectFromString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest
public class IntegrationStepDefinitions {
  @Autowired
  private WebApplicationContext context;

  protected MockHttpServletResponse response;

  protected MockHttpServletResponse performGet(String url, MultiValueMap<String, String> params) throws Exception {
    return getMockMvc().perform(
            get(url)
                    .params(params)
    ).andReturn().getResponse();
  }

  private MockMvc getMockMvc() {
    return MockMvcBuilders.webAppContextSetup(context)
            .build();

  }

  protected MockHttpServletResponse performGet(String url) throws Exception {
    return getMockMvc().perform(
                    get(url))
            .andReturn()
            .getResponse();
  }

  protected MockHttpServletResponse performPost(String url, Object body) throws Exception {
    return getMockMvc().perform(post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(body))
            ).andReturn()
            .getResponse();
  }

  protected void assertErrorResponse() {
    assertStatusCode(400);
  }

  protected void assertSuccessResponse() {
    assertStatusCode(200);
  }

  protected void assertStatusCode(Integer statusCode) {
    assertThat(response.getStatus()).isEqualTo(statusCode);
  }

  protected <T> T createObjectFromResponse(Class<T> aClass) throws JsonProcessingException, UnsupportedEncodingException {
    String value = response.getContentAsString();
    return objectFromString(aClass, value);
  }

  protected <T> T createObjectFromResponse(TypeReference<T> listTypeReference) throws UnsupportedEncodingException, JsonProcessingException {
    String value = response.getContentAsString();
    return objectFromString(listTypeReference, value);
  }
}