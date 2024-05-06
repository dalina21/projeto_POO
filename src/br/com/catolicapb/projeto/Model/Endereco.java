package br.com.catolicapb.projeto.Model;

public class Endereco {

    public String cidade;
    public String estado;
    public int cep;

    public Endereco(
            String cidade,
            String estado,
            int cep
    ){
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public String toString(){

        return "\nEndereço:\nCidade: " + this.cidade +
                "\nEstado: " + this.estado +
                "\nCep: " + this.cep;

    }

}
