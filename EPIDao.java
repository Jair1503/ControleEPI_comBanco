import java.sql.*;
import java.util.ArrayList;

public class EPIDao {
    public void inserirEPI(EPI epi) {
        String sql = "INSERT INTO epi (nome, quantidade) VALUES (?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, epi.getNome());
            stmt.setInt(2, epi.getQuantidade());
            stmt.executeUpdate();
            System.out.println("EPI inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir EPI: " + e.getMessage());
        }
    }

    public ArrayList<EPI> listarEPIs() {
        ArrayList<EPI> lista = new ArrayList<>();
        String sql = "SELECT * FROM epi";
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                EPI epi = new EPI(
                        rs.getInt("id_epi"),
                        rs.getString("nome"),
                        rs.getInt("quantidade")
                );
                lista.add(epi);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar EPIs: " + e.getMessage());
        }
        return lista;
    }

    public void atualizarEPI(EPI epi) {
        String sql = "UPDATE epi SET nome = ?, quantidade = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, epi.getNome());
            stmt.setInt(2, epi.getQuantidade());
            stmt.setInt(3, epi.getId());
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("EPI atualizado com sucesso!");
            } else {
                System.out.println("EPI não encontrado para atualização.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar EPI: " + e.getMessage());
        }
    }

    public void excluirEPI(int id) {
        String verificarSql = "SELECT COUNT(*) FROM emprestimo WHERE id_epi = ?";
        String excluirSql = "DELETE FROM epi WHERE id_epi = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement verificarstmt = conn.prepareStatement(verificarSql)) {

            verificarstmt.setInt(1, id);
            ResultSet rs = verificarstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.err.println("Erro ao excluir: Epi vinculado ao empréstimo");
                return;
            }
            try (PreparedStatement excluirstmt = conn.prepareStatement(excluirSql)) {
                excluirstmt.setInt(1, id);
                int rows = excluirstmt.executeUpdate();
                if (rows > 0) {
                    System.err.println("EPI excuído com sucesso");
                } else {
                    System.err.println("EPI não encontrado");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir EPI: ");
        }
    }
}



