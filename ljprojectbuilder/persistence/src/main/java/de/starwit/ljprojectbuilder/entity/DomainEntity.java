package de.starwit.ljprojectbuilder.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

@XmlRootElement
@Entity
@Table(name="DOMAIN")
public class DomainEntity extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	
	//domain attributes
	
	@NotBlank
	private String name;
	
	private String description;
	
	@NotNull
	private ProjectEntity project;
	
	private List<AttributeEntity> attributes;
	
	

	@Column(name="NAME", nullable = false)
	public String getName() {
		return name;
	}
	
		public void setName(String name) {
		this.name = name;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJECT_ID")
	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	public List<AttributeEntity> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeEntity> attributes) {
		this.attributes = attributes;
	}
}