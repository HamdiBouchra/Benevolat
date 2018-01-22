package org.ilis.dao;

import org.ilis.entities.association;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@WebMvcTest(value = AssociationRestService.class, secure = false)
public class AssociationControllerTest {

/*	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AssociationRestService studentService;

	association mockCourse = new association("association1", "sig1", "aides sociales","0612985475",
			"ass1@gmail.com","log1","mdp1","CIN1.jpg");

	String exampleCourseJson = "{\"nom\":\"association1\",\"sig1e\":\"sig1\",\"objet_social\":\"aides sociales\","
			+ "\"tel\":\"0612985475\",\"email\":\"ass1@gmail.com\",\"login\":\"log1\","
			+ "\"mdp\":\"mdp1\",\"doc\":\"CIN1.jpg\"}";

	@Test
	public void retrieveDetailsForCourse() throws Exception {

		Mockito.when(
				studentService.retrieveCourse(Mockito.anyString(),
						Mockito.anyString())).thenReturn(mockCourse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/students/Student1/courses/Course1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:Course1,name:Spring,description:10 Steps}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}*/

}
