
# 📦 PreparedStatement CRUD en Java

**Instituto Politécnico Nacional - Escuela Superior de Cómputo (ESCOM)**
**Materia:** Web Client and Backend Development Frameworks
**Alumno:** Wilfrido Cruz Merlin
**Grupo:** 7CM3

---

## 📝 Descripción del Proyecto
Este proyecto es una implementación completa de un CRUD (Crear, Leer, Actualizar y Eliminar) en Java, utilizando la API JDBC. El objetivo principal es la gestión de dos entidades (`Categoria` y `Producto`) aplicando buenas prácticas de desarrollo de software.

Se destacan las siguientes características:
* Implementación de los patrones arquitectónicos **DAO (Data Access Object)** y **DTO (Data Transfer Object)**.
* Uso exclusivo de la interfaz `PreparedStatement` para prevenir vulnerabilidades de Inyección SQL.
* Gestión de dependencias con **Maven**.

## 🛠️ Tecnologías y Requisitos
* **Java:** JDK 25
* **Base de Datos:** MySQL (8.x)
* **IDE Recomendado:** IntelliJ IDEA
* **Dependencias:** `mysql-connector-java` (8.0.33) y `lombok`

---

## 🚀 Guía de Instalación y Ejecución

Sigue estos pasos para clonar y ejecutar el proyecto en tu entorno local.

### 1. Clonar el repositorio
Abre tu terminal y ejecuta el siguiente comando:
```bash
git clone https://github.com/Rebus2307/PreparedStatement-CRUD.git

```

### 2. Configurar la Base de Datos

Antes de correr el proyecto, es necesario crear la base de datos y las tablas en MySQL. Abre tu gestor de base de datos (Workbench o terminal) y ejecuta el siguiente script:

```sql
-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS inventario;
USE inventario;

-- Crear la tabla categoria (debe ir primero por la llave foránea)
CREATE TABLE categoria (
    idCategoria INT AUTO_INCREMENT PRIMARY KEY,
    nombreCategoria VARCHAR(100) NOT NULL,
    descripcionCategoria VARCHAR(255),
    create_at DATE
);

-- Crear la tabla producto
CREATE TABLE producto (
    idProducto INT AUTO_INCREMENT PRIMARY KEY,
    nombreProducto VARCHAR(100) NOT NULL,
    descripcionProducto VARCHAR(255),
    precioProducto DECIMAL(10, 2) NOT NULL,
    existencia INT NOT NULL,
    create_at DATE,
    idCategoria INT NOT NULL,
    FOREIGN KEY (idCategoria) REFERENCES categoria(idCategoria)
);

```

### 3. Configurar Credenciales de MySQL

Abre el proyecto en IntelliJ IDEA. Dirígete a la clase de configuración de la base de datos:
`src/main/java/mx/ipn/escom/web/config/ConexionDB.java`

Verifica que el usuario y la contraseña coincidan con los de tu instalación local de MySQL:

```java
private static final String USER = "root"; // Tu usuario
private static final String PASS = "tu_password"; // Tu contraseña

```

### 4. Ejecutar la Aplicación

1. Espera a que Maven descargue las dependencias (si no lo hace, haz clic en *Reload All Maven Projects*).
2. Abre la clase `Main.java`.
3. Haz clic derecho y selecciona **Run 'Main.main()'**.

---

## 🖥️ Resultados de Ejecución

A continuación, se muestra una captura de pantalla con el resultado esperado en la terminal al ejecutar todas las operaciones CRUD (Create, Read, Update, Delete) para Categorías y Productos:

<img width="1762" height="896" alt="imagen" src="https://github.com/user-attachments/assets/f1b6bd0c-7e29-4d74-b6c6-242a3a37a31e" />
