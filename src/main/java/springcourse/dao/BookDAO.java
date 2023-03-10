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
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book",
                new BeanPropertyRowMapper<>(Book.class));

    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author,  age) VALUES (?,?,?)"
                , book.getTitle(), book.getAuthor(), book.getAge());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class)).stream().findAny().
                orElse(null);
    }

    public void update(int id, Book updateBook) {

        jdbcTemplate.update("UPDATE Book SET title=?, author=?, age=? WHERE id=?",
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getAge(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }


    public Optional<Person> checkByBookId(int id) {
        return jdbcTemplate.query("SELECT name FROM person JOIN book ON Person.id=Book.user_id WHERE Book.id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void deleteOwner(int id) {
        jdbcTemplate.update("UPDATE Book SET user_id = null WHERE id=?", id);
    }

    public void addOwner(int bookId, int userId) {
        jdbcTemplate.update("UPDATE Book SET user_id = ? WHERE id=?", userId, bookId);
    }

    public Optional<Book> checkByTitle(Book book) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE title = ?", new Object[]{book.getTitle()},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }
}



