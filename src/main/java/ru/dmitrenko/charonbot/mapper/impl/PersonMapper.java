package ru.dmitrenko.charonbot.mapper.impl;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.dmitrenko.charonbot.model.domain.Person;
import ru.dmitrenko.charonbot.model.request.PersonRequest;
import ru.dmitrenko.charonbot.model.view.PersonView;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = TaskMapper.class)
public interface PersonMapper {

	Person requestToPerson(PersonRequest source);

	Person mergeRequestToPerson(@MappingTarget Person target, PersonRequest source);

	PersonView personToView(Person source);

	default List<PersonView> personsToViews(Collection<Person> source) {
		return source.stream()
			.map(this::personToView)
			.collect(Collectors.toList());
	}
}
