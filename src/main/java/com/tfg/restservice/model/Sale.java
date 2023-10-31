package com.tfg.restservice.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_sale")
@Getter
@Setter
public class Sale {

	@Id
	@GeneratedValue
	@Column(name = "sale_id")
	private UUID saleId;

	@Column(name = "sale_date")
	private Date saleDate;

	@Column(name = "total_amount")
	private BigDecimal totalAmount;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "sale")
	@JsonIgnore
	private List<SaleDetail> saleDetails;

}
