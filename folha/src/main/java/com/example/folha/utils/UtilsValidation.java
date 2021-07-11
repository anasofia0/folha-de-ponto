package com.example.folha.utils;

import com.example.folha.entity.Momento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UtilsValidation {

    public static DateTimeFormatter dateTimeFormatter;

    public static Boolean validaFormatoHorario(Momento dataHora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        try {
            formatter.parse(dataHora.getDataHora().toString());
        } catch (DateTimeParseException e){
            throw e;
        }
         return true;
    }
}


