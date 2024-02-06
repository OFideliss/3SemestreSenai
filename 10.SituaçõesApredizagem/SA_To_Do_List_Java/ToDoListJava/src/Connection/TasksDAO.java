package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Task;

public class TasksDAO {
    // atributo
    private Connection connection;
    private List<Task> produtos;

    // construtor
    public TasksDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // criar Tabela
    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS produtos_noite (codProduto VARCHAR(255) PRIMARY KEY, nome VARCHAR(255), preco DOUBLE PRECISION, quantidade INTEGER)";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela de produtos criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela de produtos: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }


}
