package br.com.catolicapb.projeto.Model;

import br.com.catolicapb.projeto.Enumeradores.EnumStatusConsulta;

import java.time.LocalDateTime;

abstract public class Consulta {

    public LocalDateTime dataEHoraConsulta;
    public double valorDaConsulta; // Consulta + procedimento - desconto
    public EnumStatusConsulta statusDaConsulta;

    public Consulta(
            LocalDateTime dataDaConsulta,
            EnumStatusConsulta statusDaConsulta
    ){
        this.dataEHoraConsulta = dataDaConsulta;
        this.statusDaConsulta = statusDaConsulta;
    }

    public void atualizarStatusConsulta(EnumStatusConsulta novoStatus){
        this.statusDaConsulta = novoStatus;
    }

    public String toString(){

        return "\nData da consulta: " + this.dataEHoraConsulta.toLocalDate() +
                "\nHorário marcado: " + this.dataEHoraConsulta.toLocalTime() +
                "\nStatus da consulta: " + this.statusDaConsulta;

    }

}
