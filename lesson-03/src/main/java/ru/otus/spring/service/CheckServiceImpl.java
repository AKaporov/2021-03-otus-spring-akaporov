package ru.otus.spring.service;

import org.springframework.stereotype.Service;

/**
 * Класс по проверки ответов
 */
@Service
public class CheckServiceImpl implements CheckService{
    @Override
    public boolean check(String rightAnswer, String answer) {
        return answer.equalsIgnoreCase(rightAnswer);
    }
}
