package edu.unimagdalena.reservasespacios.dtos.mappers;

import edu.unimagdalena.reservasespacios.dtos.requests.EspacioDTOResquests;
import edu.unimagdalena.reservasespacios.dtos.response.EspacioDTOResponse;
import edu.unimagdalena.reservasespacios.entities.Espacio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EspacioMapper {

    /**
     * Request DTO → Entidad
     * - Ignora el ID que se genera en BD
     * - Ignora la relación Sede: se asigna en el servicio
     */
    @Mapping(target = "idEspacio", ignore = true)
    @Mapping(target = "sede",       ignore = true)
    Espacio dtoToEspacio(EspacioDTOResquests dto);

    /**
     * Entidad → Response DTO
     * - Mapea idEspacio → id
     * - Extrae sede.id → idSede
     */
    @Mapping(source = "idEspacio", target = "id")
    @Mapping(source = "sede.id",    target = "idSede")
    EspacioDTOResponse espacioToDTO(Espacio entidad);

    /**
     * Actualiza campos de la entidad desde el DTO:
     * - Mantiene idEspacio y la relación Sede intactos
     * - Copia nombre, tipo, restricciones y disponible
     */
    @Mapping(target = "idEspacio", ignore = true)
    @Mapping(target = "sede",       ignore = true)
    void updateEspacioFromRequestDTO(EspacioDTOResquests dto, @MappingTarget Espacio espacio);

}
