package team.sevendwarfs.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.entities.Person;
import team.sevendwarfs.persistence.repository.PersonRepository;

import java.util.List;

/**
 * Created by deng on 2017/5/6.
 */

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    MovieService movieService;

    @Override
    public void create(Person person) {
        personRepository.save(person);
    }

    @Override
    public void delete(Integer id) {
        personRepository.delete(id);
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }

    @Override
    public Person update(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person findById(Integer id) {
        return personRepository.findOne(id);
    }

    @Override
    public List<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

    @Override
    public List<Person> findByType(String type) {
        return personRepository.findByType(type);
    }

    @Override
    public List<Person> findByMovie(Movie movie) {
        movie = movieService.findById(movie.getId());
        return movie.getMoviers();
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
