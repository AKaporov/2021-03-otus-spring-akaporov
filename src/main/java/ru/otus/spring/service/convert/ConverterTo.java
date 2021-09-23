package ru.otus.spring.service.convert;

public interface ConverterTo<T, D> {
    D convert(T obj);
}
