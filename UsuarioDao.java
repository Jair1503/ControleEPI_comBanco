import java.sql.*;
import java.util.ArrayList;

public class UsuarioDao {

    private static final String[] PERFIS_VALIDOS = {"administrador", "colaborador", "gerente"};

    private boolean perfilValido(String perfil) {
        for (String p : PERFIS_VALIDOS) {
            if (p.equalsIgnoreCase(perfil)) {
                return true;
            }
        }
        return false;
    }

    public void inserirUsuario(Usuario usuario) {
        if (!perfilValido(usuario.getPerfil())) {
            System.out.println("Erro: Perfil inválido. Permitidos: administrador, colaborador, gerente.");
            return;
        }

        String sql = "INSERT INTO usuario (nome, email, senha, perfil) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getPerfil().toLowerCase());

            stmt.executeUpdate();
            System.out.println("Usuário inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    public ArrayList<Usuario> listarUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("perfil")
                );
                lista.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        return lista;
    }

    public void atualizarUsuario(Usuario usuario) {
        if (!perfilValido(usuario.getPerfil())) {
            System.out.println("Erro: Perfil inválido. Permitidos: admin, colaborador, gerente.");
            return;
        }

        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, perfil = ? WHERE id_usuario = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getPerfil().toLowerCase());
            stmt.setInt(5, usuario.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Usuário atualizado com sucesso!");
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public void excluirUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Usuário excluído com sucesso!");
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }
}


