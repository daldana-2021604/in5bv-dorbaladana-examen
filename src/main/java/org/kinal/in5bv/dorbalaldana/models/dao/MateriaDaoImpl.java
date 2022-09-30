package org.kinal.in5bv.dorbalaldana.models.dao;

import org.kinal.in5bv.dorbalaldana.models.idao.IMateriasDao;
import org.kinal.in5bv.dorbalaldana.db.Conexion;
import org.kinal.in5bv.dorbalaldana.models.domain.Materia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author USUARIO
 * @date 28/09/2022
 * @time 20:34:35 Carné 2021604 Código técnico: IN5BV Grupo: 1
 */
public class MateriaDaoImpl implements IMateriasDao {

    private static final String SQL_SELECT = "SELECT id_materia, nombre_materia, ciclo_escolar, horario_inicio, horario_final, catedratico, salon, cupo_maximo, cupo_minimo FROM materia";
    private static final String SQL_SELECT_BY_ID = "SELECT id_materia, nombre_materia, ciclo_escolar, horario_inicio, horario_final, catedratico, salon, cupo_maximo, cupo_minimo FROM materia WHERE id_materia = ?";
    private static final String SQL_INSERT = "INSERT INTO materia(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo)VALUES (?,?,?,?,?,?,?,?);";
    private static final String SQL_UPDATE = "UPDATE materia SET nombre_materia=?, ciclo_escolar=?, horario_inicio=?, horario_final=?, catedratico=?, salon=?, cupo_maximo=?, cupo_minimo=? WHERE id_materia=?";
    private static final String SQL_DELETE = "DELETE FROM materia WHERE id_materia = ?";

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private Materia materia = null;
    ObservableList<Materia> listaMateria;
    private List<Materia> listaMateria1 = new ArrayList<>();

    @Override
    public ObservableList<Materia> getALL() {
        try {
            con = Conexion.getConnection();
            pstmt = con.prepareStatement(SQL_SELECT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                materia = new Materia(rs.getInt("id_materia"), rs.getString("nombre_materia"), rs.getInt("ciclo_escolar"), rs.getTime("horario_inicio").toLocalTime(), rs.getTime("horario_final").toLocalTime(), rs.getString("catedratico"), rs.getString("salon"), rs.getInt("cupo_maximo"), rs.getInt("cupo_minimo"));
                listaMateria1.add(materia);
            }
        } catch (SQLException e) {
            System.err.println("Se produjo un error al intentar listar las materias");
            e.printStackTrace(System.out);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(pstmt);
        }
        listaMateria = FXCollections.observableArrayList(listaMateria1);
        return listaMateria;
    }

    @Override
    public int add(Materia materia) {
        int rows = 0;
        try {
            con = Conexion.getConnection();
            pstmt = con.prepareStatement(SQL_INSERT);
            pstmt.setString(1, materia.getNombreMateria());
            pstmt.setInt(2, materia.getCiclo());
            pstmt.setTime(3, Time.valueOf(materia.getHorarioInicio()));
            pstmt.setTime(4, Time.valueOf(materia.getHorarioFinal()));
            pstmt.setString(5, materia.getCatedratico());
            pstmt.setString(6, materia.getSalon());
            pstmt.setInt(7, materia.getCupoMaximo());
            pstmt.setInt(8, materia.getCupoMinimo());
            System.out.println(pstmt.toString());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Se produjo un error al intentar insertar el siguiente registro" + materia);
            e.printStackTrace(System.out);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(pstmt);
            Conexion.close(con);
        }
        return rows;
    }

    @Override
    public int update(Materia materia
    ) {
        int rows = 0;
        try {
            con = Conexion.getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE);
            pstmt.setString(1, materia.getNombreMateria());
            pstmt.setInt(2, materia.getCiclo());
            pstmt.setTime(3, Time.valueOf(materia.getHorarioInicio()));
            pstmt.setTime(4, Time.valueOf(materia.getHorarioFinal()));
            pstmt.setString(5, materia.getCatedratico());
            pstmt.setString(6, materia.getSalon());
            pstmt.setInt(7, materia.getCupoMaximo());
            pstmt.setInt(8, materia.getCupoMinimo());
            pstmt.setInt(9, materia.getIdMateria());
            System.out.println(pstmt.toString());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Se produjo un error al intentar actualizar el siguiente registro: " + materia);
            e.printStackTrace(System.out);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(pstmt);
            Conexion.close(con);
        }
        return rows;
    }

    @Override
    public int delete(Materia materia
    ) {
        int rows = 0;
        try {
            con = Conexion.getConnection();
            pstmt = con.prepareStatement(SQL_DELETE);
            pstmt.setInt(1, materia.getIdMateria());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Se produjo un error al intentar el registro seleccionado");
            e.printStackTrace(System.out);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(pstmt);
            Conexion.close(con);
        }
        return rows;
    }

    @Override
    public Materia get(Materia materia
    ) {
        try {
            con = Conexion.getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
            pstmt.setInt(1, materia.getIdMateria());
            System.out.println(pstmt.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                materia = new Materia(rs.getInt("id_materia"), rs.getString("nombre_materia"), rs.getInt("ciclo_escolar"), rs.getTime("horario_inicio").toLocalTime(), rs.getTime("horario_final").toLocalTime(), rs.getString("catedratico"), rs.getString("salon"), rs.getInt("cupo_maximo"), rs.getInt("cupo_minimo"));
            }
            System.out.println("Estudiante" + materia);
        } catch (SQLException e) {
            System.err.println("Se produjo un error al intentar listar las materias");
            e.printStackTrace(System.out);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(pstmt);
            Conexion.close(con);
        }
        return materia;
    }

}
