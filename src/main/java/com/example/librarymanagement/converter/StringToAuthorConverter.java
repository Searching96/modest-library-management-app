package com.example.librarymanagement.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.service.AuthorService;

@Component
public class StringToAuthorConverter implements Converter<String, Author> {
    private final AuthorService authorService;
    
    @Autowired
    public StringToAuthorConverter(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public Author convert(String source) {
        Long id = Long.parseLong(source);
        return authorService.findAuthorById(id);
    }
}
