package menu;

import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import static javax.swing.JOptionPane.*;
import java.util.List;

import excecao.OpcaoInvalidaException;

public class Menu {
    public void menu() {
        int opcao = 0;

        do {
            try {
                opcao = parseInt(showInputDialog(gerarMenu()));
                if (opcao < 1 || opcao > 7) {
                    throw new OpcaoInvalidaException("Opção inválida");
                } else {
                    switch (opcao) {
                        case 1:
                            cadastrarDepartamento();
                            break;
                        case 2:
                            cadastrarEmpregado();
                            break;
                        case 3:
                            pesquisarEmpregado();
                            break;
                        case 4:
                            listarEmpregado();
                            break;
                        case 5:
                            atualizarEmpregado();
                            break;
                        case 6:
                            excluirEmpregado();
                            break;
                    }
                }
            } catch (NumberFormatException e) {
                showMessageDialog(null, "Você deve digitar um número\n" + e);
            } catch (OpcaoInvalidaException e) {
                showMessageDialog(null, e.getMessage());
            }
        } while (opcao != 7);
    }
    

    private void excluirEmpregado() {
    }


    private void atualizarEmpregado() {
    }


    private void listarEmpregado() {
    }


    private void pesquisarEmpregado() {
    }


    private void cadastrarEmpregado() {
    }


    private void cadastrarDepartamento() {
    }


    private String gerarMenu() {
		String menu = "CONTROLE DE EMPREGADOS - ESCOLHA UMA OPÇÃO\n";
		menu += "1. Cadastrar departamento\n";
		menu += "2. Cadastrar empregado\n";
		menu += "3. Pesquisar empregado\n";
		menu += "4. Listar empregado\n";
		menu += "5. Atualizar empregado\n";
		menu += "6. Excluir empregado\n";
		menu += "7. Finalizar\n";
		return menu;
	}
}
