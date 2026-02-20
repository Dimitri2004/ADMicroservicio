package org.example.export;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "libros")
public class LibrosDtoWrapper {
    private List<LibroDto> libros;

    @XmlElement(name = "libro")
    public List<LibroDto> getLibros() {
        return libros;
    }

    public void setLibros(List<LibroDto> libros) {
        this.libros = libros;
    }
}
