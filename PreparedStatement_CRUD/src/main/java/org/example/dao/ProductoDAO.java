package org.example.dao;

import org.example.config.ConexionDB;
import org.example.dto.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private Connection conexion;

    private static final String SQL_INSERT = "INSERT INTO producto (nombreProducto,descripcionProducto,precioProducto,existencia,create_at,idCategoria) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE producto SET nombreProducto=?, descripcionProducto=?, precioProducto=?, existencia=? WHERE idProducto=?";
    private static final String SQL_DELETE = "DELETE FROM producto WHERE idProducto=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM producto";
    private static final String SQL_SELECT = "SELECT * FROM producto WHERE idProducto=?";

    private void Conectarse() throws SQLException {
        conexion = ConexionDB.getConexion();
    }

    private void Desconectarse() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }

    private List<Producto> ObtenerResultados(ResultSet rs) throws SQLException {
        List<Producto> lista = new ArrayList<>();

        while (rs.next()) {
            Producto producto = new Producto();
            producto.setIdProducto(rs.getInt("idProducto"));
            producto.setNombreProducto(rs.getString("nombreProducto"));
            producto.setDescripcionProducto(rs.getString("descripcionProducto"));
            producto.setPrecioProducto(rs.getDouble("precioProducto"));
            producto.setExistencia(rs.getInt("existencia"));
            producto.setCreate_at(rs.getDate("create_at"));
            producto.setIdCategoria(rs.getInt("idCategoria"));
            lista.add(producto);
        }
        return lista;
    }

    public void Create(Producto p) throws SQLException {
        Conectarse();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, p.getNombreProducto());
            ps.setString(2, p.getDescripcionProducto());
            ps.setDouble(3, p.getPrecioProducto());
            ps.setInt(4, p.getExistencia());
            ps.setDate(5, new java.sql.Date(p.getCreate_at().getTime()));
            ps.setInt(6, p.getIdCategoria());
            ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            Desconectarse();
        }
    }

    public List<Producto> readAll() throws SQLException {
        Conectarse();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Producto> lista = new ArrayList<>();

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

    public Producto read(int id) throws SQLException {
        Conectarse();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conexion.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            List<Producto> lista = ObtenerResultados(rs);

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

    public void Update(Producto p) throws SQLException {
        Conectarse();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, p.getNombreProducto());
            ps.setString(2, p.getDescripcionProducto());
            ps.setDouble(3, p.getPrecioProducto());
            ps.setInt(4, p.getExistencia());
            ps.setInt(5, p.getIdProducto());
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