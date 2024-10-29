package com.trabalhojava.sistemarpg.dao;

import com.trabalhojava.sistemarpg.model.Classe;
import com.trabalhojava.sistemarpg.model.Raca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClasseDBDAO implements ClasseDAO, IConst{
    private String sql;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    public ClasseDBDAO() {}

    private void open() throws SQLException {
        this.connection = Conexao.getConexao("jdbc:postgresql://localhost:5432/sistemarpg", "postgres", "postgres");
    }

    private void close() throws SQLException {
        this.connection.close();
    }
    public void insere(Classe classe) throws SQLException {
        this.open();
        this.sql = "INSERT INTO classe(classeId, nomeClasse, descricao, hpInicial, hpNivel, nivelMax, sistemaId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        this.statement = this.connection.prepareStatement(this.sql);
        this.statement.setInt(1,classe.getClasseId());
        this.statement.setString(2,classe.getNomeClasse());
        this.statement.setString(3,classe.getDescricao());
        this.statement.setInt(4,classe.getHpInicial());
        this.statement.setInt(5,classe.getHpNivel());
        this.statement.setInt(6,classe.getNivelMax());
        this.statement.setInt(7,classe.getSistemaId());
        this.statement.executeUpdate();
        this.close();
    }

    public void atualizar(Classe classe) throws SQLException {
        this.open();
        this.sql = "UPDATE classe SET nomeClasse=?, descricao=?, hpInicial=?, hpNivel=?, nivelMax=?, sistemaId=? WHERE classeId=?";
        this.statement = this.connection.prepareStatement(this.sql);
        this.statement.setString(1, classe.getNomeClasse());
        this.statement.setString(2, classe.getDescricao());
        this.statement.setInt(3, classe.getHpInicial());
        this.statement.setInt(4, classe.getHpNivel());
        this.statement.setInt(5, classe.getNivelMax());
        this.statement.setInt(6, classe.getSistemaId());
        this.statement.setInt(7, classe.getClasseId());
        this.statement.executeUpdate();
        this.close();
    }

    public void remover(Classe classe) throws SQLException {
        this.open();
        this.sql = "DELETE FROM classe WHERE classeId=?";
        this.statement = connection.prepareStatement(sql);
        this.statement.setInt(1,classe.getClasseId());
        this.statement.executeUpdate();
        this.close();
    }

    public List<Classe> listar() throws SQLException {
        this.open();
        this.sql = "SELECT * FROM classe";
        this.statement = this.connection.prepareStatement(this.sql);
        this.result = this.statement.executeQuery();
        ArrayList<Classe> classes = new ArrayList<>();

        while (this.result.next()) {
            Classe classe = new Classe();
            classe.setClasseId(this.result.getInt("classeId"));
            classe.setNomeClasse(this.result.getString("nomeClasse"));
            classe.setDescricao(this.result.getString("descricao"));
            classe.setHpInicial(this.result.getInt("hpInicial"));
            classe.setHpNivel(this.result.getInt("hpNivel"));
            classe.setNivelMax(this.result.getInt("nivelMax"));
            classe.setSistemaId(this.result.getInt("sistemaId"));
            classes.add(classe);
        }
        this.close();
        return classes;
    }
}
