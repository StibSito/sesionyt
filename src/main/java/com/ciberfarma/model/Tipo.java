package com.ciberfarma.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_tipos")
public class Tipo {

	@Id
	private int idtipo;
	private String descripcion;
}
