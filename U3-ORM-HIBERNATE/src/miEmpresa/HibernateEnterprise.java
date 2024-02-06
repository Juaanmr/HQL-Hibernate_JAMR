package miEmpresa;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateEnterprise {
	private static SessionFactory sf; // this SessionFactory will be created once and used for all the connections

	HibernateEnterprise() {// constructor
		// sf = HibernateUtil.getSessionFactory();
		sf = new Configuration().configure().buildSessionFactory(); // also works
	}

	public void addProduct(int id, String nombre, double precio) {
		Session session = sf.openSession();// session es l variable que tiene el método
		// save para guardar productos
		Transaction tx = null;
		// create the product with the parameters in the method
		Productos p = new Productos();
		p.setNombre(nombre);
		p.setPrecio(precio);
		p.setId(id);
		// keep it in the database=🡺session.save(p)
		try {
			System.out.printf("Inserting a row in the database: %s, %s, %s\n", id, nombre, precio);
			tx = session.beginTransaction();
			session.save(p);// we INSERT p into the table PRODUCTS
			tx.commit();// if session.save doesn't produce an exception, we "commit" the transaction
		} catch (Exception e) {// if there is any exception, we "rollback" and close safely
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

	public void mostrarProductos() {
		Session session = sf.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List allproducts = session.createQuery("FROM Productos").list();
			Iterator it = allproducts.iterator();
			while (it.hasNext()) {
				// for (Iterator iterator = allproducts.iterator(); iterator.hasNext();){
				Productos p = (Productos) it.next();
				System.out.print("Id: " + p.getId());
				System.out.print(" ,Name: " + p.getNombre());
				System.out.println(" ,Price: " + p.getPrecio());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void mostrarPorNombre(String texto) {
		Session session = sf.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			String hql = "SELECT p FROM Productos p WHERE p.nombre = :nombre";
			java.util.List allproducts = session.createQuery(hql).setParameter("nombre", texto).list();
			Iterator iterator = allproducts.iterator();

			while (iterator.hasNext()) {
				Productos p = (Productos) iterator.next();

				System.out.println("Nombre: " + p.getNombre());

			}
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}
	}

	public void productosOrdenadosPorPrecio() {
		Session session = sf.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List allproducts = session.createQuery("FROM Productos ORDER BY precio ASC").list();
			Iterator it = allproducts.iterator();
			while (it.hasNext()) {
				// for (Iterator iterator = allproducts.iterator(); iterator.hasNext();){
				Productos p = (Productos) it.next();
				System.out.print("Id: " + p.getId());
				System.out.print(" ,Name: " + p.getNombre());
				System.out.println(" ,Price: " + p.getPrecio());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void precioDe(String nombre) {
		Session session = sf.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			String hql = "SELECT p FROM Productos p WHERE p.nombre = :nombre";
			List allproducts = session.createQuery(hql).setParameter("nombre", nombre).list();
			Iterator iterator = allproducts.iterator();

			while (iterator.hasNext()) {
				Productos p = (Productos) iterator.next();

				System.out.print("Precio de '" + p.getNombre() + "' es: " + p.getPrecio());

			}
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}
	}

	public void buscaProducto(int id) {
		Session session = sf.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			// Utilizamos uniqueResult para obtener un solo resultado
			Query query = session.createQuery("FROM Productos WHERE id = :id");
			query.setParameter("id", id);

			Productos p = (Productos) query.uniqueResult();

			if (p != null) {
				System.out.println("Información del producto con id " + id + ":");
				System.out.println("Id: " + p.getId());
				System.out.println("Nombre: " + p.getNombre());
				System.out.println("Precio: " + p.getPrecio());
				// Puedes mostrar más información si es necesario
			} else {
				System.out.println("No se encontró ningún producto con id: " + id);
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}// class
