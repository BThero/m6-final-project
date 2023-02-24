package com.example.demo;

import com.example.demo.models.Conversion;
import com.example.demo.models.Currency;
import com.example.demo.models.CurrencyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = FinalApplication.class
)
@AutoConfigureMockMvc
class FinalApplicationTests {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private CurrencyRepository currencyRepository;

	@Test
	void contextLoads() throws Exception {
		Currency euro = currencyRepository.findByNameIgnoringCase("Euro");
		Currency dollar = currencyRepository.findByNameIgnoringCase("Dollar");
		Currency tenge = currencyRepository.findByNameIgnoringCase("Tenge");

		mvc.perform(post("/currencies")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(
										new Conversion(euro, dollar, 0.5f).toMap()
						))
				)
				.andExpect(status().isOk())
				.andExpect(content().string("Successfully created"));

		mvc.perform(post("/currencies")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(
										new Conversion(tenge, euro, 0.0021f).toMap()
						))
				)
				.andExpect(status().isOk())
				.andExpect(content().string("Successfully created"));

		mvc.perform(put("/currencies")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(
										new Conversion(euro, dollar, 1.05f).toMap()
						))
				)
				.andExpect(status().isOk())
				.andExpect(content().string("Successfully updated"));

		mvc.perform(get("/conversion")
				.param("firstCurrency", euro.getName())
				.param("secondCurrency", dollar.getName())
				.param("amount", String.valueOf(100.0f))
		)
				.andExpect(status().isOk())
				.andExpect(content().string("Converted amount is 105.000"));

		mvc.perform(delete("/currencies")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(
								new Conversion(euro, dollar, 0.0f).toMap()
						))
				)
				.andExpect(status().isOk())
				.andExpect(content().string("Successfully deleted"));

		mvc.perform(get("/conversion")
						.param("firstCurrency", euro.getName())
						.param("secondCurrency", dollar.getName())
						.param("amount", String.valueOf(100.0f))
				)
				.andExpect(status().isOk())
				.andExpect(content().string("Conversion does not exist"));
	}

	static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
