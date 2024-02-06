package miEmpresa;

public class UseHibernateEnterprise {
	public static void main(String[] args) {
	HibernateEnterprise h=new HibernateEnterprise();
	//h.addProduct(8, "raton",40);
	//h.mostrarProductos();
	//h.mostrarPorNombre("raton");
	//h.productosOrdenadosPorPrecio();
	//h.precioDe("monitor");
	h.buscaProducto(2);
	}
}