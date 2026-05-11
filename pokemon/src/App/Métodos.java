package App;


import java.sql.*;
import Conexion.ConexionMySQL;

	public class Métodos {
		
		public ConexionMySQL conexion; //Creamos el atributo para poderlo usar en todos los métodos.

		
			//MÉTODO PARA CONECTAR CON LA BASE DE DATOS
		
	    public  void conectar(String usuario) { //No le damos un valor base para poder introducir las credenciales. 
	    																//Pero al ser solo la lógica, no podemos introducir datos aquí.
	       	
	        String pass = "Medac24";	//La contraseña y el servicio es común. Como realmente no nos tenemos que preocupar por temas de seguridad, lo dejamos,
	        String servicio = "XEPDB1";   // O XEPDB1 si tu Oracle usa ese servicio
        	
	        conexion = new ConexionMySQL(usuario, pass, servicio);

	        try {
	        	
	            conexion.conectar();
	            System.out.println(" Conexión establecida correctamente.");


	        } catch (Exception e) {
	            System.out.println("Error intentando conectar");
	            e.printStackTrace();
	        }
	        
	    }
	    
	  //MÉTODO PARA DESCONECTAR LA BASE DE DATOS
	    
	    public void desconectar() {
	    	
	    	try {
	    		
	    		if (conexion != null ) {		//Comprobamos que la conexión existe
	    		conexion.desconectar();
	            System.out.println("Conexión cerrada correctamente.");
	            conexion=null;
	            
	    		}else {
	    			System.out.println("No hay conexión abierta.");
	    			
	    		}
	    		
	    	}catch(Exception e) {
	    		 System.out.println(" Error intentando desconectar");
		            e.printStackTrace();
	    		
	    	}
	    	
	    }
	    
	    //MÉTODO AÑADIR POKEMON
	    
	    		//Debido a que esto es solo la lógica, todos los datos se irán tomando del JFrame, JText... 
	    		//Por esta razón, este método lo que hace es llamar a esos datos para usarlos.
	    
	    public void anadirPoke (int id, String nombre, int hp, int attack, int defense, int spattack,
	    		int spdefense, int speed, boolean dualtype, int tipo1, Integer tipo2 ) {
	    	
	    		int dualtypeInt = dualtype ? 1 : 0;		//SQL pilla mejor 1 y 0 que boleano, así que usamos esto para traducirlo. Si es true, es 1, sino es 0.

	    	
	    		try {
	    			
	    			//1. Iniciamos con el insert en la tabla de Pokemons, con todos los parámetros.
	    			
	    						//En el String, metemos el comando con los DATOS que escribiríamos en la interfaz de SQL.
	    			
	    			String sqlInsertPokes = "INSERT INTO pokemon VALUES (" + id + ", " + nombre + ", " +
	    											hp + ", "+ attack + ", "+ defense + ", "+ spattack+", "+spdefense+", "
	    											+ speed +", "+ dualtypeInt+ ")";
	    			
	    						//En el siguiente, se ejecuta el método por el cual, en la conexión establecida se ejecuta el String anterior.
	    			
	    			conexion.ejecutarInsertDeleteUpdate(sqlInsertPokes);
	    			
	    			//2. Ahora le añadimos el TIPO PRINCIPAL
	    			
	    			String sqlTipo1 = "INSERT INTO poke_type VALUES ("+id+", "+tipo1+")";
	    			conexion.ejecutarInsertDeleteUpdate(sqlTipo1);
	    			
	    			//3. Insertamos SEGUNDO TIPO si tiene
	    			
	    			if(dualtypeInt == 1 && tipo2 != null) {
	    			String sqlTipo2 = "INSERT INTO poke_type VALUES ("+id+", "+ tipo2+")";
	    			conexion.ejecutarInsertDeleteUpdate(sqlTipo2);
	    			}
	    			
	    			System.out.println("Pokemon añadido correctamente.");
	    			
	    		}catch(Exception e){
	    			
	    			System.out.println("Error al añadir el pokémon.");
	    			e.printStackTrace();
	    			
	    		}
	    	
	    	
	    }
	    
	    
	
	    
	//MÉTODO DE BÚSQUEDA Y CONSULTA; Por nombre, tipo y número
	
	    public ResultSet buscarPokemon (String nombre, String tipo, String numero) {
	    	
	    	//Un parámetro como mínimo
	    	
	    		if ((nombre == null || nombre.isEmpty()) &&
	    				(tipo == null || tipo.isEmpty()) &&
	    				(numero == null || numero.isEmpty())) {
	    			
	    			System.out.println("Debe introducir al menos un parámetro de búsqueda");
	    			return null;
	    			
	    		}
	    	//Consulta a la base de datos
	    		
	    		String sqlconsulta = "SELECT p.IDpoke, p.pokename, t.typename"+
	    		"FROM pokemon p"+
	    				"JOIN poke_type pt ON p.IDpoke = pt.IDpoke"+
	    				"JOIN type t ON pt.IDtype = t.IDtype" +
	    				"WHERE 1=1";
	    
	    //Filtros dinámicos
	    			if (nombre != null && !nombre.isEmpty()) {
	    				sqlconsulta += "AND p.pokename LIKE '%"+nombre+"%' ";
	    			}
	    			if( tipo != null && !tipo.isEmpty()) {
	    				sqlconsulta += "AND t.typename LIKE '%" + tipo + "%' ";
	    			}
	    			if (numero != null && !numero.isEmpty()) {
	    				sqlconsulta += "AND p.IDpoke = " + numero + " ";
	    			}
	    try {
	    	return conexion.ejecutarSelect(sqlconsulta);
	    	
	    }catch(Exception e) {
	    	System.out.println("Error en la búsqueda.");
	    	e.printStackTrace();
	    	return null;
	    }
	    
	    
	    }
	    
	    //MÉTODO MOSTRAR POKEMON
	
	    public void mostrarPokemon(ResultSet rs) {
	    	
	    	try {
	    		if (rs != null && rs.next()) {
	    			
	    			int id = rs.getInt("IDpoke");
	    			String nombre = rs.getString("pokename");
	    			String tipo = rs.getString("typename");
	    			
	    			System.out.println("RESULTADO");
	    			System.out.println("ID: " + id);
	    			System.out.println("Nombre: " +nombre);
	    			System.out.println("Tipo: "+ tipo);
	    		
	    		}else {
	    			System.out.println("No se encontró ningún Pokemon con esos parámetros");
	    		}
	    	}catch(Exception e) {
	    		System.out.println("Error mostrando el Pokemon");
	    		e.printStackTrace();
	    	}
	    }
	    
	    
	    
	    //MÉTODO MODIFICAR POKEMON
	    
	    public void modificarPokemon (int id, String nuevoNombre,
	    		Integer hp, Integer attack, Integer defense, Integer spattack,
	    		Integer spdefense, Integer speed, Boolean dualtype, Integer tipo1, Integer tipo2) {
	    	
	    	try {	//Iniciamos modificando la tabla de pokemon
	    		String sqlconsulta ="UPDATE pokemon SET";
	    		
	    		boolean primero = true;
	    		
	    		if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
	    			sqlconsulta += "pokename =  '"+ nuevoNombre + "'";
	    			primero = false;
	    			
	    		}
	    		
	    		if(hp != null) {
	    			
	    			sqlconsulta += (primero ? "":", ") + "HP = "+hp;
	    			primero = false;
	    		}
	    		
	    		if (attack != null) {
	    			sqlconsulta += (primero ? "": ", ") + "attack = " +attack;
	    			primero = false;
	    		}
	    		
	    		if (defense != null) {
	                sqlconsulta += (primero ? "" : ", ") + "defense = " + defense;
	                primero = false;
	            }

	            if (spattack != null) {
	                sqlconsulta += (primero ? "" : ", ") + "spattack = " + spattack;
	                primero = false;
	            }

	            if (spdefense != null) {
	                sqlconsulta += (primero ? "" : ", ") + "spdefense = " + spdefense;
	                primero = false;
	            }

	            if (speed != null) {
	                sqlconsulta += (primero ? "" : ", ") + "speed = " + speed;
	            }
	            
	            int dualtypeInt = dualtype ? 1 : 0;
	            sqlconsulta += (primero ? "" : ", ")+ "dualtype = " + dualtypeInt;
	            
	            sqlconsulta += "WHERE IDpoke =  " + id;
	            
	            conexion.ejecutarInsertDeleteUpdate(sqlconsulta);
	            
	            //Ahora cambiamos el tipo.
	            
	            boolean modificarTipos = (tipo1 != null || tipo2 != null || dualtype != null);
	            
	            if (modificarTipos) {

	                // Borrar tipos actuales
	                String borrarTipos = "DELETE FROM poke_type WHERE IDpoke = " + id;
	                conexion.ejecutarInsertDeleteUpdate(borrarTipos);

	                // Insertar tipo1 si existe
	                if (tipo1 != null) {
	                    String insertarTipo1 = "INSERT INTO poke_type VALUES (" + id + ", " + tipo1 + ")";
	                    conexion.ejecutarInsertDeleteUpdate(insertarTipo1);
	                }

	                // Insertar tipo2 si dualtype = true y tipo2 no es null
	                if (dualtype != null && dualtype && tipo2 != null) {
	                    String insertarTipo2 = "INSERT INTO poke_type VALUES (" + id + ", " + tipo2 + ")";
	                    conexion.ejecutarInsertDeleteUpdate(insertarTipo2);
	                }
	            }
	            
	            
	            
	            System.out.println("Pokemon modificado correctamente.");
	    		
	    	}catch(Exception e) {
	    		
	    		System.out.println("Error al modificar Pokemon.");
	    		e.printStackTrace();
	    	}
	    	
	    }
	    
	    
	    //MÉTODO ELIMINAR POKEMON
	    
	    public void eliminarPokemon(int id) {

	        try {
	            // 1. Eliminar tipos asociados
	            String sqlTipos = "DELETE FROM poke_type WHERE IDpoke = " + id;
	            conexion.ejecutarInsertDeleteUpdate(sqlTipos);

	            // 2. Eliminar Pokémon
	            String sqlPokemon = "DELETE FROM pokemon WHERE IDpoke = " + id;
	            conexion.ejecutarInsertDeleteUpdate(sqlPokemon);

	            System.out.println("Pokémon eliminado correctamente.");

	        } catch (Exception e) {
	            System.out.println("Error al eliminar Pokémon");
	            e.printStackTrace();
	        }
	    }

	    
	}


