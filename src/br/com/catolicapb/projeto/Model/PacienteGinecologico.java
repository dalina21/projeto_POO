package br.com.catolicapb.projeto.Model;

import java.time.LocalDate;

public class PacienteGinecologico extends Paciente {

    public PacienteGinecologico(
            String nome,
            String sexo,
            String cpf,
            LocalDate dataDeNascimento,
            Endereco endereco,
            String telefone,
            boolean possuiCartaoVIP
    ){
        super(nome, sexo, cpf, dataDeNascimento, endereco, telefone, possuiCartaoVIP);
    }

    public String toString(){
        return super.toString();
    }

}
