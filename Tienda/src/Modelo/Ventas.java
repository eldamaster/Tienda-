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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class Ventas {
    
    
    //AGREGAR COMPRAS
    public String AgregarVentas(int idProducto, String fecha, int cantidad)
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
            comando.executeUpdate("insert into ventas() values('0','"+idProducto+"','"+fecha+"','"+cantidad+"')");
            //CERRAR BASE DE DATOS 
            conex.close();
            return "Venta Agregada";
            
        } catch (Exception e) {
            return "Error la agregar la venta: "+e;
        }
    }
    
    
     //ACTUALIZAR LA EXISTENCIA
     public void ActualizarExistencia(int idProducto, int existencia, int cantidad)
    {
          //CONECTAR A LA BASE DE DATOS
        Conexion nuevaConexion = new Conexion();
        Connection conex;
        existencia=existencia-cantidad;
        try 
        {
            nuevaConexion.Conectar();  //estamos conectando a la base de datos 
            conex = nuevaConexion.getConexion();
            Statement comando = conex.createStatement();   //comando que permite ejecutar la consulta
            
            //EJECUTAR LA CONSULTA DE INSERCCION 
            comando.executeUpdate("UPDATE productos SET Existencia="+existencia+" where idProducto="+idProducto+"");
            conex.close();          
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al restar la existencia del producto:  "+e);
        }
        
    }
     
     
     //BUSCAR VENTAS POR NOMBRE O POR FECHA
      public void  BuscarVentas(int idProducto, String fecha, String busqueda, DefaultTableModel modelo)   //pasa un fomrulario
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
            consulta = comando.executeQuery("select productos.NomProducto, ventas.Fecha, ventas.CantidadVenta, ventas.PrecioVenta from productos, ventas where ventas."+busqueda+"='"+buscar+"' and productos.idProducto=ventas.idProducto");
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
            JOptionPane.showMessageDialog(null, "Error al mostrar las Ventas:  "+e); 
        }   
 }
    
    
//MOSTRAR TODAS LAS VENTAS
 public void  MostrarVentas(DefaultTableModel modelo)   //pasa un fomrulario
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
            consulta = comando.executeQuery("select productos.NomProducto, ventas.Fecha, ventas.CantidadVenta, ventas.PrecioVenta from ventas, productos where productos.idProducto= ventas.idProducto");
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
            JOptionPane.showMessageDialog(null, "Error al mostrar todas las ventas:  "+e); 
        }        
 }
      
      
 //BUSCAR PRODUCTOS CON PRECIO VENTA
 public void  BuscarProductosPrecioVenta(String nombre, DefaultTableModel modelo)   //pasa un fomrulario
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
            consulta = comando.executeQuery("select NomProducto, PrecioVenta, Existencia from productos where NomProducto='"+nombre+"'");
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
