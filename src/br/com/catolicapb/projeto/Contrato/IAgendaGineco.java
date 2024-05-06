package br.com.catolicapb.projeto.Contrato;

import br.com.catolicapb.projeto.Model.ConsultaDermatologica;
import br.com.catolicapb.projeto.Model.ConsultaGinecologica;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IAgendaGineco {

    boolean adicionarConsulta(ConsultaGinecologica consulta); //agendar
    ConsultaGinecologica pesquisarConsulta(String cpfPaciente);
    boolean removerConsulta(ConsultaGinecologica consulta); //cancelar
    boolean remarcarConsulta(ConsultaGinecologica consulta,LocalDateTime novaDataEHorario);
    void mostrarConsultas();
    void mostrarConsultasDoDia();

}
