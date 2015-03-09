package dinamica.mensajeriadinamica.controlador.rest;

import org.glassfish.jersey.server.ResourceConfig;

public class Aplicacion extends ResourceConfig 
{
    public Aplicacion() {
        packages("dinamica.mensajeriadinamica.controlador.rest");
    }
}
