/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class Compras {
    
    
    //AGREGAR COMPRAS
    public String AgregarCompras(int idProducto, String fecha, int cantidad)
    {
         //CONECTAR A LA BASE DE DATOS
        Conexion nuevaConexion = new Conexion();
        Connection conex;
        
        try 
        {
            nuevaConexion.Conectar();  //estamos conectando a la base de datos 
            conex = nuevaConexion.getConexion();
            Statement comando = conex.createStatement();   //comando que permite ejecutar la consulta
            
            //EJECUTAR LA CONSULTA DE INSERCCION
            
            comando.executeUpdate("insert into compras() values('0','"+idProducto+"','"+fecha+"','"+cantidad+"')");
            //CERRAR BASE DE DATOS 
            conex.close();
            return "compra Agregada";
            
        } catch (Exception e) {
            return "Error al agregar la compra: "+e;
        }

    }
    
    //ACTUALIZAR LA EXISTENCIA
     public void ActualizarExistencia(int idProducto, int existencia, int cantidad)
    {
          //CONECTAR A LA BASE DE DATOS
        Conexion nuevaConexion = new Conexion();
        Connection conex;
        existencia=existencia+cantidad;
        try 
        {
            nuevaConexion.Conectar();  //estamos conectando a la base de datos 
            conex = nuevaConexion.getConexion();
            Statement comando = conex.createStatement();   //comando que permite ejecutar la consulta
            
            //EJECUTAR LA CONSULTA DE INSERCCION 
            comando.executeUpdate("UPDATE productos SET Existencia="+existencia+" where idProducto="+idProducto+"");
            conex.close();          
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al sumar la existencia del producto:  "+e);
        }
        
    }
     
     
     //BUSCAR COMPRAS POR NOMBRE O POR FECHA
 public void  BuscarCompras(int idProducto, String fecha, String busqueda, DefaultTableModel modelo)   //pasa un fomrulario
 {
      //CONECTAR A LA BASE DE DATOS
        Conexion nuevaConexion = new Conexion();
        Connection conex;
        ResultSet consulta = null; //GAURDA EL RESULTADO DE LA TABLA 
        String buscar ="";
        if(idProducto ==0)
        {
           buscar = fecha;
        }
        else
        {
            buscar = Integer.toString(idProducto);
        }
        
        try 
        {
            nuevaConexion.Conectar();    //estamos conectando a la base de datos 
            conex = nuevaConexion.getConexion();
            Statement comando = conex.createStatement();   //comando que permite ejecutar la consulta
            
            //EJECUTAR LA CONSULTA  
            consulta = comando.executeQuery("select productos.NomProducto, compras.Fecha, compras.CantidadCompra, compras.PrecioCosto from productos, compras where compras."+busqueda+"='"+buscar+"' and productos.idProducto=compras.idProducto");
            ResultSetMetaData rsMd = consulta.getMetaData();
             int numeroColumnas = rsMd.getColumnCount();
           
            
            // PARA A;ADIR OBJETOS DE LA CONSULTA A LA TABLA 
 
            while (consulta.next()==true)
            {
                Object [] fila = new Object [numeroColumnas];
                
                for (int y = 0; y < numeroColumnas; y++) 
                {
                    fila[y] = consulta.getObject(y+1); // pasar los valores a la fila 
                }
                modelo.addRow(fila);
            }
           
           conex.close();
               
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar las Compras:  "+e); 
        }
        
 }
 
 
 
 
     //MOSTRAR TODAS LAS COMPRAS
 public void  MostrarCompras(DefaultTableModel modelo)   //pasa un fomrulario
 {
      //CONECTAR A LA BASE DE DATOS
        Conexion nuevaConexion = new Conexion();
        Connection conex;
        ResultSet consulta = null; //GAURDA EL RESULTADO DE LA TABLA 
        
        try 
        {
            nuevaConexion.Conectar();    //estamos conectando a la base de datos 
            conex = nuevaConexion.getConexion();
            Statement comando = conex.createStatement();   //comando que permite ejecutar la consulta
            
            //EJECUTAR LA CONSULTA  
            consulta = comando.executeQuery("select productos.NomProducto, compras.Fecha, compras.CantidadCompra, compras.PrecioCosto from compras, productos where productos.idProducto= compras.idProducto");
            ResultSetMetaData rsMd = consulta.getMetaData();
             int numeroColumnas = rsMd.getColumnCount();
             
            // PARA A;ADIR OBJETOS DE LA CONSULTA A LA TABLA 
            while (consulta.next()== true)
            {
                Object [] fila = new Object [numeroColumnas];
                
                for (int y = 0; y < numeroColumnas; y++) 
                {
                    fila[y] = consulta.getObject(y+1); // pasar los valores a la fila 
                }
                modelo.addRow(fila);
            }
           
           conex.close();
               
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar todas las compras:  "+e); 
        }        
 }
 
 
 //BUSCAR PRODUCTOS CON PRECIO COSTO
 public void  BuscarProductosPrecioCosto(String nombre, DefaultTableModel modelo)   //pasa un fomrulario
 {
      //CONECTAR A LA BASE DE DATOS
        Conexion nuevaConexion = new Conexion();
        Connection conex;
        ResultSet consulta = null; //GAURDA EL RESULTADO DE LA TABLA 
        
        try 
        {
            nuevaConexion.Conectar();    //estamos conectando a la base de datos 
            conex = nuevaConexion.getConexion();
            Statement comando = conex.createStatement();   //comando que permite ejecutar la consulta
            
            //EJECUTAR LA CONSULTA  
            consulta = comando.executeQuery("select NomProducto, Precio, Existencia from productos where NomProducto='"+nombre+"'");
            ResultSetMetaData rsMd = consulta.getMetaData();
            int numeroColumnas = rsMd.getColumnCount();
           
            
            // PARA A;ADIR OBJETOS DE LA CONSULTA A LA TABLA 
 
            while (consulta.next()==true)
            {
                Object [] fila = new Object [numeroColumnas];
                
                for (int y = 0; y < numeroColumnas; y++) 
                {
                    fila[y] = consulta.getObject(y+1); // pasar los valores a la fila 
                }
                modelo.addRow(fila);
            }
           
           conex.close();
               
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los Productos:  "+e); 
        }
        
 }
 
 
}
