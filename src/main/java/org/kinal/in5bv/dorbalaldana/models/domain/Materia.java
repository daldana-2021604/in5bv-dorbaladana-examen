
package org.kinal.in5bv.dorbalaldana.models.domain;

import java.time.LocalTime;


/**
 *
 * @author Dorbal Emilio Aldana Ramos
 * @date 27/09/2022
 * @time 23:55:41
 * Carné 2021604
 * Código técnico: IN5BV
 * Grupo: 1
 */
public class Materia {

    private int idMateria;
    private String nombreMateria;
    private int ciclo;
    private LocalTime horarioInicio;
    private LocalTime horarioFinal;
    private String catedratico;
    private String salon;
    private int cupoMaximo;
    private int cupoMinimo;
    private int cantidadDatos;

    public Materia() {
    }

    public Materia(int idMateria) {
        this.idMateria = idMateria;
    }

    public Materia(int idMateria, String nombreMateria, String catedratico, String salon) {
        this.idMateria = idMateria;
        this.nombreMateria = nombreMateria;
        this.catedratico = catedratico;
        this.salon = salon;
    }

    public Materia(String nombreMateria, int ciclo, LocalTime horarioInicio, LocalTime horarioFinal, String catedratico, String salon, int cupoMaximo, int cupoMinimo) {
        this.nombreMateria = nombreMateria;
        this.ciclo = ciclo;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
        this.catedratico = catedratico;
        this.salon = salon;
        this.cupoMaximo = cupoMaximo;
        this.cupoMinimo = cupoMinimo;
    }

    public Materia(int idMateria, String nombreMateria, int ciclo, LocalTime horarioInicio, LocalTime horarioFinal, String catedratico, String salon, int cupoMaximo, int cupoMinimo) {
        this.idMateria = idMateria;
        this.nombreMateria = nombreMateria;
        this.ciclo = ciclo;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
        this.catedratico = catedratico;
        this.salon = salon;
        this.cupoMaximo = cupoMaximo;
        this.cupoMinimo = cupoMinimo;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(LocalTime horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public String getCatedratico() {
        return catedratico;
    }

    public void setCatedratico(String catedratico) {
        this.catedratico = catedratico;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public int getCupoMinimo() {
        return cupoMinimo;
    }

    public void setCupoMinimo(int cupoMinimo) {
        this.cupoMinimo = cupoMinimo;
    }

    public int getCantidadDatos() {
        return cantidadDatos;
    }

    public void setCantidadDatos(int cantidadDatos) {
        this.cantidadDatos = cantidadDatos;
    }

    @Override
    public String toString() {
        return "Materia{" + "idMateria=" + idMateria + ", nombreMateria=" + nombreMateria + ", ciclo=" + ciclo + ", horarioInicio=" + horarioInicio + ", horarioFinal=" + horarioFinal + ", catedratico=" + catedratico + ", salon=" + salon + ", cupoMaximo=" + cupoMaximo + ", cupoMinimo=" + cupoMinimo + '}';
    }
    
}
