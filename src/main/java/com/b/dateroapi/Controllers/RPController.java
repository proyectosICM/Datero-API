package com.b.dateroapi.Controllers;

import com.b.dateroapi.Models.RPModel;
import com.b.dateroapi.Services.RPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/rp")
public class RPController {
    @Autowired
    RPService rpService;

    @GetMapping
    public List<RPModel> ListarRP(){
        return rpService.ListarRP();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RPModel> ListarRPId(@PathVariable Long id){
        Optional<RPModel> rp = rpService.ListarRPXID(id);
        if (rp.isPresent()){
            return new ResponseEntity<>(rp.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("rxp/{ruta}")
    public List<RPModel> ListarPxR(@PathVariable("ruta") Long ruta){
        return rpService.ListarParaderosXRuta(ruta);
    }

    @PostMapping
    public ResponseEntity<RPModel> CrearRP(@RequestBody RPModel rpModel){
        RPModel crp = rpService.CrearRP(rpModel);
        return new ResponseEntity<>(crp, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RPModel> EditarRP(@RequestBody RPModel rpModel, @PathVariable Long id){
        RPModel erp = rpService.EditarRP(rpModel, id);
        if (erp!=null){
            return new ResponseEntity<>(erp, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RPModel> EliminarRP(@PathVariable Long id){
        rpService.EliminarRP(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
