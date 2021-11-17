package ru.dmitrenko.charonbot.service.domain;

import ru.dmitrenko.charonbot.model.request.TaskRequest;
import ru.dmitrenko.charonbot.model.view.TaskView;

import java.util.List;
import java.util.UUID;

public interface TaskDomainService {

	TaskView add(TaskRequest request);

	TaskView update(UUID id, TaskRequest request);

	List<TaskView> getAllActiveTasks();
}
