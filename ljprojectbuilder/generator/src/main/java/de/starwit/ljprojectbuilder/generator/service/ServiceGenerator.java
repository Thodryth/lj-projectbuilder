package de.starwit.ljprojectbuilder.generator.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import de.starwit.ljprojectbuilder.dto.GeneratorDto;
import de.starwit.ljprojectbuilder.entity.DomainEntity;
import de.starwit.ljprojectbuilder.generator.AbstractGenerator;

public class ServiceGenerator extends AbstractGenerator<ServiceModule> {
	
	public final static Logger LOG = Logger.getLogger(ServiceGenerator.class);
	
	@Override
	public Map<String, Object> fillTemplateDomainParameter(DomainEntity domain) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("domain", domain);
 		return data;
	}

	@Override
	public Map<String, Object> fillTemplateGlobalParameter(GeneratorDto setupBean) {
		if (setupBean.getProject() == null) {
			return null;
		}
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("appName", setupBean.getProject().getTitle().toLowerCase());
		data.put("package", setupBean.getProject().getPackagePrefix().toLowerCase());
		return data;
	}
}
