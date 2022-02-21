package learn.spring.customer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import learn.spring.config.AppConfig;
import learn.spring.dao.PersonDAO;
import learn.spring.model.Person;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		PersonDAO personDAO = context.getBean(PersonDAO.class);

		System.out.println("List of person is:");

		for (Person p : personDAO.getAllPersons()) {
			System.out.println(p);
		}

		System.out.println("\nGet person with ID 4");

		Person personById = personDAO.getPersonById(4L);
		System.out.println(personById);

		
		  System.out.println("\nCreating person: "); 
		  Person person = new Person(5, 29, "SKY", "SKY"); 
		  System.out.println(person);
		  personDAO.createPerson(person); System.out.println("\nList of person is:");
		  
		  for (Person p : personDAO.getAllPersons()) 
		  { 
			  System.out.println(p); 
		  }
		 

		  System.out.println("\nDeleting person with ID 4");
		  personDAO.deletePerson(personById);

		  System.out.println("\nUpdate person with ID 2");

		
		  Person pperson = personDAO.getPersonById(2L); 
		  pperson.setLastName("CHANGED");
		  personDAO.updatePerson(pperson);
		  
		  System.out.println("\nList of person is:"); 
		  for (Person p : personDAO.getAllPersons()) 
		  { 
			  System.out.println(p); 
		  }
		 

		context.close();
	}
}
