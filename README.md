# Alke Wallet

Aplicación Android desarrollada en Kotlin que simula las funciones básicas de una billetera digital. Permite iniciar sesión, registrar una cuenta, consultar el perfil, visualizar el saldo, ingresar dinero, retirar dinero y cerrar sesión.

## Tecnologías utilizadas

- Kotlin.
- Arquitectura MVVM con una capa de dominio sencilla.
- ViewBinding para acceder a las vistas.
- Retrofit y Gson para consumir la API REST de Stoplight.
- Room para guardar usuarios y saldos localmente.
- Preferences DataStore para guardar la sesión activa.
- LiveData y corrutinas para actualizar la interfaz sin bloquearla.
- Material Components para los elementos visuales.

## Arquitectura

La aplicación utiliza el siguiente flujo:

```text
Activity / ViewBinding
        ↓
ViewModel / LiveData
        ↓
UseCase
        ↓
Repository
        ↓
Retrofit / Room / DataStore
```

### Presentation

- `view`: contiene las Activities y la navegación.
- `viewmodel`: mantiene el estado de cada pantalla y ejecuta los casos de uso.

### Domain

- `model`: contiene el modelo `User` utilizado por la aplicación.
- `repository`: define los contratos de autenticación y saldo.
- `usecase`: contiene acciones como login, registro, depósito, retiro y cierre de sesión.

### Data

- `remote`: contiene Retrofit, los endpoints y los DTO de la API.
- `local/database`: contiene Room, `UserEntity` y `UserDao`.
- `local/preferences`: contiene la sesión guardada con DataStore.
- `repository`: implementa los contratos de la capa de dominio.

`AppContainer` construye y comparte manualmente los repositorios. No se utiliza Hilt ni Dagger.

## Funcionamiento

### Inicio de la aplicación

Splash consulta DataStore:

- Si existe una sesión activa, abre Home.
- Si no existe una sesión, muestra las opciones de login y registro.

### Inicio de sesión

1. Retrofit realiza la petición de login.
2. La aplicación obtiene los usuarios desde Stoplight.
3. Busca el correo ingresado dentro de esos usuarios.
4. Guarda o actualiza los usuarios en Room.
5. DataStore guarda el identificador del usuario que inició sesión.
6. Home muestra su nombre y saldo.

### Saldo

El saldo se guarda en Room como un número `Int`.

- Ingresar dinero aumenta el saldo del usuario activo.
- Retirar dinero descuenta el monto si existe saldo suficiente.
- El saldo se conserva al cerrar y volver a abrir la aplicación.

### Cerrar sesión

El botón **Cerrar sesión** está ubicado en el perfil.

- Limpia la sesión almacenada en DataStore.
- Regresa a la pantalla inicial.
- No elimina los usuarios ni sus saldos guardados en Room.

## Usuarios de prueba

| Nombre | Correo | Saldo inicial |
|---|---|---:|
| Amanda Alkemy | `amanda@alkemy.cl` | $124.000 |
| Reem Khaled | `reem@alkemy.cl` | $98.500 |
| Hiba Saleh | `hiba@alkemy.cl` | $76.000 |
| Carlos Soto | `carlos@alkemy.cl` | $150.000 |
| Camila Rojas | `camila@alkemy.cl` | $84.000 |
| Diego Muñoz | `diego@alkemy.cl` | $112.000 |
| Valentina Pérez | `valentina@alkemy.cl` | $93.000 |
| Martín González | `martin@alkemy.cl` | $68.000 |
| Sofía Herrera | `sofia@alkemy.cl` | $105.000 |
| Tomás Silva | `tomas@alkemy.cl` | $87.000 |

### Contraseña

Stoplight no valida una contraseña real. Se puede utilizar cualquier contraseña que no esté vacía, por ejemplo:

```text
123456
```

## Registro de una cuenta nueva

El formulario permite crear una cuenta, por ejemplo `ricardo@gmail.com`.

Después del registro:

- El usuario se guarda localmente en Room.
- Se inicia la sesión y se muestran sus datos en Home y Perfil.
- Si la aplicación se cierra sin cerrar sesión, DataStore permite recuperar esa sesión.

Limitación actual:

- Stoplight utiliza respuestas estáticas y no agrega el usuario nuevo a `GET /users`.
- La contraseña del registro no se almacena.
- Si el usuario nuevo cierra sesión, no podrá volver a iniciar sesión con esa cuenta.
- Para permitirlo se necesita una API con persistencia real o implementar autenticación local en Room.

## Datos demostrativos

La lista de últimas transacciones de Home es parte del diseño original y actualmente contiene información estática. Los depósitos y retiros modifican el saldo, pero todavía no generan un historial de transacciones.

## API utilizada

```text
https://stoplight.io/mocks/ricardo-api/mockricardo/2016512203/
```

Endpoints utilizados:

- `POST auth/login`
- `POST auth/register`
- `GET users`
- `GET users/{userId}`
