package br.com.catolicapb.projeto.Enumeradores;

public enum EnumStatusConsulta {

    AGENDADA(1),
    CANCELADA(3),
    REALIZADA(4);

    public int opcao;

    EnumStatusConsulta(int opcao){
        this.opcao = opcao;
    }

}
