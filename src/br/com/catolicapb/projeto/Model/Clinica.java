package br.com.catolicapb.projeto.Model;

import br.com.catolicapb.projeto.Contrato.IFinanceiro;
import br.com.catolicapb.projeto.Contrato.ISistema;
import br.com.catolicapb.projeto.Enumeradores.EnumStatusConsulta;

import java.time.LocalDate;

public class Clinica implements IFinanceiro, ISistema {

    public String nomeDaClinica;
    public Endereco endereco;
    public MedicoDermatologista[] medicosDermatologistasCadastrados;
    public MedicoGinecologista[] medicosGinecologistasCadastrados;
    public PacienteDermatologico[] pacientesCadastradosDermato;
    public PacienteGinecologico[] pacientesCadastradosGineco;

    public Clinica(
            String nomeDaClinica,
            Endereco endereco
    ){
        this.nomeDaClinica = nomeDaClinica;
        this.endereco = endereco;
        this.medicosDermatologistasCadastrados = new MedicoDermatologista[8];
        this.medicosGinecologistasCadastrados = new MedicoGinecologista[8];
        this.pacientesCadastradosDermato = new PacienteDermatologico[500];
        this.pacientesCadastradosGineco = new PacienteGinecologico[500];
    }

    @Override
    public boolean verificarCpfIguais(String cpf){
        boolean cpfEncontrado = false;

        for (int i = 0; i < this.medicosDermatologistasCadastrados.length; i++){
            if (this.medicosDermatologistasCadastrados[i] != null && this.medicosDermatologistasCadastrados[i].getCpf().equals(cpf)){
                cpfEncontrado = true;
                break;
            }
        }

        for (int i = 0; i < this.medicosGinecologistasCadastrados.length; i++){
            if (this.medicosGinecologistasCadastrados[i] != null && this.medicosGinecologistasCadastrados[i].getCpf().equals(cpf)){
                cpfEncontrado = true;
            }
        }

        return cpfEncontrado;
    }

    @Override
    public void gerarRelatorioDoDiaDermato(MedicoDermatologista medico){
        int numeroDeConsultas = 0;
        int canceladas = 0;
        double valorDasConsultas = 0;
        double valorProcedimentos = 0;
        double valorDosDescontos = 0;

        for (int i = 0; i < this.medicosDermatologistasCadastrados.length; i++){
            if (this.medicosDermatologistasCadastrados[i] != null && this.medicosDermatologistasCadastrados[i].equals(medico)){

                for (int j = 0; j < this.medicosDermatologistasCadastrados[i].consultas.length; j++){
                    if (this.medicosDermatologistasCadastrados[i].consultas[j] != null && this.medicosDermatologistasCadastrados[i].consultas[j].dataEHoraConsulta.toLocalDate().equals(LocalDate.now())){
                        if(this.medicosDermatologistasCadastrados[i].consultas[j].statusDaConsulta.equals(EnumStatusConsulta.REALIZADA)){
                            numeroDeConsultas += 1;
                            valorProcedimentos += this.medicosDermatologistasCadastrados[i].consultas[j].procedimento.valor;
                            valorDasConsultas += this.medicosDermatologistasCadastrados[i].consultas[j].medicoResponsavel.valorConsulta;
                            valorDosDescontos += (this.medicosDermatologistasCadastrados[i].consultas[j].procedimento.valor + this.medicosDermatologistasCadastrados[i].consultas[j].medicoResponsavel.valorConsulta) - this.medicosDermatologistasCadastrados[i].consultas[j].valorDaConsulta;

                        } if (this.medicosDermatologistasCadastrados[i].consultas[j].statusDaConsulta.equals(EnumStatusConsulta.CANCELADA)){
                            canceladas += 1;
                        }
                    }
                }
            }
        }

        double valorTotal = (valorDasConsultas + valorProcedimentos) - valorDosDescontos;

        System.out.printf("\n\t\tRELATÓRIO DO DIA\n\n" +
                "=> Número de consultas realizadas: %d\n" +
                "=> Número de consultas canceladas: %d\n" +
                "\n=> Valor total das consultas (Não considera os descontos): R$%.2f\n" +
                "=> Valor total dos procedimentos (Não considera os descontos): R$%.2f\n" +
                "=> Descontos do Cartão VIP: R$%.2f\n" +
                "\n=> Valor total (Procedimentos + Consultas - Descontos): R$%.2f\n", numeroDeConsultas, canceladas, valorDasConsultas, valorProcedimentos, valorDosDescontos, valorTotal);

    }

    @Override
    public void gerarRelatorioDoDiaGineco(MedicoGinecologista medico){
        int numeroDeConsultas = 0;
        int canceladas = 0;
        double valorDasConsultas = 0;
        double valorProcedimentos = 0;
        double valorDosDescontos = 0;

        for (int i = 0; i < this.medicosGinecologistasCadastrados.length; i++){
            if (this.medicosGinecologistasCadastrados[i] != null && this.medicosGinecologistasCadastrados[i].equals(medico)){

                for (int j = 0; j < this.medicosGinecologistasCadastrados[i].consultas.length; j++){
                    if (this.medicosGinecologistasCadastrados[i].consultas[j] != null && this.medicosGinecologistasCadastrados[i].consultas[j].dataEHoraConsulta.toLocalDate().equals(LocalDate.now())){
                        if(this.medicosGinecologistasCadastrados[i].consultas[j].statusDaConsulta.equals(EnumStatusConsulta.REALIZADA)){
                            numeroDeConsultas += 1;
                            valorProcedimentos += this.medicosGinecologistasCadastrados[i].consultas[j].procedimento.valor;
                            valorDasConsultas += this.medicosGinecologistasCadastrados[i].consultas[j].medicoResponsavel.valorConsulta;
                            valorDosDescontos += (this.medicosGinecologistasCadastrados[i].consultas[j].procedimento.valor + this.medicosGinecologistasCadastrados[i].consultas[j].medicoResponsavel.valorConsulta) - this.medicosGinecologistasCadastrados[i].consultas[j].valorDaConsulta;

                        } if (this.medicosGinecologistasCadastrados[i].consultas[j].statusDaConsulta.equals(EnumStatusConsulta.CANCELADA)){
                            canceladas += 1;
                        }
                    }
                }
            }
        }

        double valorTotal = valorDasConsultas + valorProcedimentos - valorDosDescontos;
        System.out.printf("\n\t\tRELATÓRIO DO DIA\n\n" +
                "=> Número de consultas realizadas: %d\n" +
                "=> Número de consultas canceladas: %d\n" +
                "\n=> Valor total das consultas (Não considera os descontos): R$%.2f\n" +
                "=> Valor total dos procedimentos (Não considera os descontos): R$%.2f\n" +
                "=> Descontos do Cartão VIP: R$%.2f\n" +
                "\n=> Valor total (Procedimentos + Consultas - Descontos): R$%.2f\n", numeroDeConsultas, canceladas, valorDasConsultas, valorProcedimentos, valorDosDescontos, valorTotal);

    }

    @Override
    public boolean adicionarMedicoDermatoNoSistema(MedicoDermatologista dermato){
        boolean adicionado = false;

        for (int i = 0; i < this.medicosDermatologistasCadastrados.length; i++){
            if (this.medicosDermatologistasCadastrados[i] == null){
                this.medicosDermatologistasCadastrados[i] = dermato;
                adicionado = true;
                break;
            }
        }

        return adicionado;
    }

    @Override
    public boolean adicionarMedicoGinecoNoSistema(MedicoGinecologista gineco){
        boolean adicionado = false;

        for (int i = 0; i < this.medicosGinecologistasCadastrados.length; i++){
            if (this.medicosGinecologistasCadastrados[i] == null){
                this.medicosGinecologistasCadastrados[i] = gineco;
                adicionado = true;
                break;
            }
        }

        return adicionado;
    }

    @Override
    public MedicoDermatologista pesquisarMedicoDermatoNoSistema(String crm){
        MedicoDermatologista medico = null;

        for (int i = 0; i < this.medicosDermatologistasCadastrados.length; i++){
            if (this.medicosDermatologistasCadastrados[i] != null && this.medicosDermatologistasCadastrados[i].crm.equals(crm)){
                medico = this.medicosDermatologistasCadastrados[i];
                break;
            }
        }

        return medico;
    }

    @Override
    public MedicoGinecologista pesquisarMedicoGinecoNoSistema(String crm){
        MedicoGinecologista medico = null;

        for (int i = 0; i < this.medicosGinecologistasCadastrados.length; i++){
            if (this.medicosGinecologistasCadastrados[i] != null && this.medicosGinecologistasCadastrados[i].crm.equals(crm)){
                medico = this.medicosGinecologistasCadastrados[i];
                break;
            }
        }

        return medico;
    }

    @Override
    public boolean adicionarPacienteDermatoNoSistema(PacienteDermatologico paciente){
        boolean adicionado = false;

        for (int i = 0; i < this.pacientesCadastradosDermato.length; i++){
            if (this.pacientesCadastradosDermato[i] == null){
                this.pacientesCadastradosDermato[i] = paciente;
                adicionado = true;
                break;
            }
        }

        return adicionado;
    }

    @Override
    public boolean adicionarPacienteGinecoDoSistema(PacienteGinecologico paciente){
        boolean adicionado = false;

        for (int i = 0; i < this.pacientesCadastradosGineco.length; i++){
            if (this.pacientesCadastradosGineco[i] == null){
                this.pacientesCadastradosGineco[i] = paciente;
                adicionado = true;
                break;
            }
        }

        return adicionado;
    }

    @Override
    public PacienteDermatologico pesquisarPacienteDermatoNoSistema(String cpf){
        PacienteDermatologico paciente = null;

        for (int i = 0; i < this.pacientesCadastradosDermato.length; i++){
            if (this.pacientesCadastradosDermato[i] != null && this.pacientesCadastradosDermato[i].getCpf().equals(cpf)){
                paciente = this.pacientesCadastradosDermato[i];
                break;
            }
        }

        return paciente;
    }

    @Override
    public PacienteGinecologico pesquisarPacienteGinecoNoSistema(String cpf){
        PacienteGinecologico paciente = null;

        for (int i = 0; i < this.pacientesCadastradosGineco.length; i++){
            if (this.pacientesCadastradosGineco[i] != null && this.pacientesCadastradosGineco[i].getCpf().equals(cpf)){
                paciente = this.pacientesCadastradosGineco[i];
                break;
            }
        }

        return paciente;
    }

    public String toString(){

        return "\n\t\tNome da clínica: " + this.nomeDaClinica + endereco.toString();

    }
}
