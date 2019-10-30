package com.carpetaciudadana.app.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

/**
 * A EstudianteCedel.
 */

public class EstudianteCedel implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static ObjectMapper mapper = new ObjectMapper();

	private Long id;


	private String dni;


	private String nombre;
	
	

	private String apellido;


	private String curso;


	private String fechaFin;


	private String duracion;

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public EstudianteCedel dni(String dni) {
		this.dni = dni;
		return this;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public EstudianteCedel nombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public EstudianteCedel apellido(String apellido) {
		this.apellido=apellido;
		return this;
	}


	public String getCurso() {
		return curso;
	}

	public EstudianteCedel curso(String curso) {
		this.curso = curso;
		return this;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public EstudianteCedel fechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
		return this;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public EstudianteCedel duracion(String duracion) {
		this.duracion=duracion;
		return this;
	}
	
	
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here, do not remove

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		EstudianteCedel estudianteCedel = (EstudianteCedel) o;
		if (estudianteCedel.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), estudianteCedel.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "EstudianteCedel{" + "id=" + getId() + ", dni='" + getDni() + "'" + ", nombre='" + getNombre() + "'" 
				+ ", curso='" + getCurso() + "'" + ", fechaFin='" + getFechaFin() + "'" + "}";
	}

	public String toJson() throws JsonProcessingException {
		return mapper.writeValueAsString(this);
	}


}
