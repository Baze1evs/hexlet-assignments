package exercise.service;

import exercise.dto.*;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookMapper mapper;

    @Autowired
    private BookRepository repository;

    public List<BookDTO> readAll() {
        return repository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }

    public BookDTO read(Long id) {
        var author = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));

        return mapper.map(author);
    }

    public BookDTO create(BookCreateDTO bookData) {
        var author = mapper.map(bookData);

        repository.save(author);

        return mapper.map(author);
    }

    public BookDTO update(Long id, BookUpdateDTO bookData) {
        var author = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));

        mapper.update(bookData, author);
        repository.save(author);

        return mapper.map(author);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    // END
}
