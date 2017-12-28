package de.starwit.generator.modules;

import de.starwit.generator.config.Constants;
import de.starwit.generator.config.TemplateDef;
import de.starwit.generator.dto.GeneratorDto;

public class ServiceModule extends AbstractModule {
	private final static String FS = Constants.FILE_SEP;

	public ServiceModule(GeneratorDto setupBean) {
		super(setupBean);
		this.setModuleName("persistence");
		
		TemplateDef serviceInterfaceT = new TemplateDef(getSrcDir() + "ejb" + FS, "Service.java", "service" + FS + "service.ftl");
		serviceInterfaceT.setUpperCaseFirst(true);
		TemplateDef serviceImplT = new TemplateDef(getSrcDir() + "ejb" + FS + "impl" + FS, "ServiceImpl.java", "service" + FS + "serviceImpl.ftl");
		serviceImplT.setUpperCaseFirst(true);
		TemplateDef serviceTestT = new TemplateDef(getTestDir() + "ejb" + FS  + FS, "ServiceTest.java", "service" + FS + "serviceTest.ftl");
		serviceTestT.setUpperCaseFirst(true);

		getDomainTemplates().add(serviceInterfaceT);
		getDomainTemplates().add(serviceImplT);
		getDomainTemplates().add(serviceTestT);
	}
	
	@Override
	public String getSrcDir() {
		return this.getModuleDir()
				+ FS + getPaths().getSource() + FS + "de" + FS
				+ getSetupBean().getProject().getPackagePrefix().toLowerCase()
				+ FS + getSetupBean().getProject().getTitle().toLowerCase() + FS;
	}
	
	@Override
	public String getTestDir() {
		return this.getModuleDir()
				+ FS + getPaths().getTest() + FS + "de" + FS
				+ getSetupBean().getProject().getPackagePrefix().toLowerCase()
				+ FS + getSetupBean().getProject().getTitle().toLowerCase() + FS;
	}

}
