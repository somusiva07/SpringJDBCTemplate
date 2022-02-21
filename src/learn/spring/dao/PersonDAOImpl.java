package learn.spring.dao;

import java.util.List;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import learn.spring.model.Person;
import learn.spring.model.PersonMapper;

@Component
public class PersonDAOImpl implements PersonDAO {

	//JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate jdbcTemplate;

	private final String SQL_FIND_PERSON = "select * from people where id = :id";
	private final String SQL_DELETE_PERSON = "delete from people where id = :id";
	private final String SQL_UPDATE_PERSON = "update people set first_name = :firstName, last_name = :lastName, age  = :age where id = :id";
	private final String SQL_GET_ALL = "select * from people";
	private final String SQL_INSERT_PERSON = "insert into people(id, first_name, last_name, age) values(:id,:firstName,:lastName,:age)";

	@Autowired
	public PersonDAOImpl(DataSource dataSource) {
		//jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Person getPersonById(Long id) {
		//return jdbcTemplate.queryForObject(SQL_FIND_PERSON, new Object[] {id}, new PersonMapper());
		//return (Person)(jdbcTemplate.queryForList(SQL_FIND_PERSON, new PersonMapper(), new Object[] {id} )).get(0);
		//return jdbcTemplate.queryForObject("SELECT * FROM people WHERE id=?",new PersonMapper(), id);
		
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		
		return jdbcTemplate.queryForObject(SQL_FIND_PERSON, mapSqlParameterSource, new PersonMapper());
	}

	public List<Person> getAllPersons() {
		return jdbcTemplate.query(SQL_GET_ALL, new PersonMapper());
	}

	public boolean deletePerson(Person person) {
		
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", person.getId());
		
		return jdbcTemplate.update(SQL_DELETE_PERSON, mapSqlParameterSource) > 0;
	}

	public boolean updatePerson(Person person) {
		
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", person.getId());
		mapSqlParameterSource.addValue("firstName", person.getFirstName());
		mapSqlParameterSource.addValue("lastName", person.getLastName());
		mapSqlParameterSource.addValue("age", person.getAge());
		
		  return jdbcTemplate.update(SQL_UPDATE_PERSON, mapSqlParameterSource) > 0;
		 
	}

	public boolean createPerson(Person person) {
		
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", person.getId());
		mapSqlParameterSource.addValue("firstName", person.getFirstName());
		mapSqlParameterSource.addValue("lastName", person.getLastName());
		mapSqlParameterSource.addValue("age", person.getAge());
		
		  return jdbcTemplate.update(SQL_INSERT_PERSON, mapSqlParameterSource) > 0;
		 
	}
}
