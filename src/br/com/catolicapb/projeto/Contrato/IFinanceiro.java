package br.com.catolicapb.projeto.Contrato;

import br.com.catolicapb.projeto.Model.MedicoDermatologista;
import br.com.catolicapb.projeto.Model.MedicoGinecologista;

public interface IFinanceiro {

    void gerarRelatorioDoDiaDermato(MedicoDermatologista medico);
    void gerarRelatorioDoDiaGineco(MedicoGinecologista medico);

}
