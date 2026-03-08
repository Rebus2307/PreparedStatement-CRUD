package org.example;

import org.example.dao.CategoriaDAO;
import org.example.dao.ProductoDAO;
import org.example.dto.Categoria;
import org.example.dto.CategoriaDTO;
import org.example.dto.Producto;
import org.example.dto.ProductoDTO;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            ProductoDAO productoDAO = new ProductoDAO();

            System.out.println("===== CREATE CATEGORIA =====");
            Categoria cat = new Categoria();
            cat.setNombreCategoria("Redes");
            cat.setDescripcionCategoria("Equipos de telecomunicación");
            cat.setCreate_at(Date.valueOf(LocalDate.now()));

            CategoriaDTO catDTO = new CategoriaDTO(cat);
            categoriaDAO.Create(catDTO.getEntidad());
            System.out.println("Categoría creada exitosamente.");

            System.out.println("\n===== READ ALL CATEGORIAS =====");
            List<Categoria> listaCategorias = categoriaDAO.readAll();
            if (listaCategorias != null) {
                for (Categoria c : listaCategorias) {
                    System.out.println(c);
                }
            } else {
                System.out.println("No se encontraron categorías.");
            }

            System.out.println("\n===== READ CATEGORIA BY ID =====");
            Categoria catEncontrada = categoriaDAO.read(1);
            if (catEncontrada != null) {
                System.out.println("Categoría encontrada: " + catEncontrada);
            } else {
                System.out.println("No se encontró la categoría con ID 1.");
            }

            System.out.println("\n===== UPDATE CATEGORIA =====");
            if (catEncontrada != null) {
                catEncontrada.setNombreCategoria("Redes Actualizadas");
                catEncontrada.setDescripcionCategoria("Equipos de red modernos");
                categoriaDAO.Update(catEncontrada);
                System.out.println("Categoría actualizada exitosamente.");

                Categoria catActualizada = categoriaDAO.read(catEncontrada.getIdCategoria());
                System.out.println("Verificación: " + catActualizada);
            }

            System.out.println("\n===== DELETE CATEGORIA =====");
            if (catEncontrada != null) {
                categoriaDAO.Delete(catEncontrada.getIdCategoria());
                System.out.println("Categoría eliminada exitosamente (ID: " + catEncontrada.getIdCategoria() + ").");

                Categoria catEliminada = categoriaDAO.read(catEncontrada.getIdCategoria());
                if (catEliminada == null) {
                    System.out.println("Verificación: La categoría ya no existe.");
                }
            }

            // Crear una categoría para asociar los productos (FK)
            Categoria catParaProducto = new Categoria();
            catParaProducto.setNombreCategoria("Electrónica");
            catParaProducto.setDescripcionCategoria("Dispositivos electrónicos");
            catParaProducto.setCreate_at(Date.valueOf(LocalDate.now()));
            categoriaDAO.Create(catParaProducto);

            // Obtener la categoría recién creada
            List<Categoria> cats = categoriaDAO.readAll();
            Categoria catBase = cats.get(cats.size() - 1);
            System.out.println("\nCategoría base para productos: " + catBase);

            System.out.println("\n===== CREATE PRODUCTO =====");
            Producto prod = new Producto();
            prod.setNombreProducto("Router TP-Link");
            prod.setDescripcionProducto("Router inalámbrico doble banda");
            prod.setPrecioProducto(899.99);
            prod.setExistencia(50);
            prod.setCreate_at(Date.valueOf(LocalDate.now()));
            prod.setIdCategoria(catBase.getIdCategoria());

            ProductoDTO prodDTO = new ProductoDTO(prod);
            productoDAO.Create(prodDTO.getEntidad());
            System.out.println("Producto creado exitosamente.");

            System.out.println("\n===== READ ALL PRODUCTOS =====");
            List<Producto> listaProductos = productoDAO.readAll();
            if (listaProductos != null) {
                for (Producto p : listaProductos) {
                    System.out.println(p);
                }
            } else {
                System.out.println("No se encontraron productos.");
            }

            System.out.println("\n===== READ PRODUCTO BY ID =====");
            Producto prodEncontrado = productoDAO.read(1);
            if (prodEncontrado != null) {
                System.out.println("Producto encontrado: " + prodEncontrado);
            } else {
                System.out.println("No se encontró el producto con ID 1.");
            }

            System.out.println("\n===== UPDATE PRODUCTO =====");
            if (prodEncontrado != null) {
                prodEncontrado.setNombreProducto("Router TP-Link Actualizado");
                prodEncontrado.setDescripcionProducto("Router WiFi 6 triple banda");
                prodEncontrado.setPrecioProducto(1299.99);
                prodEncontrado.setExistencia(30);
                productoDAO.Update(prodEncontrado);
                System.out.println("Producto actualizado exitosamente.");

                Producto prodActualizado = productoDAO.read(prodEncontrado.getIdProducto());
                System.out.println("Verificación: " + prodActualizado);
            }

            System.out.println("\n===== DELETE PRODUCTO =====");
            if (prodEncontrado != null) {
                productoDAO.Delete(prodEncontrado.getIdProducto());
                System.out.println("Producto eliminado exitosamente (ID: " + prodEncontrado.getIdProducto() + ").");

                Producto prodEliminado = productoDAO.read(prodEncontrado.getIdProducto());
                if (prodEliminado == null) {
                    System.out.println("Verificación: El producto ya no existe.");
                }
            }

            // Limpiar: eliminar la categoría base usada para productos
            categoriaDAO.Delete(catBase.getIdCategoria());
            System.out.println("\nCategoría base eliminada (ID: " + catBase.getIdCategoria() + ").");

            System.out.println("\n===== LISTADO FINAL CATEGORIAS =====");
            List<Categoria> catFinal = categoriaDAO.readAll();
            if (catFinal != null) {
                for (Categoria c : catFinal) {
                    System.out.println(c);
                }
            } else {
                System.out.println("No hay categorías en la tabla.");
            }

            System.out.println("\n===== LISTADO FINAL PRODUCTOS =====");
            List<Producto> prodFinal = productoDAO.readAll();
            if (prodFinal != null) {
                for (Producto p : prodFinal) {
                    System.out.println(p);
                }
            } else {
                System.out.println("No hay productos en la tabla.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
