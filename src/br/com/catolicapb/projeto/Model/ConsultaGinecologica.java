package br.com.catolicapb.projeto.Model;

import br.com.catolicapb.projeto.Enumeradores.EnumProcedimentosDermato;
import br.com.catolicapb.projeto.Enumeradores.EnumProcedimentosGineco;
import br.com.catolicapb.projeto.Enumeradores.EnumStatusConsulta;

import java.time.LocalDateTime;

public class ConsultaGinecologica extends Consulta{

    public PacienteGinecologico paciente;
    public MedicoGinecologista medicoResponsavel;
    public EnumProcedimentosGineco procedimento;

    public ConsultaGinecologica(
            LocalDateTime dataDaConsulta,
            EnumStatusConsulta statusDaConsulta,
            MedicoGinecologista medicoResponsavel,
            EnumProcedimentosGineco procedimento,
            PacienteGinecologico paciente
    ){
        super(dataDaConsulta, statusDaConsulta);
        this.medicoResponsavel = medicoResponsavel;
        this.procedimento = procedimento;
        this.paciente = paciente;
        this.valorDaConsulta = calcularValorDaConsulta();
    }

    public double calcularValorDaConsulta(){
        double valorDsconto = 0;
        double valorTotal = this.medicoResponsavel.valorConsulta + this.procedimento.valor;

        if (this.paciente.possuiCartaoVIP){
            valorDsconto = valorTotal * 0.15;
        }

        return valorTotal - valorDsconto;

    }

    public String toString(){

        return "\n=> Paciente para ginecologista\n" + this.paciente.toString() +
                "\nProcedimento a ser realizado: " + this.procedimento +
                "\nMédico(a) responsável: " + this.medicoResponsavel.nome +
                "\nCrm do médico(a): " + this.medicoResponsavel.crm +
                "\n\n=> Valor da consulta: " + this.valorDaConsulta +
                "\nStatus da consulta: " + this.statusDaConsulta;
    }

}
