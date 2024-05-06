package br.com.catolicapb.projeto.Enumeradores;

public enum EnumProcedimentosGineco {

    NENHUM(0),
    PAPANICOLAU(180.90),
    HISTEROCOPSIA(300),
    COLPOSCOPIA(250.80),
    ULTRASSONOGRAFIA_PELVICA(250.80),
    ULTRASSONOGRAFIA_MAMARIA(180),
    ULTRASSOM_TRANSVAGINAL(100.50);
    public double valor;

    EnumProcedimentosGineco(double valor){
        this.valor = valor;
    }

}
