package in.stackroute.covidapplication;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.stackroute.covidapplication.model.Covid;
import in.stackroute.covidapplication.model.User;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WatchlistServiceApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class WatchlistServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	private Covid covid;
	private User user;
	private List<Covid> cases;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@BeforeEach
	public void setUp() {

		covid = new Covid("india", "67678", "8788", "8787");
		cases = new ArrayList<>();
		cases.add(covid);
		user = new User("abcd", cases);
	}

	@AfterEach
	public void tearDown() {
		covid = null;
	}

	@Test
	@Order(1)
	public void givenJobDetailsWhenCreatedThenReturnSuccessCode() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/api/v1/watchlist/abcd")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(covid)))
				.andExpect(status().isOk())
				.andReturn();

		assertThat(mapper.readValue(mvcResult.getResponse().getContentAsString(), User.class))
				.usingRecursiveComparison().isEqualTo(user);
	}

	@Test
	@Order(2)
	public void givenExistingJobDetailsWhenCreatedThenReturnConflictCode() throws Exception {

		mockMvc.perform(post("/api/v1/watchlist/abcd")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(covid)))
				.andExpect(status().isConflict());
	}

	@Test
	@Order(3)
	public void getJobsThenReturnSuccessCode() throws Exception {

		MvcResult mvcResult = mockMvc.perform(get("/api/v1/watchlist/abcd"))
				.andExpect(status().is2xxSuccessful())
				.andReturn();
		assertThat(mapper.readValue(mvcResult.getResponse().getContentAsString(), List.class))
				.hasSize(1);
	}

	@Test
	@Order(4)
	public void givenJobDetailsWhenDeletedThenReturnSuccessCode() throws Exception {

		MvcResult mvcResult = mockMvc.perform(delete("/api/v1/watchlist/abcd/india"))
				.andExpect(status().is2xxSuccessful())
				.andReturn();
		user.getCountryList().clear();
		assertThat(mapper.readValue(mvcResult.getResponse().getContentAsString(), User.class))
				.usingRecursiveComparison().isEqualTo(user);
	}

}
