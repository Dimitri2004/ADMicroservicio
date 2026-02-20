package org.example.export;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.example.model.Libro;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class XmlExportService {

    private static final String XML_FILE_PATH = "libros_exportados.xml";

    public void exportarLibrosAXml(List<Libro> librosOriginales) {
        if (librosOriginales == null || librosOriginales.isEmpty()) {
            System.out.println("No hay libros para exportar a XML.");
            return;
        }

        // 1. Convertir la lista de Libro a una lista de LibroDto
        List<LibroDto> librosDto = new ArrayList<>();
        for (Libro libro : librosOriginales) {
            LibroDto dto = new LibroDto();
            dto.setId(libro.getId());
            dto.setTitulo(libro.getTitulo());
            dto.setAutor(libro.getAutor());
            dto.setAnoPublicacion(libro.getAnoPublicacion());
            librosDto.add(dto);
        }

        // 2. Envolver la lista de DTOs
        LibrosDtoWrapper wrapper = new LibrosDtoWrapper();
        wrapper.setLibros(librosDto);

        // 3. Serializar a XML usando JAXB
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(LibrosDtoWrapper.class, LibroDto.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(wrapper, new File(XML_FILE_PATH));
            System.out.println("Libros exportados correctamente a " + XML_FILE_PATH);
        } catch (JAXBException e) {
            System.err.println("Error al exportar libros a XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
