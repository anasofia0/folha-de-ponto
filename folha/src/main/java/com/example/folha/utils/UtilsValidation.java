package com.example.folha.utils;

import com.example.folha.entity.Momento;
import com.example.folha.exception.ApiRequestExcept;
import com.example.folha.exception.ApiRequestForbidden;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.example.folha.utils.Constantes.FIM_EXPEDIENTE;
import static com.example.folha.utils.Constantes.INICIO_EXPEDIENTE;

public final class UtilsValidation {

    private UtilsValidation() {

    }

    public static void validaDados(Momento momento) {
        validaFormatoHorario(momento);
        validaSabadoEDomingo(momento);
        validaHorarioJornadaTrabalho(momento);
    }

    public static void validaFormatoHorario(Momento momento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        try {
            formatter.parse(momento.getDataHora());
        } catch (DateTimeParseException e) {
            throw new ApiRequestExcept("Data e hora em formato inválido");
        }
    }

    public static void validaSabadoEDomingo(Momento momento) {
        LocalDateTime dataHoraFormatado = LocalDateTime.parse(momento.getDataHora());
        if (dataHoraFormatado.getDayOfWeek() == DayOfWeek.SATURDAY ||
                dataHoraFormatado.getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new ApiRequestForbidden("Sábado e domingo não são permitidos como dia de trabalho");
        }
    }

    public static void validaHorarioJornadaTrabalho(Momento momento) {
        LocalDateTime dataHoraFormatado = LocalDateTime.parse(momento.getDataHora());
        if (dataHoraFormatado.getHour() < INICIO_EXPEDIENTE || dataHoraFormatado.getHour() > FIM_EXPEDIENTE) {
            throw new ApiRequestExcept("Só pode registrar horário entre 7:00 até 22:00. " +
                    "Não trabalhamos de madrugada ou cedo demais!");
        }
    }
}


