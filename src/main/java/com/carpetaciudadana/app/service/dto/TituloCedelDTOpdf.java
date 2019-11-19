package com.carpetaciudadana.app.service.dto;

import java.io.IOException;
import java.io.Serializable;

import com.carpetaciudadana.app.domain.EstudianteCedel;
import com.carpetaciudadana.app.web.rest.util.Funciones;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TituloCedelDTOpdf implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static ObjectMapper mapper = new ObjectMapper();
	private final Logger log = LoggerFactory.getLogger(TituloCedelDTOpdf.class);

	private EstudianteCedel estudianteCedel;

	private String fechaEmision;

	private String numeroEnLetras;

	private String path;

	public TituloCedelDTOpdf(String estudianteCedel, String path) throws JsonParseException, JsonMappingException, IOException {
		this.estudianteCedel = mapper.readValue(estudianteCedel, EstudianteCedel.class);
		this.fechaEmision = Funciones.fechaMesAnio(this.estudianteCedel.getFechaFin());
		this.path = path;
	}

	public EstudianteCedel getEstudianteCedel() {
		return estudianteCedel;
	}

	public void setEstudianteCedel(EstudianteCedel estudianteCedel) {
		this.estudianteCedel = estudianteCedel;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNumeroEnLetras() {
		return numeroEnLetras;
	}

	public void setNumeroEnLetras(String numeroEnLetras) {
		this.numeroEnLetras = numeroEnLetras;
	}

	@Override
	public String toString() {
		try {
			return "{\"titulo_cedel\":" + getEstudianteCedel().toJson() + ", \"fecha_emision\": " + "\"" + getFechaEmision() + "\", \"numero_letras\": "
					+ "\"" + getNumeroEnLetras() + "\", \"path\": " + "\"" + getPath() + "\"}";

		} catch (JsonProcessingException e) {
			
			log.warn("Json Parse Exception: No se pudo serializar Titulo Cedel DTO");
			return "Json Parse Error";
		}
	}

}
