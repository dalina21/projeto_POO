package br.com.catolicapb.projeto.Enumeradores;

public enum EnumAluguelSala {

    SALA_DE_CONSULTA(1000.60),
    SALA_DE_PROCEDIMENTO(900);

    public double valor;

    EnumAluguelSala(double valor){
        this.valor = valor;
    }

}
