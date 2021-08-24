package com.example.folha.service;

import com.example.folha.dto.MomentoDTO;
import com.example.folha.entity.Momento;
import com.example.folha.exception.ApiRequestConflict;
import com.example.folha.exception.ApiRequestExcept;
import com.example.folha.exception.ApiRequestForbidden;
import com.example.folha.repository.BatidasRepository;
import com.example.folha.utils.Constante;
import com.example.folha.utils.UtilsValidation;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class BatidasService {

    private final BatidasRepository batidasRepository;

    public BatidasService(BatidasRepository batidasRepository) {
        this.batidasRepository = batidasRepository;
    }

    public void criarMomento(MomentoDTO momentoDTO) {
        if(momentoDTO.getDataHora() == null) {
            throw new ApiRequestExcept("Campo obrigatório não informado");
        }
        Momento momento = toEntity(momentoDTO);
            UtilsValidation.validaDados(momento);
            validaQuantidadeDeHorarios(momento);
            validaHorarioJaRegistrado(momento);
            validaOrdemHorario(momento);
            validaMinimoHorarioAlmoco(momento);
            this.batidasRepository.save(toEntity(momentoDTO));
    }

    public Momento toEntity(MomentoDTO momentoDTO){
        Momento momento = new Momento();
        momento.setDataHora(momentoDTO.getDataHora());
        return momento;
    }

    public void validaHorarioJaRegistrado(Momento momento){
        boolean existeDataHora = this.batidasRepository.existsByDataHora(momento.getDataHora());
        if(existeDataHora){
            throw new ApiRequestConflict("Horário já registrado");
        }
    }

    public void validaQuantidadeDeHorarios(Momento momento){
        List<Momento> horariosRegistradosPorDia = horariosRegistradosPorDia(momento);

        if(horariosRegistradosPorDia.size() >= Constante.MAX_HORARIOS_REGISTRADOS.getValor()){
            throw new ApiRequestForbidden("Apenas 4 horários podem ser registrados por dia");
        }
    }

    public List<Momento> horariosRegistradosPorDia(Momento momento){
        List<Momento> listaHorariosJaRegistradosTotal = this.batidasRepository.findAll();
        String parteData = momento.getDataHora().substring(0, Constante.ULTIMA_POSICAO_STRING_DATA.getValor());
        List<Momento> listaHorariosJaregistradosPorDia = new ArrayList<>();

        listaHorariosJaRegistradosTotal.forEach((Momento data) -> {
            if(data.getDataHora().substring(0,Constante.ULTIMA_POSICAO_STRING_DATA.getValor()).equals(parteData)){
                listaHorariosJaregistradosPorDia.add(data);
            }
        });
        return listaHorariosJaregistradosPorDia;
    }

    public void validaOrdemHorario(Momento momento){
        List<Momento> horariosRegistradosPorDia = horariosRegistradosPorDia(momento);
        int tamanhoLista = horariosRegistradosPorDia.size();
        if(tamanhoLista != 0){
            Momento ultimoLista = horariosRegistradosPorDia.get(tamanhoLista-1);
            LocalDateTime ultimoListahorario = LocalDateTime.parse(ultimoLista.getDataHora());
            LocalDateTime momentoHorario = LocalDateTime.parse(momento.getDataHora());
            if(!momentoHorario.isAfter(ultimoListahorario)){
                throw new ApiRequestExcept("Não pode registrar horários anteriores aos já registrados");
            }
        }
    }

    public void validaMinimoHorarioAlmoco(Momento momento){
        List<Momento> horariosRegistradosPorDia = horariosRegistradosPorDia(momento);
        int tamanhoLista = horariosRegistradosPorDia.size();
        if(tamanhoLista != 0){
            Momento ultimoLista = horariosRegistradosPorDia.get(tamanhoLista-1);
            LocalDateTime ultimoListahorario = LocalDateTime.parse(ultimoLista.getDataHora());
            LocalDateTime momentoHorario = LocalDateTime.parse(momento.getDataHora());
            if(Duration.between(ultimoListahorario, momentoHorario).getSeconds() <
                    Constante.UMA_HORA_SEGUNDO.getValor()){
                throw new ApiRequestForbidden("Deve haver no mínimo 1 hora de almoço");
            }
        }
    }

    public void validaCampoObrigatorio(MomentoDTO momentoDTO){
        if(momentoDTO == null){
            throw new ApiRequestExcept("Campo obrigatório não informado");
        }
    }

}
