package br.com.catolicapb.projeto.Model;

import br.com.catolicapb.projeto.Enumeradores.EnumAluguelSala;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

abstract public class Medico extends Pessoa {

    public String crm;
    public LocalDateTime[] datasDisponiveis;
    public double valorAluguelDaSala;
    public int limiteDeConsultasDiarias;
    public double valorConsulta; //valor cobrado por consulta
    public String senha;

    public Medico(
            String nome,
            String sexo,
            String cpf,
            LocalDate dataDeNascimento,
            Endereco endereco,
            String telefone,
            String senha,
            String crm,
            int limiteDeConsultasDiarias,
            double valorConsulta
    ){
        super(nome, sexo, cpf, dataDeNascimento, endereco, telefone);
        this.senha = senha;
        this.crm = crm;
        this.valorAluguelDaSala = calcularValorDoAluguel();
        this.limiteDeConsultasDiarias = limiteDeConsultasDiarias;
        this.datasDisponiveis = new LocalDateTime[24 * this.limiteDeConsultasDiarias]; //Considerando 6 dias uteis em 4 semanas
        this.valorConsulta = valorConsulta;
    }

    public boolean fazerLogin(String crm, String senha){
        boolean logado = false;

        if (this.crm.equals(crm) && this.senha.equals(senha)){
            logado = true;
        }

        return logado;
    }


    public double calcularValorDoAluguel(){

        return EnumAluguelSala.SALA_DE_CONSULTA.valor + EnumAluguelSala.SALA_DE_PROCEDIMENTO.valor;

    }

    public boolean adicionarDatasDisponiveis(LocalDateTime dataEHora){

        boolean adicionado = false;
        boolean podeAdicionar = true;

        for (int i = 0; i < this.datasDisponiveis.length; i++){
            if (this.datasDisponiveis[i] != null && this.datasDisponiveis[i].getHour() == dataEHora.getHour()){
                podeAdicionar = false;
            }
        }

        if (podeAdicionar){
            if (dataEHora.getDayOfMonth() >= LocalDate.now().getDayOfMonth() && dataEHora.getMonthValue() >= LocalDate.now().getMonthValue() && dataEHora.getYear() >= LocalDate.now().getYear()){
                for (int i = 0; i < this.datasDisponiveis.length; i++){
                    if (this.datasDisponiveis[i] == null){
                        this.datasDisponiveis[i] = dataEHora;
                        adicionado = true;
                        break;
                    }
                }
            }
        }

        return adicionado;
    }

    public boolean removerDatasDisponiveis(LocalDateTime data){

        boolean removido = false;

        for (int i = 0; i < this.datasDisponiveis.length; i++){

            if (this.datasDisponiveis[i] != null && this.datasDisponiveis[i].equals(data)){
                this.datasDisponiveis[i] = null;
                removido = true;
            }
        }

        return removido;
    }

    public boolean verificarDta(LocalDate data){
        boolean disponivel = false;

        for (int i = 0; i < this.datasDisponiveis.length; i++){
            if (this.datasDisponiveis[i] != null && this.datasDisponiveis[i].toLocalDate().equals(data)){
                disponivel = true;
            }
        }

        return disponivel;
    }

    public boolean verificarHora(LocalTime hora){
        boolean disponivel = false;

        for (int i = 0; i < this.datasDisponiveis.length; i++){
            if (this.datasDisponiveis[i] != null && this.datasDisponiveis[i].toLocalTime().equals(hora)){
                disponivel = true;
            }
        }

        return disponivel;
    }

    public void vizualizarHorariosDisponiveis(LocalDate data){

        for (int i = 0; i < this.datasDisponiveis.length; i++){
            if (this.datasDisponiveis[i] != null && this.datasDisponiveis[i].toLocalDate().equals(data)){
                System.out.printf("\n%s- %s:%s\n",i, this.datasDisponiveis[i].getHour(), this.datasDisponiveis[i].getMinute());
            }
        }

    }

    public void vizualizarDatasDisponiveis(){
        for (int i = 0; i < this.datasDisponiveis.length; i++){
            if (this.datasDisponiveis[i] != null){
                System.out.printf("\n%s- %s\n",i, this.datasDisponiveis[i].toLocalDate());
            }
        }
    }

    public String toString(){

        return "\n\tInformações do Médico(a)\n" + super.toString() +
                "\n\nCRM: " + this.crm +
                "\nValor da Consulta: R$" + this.valorConsulta;

    }
}
