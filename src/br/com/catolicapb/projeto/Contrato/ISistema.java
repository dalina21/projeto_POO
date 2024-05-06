package br.com.catolicapb.projeto.Contrato;

import br.com.catolicapb.projeto.Model.*;

import java.time.LocalDate;

public interface ISistema {

    boolean verificarCpfIguais(String cpf);

    boolean adicionarMedicoDermatoNoSistema(MedicoDermatologista dermato);

    boolean adicionarMedicoGinecoNoSistema(MedicoGinecologista gineco);

    MedicoDermatologista pesquisarMedicoDermatoNoSistema(String crm);

    MedicoGinecologista pesquisarMedicoGinecoNoSistema(String crm);

    boolean adicionarPacienteDermatoNoSistema(PacienteDermatologico paciente);

    boolean adicionarPacienteGinecoDoSistema(PacienteGinecologico paciente);

    PacienteDermatologico pesquisarPacienteDermatoNoSistema(String cpf);

    PacienteGinecologico pesquisarPacienteGinecoNoSistema(String cpf);

}
