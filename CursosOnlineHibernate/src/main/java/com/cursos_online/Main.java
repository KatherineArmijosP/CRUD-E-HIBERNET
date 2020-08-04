package com.cursos_online;



import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import com.cursos_online.entidades.Curso;
import com.cursos_online.entidades.Estudiante;

public class Main {
	static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure() // configures settings from hibernate.cfg.xml
			.build();
static SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

	public static void main(String[] args) {
		
			Curso cur1= new Curso("Fundamentos de java");
			Curso cur2= new Curso("Hibernate principiantes");
			Curso cur3= new Curso("Matematicas Financiera");
			Curso cur4= new Curso("Topicos de Informatica");
			
			
			ingresarCurso(cur1);
			ingresarCurso(cur2);
			ingresarCurso(cur3);
			ingresarCurso(cur4);
			
			//ModificarCurso
			modificarCurso(3,"Programacion VI");
			
			//EliminarCurso
			eliminarCurso(4);
			
			
			
			Estudiante es1 = new Estudiante(0, "Katherine", "Armijos");
			Estudiante es2 = new Estudiante(0, "Ronald", "Rosado");
			Estudiante es3= new Estudiante(0, "Bryan", "Alvarado");
		
			ingresarEstudiante(es1);
			ingresarEstudiante(es2);
			ingresarEstudiante(es3);
			
			//Modificar Estudiante
			modificarEstudiante(5, "Valentina","Rosales" );
			
			//Eliminar Estudiante
			eliminarEstudiante(7);
			
			
		  List<Curso> cursos=getCursos();
				for(Curso temp:cursos) {
					System.out.println(temp);
				
			}
				
		   List<Estudiante> estudiantes=getEstudiantesPorNombre("Maribel");
		   for(Estudiante e:estudiantes) {
			   System.out.println(e);
		   }
	
	}
	
	
	//CURSOS---------------------------------------------------------------
	 static void ingresarCurso(Curso curso) {
			
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(curso);
			
		session.getTransaction().commit();
		session.close();
		System.out.println(curso.getId());
		}
	 
	 
	 static void modificarCurso(int id, String nombre) {
			
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Curso curso =(Curso)session.get(Curso.class,id);
		curso.setDescripcion(nombre);
		session.update(curso);
		session.getTransaction().commit();
		}
		
	  static void eliminarCurso(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Curso curso =(Curso)session.get(Curso.class,id);
		session.delete(curso);
		session.getTransaction().commit();
		session.close();
			
		}
		
	
	//ESTUDIANTES--------------------------------------
	
	
	static void ingresarEstudiante(Estudiante estudiante) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(estudiante);
		session.getTransaction().commit();
		session.close();
		System.out.println(estudiante.getId());
		
	}
	
   
	static void modificarEstudiante(int id, String nombre, String apellido) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Estudiante estudiante =	(Estudiante)session.get(Estudiante.class,id);
		estudiante.setNombre(nombre);
		estudiante.setApellido(apellido);
		session.update(estudiante);
		session.getTransaction().commit();
		
		session.close();
		
	}
	
	
	static void eliminarEstudiante(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Estudiante estudiante =	(Estudiante)session.get(Estudiante.class,id);
		session.delete(estudiante);
		
		session.getTransaction().commit();
		session.close();
		
	}
    
	//Listas
	static List<Curso> getCursos(){
		Session session=sessionFactory.openSession();
		List<Curso> cursos=session.createQuery("from curso",Curso.class).list();
		return cursos;
	}
	
	
	static List<Estudiante> getEstudiantes(){
		Session session = sessionFactory.openSession();
		List<Estudiante> estudiantes = session.createQuery("from Estudiante", Estudiante.class).list();
		return estudiantes;
	}
	
	 static List<Estudiante> getEstudiantesPorNombre(String nombre) {
			Session session =sessionFactory.openSession();
			Query query= session
					.createQuery("from Estudiante where nombre=:nombre");
			query.setParameter("nombre", nombre);
			
			List<Estudiante> estudiantes = (List<Estudiante>) query.getResultList();
			return estudiantes;
		}
	
}

