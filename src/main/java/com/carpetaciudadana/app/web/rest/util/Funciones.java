package com.carpetaciudadana.app.web.rest.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Funciones {
	
	
	// LocalDate
	// DIA
	public static String traerDia(LocalDate fechaLD) {
		GregorianCalendar fecha = GregorianCalendar.from(fechaLD.atStartOfDay(ZoneId.systemDefault()));
		String dia = String.valueOf(traerNroDia(fecha));
		if (traerNroDia(fecha) < 10) {
			dia = "0" + dia;
		}
		return dia;
	}

	public static int traerNroDia(LocalDate fechaLD) {
		GregorianCalendar fecha = GregorianCalendar.from(fechaLD.atStartOfDay(ZoneId.systemDefault()));
		return fecha.get(Calendar.DAY_OF_MONTH);
	}

	public static int traerNroDeDia(LocalDate fechaLD) {
		GregorianCalendar fecha = GregorianCalendar.from(fechaLD.atStartOfDay(ZoneId.systemDefault()));
		return fecha.get(Calendar.DAY_OF_WEEK);
	}

	public static String traerDiaEnLetras(LocalDate fechaLD) {
		GregorianCalendar fecha = GregorianCalendar.from(fechaLD.atStartOfDay(ZoneId.systemDefault()));
		String[] diaEnLetra = { "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" };
		return diaEnLetra[traerNroDeDia(fecha) - 1];
	}

	public static boolean esDiaHabil(LocalDate fechaLD) {
		GregorianCalendar fecha = GregorianCalendar.from(fechaLD.atStartOfDay(ZoneId.systemDefault()));
		boolean respuesta = false;
		if (!(traerDiaEnLetras(fecha).compareTo("Domingo") == 0))
			if (!(traerDiaEnLetras(fecha).compareTo("Sabado") == 0))
				respuesta = true;

		return respuesta;
	}

	// MES
	public static String traerMes(LocalDate fechaLD) {
		GregorianCalendar fecha = GregorianCalendar.from(fechaLD.atStartOfDay(ZoneId.systemDefault()));
		String mes = String.valueOf(traerNroMes(fecha));
		if (traerNroMes(fecha) < 10) {
			mes = "0" + mes;
		}
		return mes;
	}

	public static int traerNroMes(LocalDate fechaLD) {
		GregorianCalendar fecha = GregorianCalendar.from(fechaLD.atStartOfDay(ZoneId.systemDefault()));
		return fecha.get(Calendar.MONTH) + 1;
	}

	public static String traerMesEnLetras(LocalDate fechaLD) {
		GregorianCalendar fecha = GregorianCalendar.from(fechaLD.atStartOfDay(ZoneId.systemDefault()));

		String[] mesEnLetra = { "Diciembre", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
				"Septiembre", "Octubre", "Noviembre" };
		return mesEnLetra[traerNroMes(fecha)];
	}

	// AÑO
	public static String traerAnio(LocalDate fechaLD) {
		GregorianCalendar fecha = GregorianCalendar.from(fechaLD.atStartOfDay(ZoneId.systemDefault()));
		return String.valueOf(traerNroAnio(fecha));
	}

	public static int traerNroAnio(LocalDate fechaLD) {
		GregorianCalendar fecha = GregorianCalendar.from(fechaLD.atStartOfDay(ZoneId.systemDefault()));
		return fecha.get(Calendar.YEAR);
	}

	// FORMATO FECHAS

	public static String traerFechaCorta(LocalDate fechaLD) {
		GregorianCalendar fecha = GregorianCalendar.from(fechaLD.atStartOfDay(ZoneId.systemDefault()));
		return traerDia(fecha) + "/" + traerMes(fecha) + "/" + traerAnio(fecha);
	}

	public static String traerFechaLarga2(LocalDate fechaLD) {
		GregorianCalendar fecha = GregorianCalendar.from(fechaLD.atStartOfDay(ZoneId.systemDefault()));
		return "día <b>"+ traerDia(fecha) + "</b> del mes de <b>" + traerMesEnLetras(fecha) + "</b> del año <b>" + traerAnio(fecha)+"</b>";
	}
	
	public static String traerFechaLarga3(LocalDate fechaLD) {
		GregorianCalendar fecha = GregorianCalendar.from(fechaLD.atStartOfDay(ZoneId.systemDefault()));
		return "día "+ traerDia(fecha) + " del mes de " + traerMesEnLetras(fecha) + " del año " + traerAnio(fecha);
	}
	
	public static String traerFechaLarga(LocalDate fechaLD) {
		GregorianCalendar fecha = GregorianCalendar.from(fechaLD.atStartOfDay(ZoneId.systemDefault()));
		return traerDia(fecha) + " de " + traerMesEnLetras(fecha) + " del año " + traerAnio(fecha);

	}

	// GregorianCalendar
	// DIA
	public static String traerDia(GregorianCalendar fecha) {
		String dia = String.valueOf(traerNroDia(fecha));
		if (traerNroDia(fecha) < 10) {
			dia = "0" + dia;
		}
		return dia;
	}

	public static int traerNroDia(GregorianCalendar fecha) {
		return fecha.get(Calendar.DAY_OF_MONTH);
	}

	public static int traerNroDeDia(GregorianCalendar fecha) {
		return fecha.get(Calendar.DAY_OF_WEEK);
	}

	public static String traerDiaEnLetras(GregorianCalendar fecha) {
		String[] diaEnLetra = { "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" };
		return diaEnLetra[traerNroDeDia(fecha) - 1];
	}

	public static boolean esDiaHabil(GregorianCalendar fecha) {
		boolean respuesta = false;
		if (!(traerDiaEnLetras(fecha).compareTo("Domingo") == 0))
			if (!(traerDiaEnLetras(fecha).compareTo("Sabado") == 0))
				respuesta = true;

		return respuesta;
	}

	// MES
	public static String traerMes(GregorianCalendar fecha) {
		String mes = String.valueOf(traerNroMes(fecha));
		if (traerNroMes(fecha) < 10) {
			mes = "0" + mes;
		}
		return mes;
	}

	public static int traerNroMes(GregorianCalendar fecha) {
		return fecha.get(Calendar.MONTH);
	}

	public static String traerMesEnLetras(GregorianCalendar fecha) {

		String[] mesEnLetra = { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO",
				"SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE","DICIEMBRE" };
		return mesEnLetra[traerNroMes(fecha)];
	}

	public static String traerMesEnLetras(int fecha) {

		String[] mesEnLetra = { "","ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO",
				"SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE","DICIEMBRE" };
		return mesEnLetra[fecha];
	}
	
	public static String traerNumeroEnLetras(Float numero) {
		String resultado="";
		String[] parteEntera = { "DIEZ","UNO", "DOS", "TRES", "CUATRO", "CINCO", "SEIS", "SIETE", "OCHO", "NUEVE",
				"DIEZ" };
		if(String.valueOf(numero).length()>3 && numero.intValue()<10) {  
			resultado=parteEntera[numero.intValue()]+" con "+String.valueOf(numero.floatValue()).substring(2,4);
		}else {
			resultado=parteEntera[numero.intValue()];
		}
		
		return resultado;	
		
	}

	// AÑO
	public static String traerAnio(GregorianCalendar fecha) {
		return String.valueOf(traerNroAnio(fecha));
	}

	public static int traerNroAnio(GregorianCalendar fecha) {
		return fecha.get(Calendar.YEAR);
	}

	// FORMATO FECHAS

	public static String traerFechaCorta(GregorianCalendar fecha) {
		return traerDia(fecha) + "/" + traerMes(fecha) + "/" + traerAnio(fecha);
	}

	public static String traerFechaLarga(GregorianCalendar fecha) {
		return traerDia(fecha) + " de " + traerMesEnLetras(fecha) + " del año" + traerAnio(fecha);

	}
	
	public static String fechaMesAnio(String fecha) {
		String salida= fecha;		
		if(fecha.length()<7) {
			salida=traerMesEnLetras(Integer.parseInt(fecha.substring(4))) + " de " + fecha.substring(0, 4);
		}else {
			salida=traerMesEnLetras(Integer.parseInt(fecha.substring(5,7))) + " de " + fecha.substring(0, 4);
		}			
		return salida;
	}
	


	public static LocalDate traerFecha(String fecha) {		
		return LocalDate.parse(fecha);

	}
	/* Pre: Date y cantidad de dias a incremetar
	 * Post: Date incrementado
	 */
    public static Date agregarDias(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); 
        return cal.getTime();
    }
	
    
    /**Date now and 00:00:00 */   

    public static Date ayerSinHoras()
    {
        Date hoy = Date.from(Instant.now());
        hoy.setDate(hoy.getDate()-1);
        hoy.setHours(0);
        hoy.setMinutes(0);
        hoy.setSeconds(0);
        return hoy;
    }
    /**Date before and 00:00:00 */
    public static Date manianaSinHoras()
    {
        Date hoy = Date.from(Instant.now());
        hoy.setDate(hoy.getDate()+1);
        hoy.setHours(0);
        hoy.setMinutes(0);
        hoy.setSeconds(0);
        return hoy;
    }


}
