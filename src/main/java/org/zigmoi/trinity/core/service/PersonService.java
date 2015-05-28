package org.zigmoi.trinity.core.service;

import java.util.List;
import org.zigmoi.trinity.core.model.Person;

public interface PersonService {

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons();
	public Person getPersonById(int id);
	public void removePerson(int id);
	
}
