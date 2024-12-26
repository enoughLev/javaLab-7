package ru.enoughLev;

import java.util.List;
import lombok.Data;

@Data
public class Person {
    private String name;
    private String surname;
    private String phone;
    private Boolean subscribed;
    private List<Book> favoriteBooks;
}
