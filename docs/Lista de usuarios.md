# Documentación de Login Records

## Modelo: LoginRecord

El modelo `LoginRecord` se encuentra en el archivo y representa un registro de inicio de sesión en el sistema.

### Atributos

- `id` (String): Identificador único del registro
- `username` (String): Nombre de usuario
- `email` (String): Correo electrónico del usuario
- `provider` (String): Proveedor del servicio de autenticación
- `activityType` (String): Tipo de actividad realizada
- `loginTimestamp` (LocalDateTime): Marca de tiempo del inicio de sesión

### Anotaciones

- `@Document(collection = "login_records")`: Indica que esta clase representa una colección en MongoDB
- `@Getter` y `@Setter`: Generan automáticamente los métodos getter y setter para todos los campos
- `@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")`: Define el formato de fecha y hora para el campo `loginTimestamp`

## Repositorio: LoginRecordRepository

El `LoginRecordRepository` es una interfaz que extiende `MongoRepository` para manejar operaciones de base de datos con documentos `LoginRecord`.

### Funcionalidades heredadas

- Operaciones CRUD básicas (Create, Read, Update, Delete)
- Paginación
- Ordenamiento

## Controlador: LoginRecordController

El `LoginRecordController` maneja las peticiones HTTP relacionadas con los registros de inicio de sesión.

### Endpoints

#### Registrar actividad de inicio de sesión
```http
POST /api/login-records
```
- **Descripción**: Guarda un nuevo registro de inicio de sesión
- **Cuerpo de la petición**: Objeto LoginRecord
- **Respuesta**: Registro guardado con código 200 (OK)

#### Obtener todos los registros
```http
GET /api/login-records
```
- **Descripción**: Recupera todos los registros de inicio de sesión
- **Respuesta**: Lista de registros con código 200 (OK)

### Anotaciones

- `@RestController`: Indica que esta clase es un controlador REST
- `@RequestMapping("/api/login-records")`: Define la ruta base para todos los endpoints
- `@Autowired`: Inyecta la dependencia del repositorio
- `@PostMapping`: Maneja peticiones POST
- `@GetMapping`: Maneja peticiones GET