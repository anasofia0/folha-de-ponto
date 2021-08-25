package com.example.folha.service;

import com.example.folha.dto.MomentoDTO;
import com.example.folha.dto.RegistroDTO;
import com.example.folha.dto.RelatorioDTO;
import com.example.folha.entity.Momento;
import com.example.folha.repository.BatidasRepository;
import com.example.folha.utils.Constante;
import com.example.folha.utils.UtilsValidation;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RelatorioService {
    private final BatidasRepository batidasRepository;
    private final BatidasService batidasService;

    public RelatorioService(BatidasRepository batidasRepository, BatidasService batidasService) {
        this.batidasRepository = batidasRepository;
        this.batidasService = batidasService;
    }

    public RelatorioDTO listarRelatorio(String mes){
        UtilsValidation.validaFormatoDataRelatorio(mes);
        RelatorioDTO relatorioDTO = new RelatorioDTO();
        relatorioDTO.setMes(mes);
        relatorioDTO.setHorasTrabalhadasMes(horasTrabalhadasPorMes(mes).toString());
//        relatorioDTO.setHorasExcedentesMes(horariosExcedentesPorMes(mes));
        relatorioDTO.setRegistrosDTO(listaRegistro(mes));
        return relatorioDTO;
    }


    public Duration horasTrabalhadasPorMes(String mes){
        List<MomentoDTO> listaMomentosDTOporMes = listaMomentos(mes);
        List<String> jaContado = new ArrayList<>();
        Duration total = Duration.ZERO;
        for (MomentoDTO momentoDTO : listaMomentosDTOporMes) {
            String parteDataString = momentoDTO.getDataHora()
                    .substring(0, Constante.ULTIMA_POSICAO_STRING_DATA.getValor());
            if(!jaContado.contains(parteDataString)) {
                jaContado.add(parteDataString);
                List<Momento> listaMomentosHorariosDia =
                        batidasService.horariosRegistradosPorDia(batidasService.toEntity(momentoDTO));
                total = total.plus(Duration.between(LocalDateTime.parse(listaMomentosHorariosDia.get(0).getDataHora()),
                        LocalDateTime.parse(listaMomentosHorariosDia
                                .get(listaMomentosHorariosDia.size() - 1).getDataHora())));
            }
        }
        return total;
        }


    public String horariosExcedentesPorMes(String mes){
        Duration horarioTotalTrabalhadoSemAlmoco = horasTrabalhadasPorMes(mes);

        if(horarioTotalTrabalhadoSemAlmoco.minusHours(Constante.TOTAL_HORAS_POR_MES.getValor()).isNegative()){
            return horarioTotalTrabalhadoSemAlmoco.minusHours(Constante.TOTAL_HORAS_POR_MES.getValor()).toString();
        }
        return Duration.ZERO.toString();
    }

    public String horariosDevidosPorMes(String mes){
        Duration horarioTotalTrabalhadoSemAlmoco = horasTrabalhadasPorMes(mes);

        if(horarioTotalTrabalhadoSemAlmoco.minusHours(Constante.TOTAL_HORAS_POR_MES.getValor()).isNegative()){
            return Duration.ZERO.toString();
        }
        return horarioTotalTrabalhadoSemAlmoco.minusHours(Constante.TOTAL_HORAS_POR_MES.getValor()).toString();
    }

    private List<MomentoDTO> listaMomentos(String mes) {
        String dataAlocacao = "%" + mes + "%";
        return batidasRepository.findByDate(dataAlocacao);
    }

    private List<RegistroDTO> listaRegistro(String mes){
        List<MomentoDTO> listaMomentosPorMes = listaMomentos(mes);
        List<String> diaJaContado = new ArrayList<>();
        List<RegistroDTO> registrosDTO = new ArrayList<>();

        listaMomentosPorMes.forEach((MomentoDTO momentoDTO) ->{
            String parteData = momentoDTO.getDataHora()
                    .substring(0, Constante.ULTIMA_POSICAO_STRING_DATA.getValor());
            if(!(diaJaContado.contains(parteData))){
                diaJaContado.add(parteData);
                RegistroDTO registroDTO = new RegistroDTO();
                registroDTO.setDia(parteData);
                registroDTO.setHorarios(horariosPorDia(parteData));
                registrosDTO.add(registroDTO);
            }
        });
        return registrosDTO;
    }

    public List<String> horariosPorDia(String parteData){
        String dataAlocacao = "%" + parteData + "%";
        List<MomentoDTO> listaMomentosRegistradosData = this.batidasRepository.findByDate(dataAlocacao);
        List<String> listaApenasHorariosPorDia = new ArrayList<>();

        listaMomentosRegistradosData.forEach((MomentoDTO momentoDTO) -> {
           listaApenasHorariosPorDia.add(momentoDTO.getDataHora().substring(11, 19));
        });
        return listaApenasHorariosPorDia;
    }
}
