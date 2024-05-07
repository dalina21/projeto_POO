package br.com.catolicapb.projeto.Main;

import br.com.catolicapb.projeto.Enumeradores.EnumProcedimentosDermato;
import br.com.catolicapb.projeto.Enumeradores.EnumProcedimentosGineco;
import br.com.catolicapb.projeto.Enumeradores.EnumStatusConsulta;
import br.com.catolicapb.projeto.Model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Clinica clinica = new Clinica("Clínica Bem Estar", new Endereco("Ipaumirim", "CE", 63340000));
        Scanner scanner = new Scanner(System.in);

        while (true){

            System.out.printf("\n\t%s\n\n" +
                    "1- ACESSO MÉDICO(A)\n" +
                    "2- AGENDAR CONSULTA\n" +
                    "0- ENCERRAR PROGRAMA\n", clinica.nomeDaClinica);

            int opcao1 = scanner.nextInt();

            switch (opcao1) {
                case 0:
                    break;
                case 1:
                    System.out.println("\n=> Escolha uma das opções abaixo:\n" +
                            "\n1 - CADASTRAR MÉDICO(A)\n" +
                                    "2 - MÉDICO CADASTRADO"
                    );
                    int opcao2 = scanner.nextInt();

                    switch (opcao2) {
                        case 1:
                            System.out.print(
                                    "\n\tCadastrar Médico(a)\n" +
                                            "\nDigite seu nome: "
                            );
                            scanner = new Scanner(System.in);
                            String nome = scanner.nextLine();

                            System.out.println(
                                    "\nNos informe seu sexo:\n" +
                                            "1 - Feminino\n" +
                                            "2 - Masculino\n" +
                                            "3 - Outro"
                            );
                            int respostaSexo = scanner.nextInt();
                            String sexo = "";
                            switch (respostaSexo) {
                                case 1:
                                    sexo = "Feminino";
                                    break;
                                case 2:
                                    sexo = "Masculino";
                                    break;
                                case 3:
                                    sexo = "Outro";
                                    break;
                                default:
                                    System.out.println("\nResposta Inválida!");
                                    continue;
                            }

                            System.out.print("\nDigite seu cpf (Apenas digítos, sem pontos ou traços): ");
                            scanner = new Scanner(System.in);
                            String cpf = scanner.nextLine();

                            if (cpf.length() != 11){
                                System.out.println("\nÉ preciso 11 digítos no campo do CPF!");
                                continue;
                            }

                            boolean cpfIguais = clinica.verificarCpfIguais(cpf);
                            if (cpfIguais){
                                System.out.println("\nJá exixte uma pessoa cadastrada com este CPF no sistema!");
                                continue;
                            } else {
                                System.out.println("Nos informe sua data de nascimento: ");
                                System.out.print("Dia: ");
                                int dia = scanner.nextInt();

                                if (dia < 1 || dia > 31){
                                    System.out.println("\nInformação Inválida");
                                    continue;
                                }

                                System.out.print("Mês: ");
                                int mes = scanner.nextInt();
                                if(mes < 1 || dia > 12){
                                    System.out.println("\nInformação Inválida");
                                    continue;
                                }

                                System.out.print("Ano: ");
                                int ano = scanner.nextInt();
                                if (ano < 1 || ano > LocalDate.now().getYear()){
                                    System.out.println("\nInformação Inválida");
                                    continue;
                                }

                                LocalDate data = LocalDate.of(ano, mes, dia);

                                System.out.print("\nInforma a cidade que mora: ");
                                scanner = new Scanner(System.in);
                                String cidade = scanner.nextLine();

                                System.out.print("O estado: ");
                                String estado = scanner.next();

                                System.out.print("Informe o cep: ");
                                int cep = scanner.nextInt();

                                System.out.print("Seu telefone: ");
                                scanner = new Scanner(System.in);
                                String telefone = scanner.nextLine();

                                System.out.print("Informe seu crm: ");
                                scanner = new Scanner(System.in);
                                String crm = scanner.nextLine();

                                MedicoDermatologista verificar = clinica.pesquisarMedicoDermatoNoSistema(crm);
                                MedicoGinecologista verificar2 = clinica.pesquisarMedicoGinecoNoSistema(crm);

                                if (verificar != null || verificar2 != null){
                                    System.out.println("\nJá existe um médico com este crm cadastrado");
                                    continue;
                                } else {

                                    System.out.print("\nInforme o limite de consultas que pode ter no dia: ");
                                    int limite = scanner.nextInt();

                                    System.out.print("Informe quanto você cobra por consulta: ");
                                    double valorConsulta = scanner.nextDouble();

                                    System.out.println(
                                            "\n=> Deseja fazer seu cadastro como:\n\n" +
                                                    "1- DERMATOLOGISTA\n" +
                                                    "2- GINECOLOGISTA"
                                    );
                                    int especialidade = scanner.nextInt();

                                    System.out.print("\nInforme uma senha (mínimo 5 digitos): ");
                                    String senha = scanner.next();
                                    if (senha.length() < 5) {
                                        System.out.println("\nInforme uma senha com no mínimo 5 gigitos!");
                                        continue;
                                    }

                                    switch (especialidade) {
                                        case 1:
                                            MedicoDermatologista medicoCadastrado = new MedicoDermatologista(nome, sexo, cpf, data, new Endereco(cidade, estado, cep), telefone, senha, crm, limite, valorConsulta);
                                            boolean concluido = clinica.adicionarMedicoDermatoNoSistema(medicoCadastrado);
                                            if (concluido) {
                                                System.out.println(medicoCadastrado);
                                                System.out.println("\nCadastro efetuado com sucesso!");
                                            } else {
                                                System.out.println("\nOcorreu um erro, limite de médicos atingido!");
                                                continue;
                                            }
                                            break;
                                        case 2:
                                            MedicoGinecologista medicoCadastrado2 = new MedicoGinecologista(nome, sexo, cpf, data, new Endereco(cidade, estado, cep), telefone, senha, crm, limite, valorConsulta);
                                            boolean concluido2 = clinica.adicionarMedicoGinecoNoSistema(medicoCadastrado2);
                                            if (concluido2) {
                                                System.out.println(medicoCadastrado2);
                                                System.out.println("\nCadastro efetuado com sucesso!");
                                            } else {
                                                System.out.println("\nOcorreu um erro, limite de médicos atingido!");
                                                continue;
                                            }
                                            break;
                                        default:
                                            System.out.println("\nInformação Inválida!");
                                            continue;
                                    }
                                }

                            }
                            break;

                        case 2:
                            System.out.println("\n=> Entrar como: " +
                                    "\n1- DERMATOLOGISTA\n" +
                                    "2- GINECOLOGISTA");
                            int respostaLogin = scanner.nextInt();

                            System.out.print("\nInforme seu nome: ");
                            scanner = new Scanner(System.in);
                            String nomeLogin = scanner.nextLine();
                            System.out.print("Informe seu crm: ");
                            String crmLogin = scanner.nextLine();
                            System.out.print("Informe sua senha: ");
                            String senhaLogin = scanner.next();

                            while (true){
                                switch (respostaLogin){
                                    case 1:
                                        MedicoDermatologista medico = clinica.pesquisarMedicoDermatoNoSistema(crmLogin);
                                        if (medico != null){
                                            boolean login = medico.fazerLogin(crmLogin, senhaLogin);
                                            if(login){
                                                while (true){
                                                    System.out.println("\n=> Escolha uma das opções abaixo:" +
                                                            "\n1- VIZUALIZAR CONSULTAS DO DIA" +
                                                            "\n2- PREENCHER DATAS" +
                                                            "\n3- REALIZAR CONSULTAS" +
                                                            "\n4- REMOVER CONSULTAS" +
                                                            "\n5- GERAR RELATÓRIO DO DIA" +
                                                            "\n6- VIZUALIZAR TODAS AS CONSULTAS DO MÊS" +
                                                            "\n0- SAIR");
                                                    int resposta = scanner.nextInt();

                                                    switch (resposta){
                                                        case 0:
                                                            break;
                                                        case 1:
                                                            System.out.println("\n\tVizualizar Consultas do dia");
                                                            medico.mostrarConsultasDoDia();
                                                            break;
                                                        case 2:
                                                            System.out.println("\n\tPreencher Datas");
                                                            System.out.println("Informe a data: ");
                                                            System.out.print("Dia: ");
                                                            int dia2 = scanner.nextInt();

                                                            if (dia2 < 1 || dia2 > 31){
                                                                System.out.println("\nInformação Inválida");
                                                                continue;
                                                            }

                                                            System.out.print("Mês: ");
                                                            int mes2 = scanner.nextInt();
                                                            if(mes2 < 1 || mes2 > 12){
                                                                System.out.println("\nInformação Inválida");
                                                                continue;
                                                            }

                                                            System.out.print("Ano: ");
                                                            int ano2 = scanner.nextInt();
                                                            if (ano2 < 1 || ano2 > LocalDate.now().getYear()){
                                                                System.out.println("\nInformação Inválida");
                                                                continue;
                                                            }

                                                            System.out.println("Hora: ");
                                                            int hora = scanner.nextInt();

                                                            System.out.println("Minuto: ");
                                                            int minuto = scanner.nextInt();

                                                            boolean concluido = medico.adicionarDatasDisponiveis(LocalDateTime.of(ano2, mes2, dia2, hora, minuto));
                                                            if (concluido){
                                                                System.out.println("Data adicionada com sucesso!");

                                                            } else {
                                                                System.out.println("Data não aceita!");
                                                                continue;
                                                            }
                                                            break;

                                                        case 3:
                                                            System.out.println("\n\tRealizar consultas\n");
                                                            System.out.print("Informe o CPF do paciente que será realizada a consulta: ");
                                                            String cpfConsulta = scanner.next();

                                                            ConsultaDermatologica realizarConsulta = medico.pesquisarConsulta(cpfConsulta);
                                                            if(realizarConsulta != null){
                                                                System.out.println(realizarConsulta);
                                                                realizarConsulta.atualizarStatusConsulta(EnumStatusConsulta.REALIZADA);
                                                                System.out.println("\nConsulta Realizada!");

                                                            } else {
                                                                System.out.println("\n=> Ocorreu um erro!\n" +
                                                                        "Verifique se o paciente está cadastrado no sistema ou se  a consulta está marcada para a data de hoje!");
                                                                continue;
                                                            }
                                                            break;
                                                        case 4:
                                                            System.out.println("\n=>Informe o CPF do paciente referente a consulta: ");
                                                            String cpfRemover = scanner.next();

                                                            ConsultaDermatologica consulta = medico.pesquisarConsulta(cpfRemover);

                                                            if (consulta != null){
                                                                boolean concluidoCancelar = medico.removerConsulta(consulta);
                                                                if (concluidoCancelar){
                                                                    System.out.println("\nConsulta Removida!");
                                                                } else {
                                                                    System.out.println("\nOcorreu um erro!");
                                                                    continue;
                                                                }
                                                            } else {
                                                                System.out.println("Paciente não encontrado!");
                                                                continue;
                                                            }
                                                            break;
                                                        case 5:
                                                            System.out.println("\nGerar Relatório do dia!");
                                                            clinica.gerarRelatorioDoDiaDermato(medico);
                                                            break;
                                                        case 6:
                                                            System.out.println("\n\tVizualizar todas as consultas do Mês\n");
                                                            medico.mostrarConsultas();
                                                            break;
                                                        default:
                                                            System.out.println("\nResposta Inválida!");
                                                            continue;
                                                    }
                                                    break;
                                                }
                                            } else {
                                                System.out.println("Não foi possível realizar o login!");
                                                break;
                                            }
                                        } else {
                                            System.out.println("Não há cadastro com esses dados no sistema!");
                                            break;
                                        }
                                        break;

                                    case 2:
                                        MedicoGinecologista medico2 = clinica.pesquisarMedicoGinecoNoSistema(crmLogin);
                                        if (medico2 != null){
                                            boolean login2 = medico2.fazerLogin(crmLogin, senhaLogin);
                                            if(login2){
                                                while (true){
                                                    System.out.println("\nEscolha uma das opções abaixo:" +
                                                            "\n1- VIZUALIZAR CONSULTAS" +
                                                            "\n2- PREENCHER DATAS" +
                                                            "\n3- REALIZAR CONSULTAS" +
                                                            "\n4- REMOVER CONSULTAS" +
                                                            "\n5- GERAR RELATÓRIO DO DIA" +
                                                            "\n6- VIZUALIZAR TODAS AS CONSULTAS DO MÊS" +
                                                            "\n0- SAIR\n");
                                                    int resposta = scanner.nextInt();

                                                    switch (resposta){
                                                        case 0:
                                                            break;
                                                        case 1:
                                                            System.out.println("\n\tVizualizar Consultas do dia");
                                                            medico2.mostrarConsultasDoDia();
                                                            break;
                                                        case 2:
                                                            System.out.println("\n\tPreencher Datas");
                                                            System.out.println("Informe a data: ");
                                                            System.out.print("Dia: ");
                                                            int dia2 = scanner.nextInt();

                                                            if (dia2 < 1 || dia2 > 31){
                                                                System.out.println("\nInformação Inválida");
                                                                continue;
                                                            }

                                                            System.out.print("Mês: ");
                                                            int mes2 = scanner.nextInt();
                                                            if(mes2 < 1 || mes2 > 12){
                                                                System.out.println("\nInformação Inválida");
                                                                continue;
                                                            }

                                                            System.out.print("Ano: ");
                                                            int ano2 = scanner.nextInt();
                                                            if (ano2 < 1 || ano2 > LocalDate.now().getYear()){
                                                                System.out.println("\nInformação Inválida");
                                                                continue;
                                                            }

                                                            System.out.println("Hora: ");
                                                            int hora = scanner.nextInt();

                                                            System.out.println("Minuto: ");
                                                            int minuto = scanner.nextInt();

                                                            boolean concluido = medico2.adicionarDatasDisponiveis(LocalDateTime.of(ano2, mes2, dia2, hora, minuto));
                                                            if (concluido){
                                                                System.out.println("Data adicionada com sucesso!");
                                                            } else {
                                                                System.out.println("Data não aceita!");
                                                                continue;
                                                            }
                                                            break;

                                                        case 3:
                                                            System.out.println("\n\tRealizar consultas\n");
                                                            System.out.print("Informe o CPF do paciente que será realizada a consulta: ");
                                                            String cpfConsulta2 = scanner.next();

                                                            ConsultaGinecologica realizarConsulta2 = medico2.pesquisarConsulta(cpfConsulta2);

                                                            if (realizarConsulta2 != null){
                                                                System.out.println(realizarConsulta2);
                                                                realizarConsulta2.atualizarStatusConsulta(EnumStatusConsulta.REALIZADA);
                                                                System.out.println("\nConsulta Realizada!");

                                                            } else {
                                                                System.out.println("\n=> Ocorreu um erro!\n" +
                                                                        "Verifique se o paciente está cadastrado no sistema ou se  a consulta está marcada para a data de hoje!");
                                                                continue;
                                                            }
                                                            break;

                                                        case 4:
                                                            System.out.println("\n=>Informe o CPF do paciente referente a consulta: ");
                                                            String cpfRemover = scanner.next();

                                                            ConsultaGinecologica consulta = medico2.pesquisarConsulta(cpfRemover);

                                                            if (consulta != null){
                                                                boolean concluidoRemarcar2 = medico2.removerConsulta(consulta);
                                                                if (concluidoRemarcar2){
                                                                    System.out.println("Consulta Removida!");
                                                                } else {
                                                                    System.out.println("\nOcorreu um erro!");
                                                                    continue;
                                                                }
                                                            } else {
                                                                System.out.println("Paciente não encontrado!");
                                                                continue;
                                                            }
                                                            break;

                                                        case 5:
                                                            System.out.println("\nGerar Relatório do dia!");
                                                            clinica.gerarRelatorioDoDiaGineco(medico2);
                                                            break;

                                                        case 6:
                                                            System.out.println("\t\nVizualizar todas as consultas do mês\n");
                                                            medico2.mostrarConsultas();
                                                            break;

                                                        default:
                                                            System.out.println("\nResposta Inválida!");
                                                            continue;
                                                    }
                                                    break;
                                                }
                                            } else {
                                                System.out.println("Não foi possível realizar o login!");
                                                break;
                                            }
                                        } else {
                                            System.out.println("Não há cadastro com esses dados no sistema!");
                                            break;
                                        }
                                        break;
                                    default:
                                        System.out.println("\nResposta Inválida!");
                                        continue;
                                }
                                break;

                            }
                            break;
                    }
                    break;

                case 2:
                    System.out.println("\tAgendar Consulta\n" +
                            "\n=> O paciente já possui cadastro no sistema?\n" +
                            "1- SIM\n" +
                            "2- NÃO\n");

                    int opcao1Agendamento = scanner.nextInt();
                    while (true){
                        switch (opcao1Agendamento){
                            case 1:
                                System.out.println("\n=> Escolha uma das opções:\n" +
                                        "1- AGENDAR CONSULTA\n" +
                                        "2- REMARCAR CONSULTA\n" +
                                        "3- CANCELAR CONSULTA\n" +
                                        "0- SAIR");

                                int opcao2Agendamento = scanner.nextInt();

                                switch (opcao2Agendamento){
                                    case 0:
                                        break;
                                    case 1:
                                        System.out.println("\n=> Deseja se consultar com:\n" +
                                                "1- DERMATOLOGISTA\n" +
                                                "2- GINECOLOGISTA\n");

                                        int opcao3Agendamento = scanner.nextInt();

                                        switch (opcao3Agendamento) {
                                            case 1:
                                                System.out.print("\n=> Informe o cpf do paciente para buscarmos seus dados no sistema: ");
                                                String cpf = scanner.next();
                                                PacienteDermatologico pacienteDermato = clinica.pesquisarPacienteDermatoNoSistema(cpf);

                                                if (pacienteDermato != null) {
                                                    System.out.println("Os dados do paciente foram localizados!");

                                                    System.out.print("\n=> Vamos precisar do nome e do CRM do médico para buscarmos no sistema\n" +
                                                            "Nome do medico(a): ");
                                                    scanner = new Scanner(System.in);
                                                    String nome = scanner.nextLine();
                                                    System.out.print("\nCRM: ");
                                                    scanner = new Scanner(System.in);
                                                    String crm = scanner.nextLine();

                                                    MedicoDermatologista medicoDermato = clinica.pesquisarMedicoDermatoNoSistema(crm);
                                                    if (medicoDermato != null) {
                                                        System.out.println(medicoDermato);
                                                        System.out.println("=> Concluir agendamento?\n" +
                                                                "1- SIM\n" +
                                                                "2- NÃO\n");
                                                        int concluir = scanner.nextInt();
                                                        switch (concluir) {
                                                            case 1:
                                                                System.out.println("=> Datas disponíveis: ");
                                                                medicoDermato.vizualizarDatasDisponiveis();
                                                                System.out.print("\nInforme a data escolhida: ");
                                                                int data = scanner.nextInt();
                                                                LocalDateTime dataEscolhida = medicoDermato.datasDisponiveis[data];

                                                                boolean concluidoVerificar = medicoDermato.verificarDta(dataEscolhida.toLocalDate());
                                                                if (concluidoVerificar){
                                                                    System.out.println("=> Horários dispiníveis para essa data: ");
                                                                    medicoDermato.vizualizarHorariosDisponiveis(dataEscolhida.toLocalDate());

                                                                    System.out.println("Informe o horário escolhido: ");
                                                                    int hora = scanner.nextInt();
                                                                    LocalDateTime horaEscolhida = medicoDermato.datasDisponiveis[hora];

                                                                    boolean verificarHora = medicoDermato.verificarHora(horaEscolhida.toLocalTime());
                                                                    if (verificarHora){
                                                                        System.out.println("=> Informe qual dos procedimentos abaixo será realizado:\n\n" +
                                                                                "1- ACTINOTERAPIA\n" +
                                                                                "2- APLICACAO DE TOXINA BOTULINICA\n" +
                                                                                "3- BIOPSIA DE LESOES DERMATOLOGICAS\n" +
                                                                                "4- CRIOCAUTERIZACAO DE LESOES DERMATOLOGICAS\n" +
                                                                                "5- ELETROCAUTERIZACAO DE LESOES CUTANEAS\n" +
                                                                                "6- ESFOLIACAO QUIMICA SUPERFICIAL\n" +
                                                                                "7- NENHUM");
                                                                        int procedimento = scanner.nextInt();
                                                                        EnumProcedimentosDermato procedimentoEscolhido = null;

                                                                        switch (procedimento) {
                                                                            case 1:
                                                                                procedimentoEscolhido = EnumProcedimentosDermato.ACTINOTERAPIA;
                                                                                break;
                                                                            case 2:
                                                                                procedimentoEscolhido = EnumProcedimentosDermato.APLICACAO_DE_TOXINA_BOTULINICA;
                                                                                break;
                                                                            case 3:
                                                                                procedimentoEscolhido = EnumProcedimentosDermato.BIOPSIA_DE_LESOES_DERMATOLOGICAS;
                                                                                break;
                                                                            case 4:
                                                                                procedimentoEscolhido = EnumProcedimentosDermato.CRIOCAUTERIZACAO_DE_LESOES_DERMATOLOGICAS;
                                                                                break;
                                                                            case 5:
                                                                                procedimentoEscolhido = EnumProcedimentosDermato.ELETROCAUTERIZACAO_DE_LESOES_CUTANEAS;
                                                                                break;
                                                                            case 6:
                                                                                procedimentoEscolhido = EnumProcedimentosDermato.ESFOLIACAO_QUIMICA_SUPERFICIAL;
                                                                                break;
                                                                            case 7:
                                                                                procedimentoEscolhido = EnumProcedimentosDermato.NENHUM;
                                                                                break;
                                                                            default:
                                                                                System.out.println("Informação Inválida!");
                                                                                continue;
                                                                        }

                                                                        ConsultaDermatologica consulta = new ConsultaDermatologica(dataEscolhida, EnumStatusConsulta.AGENDADA, medicoDermato, procedimentoEscolhido, pacienteDermato);
                                                                        boolean concluido = medicoDermato.adicionarConsulta(consulta);
                                                                        if (concluido){
                                                                            medicoDermato.removerDatasDisponiveis(dataEscolhida);
                                                                            System.out.println(consulta);
                                                                            System.out.println("Consulta agendada com sucesso!");
                                                                        } else {
                                                                            System.out.println("\nOcorreu um Erro!");
                                                                            continue;
                                                                        }

                                                                    } else {
                                                                        System.out.println("\n=> Este horário não se encontra disponível para esta data, forneça um dos horários mostrados acima!");
                                                                        continue;
                                                                    }

                                                                } else {
                                                                    System.out.println("\n=> Esta data não esta disponível, forneça uma datas mostradas na tela!");
                                                                    continue;
                                                                }
                                                                break;
                                                            case 2:
                                                                System.out.println("\nAgendamento cancelado!");
                                                                break;
                                                            default:
                                                                System.out.println("\nInformação Inválida!");
                                                                continue;
                                                        }
                                                    } else {
                                                        System.out.println("Médico(a) não encontado(a)!");
                                                    }
                                                } else {
                                                    System.out.println("\nNenhum paciente com esse CPF foi encontrado!");
                                                }
                                                break;

                                            case 2:
                                                System.out.print("\n=> Informe o cpf do paciente para buscarmos seus dados no sistema: ");
                                                String cpf2 = scanner.next();
                                                PacienteGinecologico pacienteGineco = clinica.pesquisarPacienteGinecoNoSistema(cpf2);

                                                if (pacienteGineco != null) {
                                                    System.out.println("Os dados do paciente foram localizados!");

                                                    System.out.print("\n=> Vamos precisar do nome e do CRM do médico para buscarmos no sistema\n" +
                                                            "Nome do medico(a): ");
                                                    scanner = new Scanner(System.in);
                                                    String nome2 = scanner.nextLine();
                                                    System.out.print("\nCRM: ");
                                                    scanner = new Scanner(System.in);
                                                    String crm2 = scanner.nextLine();

                                                    MedicoGinecologista medicoGineco = clinica.pesquisarMedicoGinecoNoSistema(crm2);
                                                    if (medicoGineco != null) {
                                                        System.out.println(medicoGineco);
                                                        System.out.println("=> Concluir agendamento?\n" +
                                                                "1- SIM\n" +
                                                                "2- NÃO\n");
                                                        int concluir = scanner.nextInt();
                                                        switch (concluir) {
                                                            case 1:
                                                                System.out.println("=> Datas disponíveis: ");
                                                                medicoGineco.vizualizarDatasDisponiveis();
                                                                System.out.print("\nInforme a data escolhida: ");
                                                                int dataEscolhida = scanner.nextInt();
                                                                LocalDateTime data = medicoGineco.datasDisponiveis[dataEscolhida];

                                                                boolean verificarDta = medicoGineco.verificarDta(data.toLocalDate());

                                                                if (verificarDta){
                                                                    System.out.println("=> Horários dispiníveis para essa data: ");
                                                                    medicoGineco.vizualizarHorariosDisponiveis(data.toLocalDate());
                                                                    System.out.println("Informe o horário escolhido: ");
                                                                    int horarioEscolhido = scanner.nextInt();
                                                                    LocalDateTime hora = medicoGineco.datasDisponiveis[horarioEscolhido];

                                                                    boolean verificarHora2 = medicoGineco.verificarHora(hora.toLocalTime());

                                                                    if (verificarHora2){
                                                                        System.out.println("=> Informe qual dos procedimentos abaixo será realizado:\n\n" +
                                                                                "1- PAPANICOLAU\n" +
                                                                                "2- HISTEROCOPSIA\n" +
                                                                                "3- COLPOSCOPIA\n" +
                                                                                "4- ULTRASSONOGRAFIA PELVICA\n" +
                                                                                "5- ULTRASSONOGRAFIA MAMARIA\n" +
                                                                                "6- ULTRASSOM TRANSVAGINAL\n" +
                                                                                "7- NENHUM");
                                                                        int procedimento = scanner.nextInt();
                                                                        EnumProcedimentosGineco procedimentoEscolhido = null;

                                                                        switch (procedimento) {
                                                                            case 1:
                                                                                procedimentoEscolhido = EnumProcedimentosGineco.PAPANICOLAU;
                                                                                break;
                                                                            case 2:
                                                                                procedimentoEscolhido = EnumProcedimentosGineco.HISTEROCOPSIA;
                                                                                break;
                                                                            case 3:
                                                                                procedimentoEscolhido = EnumProcedimentosGineco.COLPOSCOPIA;
                                                                                break;
                                                                            case 4:
                                                                                procedimentoEscolhido = EnumProcedimentosGineco.ULTRASSONOGRAFIA_PELVICA;
                                                                                break;
                                                                            case 5:
                                                                                procedimentoEscolhido = EnumProcedimentosGineco.ULTRASSONOGRAFIA_MAMARIA;
                                                                                break;
                                                                            case 6:
                                                                                procedimentoEscolhido = EnumProcedimentosGineco.ULTRASSOM_TRANSVAGINAL;
                                                                                break;
                                                                            case 7:
                                                                                procedimentoEscolhido = EnumProcedimentosGineco.NENHUM;
                                                                                break;
                                                                            default:
                                                                                System.out.println("Informação Inválida!");
                                                                                continue;
                                                                        }

                                                                        ConsultaGinecologica consulta = new ConsultaGinecologica(data, EnumStatusConsulta.AGENDADA, medicoGineco, procedimentoEscolhido, pacienteGineco);
                                                                        boolean concluido2 = medicoGineco.adicionarConsulta(consulta);
                                                                        if(concluido2){
                                                                            medicoGineco.removerDatasDisponiveis(data);
                                                                            System.out.println(consulta);
                                                                            System.out.println("Consulta agendada com sucesso!");
                                                                        } else {
                                                                            System.out.println("\nOcorreu um erro!");
                                                                            continue;
                                                                        }

                                                                    } else {
                                                                        System.out.println("\n=> Este horário não se encontra disponível para esta data, forneça um dos horários mostrados acima!");
                                                                        continue;
                                                                    }

                                                                } else {
                                                                    System.out.println("\n=> Esta data não esta disponível, forneça uma datas mostradas na tela!");
                                                                    continue;
                                                                }
                                                                break;
                                                            case 2:
                                                                System.out.println("\nAgendamento cancelado!");
                                                                break;
                                                            default:
                                                                System.out.println("\nInformação Inválida!");
                                                                continue;
                                                        }
                                                    } else {
                                                        System.out.println("Médico(a) não encontado(a)!");
                                                    }

                                                } else {
                                                    System.out.println("\nNenhum paciente com esse CPF foi encontrado!");
                                                }
                                                break;
                                            default:
                                                System.out.println("Informação Inválida!");
                                                continue;
                                        }
                                        break;
                                    case 2: //Remarcar consulta
                                        System.out.print("\n\tRemarcar Consulta\n" +
                                                "Informe a especialidade:\n" +
                                                "1- DERMATOLOGISTA\n" +
                                                "2- GINECOLOGISTA\n");
                                        int remarcar = scanner.nextInt();

                                        switch (remarcar){
                                            case 1:
                                                System.out.print("\nInforme o CPF do paciente da consulta: ");
                                                String cpfRemarcar = scanner.next();
                                                System.out.print("\nInforme o nome do médico responsável: ");
                                                String nome = scanner.nextLine();
                                                System.out.print("\nAgora informe o CRM do médico responsável: ");
                                                String crmRemarcar = scanner.nextLine();

                                                MedicoDermatologista medicoResponsavel = clinica.pesquisarMedicoDermatoNoSistema(crmRemarcar);
                                                ConsultaDermatologica consulta = medicoResponsavel.pesquisarConsulta(cpfRemarcar);

                                                if (consulta.statusDaConsulta.equals(EnumStatusConsulta.AGENDADA)) {
                                                    System.out.println("=> Datas Disponíveis para esse médico:");
                                                    medicoResponsavel.vizualizarDatasDisponiveis();

                                                    System.out.print("\nInforme a data escolhida: " +
                                                            "\nDia: ");
                                                    int dia = scanner.nextInt();
                                                    System.out.print("\nMês: ");
                                                    int mes = scanner.nextInt();
                                                    System.out.print("\nAno: ");
                                                    int ano = scanner.nextInt();

                                                    System.out.println("=> Horários dispiníveis para essa data: ");
                                                    medicoResponsavel.vizualizarHorariosDisponiveis(LocalDate.of(ano, mes, dia));

                                                    System.out.println("Informe o horário escolhido: ");
                                                    System.out.print("Hora: ");
                                                    int hora = scanner.nextInt();
                                                    System.out.print("Minuto: ");
                                                    int minuto = scanner.nextInt();

                                                    boolean concluido = medicoResponsavel.remarcarConsulta(consulta, LocalDateTime.of(ano, mes, dia, hora, minuto));
                                                    if (concluido){
                                                        System.out.println(consulta);
                                                        System.out.println("Consulta remarcada com sucesso!");
                                                    } else {
                                                        System.out.println("\nOcorreu um erro!");
                                                        continue;
                                                    }
                                                    break;
                                                } else {
                                                    System.out.println("Não é possível remarcar essa consulta!");
                                                }
                                                break;

                                            case 2:
                                                System.out.print("\nInforme o CPF do paciente da consulta: ");
                                                String cpfRemarcar2 = scanner.next();
                                                System.out.print("\nInforme o nome do médico responsável: ");
                                                String nome2 = scanner.nextLine();
                                                System.out.print("\nAgora informe o CRM do médico responsável: ");
                                                String crmRemarcar2 = scanner.nextLine();

                                                MedicoGinecologista medicoResponsavel2 = clinica.pesquisarMedicoGinecoNoSistema(crmRemarcar2);
                                                ConsultaGinecologica consulta2 = medicoResponsavel2.pesquisarConsulta(cpfRemarcar2);

                                                if (consulta2.statusDaConsulta.equals(EnumStatusConsulta.AGENDADA)) {
                                                    System.out.println("=> Datas Disponíveis para esse médico:");
                                                    medicoResponsavel2.vizualizarDatasDisponiveis();

                                                    System.out.print("\nInforme a data escolhida: " +
                                                            "\nDia: ");
                                                    int dia = scanner.nextInt();
                                                    System.out.print("\nMês: ");
                                                    int mes = scanner.nextInt();
                                                    System.out.print("\nAno: ");
                                                    int ano = scanner.nextInt();

                                                    System.out.println("=> Horários dispiníveis para essa data: ");
                                                    medicoResponsavel2.vizualizarHorariosDisponiveis(LocalDate.of(ano, mes, dia));

                                                    System.out.println("Informe o horário escolhido: ");
                                                    System.out.print("Hora: ");
                                                    int hora = scanner.nextInt();
                                                    System.out.print("Minuto: ");
                                                    int minuto = scanner.nextInt();

                                                    boolean concluido2 = medicoResponsavel2.remarcarConsulta(consulta2, LocalDateTime.of(ano, mes, dia, hora, minuto));
                                                    if (concluido2){
                                                        System.out.println(concluido2);
                                                        System.out.println("Consulta remarcada com sucesso!");
                                                    } else {
                                                        System.out.println("\nOcorreu um erro!");
                                                    }
                                                } else {
                                                    System.out.println("Não é possível remarcar essa consulta!");
                                                }
                                                break;
                                            default:
                                                System.out.println("Informação Inválida!");
                                                continue;
                                        }
                                        break;
                                    case 3: //Cancelar consulta
                                        System.out.print("\n\tCancelar Consulta\n" +
                                                "Informe a especialidade:\n" +
                                                "1- DERMATOLOGISTA\n" +
                                                "2- GINECOLOGISTA\n");
                                        int cancelar = scanner.nextInt();

                                        switch (cancelar){
                                            case 1:
                                                System.out.print("\nInforme o CPF do paciente da consulta: ");
                                                String cpfCancelar = scanner.next();
                                                System.out.print("\nInforme o nome do médico responsável: ");
                                                String nome = scanner.nextLine();
                                                System.out.print("\nAgora informe o CRM do médico responsável: ");
                                                String crmCancelar = scanner.nextLine();

                                                MedicoDermatologista medicoResponsavel = clinica.pesquisarMedicoDermatoNoSistema(crmCancelar);
                                                ConsultaDermatologica consulta = medicoResponsavel.pesquisarConsulta(cpfCancelar);
                                                consulta.atualizarStatusConsulta(EnumStatusConsulta.CANCELADA);
                                                System.out.println("A consulta foi cancelada!");
                                                break;

                                            case 2:
                                                System.out.print("\nInforme o CPF do paciente da consulta: ");
                                                String cpfCancelar2 = scanner.next();
                                                System.out.print("\nInforme o nome do médico responsável: ");
                                                String nome2 = scanner.nextLine();
                                                System.out.print("\nAgora informe o CRM do médico responsável: ");
                                                String crmCancelar2 = scanner.nextLine();

                                                MedicoGinecologista medicoResponsavel2 = clinica.pesquisarMedicoGinecoNoSistema(crmCancelar2);
                                                ConsultaGinecologica consulta2 = medicoResponsavel2.pesquisarConsulta(cpfCancelar2);
                                                consulta2.atualizarStatusConsulta(EnumStatusConsulta.CANCELADA);
                                                System.out.println("A consulta foi cancelada!");
                                                break;
                                            default:
                                                System.out.println("Informação Inválida!");
                                                continue;
                                        }
                                        break;
                                    default:
                                        System.out.println("Informação Inválida!");
                                        continue;
                                }
                                break;
                            case 2:
                                System.out.println("\n\tCadastrar Paciente\n");
                                System.out.print("Informe o nome do paciente: ");
                                scanner = new Scanner(System.in);
                                String nome = scanner.nextLine();

                                System.out.println(
                                        "\nInforme o sexo:\n" +
                                                "1 - Feminino\n" +
                                                "2 - Masculino\n" +
                                                "3 - Outro"
                                );
                                int respostaSexo = scanner.nextInt();
                                String sexo = "";
                                switch (respostaSexo) {
                                    case 1:
                                        sexo = "Feminino";
                                        break;
                                    case 2:
                                        sexo = "Masculino";
                                        break;
                                    case 3:
                                        sexo = "Outro";
                                        break;
                                    default:
                                        System.out.println("Resposta Inválida!");
                                        continue;
                                }

                                System.out.print("O Cpf (Apenas digítos, sem pontos ou traços): ");
                                String cpf = scanner.next();

                                System.out.println("\nNos informe a data de nascimento do paciente: ");
                                System.out.print("Dia: ");
                                int dia = scanner.nextInt();

                                if (dia < 1 || dia > 31){
                                    System.out.println("Informação Inválida");
                                    continue;
                                }

                                System.out.print("Mês: ");
                                int mes = scanner.nextInt();
                                if(mes < 1 || dia > 12){
                                    System.out.println("Informação Inválida");
                                    continue;
                                }

                                System.out.print("Ano: ");
                                int ano = scanner.nextInt();
                                if (ano < 1 || ano > LocalDate.now().getYear()){
                                    System.out.println("Informação Inválida");
                                    continue;
                                }

                                System.out.print("\nInforma a cidade que mora: ");
                                scanner = new Scanner(System.in);
                                String cidade = scanner.nextLine();

                                System.out.print("O estado: ");
                                String estado = scanner.next();

                                System.out.print("Informe o cep: ");
                                int cep = scanner.nextInt();

                                System.out.print("Seu telefone: ");
                                scanner = new Scanner(System.in);
                                String telefone = scanner.nextLine();

                                System.out.println("\n=> O paciente possui cartãoVIP?\n" +
                                        "1- SIM\n" +
                                        "2- NÃO");
                                int resposta = scanner.nextInt();
                                boolean possuiCartao = false;
                                switch (resposta) {
                                    case 1:
                                        possuiCartao = true;
                                        break;
                                    case 2:
                                        break;
                                    default:
                                        System.out.println("\nResposta Inválida!");
                                        break;
                                }

                                System.out.println("\n=> Informe a especialidade:\n" +
                                        "1- DERMATOLOGISTA\n" +
                                        "2- GINECOLOGISTA");
                                int respEspecialidade = scanner.nextInt();

                                switch (respEspecialidade){
                                    case 1:
                                        PacienteDermatologico verificar = clinica.pesquisarPacienteDermatoNoSistema(cpf);
                                        if (verificar != null){
                                            System.out.println("\nO cpf deste paciente já se encontra cadastrado no sistema!");
                                            break;
                                        } else {
                                            PacienteDermatologico paciente = new PacienteDermatologico(nome, sexo, cpf, LocalDate.of(ano, mes, dia), new Endereco(cidade, estado, cep), telefone, possuiCartao);
                                            boolean concluido = clinica.adicionarPacienteDermatoNoSistema(paciente);
                                            if (concluido){
                                                System.out.println(paciente);
                                                System.out.println("\nPaciente Cadastrado!");
                                            } else {
                                                System.out.println("\nOcorreu um erro, limite de pacientes atingido!");
                                                continue;
                                            }
                                        }
                                        break;
                                    case 2:
                                        PacienteGinecologico verificar2 = clinica.pesquisarPacienteGinecoNoSistema(cpf);
                                        if (verificar2 != null){
                                            System.out.println("\nO cpf deste paciente já se encontra cadastrado no sistema!");
                                            continue;
                                        } else {
                                            PacienteGinecologico paciente2 = new PacienteGinecologico(nome, sexo, cpf, LocalDate.of(ano, mes, dia), new Endereco(cidade, estado, cep), telefone, possuiCartao);
                                            boolean concluido2 = clinica.adicionarPacienteGinecoDoSistema(paciente2);
                                            if (concluido2){
                                                System.out.println(paciente2
                                                );
                                                System.out.println("\nPaciente Cadastrado!");
                                            } else {
                                                System.out.println("\nOcorreu um erro, limite de pacientes atingido!");
                                                continue;
                                            }
                                        }
                                        break;
                                    default:
                                        System.out.println("\nInformação Inválida!");
                                        continue;
                                }
                                break;
                            default:
                                System.out.println("\nInformação Inválida!");
                        }
                        break;
                    }

            }
        }

    }
}
