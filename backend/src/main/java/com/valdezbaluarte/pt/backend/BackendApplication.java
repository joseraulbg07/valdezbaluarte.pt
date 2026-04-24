package com.valdezbaluarte.pt.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase principal que inicializa la aplicación Spring Boot.
 * <p>
 * Actualmente también actúa como un controlador REST de diagnóstico para verificar
 * el estado general del servicio y la conexión del depurador remoto (debugger).
 */
@SpringBootApplication
@RestController // Permite exponer endpoints HTTP directamente desde esta clase para propósitos de prueba rápida.
public class BackendApplication {

	/**
	 * Punto de entrada principal de la aplicación.
	 * 
	 * @param args Argumentos de línea de comandos al iniciar la aplicación.
	 */
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	/**
	 * Endpoint de diagnóstico (Health check).
	 * Útil para comprobar que el contenedor levantó correctamente y para probar
	 * el punto de interrupción (breakpoint) mediante el puerto 5005 expuesto en Docker.
	 * 
	 * @return Un mensaje indicando que el backend responde adecuadamente.
	 */
	@GetMapping("/ping")
	public String ping() {
		// INFO: Coloca un breakpoint en la línea del return para validar la conexión JDWP desde tu IDE.
		return "¡Backend funcionando y Debugger conectado!";
	}
}
