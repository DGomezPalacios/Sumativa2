/* package com.Charmeetchic.Inventario.assembler;

import com.Charmeetchic.Inventario.controller.InventarioControllerV2;
import com.Charmeetchic.Inventario.model.Inventario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<Inventario, EntityModel<Inventario>> {

    @Override
    public EntityModel<Inventario> toModel(Inventario inventario) {
        return EntityModel.of(inventario,
                linkTo(methodOn(InventarioControllerV2.class).obtenerPorId(inventario.getId())).withSelfRel(),
                linkTo(methodOn(InventarioControllerV2.class).listarInventario()).withRel("todos"));
    }
}
 */