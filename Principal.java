import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioDao usuarioDao = new UsuarioDao();
        EPIDao epiDao = new EPIDao();
        EmprestimoDao emprestimoDao = new EmprestimoDao();
        DevolucaoDao devolucaoDao = new DevolucaoDao();

        int opcao;
        do {
            System.out.println("\n===== CONTROLE DE USUÁRIO =====");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Listar usuários");
            System.out.println("3. Atualizar usuário");
            System.out.println("4. Excluir usuário");

            System.out.println ("\n===== CONTROLE DE EPI =====");
            System.out.println("5. Cadastrar EPI");
            System.out.println("6. Listar EPIs");
            System.out.println("7. Atualizar EPI");
            System.out.println("8. Excluir EPI");

            System.out.println ("\n===== CONTROLE DE EMPRÉSTIMO =====");
            System.out.println("9. Registrar empréstimo");
            System.out.println("10. Listar empréstimos");
            System.out.println("11. Atualizar empréstimo");
            System.out.println("12. Excluir empréstimo");

            System.out.println ("\n===== CONTROLE DE DEVOLUCAO =====");
            System.out.println("\n13. Registrar devolução");
            System.out.println("14. Listar devoluções");
            System.out.println("15. Atualizar devolução");
            System.out.println("16. Excluir devolução");

            System.out.println("\n0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Nome do usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email do usuário: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha do usuário: ");
                    String senha = scanner.nextLine();
                    System.out.print("Perfil (administrador/colaborador/gerente): ");
                    String perfil = scanner.nextLine();
                    Usuario u = new Usuario(0, nome, email, senha, perfil);
                    usuarioDao.inserirUsuario(u);
                    break;

                case 2:
                    List<Usuario> usuarios  = usuarioDao.listarUsuarios();
                    if(usuarios == null || usuarios.isEmpty()) {
                        System.out.println("Usuário não encontrado");
                    }else{
                        usuarios.forEach(System.out::println);
                    }
                    break;

                case 3:
                    System.out.print("ID do usuário: ");
                    int idU = Integer.parseInt(scanner.nextLine());
                    System.out.print("Novo nome: ");
                    nome = scanner.nextLine();
                    System.out.print("Novo email: ");
                    email = scanner.nextLine();
                    System.out.print("Nova senha: ");
                    senha = scanner.nextLine();
                    System.out.print("Novo perfil: ");
                    perfil = scanner.nextLine();
                    Usuario uAtualizado = new Usuario(idU, nome, email, senha, perfil);
                    usuarioDao.atualizarUsuario(uAtualizado);
                    break;

                case 4:
                    System.out.print("ID do usuário para excluir: ");
                    int idExcluirU = Integer.parseInt(scanner.nextLine());
                    usuarioDao.excluirUsuario(idExcluirU);
                    break;

                case 5:
                    System.out.print("Nome do EPI: ");
                    String nomeEpi = scanner.nextLine();
                    System.out.print("Quantidade: ");
                    int quantidade = Integer.parseInt(scanner.nextLine());
                    EPI epi = new EPI(nomeEpi, quantidade);
                    epiDao.inserirEPI(epi);
                    break;

                case 6:
                    List<EPI> epis = epiDao.listarEPIs();
                    if(epis == null || epis.isEmpty()) {
                        System.out.println("EPI não encontrado");
                    }else {
                        epis.forEach(System.out::println);
                    }
                    break;

                case 7:
                    System.out.print("ID do EPI: ");
                    int idEpi = Integer.parseInt(scanner.nextLine());
                    System.out.print("Novo nome: ");
                    nomeEpi = scanner.nextLine();
                    System.out.print("Nova quantidade: ");
                    quantidade = Integer.parseInt(scanner.nextLine());
                    EPI epiAtualizado = new EPI(idEpi, nomeEpi, quantidade);
                    epiDao.atualizarEPI(epiAtualizado);
                    break;

                case 8:
                    System.out.print("ID do EPI para excluir: ");
                    int idExcluirEpi = Integer.parseInt(scanner.nextLine());
                    epiDao.excluirEPI(idExcluirEpi);
                    break;

                case 9:
                    System.out.print("ID do usuário: ");
                    int idUsuarioEmp = Integer.parseInt(scanner.nextLine());
                    System.out.print("ID do EPI: ");
                    int idEpiEmp = Integer.parseInt(scanner.nextLine());
                    System.out.print("Data de retirada (YYYY/MM/DD HH:MM:SS): ");
                    String dataRetirada = scanner.nextLine();
                    System.out.print("Data prevista de devolução (YYYY/MM/DD HH:MM:SS): ");
                    String dataPrevista = scanner.nextLine();
                    System.out.print("Confirmação de retirada (1 para sim, 0 para não): ");
                    int confirmacaoInt = Integer.parseInt(scanner.nextLine());
                    boolean confirmacao = (confirmacaoInt == 1);
                    Emprestimo emp = new Emprestimo(idUsuarioEmp, idEpiEmp, dataRetirada, dataPrevista, confirmacao);
                    emprestimoDao.inserirEmprestimo(emp);
                    break;

                case 10:
                    List<Emprestimo> emprestimos = emprestimoDao.listarEmprestimos();
                    if(emprestimos == null || emprestimos.isEmpty()) {
                        System.out.println("Empréstimo não encontrado");
                    }else {
                        emprestimos.forEach(System.out::println);
                    }
                    break;

                case 11:
                    System.out.print("ID do empréstimo: ");
                    int idEmp = Integer.parseInt(scanner.nextLine());
                    System.out.print("Novo ID do usuário: ");
                    int novoIdUsuario = Integer.parseInt(scanner.nextLine());
                    System.out.print("Novo ID do EPI: ");
                    int novoIdEpi = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nova data de retirada (YYYY-MM-DD HH:MM:SS): ");
                    String novaDataRetirada = scanner.nextLine();
                    System.out.print("Nova data prevista de devolução (YYYY-MM-DD HH:MM:SS): ");
                    String novaDataPrevista = scanner.nextLine();
                    System.out.print("Nova confirmação de retirada (1 ou 0): ");
                    int novaConfirmacaoInt = Integer.parseInt(scanner.nextLine());
                    boolean novaConfirmacao = (novaConfirmacaoInt == 1);
                    Emprestimo empAtualizado = new Emprestimo(idEmp, novoIdUsuario, novoIdEpi, novaDataRetirada, novaDataPrevista, novaConfirmacao);
                    emprestimoDao.atualizarEmprestimo(empAtualizado);
                    break;

                case 12:
                    System.out.print("ID do empréstimo para excluir: ");
                    int idExcluirEmp = Integer.parseInt(scanner.nextLine());
                    emprestimoDao.excluirEmprestimo(idExcluirEmp);
                    break;

                case 13:
                    System.out.print("ID do empréstimo: ");
                    int idEmpDev = Integer.parseInt(scanner.nextLine());
                    System.out.print("Data da devolução (AAAA-MM-DD): ");
                    String dataDevolucao = scanner.nextLine();
                    Devolucao dev = new Devolucao(idEmpDev, dataDevolucao);
                    devolucaoDao.inserirDevolucao(dev);
                    break;

                case 14:
                    List<Devolucao> devolucoes = devolucaoDao.listarDevolucoes();
                    if(devolucoes == null || devolucoes.isEmpty()) {
                        System.out.println("Devolução não encontrada");
                    }else {
                        devolucoes.forEach(System.out::println);
                    }
                    break;

                case 15:
                    System.out.print("ID da devolução: ");
                    int idDev = Integer.parseInt(scanner.nextLine());
                    System.out.print("Novo ID do empréstimo: ");
                    int novoIdEmpDev = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nova data da devolução (AAAA-MM-DD): ");
                    String novaDataDev = scanner.nextLine();
                    Devolucao devAtualizada = new Devolucao(idDev, novoIdEmpDev, novaDataDev);
                    devolucaoDao.atualizarDevolucao(devAtualizada);
                    break;

                case 16:
                    System.out.print("ID da devolução para excluir: ");
                    int idExcluirDev = Integer.parseInt(scanner.nextLine());
                    devolucaoDao.excluirDevolucao(idExcluirDev);
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}



