package org.openjfx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openjfx.enums.TaskStatus;

import java.time.LocalDate;
import java.util.UUID;

@Data // генерирует getters, setters, toString, equals, hashCode
@NoArgsConstructor // пустой конструктор
@AllArgsConstructor // конструктор со всеми полями
public class Task {
    private UUID id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskStatus taskStatus;

    public Task(String title, String description, LocalDate dueDate) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }
}
