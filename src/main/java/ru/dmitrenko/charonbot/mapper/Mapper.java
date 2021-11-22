package ru.dmitrenko.charonbot.mapper;

import java.util.Collection;
import java.util.List;

public interface Mapper<E, R, V> {

	E toEntity(R request);

	E merge(E entity, R request);

	V toView(E entity);

	default List<E> toEntities(Collection<R> requests) {
		return requests.stream()
			.map(this::toEntity)
			.toList();
	}

	default List<V> toViews(Collection<E> entities) {
		return entities.stream()
			.map(this::toView)
			.toList();
	}
}
