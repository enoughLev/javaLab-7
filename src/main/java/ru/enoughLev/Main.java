package ru.enoughLev;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Gson g = new Gson();
        List<Person> persons;
        Type personList = new TypeToken<List<Person>>() {
        }.getType();

        System.out.println(Main.class.getClassLoader().getResource("").getPath());

        try (InputStreamReader ir = new InputStreamReader(Main.class.getResourceAsStream("ru/enoughLev/books.json"))) {
            persons = g.fromJson(ir, personList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println("\n\tTask 1:");
        System.out.println("All visitors: ");
        persons.forEach(i -> System.out.println("\t" + i.getName() + " " + i.getSurname()));
        int personsCount = persons.size();
        System.out.println("Persons count is " + personsCount);


        System.out.println("\n\tTask 2:");
        System.out.println("Favorite books:");
        Set<Book> uniquePeople = persons.stream()
                .flatMap(i -> i.getFavoriteBooks().stream())
                .collect(Collectors.toSet());

        uniquePeople
                .forEach(i -> System.out.println("\t" + i.getName()));

        long favBookCount = uniquePeople.size();
        System.out.println("Total books: " + favBookCount);


        System.out.println("\n\tTask 3:");
        System.out.println("Sorted books by publishing year:");
        uniquePeople.stream()
                .sorted(Comparator.comparingInt(Book::getPublishingYear))
                .forEach(i -> System.out.println("\t" + i.getName() + " " + i.getPublishingYear()));


        System.out.println("\n\tTask 4:");
        System.out.print("The book by author Jane Austen is on the list - ");
        boolean author = uniquePeople.stream()
                .anyMatch(i -> i.getAuthor().equals("Jane Austen"));
        System.out.println(author);


        System.out.println("\n\tTask 5:");
        long maxBooks = persons.stream()
                .map(Person::getFavoriteBooks)
                .map(List::size)
                .max(Integer::compare)
                .orElse(0);
        System.out.println("Count of maximum books into favorite list: " + maxBooks);


        System.out.println("\n\tTask 6:");
        double averBooks = persons.stream()
                .map(Person::getFavoriteBooks)
                .map(List::size)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
        ArrayList<Message> messages = new ArrayList<>();
        persons.stream()
                .filter(Person::getSubscribed)
                .forEach(i -> {
                    int books = i.getFavoriteBooks().size();
                    String text = books < averBooks ? "read more" : (books == averBooks ? "fine" : "you are a bookworm!");
                    Message message = new Message();
                    message.setName(i.getName());
                    message.setText(text);
                    message.setNumber(i.getPhone());
                    messages.add(message);
                });
        messages.stream().forEach(System.out::println);


    }
}
