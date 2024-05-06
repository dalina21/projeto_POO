package br.com.catolicapb.projeto.Contrato;

import br.com.catolicapb.projeto.Enumeradores.EnumStatusConsulta;
import br.com.catolicapb.projeto.Model.ConsultaDermatologica;
import br.com.catolicapb.projeto.Model.PacienteGinecologico;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IAgendaDermato {

    boolean adicionarConsulta(ConsultaDermatologica consulta); //agendar
    ConsultaDermatologica pesquisarConsulta(String cpfPaciente);
    boolean removerConsulta(ConsultaDermatologica consulta); //cancelar
    boolean remarcarConsulta(ConsultaDermatologica consulta,LocalDateTime novaDataEHorario);
    void mostrarConsultas();
    void mostrarConsultasDoDia();

}
