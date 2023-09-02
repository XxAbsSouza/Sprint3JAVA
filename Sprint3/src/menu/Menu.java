package menu;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.*;
import java.util.List;

import entidade.Consumidor;
import entidade.Feedback;
import entidade.MeiodoFeedback;
import excecao.OpcaoInvalidaException;

public class Menu {
    public void menu() {
        int opcao = 0;

        do {
            try {
                opcao = parseInt(showInputDialog(gerarMenu()));
                if (opcao < 1 || opcao > 9) {
                    throw new OpcaoInvalidaException("Opção inválida");
                } else {
                    switch (opcao) {
                        case 1:
                            cadastrarFeedback();
                            break;
                        case 2:
                            cadastrarConsumidor();
                            break;
                        case 3:
                            cadastrarFornecedor();
                            break;
                        case 4:
                            cadastrarMeioFeedback();
                            break;
                        case 5:
                            listarMeioFeedback();
                            break;
                        case 6:
                            pesquisarFeedback();
                            break;
                        case 7:
                            alterarFeedback();
                            break;
                        case 8:
                            excluirFeedback();
                            break;
                    }
                }
            } catch (NumberFormatException e) {
                showMessageDialog(null, "Você deve digitar um número\n" + e);
            } catch (OpcaoInvalidaException e) {
                showMessageDialog(null, e.getMessage());
            }
        } while (opcao != 9);
    }

    private void excluirFeedback() {
    }

    private void alterarFeedback() {
    }

    private void pesquisarFeedback() {
    }

    private void listarMeioFeedback() {
    }

    private void cadastrarMeioFeedback() {
    }

    private void cadastrarFornecedor() {
    }

    private void cadastrarConsumidor() {
    }

    private void cadastrarFeedback() {

        
        MeiodoFeedback meioFeedback = new MeiodoFeedback(WIDTH, TOOL_TIP_TEXT_KEY);

        String nome_Consumidor;
        String nome_MeioFeedback;
        int id = parseInt(showInputDialog("ID"));
		String fb = showInputDialog("FeedBack: ");
        String data = showInputDialog("Data ");
        //(int id, String feedback, String data, Consumidor consumidor, MeiodoFeedback meiodoFeedback)
        
        //FIXME
        Feedback feedback = new Feedback(id, fb, fb, null, null);
        //FIXME

		if (dao.pesquisar(departamento)) {
			showMessageDialog(null, "Departamento já existe, insira um diferente");
		} else{
			dao.inserir(departamento);
		}
    }

    private String gerarMenu() {
		String menu = "CONTROLE DE EMPREGADOS - ESCOLHA UMA OPÇÃO\n";
		menu += "1. Cadastrar FeedBack\n";
		menu += "2. Cadastrar Consumidor\n";
		menu += "3. Cadastrar Fornecedor\n";
		menu += "4. Cadastrar Plataforma em que será retirado o FeedBack\n";
		menu += "5. Listar plataformas disponíveis\n";
		menu += "6. Pesquisar FeedBack\n";
		menu += "7. Alterar FeedBack\n";
		menu += "8. Excluir FeedBack\n";
		menu += "9. Finalizar\n";
		return menu;
	}
}
