package br.com.catolicapb.projeto.Model;

import br.com.catolicapb.projeto.Enumeradores.EnumProcedimentosDermato;
import br.com.catolicapb.projeto.Enumeradores.EnumStatusConsulta;

import java.time.LocalDateTime;

public class ConsultaDermatologica extends Consulta {

    public PacienteDermatologico paciente;
    public MedicoDermatologista medicoResponsavel;
    public EnumProcedimentosDermato procedimento;

    public ConsultaDermatologica(
            LocalDateTime dataDaConsulta,
            EnumStatusConsulta statusDaConsulta,
            MedicoDermatologista medicoResponsavel,
            EnumProcedimentosDermato procedimento,
            PacienteDermatologico paciente
    ){
        super(dataDaConsulta, statusDaConsulta);
        this.medicoResponsavel = medicoResponsavel;
        this.procedimento = procedimento;
        this.paciente = paciente;
        this.valorDaConsulta = calcularValorDaConsulta();
    }

    public double calcularValorDaConsulta(){
        double valorDsconto = 0;

        this.valorDaConsulta += this.procedimento.valor;

        if (this.paciente.possuiCartaoVIP){
            valorDsconto = this.valorDaConsulta * 0.15;
        }

        return this.valorDaConsulta - valorDsconto;

    }

    public String toString(){

        return "\n=> Paciente para dermatologista\n" + this.paciente.toString() +
                "\nProcedimento a ser realizado: " + this.procedimento +
                "\nMédico(a) responsável: " + this.medicoResponsavel.nome +
                "\nCrm do médico(a): " + this.medicoResponsavel.crm +
                "\n\n=> Valor da consulta: " + this.valorDaConsulta +
                "\nStatus da consulta: " + this.statusDaConsulta;

    }

}
