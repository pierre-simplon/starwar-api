package controllerTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class PlanetControllerTest {

        @Autowired
        MockMvc mockMvc;
//        @MockBean
//        CityService planetService;

//        @Test
//        public void listPlanets() throws Exception {
//            this.mockMvc.perform(listPlanets()).andExpect(status().isOk());
//        }

    }




