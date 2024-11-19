package servicios;

import modelos.Productos;

import java.util.Arrays;
import java.util.List;

public class ProductosServiceImplement implements ProductosServices {

    @Override
    public List<Productos> listar() {
        return Arrays.asList(new Productos(1,"Laptop","computación", 575.25),
                new Productos(2,"cocina", "hogar",359.45),
                new Productos(3,"mouse", "tecnología",15.52));
    }
}
