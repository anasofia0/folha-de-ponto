package com.example.folha;

import com.example.folha.entity.Momento;
import com.example.folha.exception.ApiRequestConflict;
import com.example.folha.repository.BatidasRepository;
import com.example.folha.service.BatidasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class batidasTeste {

    private BatidasService batidasService;

    @Mock
    private BatidasRepository batidasRepository;

    @BeforeEach
    public void setup() {
        this.batidasRepository = mock(BatidasRepository.class);
        this.batidasService = new BatidasService(this.batidasRepository);
    }

    @Test
    public void validaHorarioJaregistrado() {
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
}
