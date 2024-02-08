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
    private List<Task> tasks;

    // construtor
    public TasksDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // criar Tabela
    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS Tarefas (id SERIAL PRIMARY KEY, descTarefa VARCHAR(255), feito BOOLEAN)";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela de Tarefas criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela de Tarefas: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // Listar todos os valores cadastrados
    public List<Task> listarTodos() {
        PreparedStatement stmt = null;
        // Declaração do objeto PreparedStatement para executar a consulta
        ResultSet rs = null;
        // Declaração do objeto ResultSet para armazenar os resultados da consulta
        tasks = new ArrayList<>();
        // Cria uma lista para armazenar os carros recuperados do banco de dados
        try {
            stmt = connection.prepareStatement("SELECT * FROM Tarefas");
            // Prepara a consulta SQL para selecionar todos os registros da tabela
            rs = stmt.executeQuery();
            // Executa a consulta e armazena os resultados no ResultSet
            while (rs.next()) {
                // Para cada registro no ResultSet, cria um objeto Produtos com os valores do
                // registro

                Task task = new Task(
                        rs.getString("descricaoTarefa")
                        );
                tasks.add(task); // Adiciona o objeto Produto à lista de produtos
            }
        } catch (SQLException ex) {
            System.out.println(ex); // Em caso de erro durante a consulta, imprime o erro
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);

            // Fecha a conexão, o PreparedStatement e o ResultSet
        }
        return tasks; // Retorna a lista de carros recuperados do banco de dados
    }

    // Cadastrar Carro no banco
    public void cadastrar(String descricaoTarefa) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para cadastrar na tabela
        String sql = "INSERT INTO Tarefas (descTarefa)VALUES (?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, descricaoTarefa);
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Atualizar dados no banco
    public void atualizar(String descricaoTarefa, boolean done) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para atualizar dados pelo código do
        // produto
        String sql = "UPDATE Tarefas SET descTarefa = ?, done = ? WHERE id = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, descricaoTarefa); // Corrigido: nome vem primeiro
            stmt.setBoolean(2, done);

            int rowsEdit = stmt.executeUpdate(); // Executa a instrução SQL e obtém o número de linhas afetadas
            if (rowsEdit > 0) {
                System.out.println("Produto atualizado com sucesso");
            } else {
                System.out.println("Nenhum produto foi atualizado. Verifique o código do produto.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Apagar dados do banco
    public void apagar(String codProduto) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para apagar dados pelo código do produto
        String sql = "DELETE FROM Tarefas WHERE id = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, codProduto);
            int rowsDeleted = stmt.executeUpdate(); // Executa a instrução SQL e obtém o número de linhas deletadas
            if (rowsDeleted > 0) {
                System.out.println("Produto apagado com sucesso");
            } else {
                System.out.println("Nenhum produto foi apagado. Verifique o código do produto.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

}
