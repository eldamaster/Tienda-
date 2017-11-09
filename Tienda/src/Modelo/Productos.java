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
public class Productos {
    
    
    //AGREGAR PRODUCTOS
    public String AgregarProsucto(String nombre, float precio, float precioVenta)
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
            
            comando.executeUpdate("insert into productos() values('0','"+nombre+"','"+precio+"','"+precioVenta+"','0')");
            //CERRAR BASE DE DATOS 
            conex.close();
            return "Producto Agregado";
            
        } catch (Exception e) {
            return "Error al ejecutar la agregar visitante: "+e;
        }

    }
    
    //BUSCAR PRODUCTOS
     
 public void  BuscarProductos(String nombre, DefaultTableModel modelo)   //pasa un fomrulario
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
            consulta = comando.executeQuery("select NomProducto, Precio, PrecioVenta, Existencia from productos where NomProducto='"+nombre+"'");
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
 
    
 //MOSTRAR TODOS LOS PRODUCTOS DE LA TABLA
 public void  MostrarProductos(DefaultTableModel modelo)   //pasa un fomrulario
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
            consulta = comando.executeQuery("select NomProducto, Precio, PrecioVenta, Existencia  from productos");
            ResultSetMetaData rsMd = consulta.getMetaData();
             int numeroColumnas = rsMd.getColumnCount();
           
            
            // PARA A;ADIR OBJETOS DE LA CONSULTA A LA TABLA 
 
            while (consulta.next())
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
            JOptionPane.showMessageDialog(null, "Error al mostrar los productos:  "+e); 
        }
        
 }
     
    //LIMPIAR UNA TABLA
    public void LimpiarTabla(DefaultTableModel modelo)
    {
        int filas = modelo.getRowCount(); // cuenta numero de filas q tiene la tabla 
        
        for(int i=0; i<filas; i++)
        {
            modelo.removeRow(0);// valor fijo de 0
        }   
        
    }
    
    
    //OBTIENE ID DEL PRODUCTO
    public int  BuscarId(String nombre) 
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
            consulta = comando.executeQuery("select * from productos where NomProducto='"+nombre+"'");
            int id =0;
            while (consulta.next()==true)
            {
                id=consulta.getInt("idProducto");
            }
           
           conex.close();
           return id;
               
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al mostrar los Productos:  "+e); 
            return 0;
        }
   
 }
 
    
    
//EVALUAR SI NO HAY UN PRODUCTO REPETIDOS
     public boolean  ProductoRepetido(String nombre) 
 {
      //CONECTAR A LA BASE DE DATOS
        Conexion nuevaConexion = new Conexion();
        Connection conex;
        ResultSet consulta = null; //GAURDA EL RESULTADO DE LA TABLA 
        boolean auxiliar=false;
        try 
        {
            nuevaConexion.Conectar();    //estamos conectando a la base de datos 
            conex = nuevaConexion.getConexion();
            Statement comando = conex.createStatement();   //comando que permite ejecutar la consulta
            
            //EJECUTAR LA CONSULTA  
            consulta = comando.executeQuery("select * from productos where NomProducto='"+nombre+"'");
            int id =0;
            while (consulta.next()==true)
            {
                auxiliar=true;
            }
           
           conex.close();
           return auxiliar;
               
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error al Evaluar si hay un producto repetido:  "+e); 
            return false;
        }
 }
    
     
    //MOSTRAR TODOS LOS PRODUCTOS DE LA TABLA
 public void  MostrarProductosPrecioCosto_Venta(String precio, DefaultTableModel modelo)   //pasa un fomrulario
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
            consulta = comando.executeQuery("select NomProducto, "+precio+", Existencia  from productos");
            ResultSetMetaData rsMd = consulta.getMetaData();
             int numeroColumnas = rsMd.getColumnCount();
           
            
            // PARA A;ADIR OBJETOS DE LA CONSULTA A LA TABLA 
 
            while (consulta.next())
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
            JOptionPane.showMessageDialog(null, "Error al mostrar los productos con su Precio Respectivo:  "+e); 
        }
        
 }
     
     
     
//MODIFICAR DATOS DEL PRODUCTO
   public void ActualizarProducto(String nombre, float precioCosto, float precioVenta, int idProducto, int existencia)
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
            comando.executeUpdate("UPDATE productos SET NomProducto='"+nombre+"',  Precio='"+precioCosto+"', PrecioVenta='"+precioVenta+"', Existencia='"+existencia+"' where idProducto="+idProducto+"");
            conex.close();          
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al actualizar el producto:  "+e);
        }
        
    }
   
   
    
}
