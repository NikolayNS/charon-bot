package ru.dmitrenko.charonbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmitrenko.charonbot.model.domain.PartyStatus;
import ru.dmitrenko.charonbot.model.domain.Person;
import ru.dmitrenko.charonbot.model.domain.PersonStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

	Optional<Person> findBySteamId(Long steamId);

	List<Person> findAllByNickname(String nickname);

	List<Person> findAllByPersonStatus(PersonStatus personStatus);

	List<Person> findAllByPartyStatus(PartyStatus partyStatus);
}
