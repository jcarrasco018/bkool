package com.bkool.infraestructure.controller;


import com.bkool.application.api.BikeService;
import com.bkool.domain.exception.NotFoundBikesException;
import com.bkool.infraestructure.dto.BikeDTO;
import com.bkool.infraestructure.mapper.BikeDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/bike")
@SecurityRequirement(name = "Authorization")
public class BikeController {

    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @Operation(summary = "Devuelve bikes que concidan con la busqueda",
            description = "Este metodo buscara en BBDD las bikes que coincidan con los filtros de entrada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encuentra bike que coinciden"),
            @ApiResponse(responseCode = "404", description = "No Se encuentra una bike", content = @Content),
            @ApiResponse(responseCode = "400", description = "Hay algún problema en la consulta", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<BikeDTO>> searchBikes(
            @RequestParam(name = "name",required = false) String name,
            @RequestParam(name = "manufacturer",required = false) String manufacturer,
            @RequestParam(name = "itemType",required = false) String itemType,
            @RequestParam(name = "order",required = false) String order

    ) throws NotFoundBikesException {
        List<BikeDTO> bikes = bikeService.search(name,manufacturer,itemType,order).stream().map(BikeDTOMapper::toDTO).toList();
        if (bikes.isEmpty()) {
            throw new NotFoundBikesException(String.format("Bikes no encontrado para name %s - manufacturer %s - itemType: %s - order: %s",name,manufacturer,itemType,order));
        }
        return new ResponseEntity<>(bikes, HttpStatus.OK);
    }

    @Operation(summary = "Guarda una Bike",
            description = "Este metodo guardara en BBDD el BikeDTO que le pasamos por el body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guarda Correctamente"),
            @ApiResponse(responseCode = "500", description = "Hay algún problema en el guardado", content = @Content)
    })
    @PostMapping
    public ResponseEntity<BikeDTO> save(@RequestBody BikeDTO bikeDTO) {
        BikeDTO bike = BikeDTOMapper.toDTO(bikeService.save(BikeDTOMapper.toEntity(bikeDTO)));
        if (Objects.isNull(bike)) {
            throw new InternalError("Error al guardar");
        }
        return new ResponseEntity<>(bike, HttpStatus.CREATED);
    }


}
