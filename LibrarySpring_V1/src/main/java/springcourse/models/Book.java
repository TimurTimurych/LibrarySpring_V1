package springcourse.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Book {

    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30,
            message = "Name should be between 2 and 30 charters")
    private String title;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30,
            message = "Name should be between 2 and 30 charters")
    private String author;

    @Min(value = 1700, message = "Age should be greater that 0")
    private int age;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
