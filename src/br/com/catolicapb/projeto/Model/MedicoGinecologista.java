package br.com.catolicapb.projeto.Model;

import br.com.catolicapb.projeto.Contrato.IAgendaGineco;
import br.com.catolicapb.projeto.Enumeradores.EnumStatusConsulta;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MedicoGinecologista extends Medico implements IAgendaGineco {

    public ConsultaGinecologica[] consultas;

    public MedicoGinecologista(
            String nome,
            String sexo,
            String cpf,
            LocalDate dataDeNascimento,
            Endereco endereco,
            String telefone,
            String senha,
            String crm,
            int limiteDeConsultasDiarias,
            double valorConsulta
    ){
        super(nome, sexo, cpf, dataDeNascimento, endereco, telefone, senha, crm, limiteDeConsultasDiarias, valorConsulta);
        this.consultas = new ConsultaGinecologica[this.datasDisponiveis.length];
    }

    @Override
    public boolean adicionarConsulta(ConsultaGinecologica consulta){
        boolean adicionado = false;

        for (int i = 0; i < this.consultas.length; i++){
            if (this.consultas[i] == null){
                this.consultas[i] = consulta;
                adicionado = true;
                break;
            }
        }

        return adicionado;
    }

    @Override
    public ConsultaGinecologica pesquisarConsulta(String cpfPaciente){
        ConsultaGinecologica consulta = null;

        for (int i = 0; i < this.consultas.length; i++){
            if (this.consultas[i] != null && this.consultas[i].paciente.getCpf().equals(cpfPaciente)){
                consulta = this.consultas[i];
            }
        }

        return consulta;
    }

    @Override
    public boolean removerConsulta(ConsultaGinecologica consulta){
        boolean removido = false;

        for (int i = 0; i < this.consultas.length; i++){
            if(this.consultas[i].equals(consulta)){
                this.consultas[i] = null;
                removido = true;
                break;
            }
        }

        return removido;
    }

    @Override
    public boolean remarcarConsulta(ConsultaGinecologica consulta,LocalDateTime novaDataEHorario){
        boolean remarcado = false;
        LocalDateTime dataAntiga = null;

        for (int i = 0; i < this.consultas.length; i++){
            if (this.consultas[i] != null && this.consultas[i].equals(consulta)){
                dataAntiga = this.consultas[i].dataEHoraConsulta;
                this.consultas[i].dataEHoraConsulta = novaDataEHorario;
                remarcado = true;
                adicionarDatasDisponiveis(dataAntiga);
                removerDatasDisponiveis(novaDataEHorario);
                break;
            }
        }

        return remarcado;
    }

    @Override
    public void mostrarConsultas(){

        for (int i = 0; i < this.consultas.length; i++){
            if(this.consultas[i] != null){
                System.out.println(this.consultas[i]);
            }
        }
    }

    @Override
    public void mostrarConsultasDoDia(){

        for (int i = 0; i < this.consultas.length; i++){
            if (this.consultas[i] != null && this.consultas[i].dataEHoraConsulta.toLocalDate().equals(LocalDate.now())){
                System.out.println(this.consultas[i]);
            }
        }
    }

    public String toString(){
        return super.toString();
    }

}
