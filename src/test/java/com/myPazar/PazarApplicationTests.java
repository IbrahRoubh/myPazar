package com.myPazar;

import com.myPazar.model.Customer;
import com.myPazar.model.Role;
import com.myPazar.service.detailsService.CustomerDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;

@SpringBootTest
class PazarApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testUserRoleMatching() {
		// Create a test customer with a specific role
		Customer customer = new Customer("a@gmail.com", "ibrahim", "sd", "0531474", "sdds");
		CustomerDetails customerDetails = new CustomerDetails(customer);

		// Get the authorities from the customer details
		GrantedAuthority grantedAuthority = customerDetails.getAuthorities().iterator().next();

		// Verify if the role in the authorities matches the expected role
		Assertions.assertEquals(Role.CUSTOMER.toString(), grantedAuthority.getAuthority());
		System.out.println(grantedAuthority.getAuthority());
	}

}
