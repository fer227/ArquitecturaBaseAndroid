# Arquitectura básica Cliente-Servidor
Los puntos y objetivos más importantes de este repositorio se comentan a continuación.
- Crear una arquitectura básica Cliente-Servidor:
  - El servidor se ha llevado a cabo con **NodeJS** y concretamente con el uso del framework de **KoaJS**. En él pretendemos simular una *API REST* que serviría para gestionar los datos de la aplicación. El código del servidor se puede encontrar en la carpeta [server_node](./server_node).
  - El cliente se ha llevado a cabo en una aplicación de Android. 
  
- Dentro de esta aplicación se han probado dos herramientas (con el objetivo de compararlas) para la comunicación con servidores o en otras palabras, para llevar a cabo peticiones *HTTP*:
    - Volley.
    - Retrofit2.