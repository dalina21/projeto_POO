package br.com.catolicapb.projeto.Model;

import java.time.LocalDate;

abstract public class Paciente extends Pessoa{

    public boolean possuiCartaoVIP;

    public Paciente(
            String nome,
            String sexo,
            String cpf,
            LocalDate dataDeNascimento,
            Endereco endereco,
            String telefone,
            boolean possuiCartaoVIP
    ){
        super(nome, sexo, cpf, dataDeNascimento, endereco, telefone);
        this.possuiCartaoVIP = possuiCartaoVIP;
    }

    public String toString(){

        return "\n\tPaciente\n" + super.toString() +
                "\nPossui cartão VIP: " + this.possuiCartaoVIP;

    }

}


