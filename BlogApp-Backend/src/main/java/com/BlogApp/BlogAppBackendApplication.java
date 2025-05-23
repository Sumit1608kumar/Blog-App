package com.BlogApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.BlogApp.constants.GlobalConstants;
import com.BlogApp.entities.Category;
import com.BlogApp.entities.Role;
import com.BlogApp.repositories.CategoryRepository;
import com.BlogApp.repositories.RoleRepository;

@SpringBootApplication
public class BlogAppBackendApplication implements ApplicationRunner{
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppBackendApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		//CREATE 2 ROLES IF NOT EXISTS WITH CUSTOM ID
		//1-NORMAL
		//2-ADMIN
		
		Role role;
		if(roleRepository.findById(1).isEmpty()) {
			role=new Role();
			role.setRid(1);
			role.setRoleName(GlobalConstants.ROLE_NORMAL);
			roleRepository.save(role);
		}
		
		if(roleRepository.findById(2).isEmpty()) {
			role=new Role();
			role.setRid(2);
			role.setRoleName(GlobalConstants.ROLE_ADMIN);
			roleRepository.save(role);
		}
		
		//create 3 Categories by default
		Category category;
		if(categoryRepository.findById(1).isEmpty()) {
			category=new Category();
			category.setName("Technology");
			category.setDescription("Content regarding Tools and Technology.");
			categoryRepository.save(category);
		}
		if(categoryRepository.findById(2).isEmpty()) {
			category=new Category();
			category.setName("Sports");
			category.setDescription("Content regarding Sports.");
			categoryRepository.save(category);
		}
		if(categoryRepository.findById(3).isEmpty()) {
			category=new Category();
			category.setName("Bollywood");
			category.setDescription("Content regarding Bollywood.");
			categoryRepository.save(category);
		}
	}
}
