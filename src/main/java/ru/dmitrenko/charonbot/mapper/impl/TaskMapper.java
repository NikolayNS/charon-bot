package ru.dmitrenko.charonbot.mapper.impl;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.dmitrenko.charonbot.model.domain.Task;
import ru.dmitrenko.charonbot.model.request.TaskRequest;
import ru.dmitrenko.charonbot.model.view.TaskView;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TaskMapper {

	Task requestToTask(TaskRequest source);

	Task mergeRequestToTask(@MappingTarget Task target, TaskRequest source);

	TaskView taskToView(Task source);

	default List<TaskView> personsToViews(Collection<Task> source) {
		return source.stream()
			.map(this::taskToView)
			.collect(Collectors.toList());
	}
}
