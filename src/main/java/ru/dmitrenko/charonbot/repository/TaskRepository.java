package ru.dmitrenko.charonbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmitrenko.charonbot.model.domain.Task;
import ru.dmitrenko.charonbot.model.domain.TaskStatus;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

	List<Task> findAllByTaskStatusOrTaskStatus(TaskStatus taskStatus1, TaskStatus taskStatus2);
}
