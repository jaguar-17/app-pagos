# 🧾 Backend - Sistema Web de Procesos de Pagos con Spring Boot y MySQL

## 📘 Descripción General
El **Sistema Web de Procesos de Pagos** es una aplicación backend desarrollada con **Spring Boot** y **MySQL**, que permite registrar pagos, consultar el historial de transacciones y gestionar métodos de pago de manera modular y extensible.

El proyecto está diseñado como un caso académico para demostrar la correcta aplicación de los **principios SOLID**, en particular:
- **SRP (Single Responsibility Principle)**
- **OCP (Open/Closed Principle)**
- **DIP (Dependency Inversion Principle)**

---

## 🎯 Objetivos
- Implementar un backend funcional para la gestión de pagos.
- Aplicar los principios SOLID en una arquitectura limpia.
- Permitir la extensión del sistema con nuevos métodos de pago sin modificar el código existente.
- Facilitar la comprensión de la inversión de control (IoC) y la inyección de dependencias en Spring Boot.

---

## ⚙️ Tecnologías Utilizadas
| Componente | Tecnología |
|-------------|-------------|
| Lenguaje | Java 17 |
| Framework | Spring Boot 3.x |
| Base de Datos | MySQL 8 |
| ORM | Spring Data JPA |
| Herramienta de construcción | Maven |
| IDE recomendado | IntelliJ IDEA / VSCode / Eclipse |
| Control de versiones | Git |

---

## 🧩 Principios SOLID implementados

### 1️⃣ SRP (Single Responsibility Principle)
Cada clase tiene una única responsabilidad:
- Entidades → Representación del modelo de datos.
- Repositorios → Acceso a base de datos.
- Servicios → Lógica de negocio.
- Controladores → Exposición de endpoints REST.

### 2️⃣ OCP (Open/Closed Principle)
El sistema permite **agregar nuevos métodos de pago sin modificar el código existente**:
- La interfaz `MetodoPagoStrategy` define el comportamiento general.
- Cada método (Tarjeta, Yape, Transferencia, etc.) se implementa como una clase `@Component`.
- Al agregar una nueva estrategia, como `PagoPlin`, el sistema la detecta automáticamente.

### 3️⃣ DIP (Dependency Inversion Principle)
Las dependencias de alto nivel (`PagoServiceImpl`, `PagoController`) se definen sobre **interfaces**:
- El controlador depende de `IPagoService`, no de una implementación concreta.
- El servicio recibe un `Map<String, MetodoPagoStrategy>` inyectado por Spring (IoC), cumpliendo el principio de inversión de dependencias.

---

## 📋 Requerimientos Funcionales

| Código | Descripción |
|--------|--------------|
| **RF1** | Registrar un pago: el sistema permite registrar un nuevo pago, indicando método, monto y usuario. |
| **RF2** | Consultar historial de pagos: el usuario o administrador puede listar todos los pagos registrados. |
| **RF3** | Agregar nuevos métodos de pago: el administrador puede agregar métodos sin modificar el código existente. |

---

## 🧱 Modelo de Datos (Entidades Principales)

| Entidad | Descripción                                                      |
|----------|------------------------------------------------------------------|
| **Usuario** | Persona que realiza o administra pagos.                          |
| **Rol** | Permite distinguir entre cliente y administrador.                |
| **MetodoPago** | Define el tipo de pago (tarjeta, Yape, transferencia, etc.).     |
| **Pago** | Transacción realizada por un usuario mediante un método de pago. |

---

## 🛠️ Instalación y Configuración

### 1. Clonar el repositorio
```bash
git clone https://github.com/jaguar-17/app-pagos.git
cd app-pagos
```

### 2. Crear la base de datos MySQL
```sql
CREATE DATABASE pagos_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3. Configurar credenciales
Editar el archivo `src/main/resources/application.properties`:
```properties
server.port=tu_puerto_preferido
spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_datos?useSSL=false&serverTimezone=UTC
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

### 4. Ejecutar la aplicación
```bash
mvn spring-boot:run
```

La aplicación se ejecutará por defecto en:
```
http://localhost:8080
```

---

## 🚀 Endpoints Principales

| Método | Endpoint            | Descripción | Ejemplo de Uso                                                            |
|---------|---------------------|--------------|---------------------------------------------------------------------------|
| `POST` | `/api/pagos`        | Registrar un nuevo pago (RF1) | `{ "monto": 150.5, "usuario": {"id":1}, "metodoPago": {"tipo": "yape"} }` |
| `GET` | `/api/pagos`        | Consultar historial de pagos (RF2) | —                                                                         |
| `POST` | `/api/metodos-pago` | Agregar nuevo método de pago (RF3) | `{ "tipo": "efectivo" }`                                                  |

---

## 🧠 Ejemplo de Flujo de Pruebas en Postman

1. **Crear método de pago (RF3):**
   ```json
   POST /api/metodos
   {
     "tipo": "yape"
   }
   ```
2. **Registrar un pago (RF1):**
   ```json
   POST /api/pagos
   {
     "monto": 250.75,
     "usuario": {"id": 1},
     "metodoPago": {"tipo": "yape"}
   }
   ```
3. **Consultar historial (RF2):**
   ```bash
   GET /api/pagos
   ```

---

## 🧩 Datos iniciales (DataLoader)
El archivo `DataLoader.java` crea automáticamente:
- Roles: `ADMIN`, `CLIENTE`
- Usuario demo: `Usuario Demo`
- Métodos: `tarjeta`, `yape`, `transferencia`

Esto permite probar la aplicación sin ingresar datos manualmente.

---

## 🧠 Ejemplo de Extensión (OCP)
Para agregar un nuevo método de pago (por ejemplo, plin):

1. Crear clase:
```java
@Component("plin")
public class PagoPlin implements MetodoPagoStrategy {
    @Override
    public void procesarPago(Double monto) {
        System.out.println("Procesando pago en plin por un monto de: S/." + monto);
    }
    @Override
    public String getTipo() { 
        return "plin"; 
    }
}
```
2. Reiniciar la aplicación.
3. Ya puedes registrar pagos con:
```json
{ "monto": 99.99, "usuario": {"id":1}, "metodoPago": {"tipo":"plin"} }
```

Sin modificar el código existente → cumple OCP.

---

## 📦 Estructura del Proyecto

```
src/
 ├── main/
 │   ├── java/com/pagos/
 │   │   ├── models/          → Entidades JPA
 │   │   ├── repositories/     → Repositorios JPA
 │   │   ├── services/        → Lógica de negocio e interfaces
 │   │   │   └── estrategies/ → Clases concretas de métodos de pago
 │   │   ├── controllers/     → Endpoints REST
 │   │   └── config/         → DataLoader (datos iniciales)
 │   └── resources/
 │       └── application.properties
 └── pom.xml
```

---

## 🧑‍💻 Autor y Uso Académico
Proyecto desarrollado con fines **académicos y demostrativos** como ejemplo de aplicación de los **principios SOLID** en un entorno web moderno.  
Autor: **Solano Liberato Isaias Leandro**  
Versión: **1.0.0 — Octubre 2025**
