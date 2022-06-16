package com.tecazuay.complexivog2c2.dto.convenios;

import com.tecazuay.complexivog2c2.dto.empresa.EmpresaRequest;
import com.tecazuay.complexivog2c2.dto.empresa.EmpresaResponse;
import com.tecazuay.complexivog2c2.model.Primary.empresa.Empresa;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class RegistroConveniosRequest implements Serializable {

    private Long id;

    private Date fechaConvenio;

    private String nombreCordinadorVinculacion;

    private List<EmpresaRequest> registroConvenioEmpresa;


}
