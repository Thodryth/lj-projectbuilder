package de.starwit.ljprojectbuilder.generator.entity;

import java.util.HashSet;
import java.util.Set;

import de.starwit.ljprojectbuilder.entity.AttributeEntity;
import de.starwit.ljprojectbuilder.entity.DataType;
import de.starwit.ljprojectbuilder.entity.DomainEntity;

public class EntityImports {
	
	public static Set<String> gatherEntityImports(DomainEntity domain) {
		Set<String> imports = new HashSet<String>();
		if (domain.getAttributes() != null) {
			for (AttributeEntity attr : domain.getAttributes()) {
				if (DataType.String.equals(attr.getDataType())) {
					if (attr.getMax() != null || attr.getMin() != null) {
						imports.add("import javax.validation.constraints.Size;");
					}
					if (!attr.isNullable()) {
						imports.add("import org.hibernate.validator.constraints.NotBlank;");
					}
				} else if (DataType.Date.equals(attr.getDataType()) || DataType.Time.equals(attr.getDataType())
						|| DataType.Timestamp.equals(attr.getDataType())) {
					imports.add("import java.util.Date;");
					imports.add("import javax.persistence.Temporal;");
					imports.add("import javax.persistence.TemporalType;");
				} else if (DataType.Enum.equals(attr.getDataType())) {
					imports.add("import javax.persistence.EnumType;");
					imports.add("import javax.persistence.Enumerated;");
				} else if (DataType.BigDecimal.equals(attr.getDataType())) {
					imports.add("import java.math.BigDecimal;");
				}
				if (attr.getPattern() != null) {
					imports.add("import javax.validation.constraints.Pattern;");
				}

				if (!DataType.String.equals(attr.getDataType())) {
					if (!attr.isNullable()) {
						imports.add("import javax.validation.constraints.NotNull;");
					}
					if (attr.getMax() != null) {
						imports.add("import javax.validation.constraints.Max;");
					}
					if (attr.getMin() != null) {
						imports.add("import javax.validation.constraints.Min;");
					}
				}
			}
		}
		return imports;
	}
}