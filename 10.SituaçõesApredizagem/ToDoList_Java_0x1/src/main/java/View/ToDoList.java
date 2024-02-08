package View;

import java.util.ArrayList; // Importa a classe ArrayList para gerenciar uma lista de tarefas
import java.util.List; // Importa a interface List para trabalhar com listas

import javax.swing.DefaultListModel; // Importa DefaultListModel para usar em uma JList
import javax.swing.JButton; // Importa JButton para criar botões
import javax.swing.JComboBox; // Importa JComboBox para criar uma caixa de seleção
import javax.swing.JFrame; // Importa JFrame para criar a janela principal
import javax.swing.JList; // Importa JList para exibir a lista de tarefas
import javax.swing.JOptionPane;
import javax.swing.JPanel; // Importa JPanel para criar painéis
import javax.swing.JScrollPane; // Importa JScrollPane para adicionar barras de rolagem
import javax.swing.JTextField; // Importa JTextField para criar campo de entrada de texto

import Connection.TasksDAO;
import Model.Task;

import java.awt.*; // Importa classes do pacote awt para gerenciar a interface gráfica

import java.awt.event.ActionListener; // Importa a classe ActionListener para lidar com eventos de ação
import java.awt.event.ActionEvent; // Importa a classe ActionEvent para representar eventos de ação

import java.awt.event.MouseEvent; // Importa a classe MouseEvent para lidar com eventos de mouse
import java.awt.event.MouseListener; // Importa a interface MouseListener para lidar com eventos de mouse

import java.awt.event.KeyEvent; // Importa a classe KeyEvent para lidar com eventos de teclado
import java.awt.event.KeyListener; // Importa a interface KeyListener para lidar com eventos de teclado

public class ToDoList extends JFrame {
    // atributos
    private JPanel mainPanel; // Painel principal da interface
    private JTextField taskInputField; // Campo de entrada de texto para adicionar tarefas
    private JButton addButton; // Botão para adicionar tarefas
    private JList<String> taskList; // Lista de tarefas
    private DefaultListModel<String> listModel; // Modelo da lista de tarefas
    private JButton deleteButton; // Botão para excluir tarefas
    private JButton markDoneButton; // Botão para marcar tarefas como concluídas
    private JComboBox<String> filterComboBox; // Caixa de seleção para filtrar tarefas
    private JButton clearCompletedButton; // Botão para limpar tarefas concluídas
    private List<Task> tasks;
    private TasksDAO tasksDAO;
   // private List<Task> tasks; // Lista de tarefas



    // CONSTRUTOR
    public ToDoList() {
        // Configuração da janela principal
        super("To-Do List App"); // Define o título da janela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento padrão de fechar a janela
        this.setSize(425, 300); // Define o tamanho da janela

        // Inicializa o painel principal
        mainPanel = new JPanel(); // Cria um novo painel
        mainPanel.setLayout(new BorderLayout()); // Define o layout do painel como BorderLayout

        // Inicializa a lista de tasks e a lista de tasks concluídas
        tasks = new ArrayList<>(); // Cria uma lista vazia para armazenar as tarefas
        listModel = new DefaultListModel<>(); // Cria um modelo de lista para a JList
        taskList = new JList<>(listModel); // Cria a JList com o modelo

        // Inicializa campos de entrada, botões e JComboBox
        taskInputField = new JTextField(); // Cria um campo de entrada de texto
        addButton = new JButton("Adicionar"); // Cria um botão com rótulo "Adicionar"
        Color corAdd = new Color(127,255,212);
        addButton.setBackground(corAdd);
        deleteButton = new JButton("Excluir"); // Cria um botão com rótulo "Excluir"
        Color corDell = new Color(255, 192, 203);
        deleteButton.setBackground(corDell);
        markDoneButton = new JButton("Concluir"); // Cria um botão com rótulo "Concluir"
        Color corDone = new Color(218,165,32);
        markDoneButton.setBackground(corDone);
        filterComboBox = new JComboBox<>(new String[] { "Todas", "Ativas", "Concluídas" }); // Cria uma caixa de seleção
                                                                                            // com opções
        clearCompletedButton = new JButton("Limpar Concluídas"); // Cria um botão com rótulo "Limpar Concluídas"

        // Configuração do painel de entrada
        JPanel inputPanel = new JPanel(new BorderLayout()); // Cria um novo painel com BorderLayout
        inputPanel.add(taskInputField, BorderLayout.CENTER); // Adiciona o campo de entrada ao centro do painel
        inputPanel.add(addButton, BorderLayout.EAST); // Adiciona o botão "Adicionar" à direita do painel

        // Configuração do painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Cria um novo painel com layout FlowLayout à
                                                                          // esquerda
        buttonPanel.add(deleteButton); // Adiciona o botão "Excluir" ao painel
        buttonPanel.add(markDoneButton); // Adiciona o botão "Concluir" ao painel
        buttonPanel.add(filterComboBox); // Adiciona a caixa de seleção ao painel
        buttonPanel.add(clearCompletedButton); // Adiciona o botão "Limpar Concluídas" ao painel

        // Adiciona os componentes ao painel principal
        mainPanel.add(inputPanel, BorderLayout.NORTH); // Adiciona o painel de entrada na parte superior do painel
                                                       // principal
        mainPanel.add(new JScrollPane(taskList), BorderLayout.CENTER); // Adiciona a lista de tarefas com barra de
                                                                       // rolagem ao centro do painel principal
        mainPanel.add(buttonPanel, BorderLayout.SOUTH); // Adiciona o painel de botões na parte inferior do painel
                                                        // principal

        // Adiciona o painel principal à janela
        this.add(mainPanel); // Adiciona o painel principal à janela
        this.setVisible(true); // Torna a janela visível

        // criar os tratamentos de eventos - listener e eventos (FALTA IMPLEMENTAR)

        Handler botaoAdd = new Handler(); // Cria uma instância de Handler para lidar com a ação do botão "Adicionar"
        addButton.addActionListener(botaoAdd); // Adiciona o Handler como ActionListener do botão "Adicionar"

        Handler2 botaoDell = new Handler2(); // Cria uma instância de Handler2 para lidar com a ação do botão "Excluir"
        deleteButton.addActionListener(botaoDell); // Adiciona o Handler2 como ActionListener do botão "Excluir"

        Handler3 botaoDone = new Handler3(); // Cria uma instância de Handler3 para lidar com a ação do botão "Concluir"
        markDoneButton.addActionListener(botaoDone); // Adiciona o Handler3 como ActionListener do botão "Concluir"

        Handler4 botaoClear = new Handler4(); // Cria uma instância de Handler4 para lidar com a ação do botão "Limpar
        // Concluídas"
        clearCompletedButton.addActionListener(botaoClear); // Adiciona o Handler4 como ActionListener do botão "Limpar
        // Concluídas"

        Handler5 filter = new Handler5(); // Cria uma instância de Handler5 para lidar com a ação do JComboBox de filtro
        filterComboBox.addActionListener(filter); // Adiciona o Handler5 como ActionListener do JComboBox

        Handler6 teclaEnter = new Handler6(); // Cria uma instância de Handler6 para lidar com eventos de tecla no campo
                                              // de texto
        taskInputField.addKeyListener(teclaEnter); // Adiciona o Handler6 como KeyListener ao campo de texto

        Handler7 teclaDelete = new Handler7(); // Cria uma instância de Handler7 para lidar com eventos de tecla na
                                               // lista
                                               // de tarefas
        taskList.addKeyListener(teclaDelete); // Adiciona o Handler7 como KeyListener à lista de tarefas

        Handler8 confirmaMause = new Handler8(); // Cria uma instância de Handler8 para lidar com eventos de mouse na
                                                 // lista
        // de tarefas
        taskList.addMouseListener(confirmaMause); // Adiciona o Handler8 como MouseListener à lista de tarefas

        new TasksDAO().criaTabela();
        tasksDAO = new TasksDAO();
    }

    
    // Classe Handler implementa a interface ActionListener para o botão adicinar
    // tarefa
    public class Handler implements ActionListener {
        @Override
        // Método chamado quando o botão "Adicionar" é clicado
        public void actionPerformed(ActionEvent e) {
            // Chama o método addTask() para adicionar uma nova tarefa
            addTask();
        }
    }

    // Classe Handler2 implementa a interface ActionListener para o botão deletar
    // tarefa
    public class Handler2 implements ActionListener {
        @Override
        // Método chamado quando o botão "Excluir" é clicado
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = taskList.getSelectedIndex(); // Obtém o índice da tarefa selecionada na lista
            if (selectedIndex >= 0 && selectedIndex < tasks.size()) { // Verifica se o índice é válido
                // Exibe uma caixa de diálogo de confirmação para excluir a tarefa
                int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir a tarefa?",
                        "Confirmação", JOptionPane.YES_NO_OPTION);
                // Se a confirmação for sim, chama o método deleteTask() para excluir a tarefa
                if (confirmacao == JOptionPane.YES_OPTION) {
                    deleteTask();
                }
            }
        }
    }

    // Classe Handler3 implementa a interface ActionListener para o botão concluir
    // tarefa
    public class Handler3 implements ActionListener {
        @Override
        // Método chamado quando o botão "Concluir" é clicado
        public void actionPerformed(ActionEvent e) {
            // Chama o método markTaskDone() para marcar a tarefa como concluída
            markTaskDone();
        }
    }

    // Classe Handler4 implementa a interface ActionListener
    public class Handler4 implements ActionListener {
        @Override
        // Método chamado quando o botão "Limpar Concluídas" é clicado
        public void actionPerformed(ActionEvent e) {
            // Chama o método clearCompletedTasks() para limpar tarefas concluídas
            clearCompletedTasks();
        }
    }

    // Classe Handler5 implementa a interface ActionListener
    public class Handler5 implements ActionListener {
        @Override
        // Método chamado quando uma opção é selecionada na caixa de seleção (JComboBox)
        public void actionPerformed(ActionEvent e) {
            // Chama o método filterTasks() para filtrar as tarefas
            filterTasks();
        }
    }

    // Classe Handler6 implementa a interface KeyListener
    public class Handler6 implements KeyListener {
        @Override
        // Método chamado quando uma tecla é digitada, mas não será usado neste caso
        public void keyTyped(KeyEvent e) {
            // não vou usar
        }

        @Override
        // Método chamado quando uma tecla é pressionada, mas não será usado neste caso
        public void keyPressed(KeyEvent e) {
            // não vou usar
        }

        @Override
        // Método chamado quando uma tecla é liberada
        public void keyReleased(KeyEvent e) {
            // Se a tecla "Enter" for pressionada, chama o método addTask() para adicionar
            // uma nova tarefa
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                addTask();
            }
        }
    }

    public class Handler7 implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            // Este método é acionado quando uma tecla é digitada, mas não será usado neste
            // neste caso
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // Este método é acionado quando uma tecla é pressionada, mas não será usado
            // neste caso
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_DELETE) {

                int selectedIndex = taskList.getSelectedIndex(); // Obtém o índice da tarefa selecionada na lista
                if (selectedIndex >= 0 && selectedIndex < tasks.size()) { // Verifica se o índice é válido
                    String opcao[] = {"sim", "Nao"}; 
                    int confirmacao = JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir a tarefa?", "confirmar", 0, 0, null, opcao, opcao[0]);
                    if (confirmacao == JOptionPane.YES_OPTION) {
                        deleteTask();
                    }
                }

            }
        }
    }

    public class Handler8 implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                // Este Handler responde a um duplo clique na lista de tarefas e chama o método
                // markTaskDone()
                markTaskDone();
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {
            // Este método é acionado quando o mouse é pressionado, mas não será usado neste
            // caso
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // Este método é acionado quando o mouse é liberado, mas não será usado neste
            // caso
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // Este método é acionado quando o mouse entra na área da lista de tarefas, mas
            // não será usado neste caso
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // Este método é acionado quando o mouse sai da área da lista de tarefas, mas
            // não será usado neste caso
        }
    }

    

    // criar os métodos (CRUD)
    private void addTask() {
        // Adiciona uma nova task à lista de tasks
        String taskDescription = taskInputField.getText().trim(); // Obtém o texto do campo de entrada e remove espaços
                                                                  // em branco
        if (!taskDescription.isEmpty()) { // Verifica se a descrição da tarefa não está vazia
            Task newTask = new Task(taskDescription); // Cria uma nova tarefa com a descrição
            tasks.add(newTask); // Adiciona a nova tarefa à lista de tarefas
            updateTaskList(); // Atualiza a lista de tarefas exibida na GUI
            taskInputField.setText(""); // Limpa o campo de entrada de texto
            tasksDAO.cadastrar(newTask.getDescription());
        }
    }

    private void deleteTask() {
        // Exclui a task selecionada da lista de tasks
        int selectedIndex = taskList.getSelectedIndex(); // Obtém o índice da tarefa selecionada na lista
        if (selectedIndex >= 0 && selectedIndex < tasks.size()) { // Verifica se o índice é válido
            tasks.remove(selectedIndex); // Remove a tarefa da lista
            updateTaskList(); // Atualiza a lista de tarefas exibida na GUI
        }
    }

    private void markTaskDone() {
        // Marca a task selecionada como concluída
        int selectedIndex = taskList.getSelectedIndex(); // Obtém o índice da tarefa selecionada na lista
        if (selectedIndex >= 0 && selectedIndex < tasks.size()) { // Verifica se o índice é válido
            Task task = tasks.get(selectedIndex); // Obtém a tarefa selecionada
            task.setDone(true); // Marca a tarefa como concluída
            updateTaskList(); // Atualiza a lista de tarefas exibida na GUI
        }
    }

    private void filterTasks() {
        // Filtra as tasks com base na seleção do JComboBox
        String filter = (String) filterComboBox.getSelectedItem(); // Obtém o filtro selecionado na caixa de seleção
        listModel.clear(); // Limpa o modelo da lista
        for (Task task : tasks) { // Itera sobre todas as tarefas na lista
            if (filter.equals("Todas") || (filter.equals("Ativas") &&
                    !task.isDone()) || (filter.equals("Concluídas") && task.isDone())) {
                // Verifica se a tarefa deve ser exibida com base no filtro selecionado
                listModel.addElement(task.getDescription() + (task.isDone() ? " (Concluída)" : "")); // Adiciona a
                                                                                                     // descrição da
                                                                                                     // tarefa à lista
                                                                                                     // exibida na GUI
            }
        }
    }

    private void clearCompletedTasks() {
        // Limpa todas as tasks concluídas da lista
        List<Task> completedTasks = new ArrayList<>(); // Cria uma lista temporária para armazenar tarefas concluídas
        for (Task task : tasks) { // Itera sobre todas as tarefas na lista
            if (task.isDone()) { // Verifica se a tarefa está concluída
                completedTasks.add(task); // Adiciona a tarefa à lista de tarefas concluídas
            }
        }
        tasks.removeAll(completedTasks); // Remove todas as tarefas concluídas da lista principal
        updateTaskList(); // Atualiza a lista de tarefas exibida na GUI
    }

    private void updateTaskList() {
        // Atualiza a lista de tasks exibida na GUI
        listModel.clear(); // Limpa o modelo da lista
        for (Task task : tasks) { // Itera sobre todas as tarefas na lista
            listModel.addElement(task.getDescription() + (task.isDone() ? " (Concluída)" : ""));
            // Adiciona a descrição da tarefa à lista exibida na GUI, indicando se está
            // concluída
        }
    }

    public void run() {
        // Exibe a janela
        this.setVisible(true); // Torna a janela visível
    }
}