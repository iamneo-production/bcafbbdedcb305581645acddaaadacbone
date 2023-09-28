package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class SpringappApplicationTests {

	@Autowired
    private MockMvc mockMvc;
	
	@Test
	public void testPostData() throws Exception {
		String st = "{\"id\": 1, \"userName\": \"SampleUser\", \"password\": \"123\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(st)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
	}
	
	@Test
	public void testPostAccountData() throws Exception {
		String st = "{\"accountId\": 1, \"accountNumber\": \"12345\", \"balance\": 20000}";
		mockMvc.perform(MockMvcRequestBuilders.post("/user/1/account")
				.contentType(MediaType.APPLICATION_JSON)
				.content(st)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
	}
	

    @Test
    public void testGetUserByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    
    @Test
    public void testGetAccountById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/account/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetBalanceById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/account/balance").param("accountNumber", "12345")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    

    private void checkClassExists(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " does not exist.");
        }
    }

    private void checkFieldExists(String className, String fieldName) {
        try {
            Class<?> clazz = Class.forName(className);
            clazz.getDeclaredField(fieldName);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            fail("Field " + fieldName + " in class " + className + " does not exist.");
        }
    }

	private void checkAnnotationExists(String className, String annotationName) {
		try {
			Class<?> clazz = Class.forName(className);
			ClassLoader classLoader = clazz.getClassLoader();
			Class<?> annotationClass = Class.forName(annotationName, false, classLoader);
			assertNotNull(clazz.getAnnotation((Class) annotationClass)); // Use raw type
		} catch (ClassNotFoundException | NullPointerException e) {
			fail("Class " + className + " or annotation " + annotationName + " does not exist.");
		}
	}
	

	 @Test
   public void testControllerClassExists() {
       checkClassExists("com.examly.springapp.controller.ApiController");
   }

   @Test
   public void testAccountRepoClassExists() {
       checkClassExists("com.examly.springapp.repository.AccountRepo");
   }
   
   @Test
   public void testUserRepoClassExists() {
       checkClassExists("com.examly.springapp.repository.UserRepo");
   }

   @Test
   public void testServiceClassExists() {
       checkClassExists("com.examly.springapp.service.ApiService");
   }

   @Test
   public void testAccountModelClassExists() {
       checkClassExists("com.examly.springapp.model.Account");
   }
   
   @Test
   public void testUserModelClassExists() {
       checkClassExists("com.examly.springapp.model.User");
   }


   @Test
   public void testModelHasUsernameField() {
       checkFieldExists("com.examly.springapp.model.User", "userName");
   }

   @Test
   public void testModelHasPasswordForField() {
       checkFieldExists("com.examly.springapp.model.User", "password");
   }

   @Test
   public void testModelHasAccountNumberForField() {
       checkFieldExists("com.examly.springapp.model.Account", "accountNumber");
   }
   
   @Test
   public void testModelHasBalanceForField() {
       checkFieldExists("com.examly.springapp.model.Account", "balance");
   }
   

   @Test
   public void testTeamModelHasEntityAnnotation() {
       checkAnnotationExists("com.examly.springapp.model.User", "javax.persistence.Entity");
   }
   
   @Test
   public void testPlayerModelHasEntityAnnotation() {
       checkAnnotationExists("com.examly.springapp.model.Account", "javax.persistence.Entity");
   }

   @Test
   public void testRepoHasRepositoryAnnotation() {
       checkAnnotationExists("com.examly.springapp.repository.UserRepo", "org.springframework.stereotype.Repository");
   }
   
   @Test
   public void testAccountRepoHasRepositoryAnnotation() {
       checkAnnotationExists("com.examly.springapp.repository.AccountRepo", "org.springframework.stereotype.Repository");
   }
   
   @Test
   public void testServiceHasServiceAnnotation() {
       checkAnnotationExists("com.examly.springapp.service.ApiService", "org.springframework.stereotype.Service");
   }
   
   @Test
   public void testControllerHasRestControllerAnnotation() {
       checkAnnotationExists("com.examly.springapp.controller.ApiController", "org.springframework.web.bind.annotation.RestController");
   }
   
   @Test 
   public void testControllerFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/controller"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }
   

	@Test 
   public void testModelFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/model"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }
   

	@Test 
   public void testRepositoryFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/repository"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }
   

	@Test 
   public void testServiceFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/service"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }

}
