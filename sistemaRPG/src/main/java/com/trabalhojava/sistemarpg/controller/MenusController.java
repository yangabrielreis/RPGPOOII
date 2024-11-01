package com.trabalhojava.sistemarpg.controller;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import java.sql.SQLException;

import com.trabalhojava.sistemarpg.dao.ClasseDBDAO;
import com.trabalhojava.sistemarpg.dao.PersonagemDBDAO;
import com.trabalhojava.sistemarpg.dao.PersonagemSistemaDBDAO;
import com.trabalhojava.sistemarpg.model.Personagem;
import com.trabalhojava.sistemarpg.model.PersonagemSistema;
import com.trabalhojava.sistemarpg.model.Classe;
import com.trabalhojava.sistemarpg.model.Raca;
import com.trabalhojava.sistemarpg.model.Sistema;
import com.trabalhojava.sistemarpg.dao.RacaDBDAO;
import com.trabalhojava.sistemarpg.dao.SistemaDBDAO;

import java.util.ArrayList;
import java.util.List;

public class MenusController extends Application {
    List<Personagem> personagens = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerenciador de Personagens");

        BorderPane root = new BorderPane();
        ImageView imagemPersonagem = new ImageView(new Image("file:C:/Users/arthur/Downloads/png.png"));
        imagemPersonagem.setFitWidth(250);
        imagemPersonagem.setFitHeight(350);

        HBox barraTopo = new HBox();
        barraTopo.setSpacing(10);
        barraTopo.setPadding(new Insets(10));
        barraTopo.setStyle("-fx-background-color: #D3D3D3;");

        Button btnAdicionar = new Button("+ Adicionar");
        btnAdicionar.setFont(Font.font("Arial", 16));
        btnAdicionar.setTextFill(Color.WHITE);
        btnAdicionar.setStyle("-fx-background-color: #27ae60;");

        Button btnVer = new Button("👁 Ver Personagens");
        btnVer.setFont(Font.font("Arial", 16));
        btnVer.setTextFill(Color.WHITE);
        btnVer.setStyle("-fx-background-color: #3498db;");

        Button btnEditar = new Button("✏ Editar Personagens");
        btnEditar.setFont(Font.font("Arial", 16));
        btnEditar.setTextFill(Color.WHITE);
        btnEditar.setStyle("-fx-background-color: #f39c12;");

        btnAdicionar.setOnAction(e -> exibirTelaDefinirCaracteristicas());
        btnVer.setOnAction(e -> exibirTelaVerPersonagens());
        btnEditar.setOnAction(e -> exibirTelaEditarPersonagens());

        barraTopo.getChildren().addAll(btnAdicionar, btnVer, btnEditar);
        root.setTop(barraTopo);

        VBox centro = new VBox();
        centro.setSpacing(15);
        centro.setPadding(new Insets(15));
        centro.setStyle("-fx-background-color: #D3D3D3;");
        Label titulo = new Label("Personagem Atual");
        titulo.setFont(Font.font("Arial", 24));
        centro.getChildren().addAll(titulo, imagemPersonagem);
        centro.setStyle("-fx-alignment: center;");
        root.setCenter(centro);

        Scene cena = new Scene(root, 600, 500);
        primaryStage.setScene(cena);
        primaryStage.show();
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
        }

        private void exibirTelaDefinirCaracteristicas() {
        Stage stageCaracteristicas = new Stage();
        stageCaracteristicas.setTitle("Definir Características");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setStyle("-fx-background-color: #D3D3D3;");

        Label titulo = new Label("Definir Características do Personagem");
        titulo.setFont(Font.font("Arial", 18));
        titulo.setStyle("-fx-text-fill: black;");
        GridPane.setColumnSpan(titulo, 2);

        TextField campoPersonagemId = new TextField();
        ComboBox<String> campoClasse = new ComboBox<>();  
        ComboBox<String> campoRaca = new ComboBox<>();
        campoRaca.getItems().addAll("Humano", "Elfo", "Anão", "Orc"); 
        campoClasse.getItems().addAll("Guerreiro", "Mago", "Arqueiro", "Ladino");

        TextField campoNome = new TextField();
        TextField campoInfo = new TextField();
        TextField campoImagem = new TextField();

        Button btnProximo = new Button("Próximo: Definir Atributos");
        btnProximo.setFont(Font.font("Arial", 16));
        btnProximo.setTextFill(Color.WHITE);
        btnProximo.setStyle("-fx-background-color: #2980b9;");
        btnProximo.setOnAction(e -> exibirTelaDefinirAtributos(
            Integer.parseInt(campoPersonagemId.getText()), 
            campoNome.getText(), 
            campoClasse.getValue(), 
            campoRaca.getValue(), 
            campoInfo.getText(), 
            campoImagem.getText(), 
            stageCaracteristicas
        ));
        
        grid.add(titulo, 0, 0);
        grid.add(new Label("ID do Personagem:"), 0, 1);
        grid.add(campoPersonagemId, 1, 1);
        grid.add(new Label("Nome:"), 0, 2);
        grid.add(campoNome, 1, 2);
        grid.add(new Label("Classe:"), 0, 3);
        grid.add(campoClasse, 1, 3);
        grid.add(new Label("Raça:"), 0, 4);
        grid.add(campoRaca, 1, 4);
        grid.add(new Label("Descrição:"), 0, 5);
        grid.add(campoInfo, 1, 5);
        grid.add(new Label("Caminho da Imagem:"), 0, 6);
        grid.add(campoImagem, 1, 6);
        grid.add(btnProximo, 1, 7);
        
        Scene cena = new Scene(grid, 400, 350);
        stageCaracteristicas.setScene(cena);
        stageCaracteristicas.show();
        }

        private void exibirTelaDefinirAtributos(int personagemId, String nome, String classe, String raca, String info, String caminhoImagem, Stage telaAnterior) {
        telaAnterior.close();
        Stage stageAtributos = new Stage();
        stageAtributos.setTitle("Definir Atributos");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setStyle("-fx-background-color: #D3D3D3;");

        Label titulo = new Label("Definir Atributos do Personagem");
        titulo.setFont(Font.font("Arial", 18));
        titulo.setStyle("-fx-text-fill: black;");
        GridPane.setColumnSpan(titulo, 2);

        TextField campoForca = new TextField();
        TextField campoDestreza = new TextField();
        TextField campoConstituicao = new TextField();
        TextField campoInteligencia = new TextField();
        TextField campoSabedoria = new TextField();
        TextField campoCarisma = new TextField();

        Button btnSalvar = new Button("Salvar Personagem");
        btnSalvar.setFont(Font.font("Arial", 16));
        btnSalvar.setTextFill(Color.WHITE);
        btnSalvar.setStyle("-fx-background-color: #27ae60;");
        btnSalvar.setOnAction(e -> {
            try {
                if (campoForca.getText().isEmpty() || campoDestreza.getText().isEmpty() || 
                    campoInteligencia.getText().isEmpty() || campoConstituicao.getText().isEmpty() || campoSabedoria.getText().isEmpty() || campoCarisma.getText().isEmpty()) {
                    throw new IllegalArgumentException("Todos os campos de atributos devem ser preenchidos.");
                }
        
                Personagem personagem = new Personagem(personagemId, nome, info, caminhoImagem, 1,
                        Integer.parseInt(campoForca.getText()), Integer.parseInt(campoDestreza.getText()),
                        Integer.parseInt(campoInteligencia.getText()), Integer.parseInt(campoConstituicao.getText()), Integer.parseInt(campoSabedoria.getText()), Integer.parseInt(campoCarisma.getText()));
                personagens.add(personagem);
                PersonagemDBDAO personagemDB = new PersonagemDBDAO();
                SistemaDBDAO sistemaDB = new SistemaDBDAO();
                Sistema sistema;
                try {
                    sistema = sistemaDB.buscaPorCodigo(1);
                } catch (SQLException ex) {
                    exibirAlerta("Erro de Banco de Dados", "Erro ao buscar classe no banco de dados.");
                    return;
                }
                ClasseDBDAO classeDB = new ClasseDBDAO();
                Classe classet;
                try {
                    classet = classeDB.buscaPorNome(classe);
                } catch (SQLException ex) {
                    exibirAlerta("Erro de Banco de Dados", "Erro ao buscar classe no banco de dados.");
                    return;
                }

                RacaDBDAO racaDB = new RacaDBDAO();
                Raca racat;
                try {
                    racat = racaDB.buscaPorNome(raca);
                } catch (SQLException ex) {
                    exibirAlerta("Erro de Banco de Dados", "Erro ao buscar raça no banco de dados.");
                    return;
                }
                PersonagemSistemaDBDAO personagemSistemaDB = new PersonagemSistemaDBDAO();
                PersonagemSistema personagemSistema = new PersonagemSistema(personagem, sistema, racat, classet);

                try {
                    personagemDB.insere(personagem);
                    personagemSistemaDB.insere(personagemSistema);
                    personagemDB.listar();
                } catch (SQLException ex) {
                    exibirAlerta("Erro de Banco de Dados", "Erro ao salvar personagem no banco de dados.");
                }
                stageAtributos.close();
            } catch (NumberFormatException ex) {
                exibirAlerta("Erro de Formato", "Os atributos devem ser números inteiros.");
            } catch (IllegalArgumentException ex) {
                exibirAlerta("Campos Vazios", ex.getMessage());
            }
        });

        grid.add(titulo, 0, 0);
        grid.add(new Label("Força:"), 0, 1);
        grid.add(campoForca, 1, 1);
        grid.add(new Label("Destreza:"), 0, 2);
        grid.add(campoDestreza, 1, 2);
        grid.add(new Label("Constituição:"), 0, 3);
        grid.add(campoConstituicao, 1, 3);
        grid.add(new Label("Inteligência:"), 0, 4);
        grid.add(campoInteligencia, 1, 4);
        grid.add(new Label("Sabedoria:"), 0, 5);
        grid.add(campoSabedoria, 1, 5);
        grid.add(new Label("Carisma:"), 0, 6);
        grid.add(campoCarisma, 1, 6);
        grid.add(btnSalvar, 1, 7);

        Scene cena = new Scene(grid, 400, 300);
        stageAtributos.setScene(cena);
        stageAtributos.show();
        }

        private void exibirTelaVerPersonagens() {
        Stage stageVisualizar = new Stage();
        stageVisualizar.setTitle("Personagens Cadastrados");

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(15));
        vbox.setStyle("-fx-background-color: #D3D3D3;");

        Label titulo = new Label("Personagens Cadastrados");
        titulo.setFont(Font.font("Arial", 18));
        titulo.setTextFill(Color.BLACK);
        vbox.getChildren().add(titulo);

        PersonagemSistemaDBDAO personagemSistemaDB = new PersonagemSistemaDBDAO();
        List<PersonagemSistema> personagensSistema;
        try {
            personagensSistema = personagemSistemaDB.listar();
        } catch (SQLException ex) {
            exibirAlerta("Erro de Banco de Dados", "Erro ao buscar personagens no banco de dados.");
            return;
        }

        if (personagensSistema.isEmpty()) {
            Label vazio = new Label("Nenhum personagem cadastrado.");
            vazio.setFont(Font.font("Arial", 18));
            vazio.setTextFill(Color.BLACK);
            vbox.getChildren().add(vazio);
        } else {
            Label taai = new Label("Personagens cadastrados:");
            for (PersonagemSistema personagemSistema : personagensSistema) {
            Label personagemLabel = new Label(personagemSistema.toString());
            personagemLabel.setFont(Font.font("Arial", 16));
            personagemLabel.setTextFill(Color.BLACK);
            vbox.getChildren().add(personagemLabel);
            }
        }

        Scene cena = new Scene(vbox, 400, 300);
        stageVisualizar.setScene(cena);
        stageVisualizar.show();
        }

        private void exibirTelaEditarPersonagens() {
            Stage stageEditar = new Stage();
            stageEditar.setTitle("Editar Personagens");

            VBox vbox = new VBox();
            vbox.setSpacing(10);
            vbox.setPadding(new Insets(15));
            vbox.setStyle("-fx-background-color: #D3D3D3;");

            PersonagemSistemaDBDAO personagemSistemaDB = new PersonagemSistemaDBDAO();
            List<PersonagemSistema> personagensSistema;
            try {
                personagensSistema = personagemSistemaDB.listar();
            } catch (SQLException ex) {
                exibirAlerta("Erro de Banco de Dados", "Erro ao buscar personagens no banco de dados.");
                return;
            }

            for (PersonagemSistema personagemSistema : personagensSistema) {
                Label personagemLabel = new Label(personagemSistema.toString());
                personagemLabel.setFont(Font.font("Arial", 16));
                personagemLabel.setTextFill(Color.BLACK);

                Button btnEditar = new Button("Editar");
                btnEditar.setOnAction(e -> {
                    Stage stageEditarPersonagem = new Stage();
                    stageEditarPersonagem.setTitle("Editar Personagem");

                    GridPane grid = new GridPane();
                    grid.setPadding(new Insets(20));
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setStyle("-fx-background-color: #D3D3D3;");

                    Label titulo = new Label("Editar Personagem");
                    titulo.setFont(Font.font("Arial", 18));
                    titulo.setStyle("-fx-text-fill: black;");
                    GridPane.setColumnSpan(titulo, 2);

                    TextField campoNome = new TextField(personagemSistema.getPersonagem().getNome());
                    ComboBox<String> campoClasse = new ComboBox<>();
                    ComboBox<String> campoRaca = new ComboBox<>();
                    campoRaca.getItems().addAll("Humano", "Elfo", "Anão", "Orc");
                    campoClasse.getItems().addAll("Guerreiro", "Mago", "Arqueiro", "Ladino");
                    campoRaca.setValue(personagemSistema.getRaca().getNomeRaca());
                    campoClasse.setValue(personagemSistema.getClasse().getNomeClasse());
                    TextField campoInfo = new TextField(personagemSistema.getPersonagem().getDescricao());
                    TextField campoImagem = new TextField(personagemSistema.getPersonagem().getUrlImg());
                    TextField campoForca = new TextField(String.valueOf(personagemSistema.getPersonagem().getForca()));
                    TextField campoDestreza = new TextField(String.valueOf(personagemSistema.getPersonagem().getDestreza()));
                    TextField campoInteligencia = new TextField(String.valueOf(personagemSistema.getPersonagem().getInteligencia()));
                    TextField campoConstituicao = new TextField(String.valueOf(personagemSistema.getPersonagem().getConstituicao()));
                    TextField campoSabedoria = new TextField(String.valueOf(personagemSistema.getPersonagem().getSabedoria()));
                    TextField campoCarisma = new TextField(String.valueOf(personagemSistema.getPersonagem().getCarisma()));

                    Button btnSalvar = new Button("Salvar Alterações");
                    btnSalvar.setFont(Font.font("Arial", 16));
                    btnSalvar.setTextFill(Color.WHITE);
                    btnSalvar.setStyle("-fx-background-color: #27ae60;");
                    btnSalvar.setOnAction(ev -> {
                        try {

                            Personagem personagemNovo = new Personagem(
                                personagemSistema.getPersonagem().getPersonagemId(),
                                campoNome.getText(),
                                campoInfo.getText(),
                                campoImagem.getText(),
                                personagemSistema.getPersonagem().getNivel(),
                                Integer.parseInt(campoForca.getText()),
                                Integer.parseInt(campoDestreza.getText()),
                                Integer.parseInt(campoInteligencia.getText()),
                                Integer.parseInt(campoConstituicao.getText()),
                                Integer.parseInt(campoSabedoria.getText()),
                                Integer.parseInt(campoCarisma.getText())
                            );

                            ClasseDBDAO classeDB = new ClasseDBDAO();
                            RacaDBDAO racaDB = new RacaDBDAO();
                            Classe classeNova = classeDB.buscaPorNome(campoClasse.getValue());
                            Raca racaNova = racaDB.buscaPorNome(campoRaca.getValue());

                            PersonagemSistema personagemSistemaNovo = new PersonagemSistema(
                                personagemNovo,
                                personagemSistema.getSistema(),
                                racaNova,
                                classeNova
                            );
                            personagemSistemaDB.atualizar(personagemSistema, personagemSistemaNovo);
                            stageEditarPersonagem.close();
                            stageEditar.close();
                            exibirTelaEditarPersonagens();
                        } catch (NumberFormatException ex) {
                            exibirAlerta("Erro de Formato", "Os atributos devem ser números inteiros.");
                        } 
                        catch (SQLException ex) {
                            exibirAlerta("Erro de Banco de Dados", "Erro ao atualizar personagem no banco de dados.");
                        }
                    });

                    grid.add(titulo, 0, 0);
                    grid.add(new Label("Nome:"), 0, 1);
                    grid.add(campoNome, 1, 1);
                    grid.add(new Label("Classe:"), 0, 2);
                    grid.add(campoClasse, 1, 2);
                    grid.add(new Label("Raça:"), 0, 3);
                    grid.add(campoRaca, 1, 3);
                    grid.add(new Label("Descrição:"), 0, 4);
                    grid.add(campoInfo, 1, 4);
                    grid.add(new Label("Caminho da Imagem:"), 0, 5);
                    grid.add(campoImagem, 1, 5);
                    grid.add(new Label("Força:"), 0, 6);
                    grid.add(campoForca, 1, 6);
                    grid.add(new Label("Destreza:"), 0, 7);
                    grid.add(campoDestreza, 1, 7);
                    grid.add(new Label("Constituição:"), 0, 8);
                    grid.add(campoConstituicao, 1, 8);
                    grid.add(new Label("Inteligência:"), 0, 9);
                    grid.add(campoInteligencia, 1, 9);
                    grid.add(new Label("Sabedoria:"), 0, 10);
                    grid.add(campoSabedoria, 1, 10);
                    grid.add(new Label("Carisma:"), 0, 11);
                    grid.add(campoCarisma, 1, 11);
                    grid.add(btnSalvar, 1, 12);

                    Scene cena = new Scene(grid, 400, 500);
                    stageEditarPersonagem.setScene(cena);
                    stageEditarPersonagem.show();
                });

                HBox linha = new HBox(10, personagemLabel, btnEditar);
                vbox.getChildren().add(linha);
            }

            Scene cena = new Scene(vbox, 400, 300);
            stageEditar.setScene(cena);
            stageEditar.show();
        }

}
