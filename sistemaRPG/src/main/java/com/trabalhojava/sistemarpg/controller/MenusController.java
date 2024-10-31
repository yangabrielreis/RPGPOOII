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
        btnProximo.setOnAction(e -> exibirTelaDefinirAtributos(campoNome.getText(), campoClasse.getValue(), campoRaca.getValue(), campoInfo.getText(), campoImagem.getText(), stageCaracteristicas));

        grid.add(titulo, 0, 0);
        grid.add(new Label("Nome:"), 0, 1);
        grid.add(campoNome, 1, 1);
        grid.add(new Label("Classe:"), 0, 2);
        grid.add(campoClasse, 1, 2);
        grid.add(new Label("Raça:"), 0, 3);
        grid.add(campoRaca, 1, 3);
        grid.add(new Label("Descrição':"), 0, 4);
        grid.add(campoInfo, 1, 4);
        grid.add(new Label("Caminho da Imagem:"), 0, 5);
        grid.add(campoImagem, 1, 5);
        grid.add(btnProximo, 1, 6);
        

        Scene cena = new Scene(grid, 400, 300);
        stageCaracteristicas.setScene(cena);
        stageCaracteristicas.show();
    }

    private void exibirTelaDefinirAtributos(String nome, String classe, String raca, String info, String caminhoImagem, Stage telaAnterior) {
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
        
                Personagem personagem = new Personagem(nome, classe, raca, info, caminhoImagem,
                        Integer.parseInt(campoForca.getText()), Integer.parseInt(campoDestreza.getText()),
                        Integer.parseInt(campoInteligencia.getText()), Integer.parseInt(campoConstituicao.getText()), Integer.parseInt(campoSabedoria.getText()), Integer.parseInt(campoCarisma.getText()));
                personagens.add(personagem);
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

        if (personagens.isEmpty()) {
            Label vazio = new Label("Nenhum personagem cadastrado.");
            vazio.setFont(Font.font("Arial", 18));
            vazio.setTextFill(Color.BLACK);
            vbox.getChildren().add(vazio);
        } else {
            for (Personagem personagem : personagens) {
                Label personagemLabel = new Label(personagem.toString());
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

        for (Personagem personagem : personagens) {
            Label personagemLabel = new Label(personagem.toString());
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

                TextField campoNome = new TextField(personagem.nome);
                //TextField campoClasse = new TextField(personagem.classe);
                //TextField campoRaca = new TextField(personagem.raca);
                ComboBox<String> campoClasse = new ComboBox<>();
                ComboBox<String> campoRaca = new ComboBox<>();
                campoRaca.getItems().addAll("Humano", "Elfo", "Anão", "Orc");
                campoClasse.getItems().addAll("Guerreiro", "Mago", "Arqueiro", "Ladino");
                campoRaca.setValue(personagem.raca);
                campoClasse.setValue(personagem.classe);
                TextField campoInfo = new TextField(personagem.info);
                TextField campoImagem = new TextField(personagem.caminhoImagem);
                TextField campoForca = new TextField(String.valueOf(personagem.forca));
                TextField campoDestreza = new TextField(String.valueOf(personagem.destreza));
                TextField campoInteligencia = new TextField(String.valueOf(personagem.inteligencia));
                TextField campoConstituicao = new TextField(String.valueOf(personagem.constituicao));
                TextField campoSabedoria = new TextField(String.valueOf(personagem.sabedoria));
                TextField campoCarisma = new TextField(String.valueOf(personagem.carisma));

                Button btnSalvar = new Button("Salvar Alterações");
                btnSalvar.setFont(Font.font("Arial", 16));
                btnSalvar.setTextFill(Color.WHITE);
                btnSalvar.setStyle("-fx-background-color: #27ae60;");
                btnSalvar.setOnAction(ev -> {
                    try {
                        personagem.setNome(campoNome.getText());
                        personagem.setClasse(campoClasse.getValue());
                        personagem.setRaca(campoRaca.getValue());
                        personagem.setInfo(campoInfo.getText());
                        personagem.setCaminhoImagem(campoImagem.getText());
                        personagem.setForca(Integer.parseInt(campoForca.getText()));
                        personagem.setDestreza(Integer.parseInt(campoDestreza.getText()));
                        personagem.setInteligencia(Integer.parseInt(campoInteligencia.getText()));
                        personagem.setConstituicao(Integer.parseInt(campoConstituicao.getText()));
                        personagem.setSabedoria(Integer.parseInt(campoSabedoria.getText()));
                        personagem.setCarisma(Integer.parseInt(campoCarisma.getText()));
                        stageEditarPersonagem.close();
                        stageEditar.close();
                    } catch (NumberFormatException ex) {
                        exibirAlerta("Erro de Formato", "Os atributos devem ser números inteiros.");
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

    class Personagem {
        String nome;
        String classe;
        String raca;
        String info;
        String caminhoImagem;
        int forca;
        int destreza;
        int constituicao;
        int inteligencia;
        int sabedoria;
        int carisma;

        Personagem(String nome, String classe, String raca, String info, String caminhoImagem, int forca, int destreza, int inteligencia, int constituicao, int sabedoria, int carisma) {
            this.nome = nome;
            this.classe = classe;
            this.raca = raca;
            this.info = info;
            this.caminhoImagem = caminhoImagem;
            this.forca = forca;
            this.destreza = destreza;
            this.inteligencia = inteligencia;
            this.constituicao = constituicao;
            this.sabedoria = sabedoria;
            this.carisma = carisma;

        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setClasse(String classe) {
            this.classe = classe;
        }

        public void setRaca(String raca) {
            this.raca = raca;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public void setCaminhoImagem(String caminhoImagem) {
            this.caminhoImagem = caminhoImagem;
        }

        public void setForca(int forca) {
            this.forca = forca;
        }

        public void setDestreza(int destreza) {
            this.destreza = destreza;
        }

        public void setInteligencia(int inteligencia) {
            this.inteligencia = inteligencia;
        }

        public void setConstituicao(int constituicao) {
            this.constituicao = constituicao;
        }

        public void setSabedoria(int sabedoria) {
            this.sabedoria = sabedoria;
        }

        public void setCarisma(int carisma) {
            this.carisma = carisma;
        }

        @Override
        public String toString() {
            return String.format("Nome: %s \n Classe: %s \n Raça: %s \n Força: %d \n Destreza: %d \n Constituição: %d \n Inteligência: %d \n Sabedoria: %d \n Carisma: %d\n\n",
                    nome, classe, raca, forca, destreza, constituicao, inteligencia, sabedoria, carisma);
        }
    }
}
