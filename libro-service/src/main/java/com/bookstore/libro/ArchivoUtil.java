package com.bookstore.libro;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class ArchivoUtil {
	private final static String DIRECTORIO_UPLOAD = "D:\\img";
	public static Resource cargar(String foto) throws MalformedURLException {
		Path rutaArchivo = getPath(foto);
		
		Resource recurso = new UrlResource(rutaArchivo.toUri());
		
		if(!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get(DIRECTORIO_UPLOAD).resolve("book.png").toAbsolutePath();
			
			recurso = new UrlResource(rutaArchivo.toUri());
			
		}
		return recurso;
	}
	public static Path getPath(String foto) {
		return Paths.get(DIRECTORIO_UPLOAD).resolve(foto).toAbsolutePath();
	}
}
