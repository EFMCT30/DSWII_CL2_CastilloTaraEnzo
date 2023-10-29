package pe.edu.cibertec.DSWII_CL2_CastilloTaraEnzo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.DSWII_CL2_CastilloTaraEnzo.model.Producto;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    Optional<Producto> findByNombre(String nombre);

    @Query(value = "SELECT * FROM Producto WHERE YEAR(fechavencimiento) = 2024", nativeQuery = true)
    List<Producto> findProductosVencimientoAnio2024();

    @Query("SELECT p FROM Producto p WHERE p.cantidad > 10 AND p.cantidad < 100")
    List<Producto> findProductosCantidadEntre10y100();

    @Query("SELECT p FROM Producto p WHERE p.nombre LIKE %:filtro%")
    List<Producto> filtrarProductosPorNombre(@Param("filtro") String filtro);

    @Query(value = "SELECT * FROM Producto WHERE nombre LIKE %:filtro%",
            nativeQuery = true)
    List<Producto> filtrarProductosPorNombreSQL(@Param("filtro") String filtro);

}
