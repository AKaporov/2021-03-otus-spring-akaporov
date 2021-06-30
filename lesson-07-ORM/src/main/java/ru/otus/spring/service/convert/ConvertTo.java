package ru.otus.spring.service.convert;

public interface ConvertTo<T, D> {
    D convert(T obj);
}
