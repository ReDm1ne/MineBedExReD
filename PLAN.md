# Plan de Integración: MineBedExReD

## 1. Flujo Principal de Conexión
1. Un jugador se conecta al proxy (PostLoginEvent).
2. Se verifica si el jugador es de Bedrock usando `FloodgateApi.getInstance().isFloodgatePlayer(uuid)`.
3. Si es de Bedrock, se lanza una tarea asíncrona con un retraso de `initial-open-delay-ms` (vía `BungeeCord Scheduler`). Esto evita conflictos con el auto-login de MineLogin.

## 2. Gestión de Formularios (Cumulus)
1. Pasado el retraso inicial, se construye y envía un `CustomForm` de Cumulus.
2. La respuesta se maneja asíncronamente en el callback del formulario.
3. Si la respuesta es válida (el usuario introduce texto), se obliga al jugador a ejecutar el comando `/login <password>` mediante `player.chat()`.
4. Si la respuesta es `closed` o `invalid` (cerró el formulario por error), se programa otra tarea asíncrona (`reopen-delay-seconds`) para volver a mostrar el formulario.

## 3. Integración con Eventos de MineLogin
1. Se registra un listener para `UserLoginFailedEvent` de MineLogin.
2. Al dispararse, verificamos si el UUID pertenece a un jugador de Bedrock.
3. De ser así, se lanza una tarea asíncrona que espere `reopen-delay-seconds`. Esto permite al jugador leer en el chat el motivo del error (ej. "Contraseña incorrecta").
4. Tras la espera, se vuelve a invocar el envío del formulario.

## 4. Recarga en Caliente
- El comando `/mbex reload` recarga la configuración desde el disco y actualiza los valores en memoria sin necesidad de reiniciar el proxy.