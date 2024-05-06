package br.com.catolicapb.projeto.Enumeradores;

public enum EnumProcedimentosDermato {

    NENHUM(0),
    ACTINOTERAPIA(15.14), //valor por seção
    APLICACAO_DE_TOXINA_BOTULINICA(2400), //botox
    BIOPSIA_DE_LESOES_DERMATOLOGICAS(330),
    CRIOCAUTERIZACAO_DE_LESOES_DERMATOLOGICAS(330),
    ELETROCAUTERIZACAO_DE_LESOES_CUTANEAS(350),
    ESFOLIACAO_QUIMICA_SUPERFICIAL(500.80);

    public double valor;

    EnumProcedimentosDermato(double valor){
        this.valor = valor;
    }

}
