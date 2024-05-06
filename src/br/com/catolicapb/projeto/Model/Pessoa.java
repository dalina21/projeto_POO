package br.com.catolicapb.projeto.Model;

import java.time.LocalDate;

abstract public class Pessoa {

    public String nome;
    public String sexo;
    private String cpf;
    public LocalDate dataDeNascimento;
    public Endereco endereco;
    public String telefone;

    public Pessoa(
            String nome,
            String sexo,
            String cpf,
            LocalDate dataDeNascimento,
            Endereco endereco,
            String telefone
    ){
        this.nome = nome;
        this.sexo = sexo;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public int calcularIdade(){

        int idade = LocalDate.now().getYear() - dataDeNascimento.getYear();

        if (dataDeNascimento.getDayOfMonth() > LocalDate.now().getDayOfMonth() ||  dataDeNascimento.getMonthValue() > LocalDate.now().getMonthValue()){
            idade -= 1;
        }

        return idade;

    }

    public String toString(){
        return "\nNome: " + this.nome +
                "\nCPF: " + this.cpf +
                "\nSexo: " + this.sexo +
                "\nData de Nascimento: " + this.dataDeNascimento.getDayOfMonth()+ "/" + this.dataDeNascimento.getMonthValue() + "/" + this.dataDeNascimento.getYear() +
                "\nIdade: " + calcularIdade() +
                "\nTelefone: " + this.telefone +
                "\n" + this.endereco;
    }
}
