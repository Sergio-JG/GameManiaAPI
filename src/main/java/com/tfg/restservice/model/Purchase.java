package com.tfg.restservice.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "purchase")
@Getter
@Setter
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "purchase_id")
	private UUID purchaseId;

	@Column(name = "purchase_date")
	private Date purchaseDate;

	@Column(name = "total_amount")
	private BigDecimal totalAmount;

	@ManyToOne
	@JoinColumn(name = "provider_id")
	private Provider provider;

	@OneToMany(mappedBy = "purchase")
	@JsonIgnore
	private List<PurchaseDetail> purchaseDetails;

}
