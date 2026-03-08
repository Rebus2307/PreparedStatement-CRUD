package org.example.dao;

import org.example.config.ConexionDB;
import org.example.dto.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Connection conexion;

    private static final String SQL_INSERT = "INSERT INTO categoria (nombreCategoria,descripcionCategoria,create_at) VALUES (?,?,?)";
    private static final String SQL_UPDATE = "UPDATE categoria SET nombreCategoria=?, descripcionCategoria=? WHERE idCategoria=?";
    private static final String SQL_DELETE = "DELETE FROM categoria WHERE idCategoria=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM categoria";
    private static final String SQL_SELECT = "SELECT * FROM categoria WHERE idCategoria=?";

    private void Conectarse() throws SQLException {
        conexion = ConexionDB.getConexion();
    }

    private void Desconectarse() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }

    private List<Categoria> ObtenerResultados(ResultSet rs) throws SQLException {
        List<Categoria> lista = new ArrayList<>();

        while (rs.next()) {
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(rs.getInt("idCategoria"));
            categoria.setNombreCategoria(rs.getString("nombreCategoria"));
            categoria.setDescripcionCategoria(rs.getString("descripcionCategoria"));
            categoria.setCreate_at(rs.getDate("create_at"));
            lista.add(categoria);
        }
        return lista;
    }

    public void Create(Categoria c) throws SQLException {
        Conectarse();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, c.getNombreCategoria());
            ps.setString(2, c.getDescripcionCategoria());
            ps.setDate(3, new java.sql.Date(c.getCreate_at().getTime()));
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            Desconectarse();
        }
    }

    public List<Categoria> readAll() throws SQLException {
        Conectarse();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Categoria> lista = new ArrayList<>();

        try {
            ps = conexion.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();

            lista = ObtenerResultados(rs);

            if (lista.size() > 0) {
                return lista;
            } else {
                return null;
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            Desconectarse();
        }
    }

    public Categoria read(int id) throws SQLException {
        Conectarse();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conexion.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            List<Categoria> lista = ObtenerResultados(rs);

            if (lista.size() > 0) {
                return lista.get(0);
            } else {
                return null;
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            Desconectarse();
        }
    }

    public void Update(Categoria c) throws SQLException {
        Conectarse();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombreCategoria());
            ps.setString(2, c.getDescripcionCategoria());
            ps.setInt(3, c.getIdCategoria());
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            Desconectarse();
        }
    }

    public void Delete(int id) throws SQLException {
        Conectarse();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            Desconectarse();
        }
    }
}