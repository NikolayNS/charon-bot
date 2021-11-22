package ru.dmitrenko.charonbot.mapper.impl;

import org.springframework.stereotype.Service;
import ru.dmitrenko.charonbot.mapper.Mapper;
import ru.dmitrenko.charonbot.model.domain.Person;
import ru.dmitrenko.charonbot.model.domain.Task;
import ru.dmitrenko.charonbot.model.domain.TaskStatus;
import ru.dmitrenko.charonbot.model.request.TaskRequest;
import ru.dmitrenko.charonbot.model.view.TaskView;
import ru.dmitrenko.charonbot.util.Pair;

@Service
public class TaskMapperImpl implements Mapper<Task, Pair<TaskRequest, Person>, TaskView> {

	@Override
	public Task toEntity(Pair<TaskRequest, Person> pair) {
		return new Task()
			.setName(pair.getLeft().getName())
			.setDescription(pair.getLeft().getDescription())
			.setStartDate(pair.getLeft().getStartDate())
			.setEndDate(pair.getLeft().getEndDate())
			.setTaskStatus(TaskStatus.fromValue(pair.getLeft().getTaskStatus()))
			.setPerson(pair.getRight());
	}

	@Override
	public Task merge(Task entity, Pair<TaskRequest, Person> pair) {
		return entity
			.setName(pair.getLeft().getName() == null ? entity.getName() : pair.getLeft().getName())
			.setDescription(pair.getLeft().getDescription() == null ? entity.getDescription() : pair.getLeft().getName())
			.setStartDate(pair.getLeft().getStartDate() == null ? entity.getStartDate() : pair.getLeft().getStartDate())
			.setEndDate(pair.getLeft().getEndDate() == null ? entity.getEndDate() : pair.getLeft().getEndDate())
			.setTaskStatus(pair.getLeft().getTaskStatus() == null ? entity.getTaskStatus() : TaskStatus.fromValue(pair.getLeft().getTaskStatus()))
			.setPerson(pair.getRight());
	}

	@Override
	public TaskView toView(Task entity) {
		return new TaskView()
			.setId(entity.getId())
			.setName(entity.getName())
			.setDescription(entity.getDescription())
			.setStartDate(entity.getStartDate())
			.setEndDate(entity.getEndDate())
			.setTaskStatus(entity.getTaskStatus().getValue())
			.setPersonSteamId(entity.getPerson().getSteamId());
	}
}
