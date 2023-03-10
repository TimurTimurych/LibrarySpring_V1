package springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import springcourse.models.Book;
import springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PersonDAO() {
        jdbcTemplate = null;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> checkByName(Person person) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name = ?", new Object[]{person.getName()},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class)).stream().findAny().
                orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, year) VALUES (?,?)", person.getName(),
                person.getYear());
    }

    public void update(int id, Person updatedPerson) {

        jdbcTemplate.update("UPDATE Person SET name=?, year=? WHERE id=?",
                updatedPerson.getName(), updatedPerson.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public List<Book> checkByUserId(int userId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE user_id=?", new Object[]{userId},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
