/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.kinal.in5bv.dorbalaldana.models.idao;

import java.util.List;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import org.kinal.in5bv.dorbalaldana.models.domain.Materia;

/**
 *
 * @author USUARIO
 */
public interface IMateriasDao {
    
    //Listar todos los registros 
    public ObservableList<Materia> getALL();

    //Agregar un nuevo registro
    public int add(Materia materia);

    //Modificar un registro
    public int update(Materia materia);

    //Eliminar un registro
    public int delete(Materia materia);
    
    // Buscar o encontrar un registro en especifico
    public Materia get(Materia materia);
}
