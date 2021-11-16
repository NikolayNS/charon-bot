package ru.dmitrenko.charonbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmitrenko.charonbot.model.domain.Person;

import java.util.UUID;

@Repository
public interface StalkerRepository extends JpaRepository<UUID, Person> {
}
