package com.example.folha;

import com.example.folha.entity.Momento;
import com.example.folha.exception.ApiRequestConflict;
import com.example.folha.exception.ApiRequestExcept;
import com.example.folha.exception.ApiRequestForbidden;
import com.example.folha.repository.BatidasRepository;
import com.example.folha.service.BatidasService;
import com.example.folha.utils.UtilsValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

@ContextConfiguration(classes = BatidasService.class)
@ExtendWith(SpringExtension.class)
public class BatidasTeste {

    @Autowired
    private BatidasService batidasService;

    @MockBean
    private BatidasRepository batidasRepository;

    @Test
    public void testValidaHorarioJaregistrado() {
        Momento momento = new Momento();
        momento.setDataHora("2018-08-22T08:00:00");

        Mockito.when(batidasRepository.existsByDataHora(anyString())).thenReturn(true);

        Exception exception = assertThrows(ApiRequestConflict.class, () -> {
            batidasService.validaHorarioJaRegistrado(momento);
        });

        String expectedMessage = "Horário já registrado";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testValidaFormatoHorario() {
        Momento momento = new Momento();
        momento.setDataHora("2018");

        Exception exception = assertThrows(ApiRequestExcept.class, () -> {
            UtilsValidation.validaFormatoHorario(momento);
        });

        String expectedMessage = "Data e hora em formato inválido";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testValidaSabadoEDomingo() {
        Momento momento = new Momento();
        momento.setDataHora("2021-08-22T08:00:00");

        Exception exception = assertThrows(ApiRequestForbidden.class, () -> {
            UtilsValidation.validaSabadoEDomingo(momento);
        });

        String expectedMessage = "Sábado e domingo não são permitidos como dia de trabalho";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testValidaHorarioJornadaTrabalho() {
        Momento momento = new Momento();
        momento.setDataHora("2021-08-22T05:00:00");

        Exception exception = assertThrows(ApiRequestExcept.class, () -> {
            UtilsValidation.validaHorarioJornadaTrabalho(momento);
        });

        String expectedMessage = "Só pode registrar horário entre 7:00 até 22:00. " +
                "Não trabalhamos de madrugada ou cedo demais!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testValidaMinimoHorarioAlmoco() {
        Momento momento1 = new Momento();
        momento1.setDataHora("2021-08-22T12:00:00");
        Momento momento2 = new Momento();
        momento2.setDataHora("2021-08-22T12:30:00");
        List<Momento> momentos = new ArrayList<>(2);
        momentos.add(momento1);
        momentos.add(momento2);

        Momento momento3 = new Momento();
        momento3.setDataHora("2021-08-22T13:00:00");

        Mockito.when(batidasRepository.findAll()).thenReturn(momentos);


        Exception exception = assertThrows(ApiRequestForbidden.class, () -> {
            batidasService.validaMinimoHorarioAlmoco(momento3);
        });

        String expectedMessage = "Deve haver no mínimo 1 hora de almoço";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testValidaOrdemHorario() {
        Momento momento1 = new Momento();
        momento1.setDataHora("2021-08-22T12:00:00");
        Momento momento2 = new Momento();
        momento2.setDataHora("2021-08-22T12:30:00");
        List<Momento> momentos = new ArrayList<>(2);
        momentos.add(momento1);
        momentos.add(momento2);

        Momento momento3 = new Momento();
        momento3.setDataHora("2021-08-22T11:00:00");

        Mockito.when(batidasRepository.findAll()).thenReturn(momentos);


        Exception exception = assertThrows(ApiRequestExcept.class, () -> {
            batidasService.validaOrdemHorario(momento3);
        });

        String expectedMessage = "Não pode registrar horários anteriores aos já registrados";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testHorariosRegistradosPorDia() {
        Momento momento1 = new Momento();
        momento1.setDataHora("2021-08-22T12:00:00");
        Momento momento2 = new Momento();
        momento2.setDataHora("2021-08-22T12:30:00");
        List<Momento> momentos = new ArrayList<>(2);
        momentos.add(momento1);
        momentos.add(momento2);

        Momento momento3 = new Momento();
        momento3.setDataHora("2021-08-22T12:40:00");

        Mockito.when(batidasRepository.findAll()).thenReturn(momentos);
        List<Momento> actualList = batidasService.horariosRegistradosPorDia(momento3);

        assertEquals("2021-08-22T12:00:00", actualList.get(0).getDataHora());
    }
}
