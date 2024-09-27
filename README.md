# Proyecto de Microservicios con Spring Boot WebFlux - cashout

Este proyecto es una aplicación construida utilizando Spring Boot WebFlux y microservicios. Permite a los usuarios realizar cashouts de productos, consultar su historial de transacciones, y manejar pagos. El proyecto está basado en una arquitectura de microservicios, y usa MongoDB para el almacenamiento de datos.

## Arquitectura

El sistema está compuesto por varios servicios:

- **User Service**: Maneja la creación y actualización de usuarios.
- **Cashout Service**: Administra los cashouts de los usuarios.
- **Payment Service**: Procesa pagos para cashouts.
- **Transaction History Service**: Provee el historial de transacciones para cada usuario.

### Flujo General del Cashout
1. El usuario realiza una solicitud de cashout.
2. Se verifica si el usuario tiene suficiente balance.
3. Si es aprobado, se procesa el pago a través del Payment Service.
4. Se actualiza el balance del usuario y se guarda la transacción en el historial.

## Endpoints

### User Endpoints
- **GET /users/{id}**: Obtiene los detalles del usuario.
  - **Ejemplo de Respuesta**:
  ```json
  {
    "id": 1,
    "name": "John Doe",
    "balance": 100.0
  }

- **POST /users**: Crea un nuevo usuario en el sistema.
  - **Ejemplo de Request**:
  ```json
  {
    "name": "John Doe",
    "balance": 100.0
  }
  ```
  - **Ejemplo de Respuesta**:
  ```json
  {
    "id": "1",
    "name": "John Doe",
    "balance": 100.0
  }
  ```
- **PUT /users/{idUser}/balance**: Actualiza el balance de un usuario.
  - **Ejemplo de Request**:
  ```json
  {
    "amount": 50.0
  }
  ```
  - **Ejemplo de Respuesta**:
  ```json
  {
    "id": "1",
    "name": "John Doe",
    "balance": 150.0
  }
  ```

### Cashout Endpoints
- **POST /cashouts**: Crea un nuevo cashout para un usuario.
  - **Ejemplo de Request**:
  ```json
  {
    "userId": "1",
    "amount": 50.0
  }
  ```
  - **Ejemplo de Respuesta**:
  ```json
  {
    "id": "1",
    "name": "John Doe",
    "balance": 50.0
  }
  ```
  - **GET /cashouts/user/{userId}**: Obtiene todos los cashouts realizados por un usuario específico.
  - **Ejemplo de Respuesta**:
  ```json
  [
    {
        "id": "1",
        "userId": "1",
        "amount": 50.0
    },
    {
        "id": "2",
        "userId": "1",
        "amount": 30.0
    }
  ]

### Payment Endpoints
- **POST /payments**: Procesa el pago de un cashout.
  - **Ejemplo de Request**:
  ```json
  {
    "userId": "1",
    "amount": 50.0
  }
  ```
  - **Ejemplo de Respuesta**:
  ```json
  {
    "userId": "1",
    "paymentStatus": "approved"
  }

  ### Transaction History Endpoints
- **GET /transaction-history/user/{userId}**: Obtiene el historial de transacciones de un usuario específico.
 
  - **Ejemplo de Respuesta**:
  ```json
  [
    {
        "transactionId": "1",
        "userId": "1",
        "amount": 50.0,
        "type": "cashout"
    },
    {
        "transactionId": "2",
        "userId": "1",
        "amount": 30.0,
        "type": "cashout"
    }
  ]

## Diagrama de Secencia

El diagrama de secuencia a continuación muestra el flujo de las operaciones para un cashout. El usuario inicia solicitando un cashout, el sistema verifica el balance del usuario, realiza la actualización del balance, llama a un microservicio externo de pagos y finalmente crea un registro de cashout.

![image](https://github.com/user-attachments/assets/86ef652b-a080-4101-a6c0-79393c68bcc5)

