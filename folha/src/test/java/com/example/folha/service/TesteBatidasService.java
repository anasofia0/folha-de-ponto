package com.example.folha.service;

import com.example.folha.entity.Momento;
import com.example.folha.exception.ApiRequestConflict;
import com.example.folha.exception.ApiRequestExcept;
import com.example.folha.exception.ApiRequestForbidden;
import com.example.folha.repository.BatidasRepository;
import com.example.folha.utils.UtilsValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TesteBatidasService {

    private BatidasService batidasService;

    @Mock
    private BatidasRepository batidasRepository;

    @BeforeEach
    public void setup() {
        this.batidasRepository = mock(BatidasRepository.class);
        this.batidasService = new BatidasService(this.batidasRepository);
    }

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
    public void testHorariosRegistradosPorDia() {
        Momento momento = new Momento();
        momento.setDataHora("2018-08-22T08:00:00");
        List<Momento> listaMomentos = new ArrayList<>();
        listaMomentos.add(momento);

        Mockito.when(batidasRepository.findAll()).thenReturn(listaMomentos);

        List<Momento> horariosRegistradosPorDia = batidasService.horariosRegistradosPorDia(momento);
        assertEquals("2018-08-22T08:00:00", horariosRegistradosPorDia.get(0).getDataHora());
    }

    @Test
    public void testValidaQuantidadeDeHorarios() {
        Momento momento = new Momento();
        momento.setDataHora("2018-08-22T08:00:00");

        Mockito.when(batidasRepository.findAll()).thenReturn(listaMomentos());
        Exception exception = assertThrows(ApiRequestForbidden.class, () -> {
            batidasService.validaQuantidadeDeHorarios(momento);
        });

        String expectedMessage = "Apenas 4 horários podem ser registrados por dia";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testValidaOrdemHorario() {
        Momento momentoAnterior = new Momento();
        momentoAnterior.setDataHora("2018-08-22T07:00:00");

        Mockito.when(batidasRepository.findAll()).thenReturn(listaMomentos());
        Exception exception = assertThrows(ApiRequestExcept.class, () -> {
            batidasService.validaOrdemHorario(momentoAnterior);
        });

        String expectedMessage = "Não pode registrar horários anteriores aos já registrados";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testValidaMinimoHorarioAlmoco() {
        Momento momento = new Momento(), momento1 = new Momento(), momento2 = new Momento();
        momento.setDataHora("2018-08-22T08:00:00");
        momento1.setDataHora("2018-08-22T12:00:00");
        momento2.setDataHora("2018-08-22T12:59:00");
        List<Momento> listaMomentos = new ArrayList<>();
        listaMomentos.add(momento);
        listaMomentos.add(momento1);

        Mockito.when(batidasRepository.findAll()).thenReturn(listaMomentos);
        Exception exception = assertThrows(ApiRequestForbidden.class, () -> {
            batidasService.validaMinimoHorarioAlmoco(momento2);
        });

        String expectedMessage = "Deve haver no mínimo 1 hora de almoço";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testValidaCampoObrigatorio(){

        Exception exception = assertThrows(ApiRequestExcept.class, () -> {
            batidasService.validaCampoObrigatorio(null);
        });

        String expectedMessage = "Campo obrigatório não informado";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testValidaFormatoHorarioException() {
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
    public void testValidaSabadoException() {
        Momento momento = new Momento();
        momento.setDataHora("2021-06-12T08:00:00");

        Exception exception = assertThrows(ApiRequestForbidden.class, () -> {
            UtilsValidation.validaSabadoEDomingo(momento);
        });

        String expectedMessage = "Sábado e domingo não são permitidos como dia de trabalho";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testValidaDomingoException() {
        Momento momento = new Momento();
        momento.setDataHora("2021-06-13T08:00:00");

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
        momento.setDataHora("2021-06-14T06:00:00");

        Exception exception = assertThrows(ApiRequestExcept.class, () -> {
            UtilsValidation.validaHorarioJornadaTrabalho(momento);
        });

        String expectedMessage = "Só pode registrar horário entre 7:00 até 22:00. " +
                "Não trabalhamos de madrugada ou cedo demais!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    public List<Momento> listaMomentos() {
        Momento momento = new Momento(), momento1 = new Momento(), momento2 = new Momento(),
                momento3 = new Momento(), momento4 = new Momento();

        momento.setDataHora("2018-08-22T08:00:00");
        momento1.setDataHora("2018-08-22T09:00:00");
        momento2.setDataHora("2018-08-22T10:00:00");
        momento3.setDataHora("2018-08-22T11:00:00");
        momento4.setDataHora("2018-08-22T12:00:00");

        List<Momento> listaMomentos = new ArrayList<>();
        listaMomentos.add(momento);
        listaMomentos.add(momento1);
        listaMomentos.add(momento2);
        listaMomentos.add(momento3);
        listaMomentos.add(momento4);

        return listaMomentos;

    }
}
