package tamagocc.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import tamagocc.ast.api.AEntity;
import tamagocc.ast.impl.AIEntity;
import tamagocc.ast.impl.AIImplements;
import tamagocc.ast.impl.AIInherit;
import tamagocc.ast.impl.AIModule;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GAssemblyContainer;
import tamagocc.generic.api.GComponentContainer;
import tamagocc.generic.api.GCompositeContainer;
import tamagocc.generic.api.GPercolator;
import tamagocc.generic.api.GServiceInterface;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.logger.TamagoCCLogger;

/**
 * The TamagoCCGenerator generate for a specific language the container and/or skeleton
 * for the specified entity. Issues are stored in the output directory.
 * 
 * @author Hakim Belhaouari
 */
public final class TamagoCCGenerator implements TamagoCCIGenerator {

	private String outputDir;
	private File fout;
	private TamagoCCGeneratorTargetLanguageBuilder targetlanguagebuilder;
	private GTamagoEntity entity;
	
	private AIImplements tamagorootservice;
	private AIImplements tamagorootcomponent;
	private AIImplements tamagorootcomposite;
	private AIImplements tamagorootassembly;
	
	private AIImplements tamagorootcontainer;
	private boolean flagNoServiceInterface;
	private boolean flagNoSkeleton;
	
	/**
	 * This constructor initialize the generator to prepare container and/or skeleton.
	 * @param targetlanguagebuilder : the generator of the target language
	 * @param entity : the entity to convert into container and/or skeleton
	 * @param outputdir : the output directory
	 * @param cancreat : specify if the ouput directory and ouput file can be created
	 * @throws TamagoCCException
	 */
	public TamagoCCGenerator(TamagoCCGeneratorTargetLanguageBuilder targetlanguagebuilder,
			GTamagoEntity entity,
			String outputdir,
			boolean cancreat,
			boolean noskeleton,
			boolean nointerface
		) throws TamagoCCException {
		super();
		this.targetlanguagebuilder = targetlanguagebuilder;
		this.entity = entity;
		this.outputDir = outputdir;
	
		tamagorootservice = new AIImplements("TamagoCCService",new AIModule("tamago.ext.tamagocc"));
		tamagorootcomponent = new AIImplements("TamagoCCComponent",new AIModule("tamago.ext.tamagocc"));
		tamagorootcomposite = new AIImplements("TamagoCCComposite",new AIModule("tamago.ext.tamagocc"));
		tamagorootassembly = new AIImplements("TamagoCCAssembly",new AIModule("tamago.ext.tamagocc"));
		tamagorootcontainer = new AIImplements("TamagoCCContainer",new AIModule("tamago.ext.tamagocc"));
		
		flagNoSkeleton = noskeleton;
		flagNoServiceInterface = nointerface;
		
		fout = new File(outputdir);		
		if((!fout.exists()) && (!cancreat))
			throw new TamagoCCException("TamagoCCGenerator.constructor : The output directory doesn't exist and i can't creat it <"+outputdir+">");

		if((!fout.exists()) && cancreat) {
			fout.mkdirs();
			if(!fout.exists())
				throw new TamagoCCException("TamagoCCGenerator.constructor : The creation of the output directory fail ("+outputdir+")");
		}
		
	}
	
	/**
	 * Gets the current output directory
	 * @return Return the output directory
	 */	
	public String getOutputDirectory() {
		return outputDir;
	}
	
	protected File getFileOutputDirectory() {
		return fout;
	}
	
	/* (non-Javadoc)
	 * @see tamagocc.generator.TamagoCCIGenerator#getBuilder()
	 */
	public TamagoCCGeneratorTargetLanguageBuilder getBuilder() {
		return targetlanguagebuilder;
	}
	
	private AEntity generateService(GServiceInterface service) throws TamagoCCException {
		TamagoCCGeneratorInterface inter = new TamagoCCGeneratorInterface(this,service);
		AIEntity res = (AIEntity)inter.getASTEntity();
		res.addImplement(tamagorootservice);
		return res;
	}
	
	private AEntity generateComponentInterface(GComponentContainer component) throws TamagoCCException {
		// TODO : AJOUTER LES DIFFERENCES AVEC LES INTERFACES ....
		TamagoCCGeneratorInterface inter = new TamagoCCGeneratorInterface(this,component);
		AIEntity entity = (AIEntity)inter.getASTEntity();
		entity.addImplement(tamagorootcomponent);
		return entity;
	}
	
	
	private AEntity generateComponentSkeleton(GComponentContainer component) throws TamagoCCException {
		// TODO : ajouter les methodes qui doivent etre completer pour les interfaces classiques
		// de  tamago
		TamagoCCGeneratorSkel skel = new TamagoCCGeneratorSkel(this,component);
		AIEntity entity = (AIEntity)skel.getASTEntity();
		return entity;
	}
	
	private AEntity generateComponentContainerInterface(GComponentContainer component) throws TamagoCCException {
		// TODO : AJOUTER LES DIFFERENCES AVEC LES INTERFACES ....
		TamagoCCGeneratorContainerInterface inter = new TamagoCCGeneratorContainerInterface(this,component);
		AIEntity entity = (AIEntity)inter.getASTEntity();
		entity.setName(this.getBuilder().generateContainerInterfaceName(component));
		entity.addImplement(tamagorootcontainer);
		return entity;
	}
	private AEntity generateComponentContainer(GComponentContainer component,GPercolator percolator) throws TamagoCCException {
		// TODO : ajouter les implementations necessaire pour respecter l'interface container
		TamagoCCGeneratorComponentContainer inter = new TamagoCCGeneratorComponentContainer(this,component,percolator);
		AIEntity entity = (AIEntity)inter.getASTEntity();
		
		AEntity containerinterface = generateComponentContainerInterface(component);
		
		entity.addImplement(new AIImplements(containerinterface.getNameAsType()));
		
		
		entity.setInherit(new AIInherit("TamagoCCContainerImpl",new AIModule("tamago.ext.tamagocc")));
		return entity;
	}
	
	
	private AEntity generateCompositeInterface(GCompositeContainer composite) throws TamagoCCException {
		// 	TODO : AJOUTER LES DIFFERENCES AVEC LES INTERFACES ....
		TamagoCCGeneratorInterface inter = new TamagoCCGeneratorInterface(this,composite);
		AIEntity entity = (AIEntity)inter.getASTEntity();
		entity.setName(this.getBuilder().generateInterfaceName(composite));
		entity.addImplement(tamagorootcomposite);
		return entity;
	}
	private AEntity generateCompositeSkeleton(GCompositeContainer composite) throws TamagoCCException {
		// TODO : ajouter les methodes qui doivent etre completer pour les interfaces classiques
		// de  tamago
		TamagoCCGeneratorSkel skel = new TamagoCCGeneratorSkel(this,composite);
		AIEntity entity = (AIEntity)skel.getASTEntity();
		return entity;
	}
	
	private AEntity generateCompositeContainerInterface(GCompositeContainer composite) throws TamagoCCException {
		//		 TODO : AJOUTER LES DIFFERENCES AVEC LES INTERFACES ....
		TamagoCCGeneratorContainerInterface inter = new TamagoCCGeneratorContainerInterface(this,composite);
		AIEntity entity = (AIEntity)inter.getASTEntity();
		entity.setName(this.getBuilder().generateContainerInterfaceName(composite));
		entity.addImplement(tamagorootcomposite);
		return entity;
	}
	private AEntity generateCompositeContainer(GCompositeContainer composite, GPercolator percolator) throws TamagoCCException {
		TamagoCCGeneratorCompositeContainer inter = new TamagoCCGeneratorCompositeContainer(this,composite,percolator);
		AIEntity entity = (AIEntity)inter.getASTEntity();
		
		entity.addImplement(new AIImplements(this.getBuilder().generateContainerInterfaceName(composite),entity.getModule()));
		entity.setInherit(new AIInherit("TamagoCCContainerImpl",new AIModule("tamago.ext.tamagocc")));
		return entity;
	}
	
	private AEntity generateAssembly(GAssemblyContainer assembly) throws TamagoCCException {
	
		TamagoCCGeneratorAssembly as = new TamagoCCGeneratorAssembly(this,assembly);
		AIEntity entity = (AIEntity)as.getASTEntity();
		entity.addImplement(tamagorootassembly);
		return entity;
	}
	
	private AEntity generateTamagoType(GTamagoEntity entity) throws TamagoCCException {
		TamagoCCGeneratorType type = new TamagoCCGeneratorType(this,entity);
		return type.getASTEntity();
	}
		
	/**
	 * This function generate for a specific language (given by the the builder) the container and/or skeleton
	 * for the specified entity. Issues are stored in the output directory.
	 * @throws TamagoCCException
	 */
	public final void generate() throws TamagoCCException {
		TamagoCCGeneratorTargetLanguage tl;
		TamagoCCLogger.println(3,"Construction of type...");
		AEntity entitytype = generateTamagoType(entity);
		tl = targetlanguagebuilder.getTargetLanguage(entitytype,this.fout);
		tl.generate();
		TamagoCCLogger.println(3,"End of construction of type.");
		
		switch(entity.getCategoryEntity()) {
		case GTamagoEntity.TAMAGO_SERVICE:
			if(flagNoServiceInterface) {
				TamagoCCLogger.println(3,"\t-- User don't want interface of service.");
			}
			else {
				TamagoCCLogger.println(3,"Construction of an AST...");
				AEntity interfaceofservice = generateService((GServiceInterface)entity);
				TamagoCCLogger.println(3,"End of construction");
				TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
				tl = targetlanguagebuilder.getTargetLanguage(interfaceofservice,this.fout);
				tl.generate();
				TamagoCCLogger.println(3,"End of generation.");
			}
			break;
		case GTamagoEntity.TAMAGO_COMPONENT:
			TamagoCCLogger.println(3,"Construction of the Component Interface...");
			AEntity componentinterface = generateComponentInterface((GComponentContainer)entity);
			TamagoCCLogger.println(3,"End of construction");
			TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
			tl = targetlanguagebuilder.getTargetLanguage(componentinterface,this.fout);
			tl.generate();
			TamagoCCLogger.println(3,"End of generation.");
			
			if(flagNoSkeleton) {
				TamagoCCLogger.println(3,"\t-- User don't want a skeleton.");
			}
			else {			
				TamagoCCLogger.println(3,"Construction of the Component Skeleton...");
				AEntity componentskeleton = generateComponentSkeleton((GComponentContainer)entity);
				TamagoCCLogger.println(3,"End of construction");
				TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
				tl = targetlanguagebuilder.getTargetLanguage(componentskeleton,this.fout);
				tl.generate();
				TamagoCCLogger.println(3,"End of generation.");
			}
			
			TamagoCCLogger.println(3,"Construction of the Component Container Interface...");
			AEntity componentcontainerinterface = generateComponentContainerInterface((GComponentContainer)entity);
			TamagoCCLogger.println(3,"End of construction");
			TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
			tl = targetlanguagebuilder.getTargetLanguage(componentcontainerinterface,this.fout);
			tl.generate();
			TamagoCCLogger.println(3,"End of generation.");
			
			TamagoCCLogger.println(3,"Construction of the Component Container Stub...");
			GComponentContainer componentcontainer = (GComponentContainer)entity;
			Iterator<GPercolator> allowedpercolators = componentcontainer.getAllowedPercolators();
			while(allowedpercolators.hasNext()) {
				GPercolator percolator = allowedpercolators.next();
				TamagoCCLogger.println(3,"Generation of the container for the percolator called : "+percolator.getName());
				AEntity container = generateComponentContainer(componentcontainer,percolator);
				TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
				tl = targetlanguagebuilder.getTargetLanguage(container,this.fout);
				tl.generate();
				TamagoCCLogger.println(3,"End generation of the container for the percolator called : "+percolator.getName());
			}
					
			break;
		case GTamagoEntity.TAMAGO_COMPOSITE:
			TamagoCCLogger.println(3,"Construction of the Composite Interface...");
			AEntity compositeinterface = generateCompositeInterface((GCompositeContainer)entity);
			TamagoCCLogger.println(3,"End of construction");
			TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
			tl = targetlanguagebuilder.getTargetLanguage(compositeinterface,this.fout);
			tl.generate();
			TamagoCCLogger.println(3,"End of generation.");
			
			if(flagNoSkeleton) {
				TamagoCCLogger.println(3,"\t-- User don't want a skeleton.");
			}
			else {			
				TamagoCCLogger.println(3,"Construction of the Composite Skeleton...");
				AEntity compositeskeleton = generateCompositeSkeleton((GCompositeContainer)entity);
				TamagoCCLogger.println(3,"End of construction");
				TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
				tl = targetlanguagebuilder.getTargetLanguage(compositeskeleton,this.fout);
				tl.generate();
				TamagoCCLogger.println(3,"End of generation.");
			}
			
			TamagoCCLogger.println(3,"Construction of the Composite Container Interface...");
			AEntity compositecontainerinterface = generateCompositeContainerInterface((GCompositeContainer)entity);
			TamagoCCLogger.println(3,"End of construction");
			TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
			tl = targetlanguagebuilder.getTargetLanguage(compositecontainerinterface,this.fout);
			tl.generate();
			TamagoCCLogger.println(3,"End of generation.");
			
			TamagoCCLogger.println(3,"Construction of the Composite Container Stub...");
			GCompositeContainer compositecontainer = (GCompositeContainer)entity;
			Iterator<GPercolator> callowedpercolators = compositecontainer.getAllowedPercolators();
			while(callowedpercolators.hasNext()) {
				GPercolator percolator = callowedpercolators.next();
				TamagoCCLogger.println(3,"Generation of the container for the percolator called : "+percolator.getName());
				AEntity container = generateCompositeContainer(compositecontainer,percolator);
				TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
				tl = targetlanguagebuilder.getTargetLanguage(container,this.fout);
				tl.generate();
				TamagoCCLogger.println(3,"End generation of the container for the percolator called : "+percolator.getName());
			}
			break;
		case GTamagoEntity.TAMAGO_ASSEMBLY:
			TamagoCCLogger.println(3,"Construction of the Assembly...");
			AEntity assembly = generateAssembly((GAssemblyContainer)entity);
			TamagoCCLogger.println(3,"End of construction");
			TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
			tl = targetlanguagebuilder.getTargetLanguage(assembly,this.fout);
			tl.generate();
			TamagoCCLogger.println(3,"End of generation.");			
			break;
		default:
			TamagoCCLogger.println(3,"TamagoCCGenerator<generate> : Unknow entity");
			throw new TamagoCCException("TamagoCCGenerator<generate> : Unknow entity");
		}
	}

	
	
	/**
	 * This function generate for a specific language (given by the the builder) the container and/or skeleton
	 * for the specified entity. Issues are stored in the output directory.
	 * @throws TamagoCCException
	 */
	public final ArrayList<AEntity> generateAST() throws TamagoCCException {
		ArrayList<AEntity>  res = new ArrayList<AEntity>();
		TamagoCCLogger.println(3,"Construction of type...");
		AEntity entitytype = generateTamagoType(entity);
		TamagoCCLogger.println(3,"End of construction of type.");
		res.add(entitytype);
		
		switch(entity.getCategoryEntity()) {
		case GTamagoEntity.TAMAGO_SERVICE:
			if(flagNoServiceInterface) {
				TamagoCCLogger.println(3,"\t-- User don't want interface of service.");
			}
			else {
				TamagoCCLogger.println(3,"Construction of an AST...");
				AEntity interfaceofservice = generateService((GServiceInterface)entity);
				TamagoCCLogger.println(3,"End of construction");
				TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
				res.add(interfaceofservice);
				TamagoCCLogger.println(3,"End of generation.");
			}
			break;
		case GTamagoEntity.TAMAGO_COMPONENT:
			TamagoCCLogger.println(3,"Construction of the Component Interface...");
			AEntity componentinterface = generateComponentInterface((GComponentContainer)entity);
			TamagoCCLogger.println(3,"End of construction");
			TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
			res.add(componentinterface);
			TamagoCCLogger.println(3,"End of generation.");
			
			if(flagNoSkeleton) {
				TamagoCCLogger.println(3,"\t-- User don't want a skeleton.");
			}
			else {			
				TamagoCCLogger.println(3,"Construction of the Component Skeleton...");
				AEntity componentskeleton = generateComponentSkeleton((GComponentContainer)entity);
				TamagoCCLogger.println(3,"End of construction");
				TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
				res.add(componentskeleton);
				TamagoCCLogger.println(3,"End of generation.");
			}
			
			TamagoCCLogger.println(3,"Construction of the Component Container Interface...");
			AEntity componentcontainerinterface = generateComponentContainerInterface((GComponentContainer)entity);
			TamagoCCLogger.println(3,"End of construction");
			TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
			res.add(componentcontainerinterface);
			TamagoCCLogger.println(3,"End of generation.");
			
			TamagoCCLogger.println(3,"Construction of the Component Container Stub...");
			GComponentContainer componentcontainer = (GComponentContainer)entity;
			Iterator<GPercolator> allowedpercolators = componentcontainer.getAllowedPercolators();
			while(allowedpercolators.hasNext()) {
				GPercolator percolator = allowedpercolators.next();
				TamagoCCLogger.println(3,"Generation of the container for the percolator called : "+percolator.getName());
				AEntity container = generateComponentContainer(componentcontainer,percolator);
				TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
				res.add(container);
				TamagoCCLogger.println(3,"End generation of the container for the percolator called : "+percolator.getName());
			}
					
			break;
		case GTamagoEntity.TAMAGO_COMPOSITE:
			TamagoCCLogger.println(3,"Construction of the Composite Interface...");
			AEntity compositeinterface = generateCompositeInterface((GCompositeContainer)entity);
			res.add(compositeinterface);
			TamagoCCLogger.println(3,"End of construction");
			TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
			TamagoCCLogger.println(3,"End of generation.");
			
			if(flagNoSkeleton) {
				TamagoCCLogger.println(3,"\t-- User don't want a skeleton.");
			}
			else {			
				TamagoCCLogger.println(3,"Construction of the Composite Skeleton...");
				AEntity compositeskeleton = generateCompositeSkeleton((GCompositeContainer)entity);
				res.add(compositeskeleton);
				TamagoCCLogger.println(3,"End of construction");
				TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
				TamagoCCLogger.println(3,"End of generation.");
			}
			
			TamagoCCLogger.println(3,"Construction of the Composite Container Interface...");
			AEntity compositecontainerinterface = generateCompositeContainerInterface((GCompositeContainer)entity);
			TamagoCCLogger.println(3,"End of construction");
			TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
			res.add(compositecontainerinterface);
			TamagoCCLogger.println(3,"End of generation.");
			
			TamagoCCLogger.println(3,"Construction of the Composite Container Stub...");
			GCompositeContainer compositecontainer = (GCompositeContainer)entity;
			Iterator<GPercolator> callowedpercolators = compositecontainer.getAllowedPercolators();
			while(callowedpercolators.hasNext()) {
				GPercolator percolator = callowedpercolators.next();
				TamagoCCLogger.println(3,"Generation of the container for the percolator called : "+percolator.getName());
				AEntity container = generateCompositeContainer(compositecontainer,percolator);
				TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
				res.add(container);
				TamagoCCLogger.println(3,"End generation of the container for the percolator called : "+percolator.getName());
			}
			break;
		case GTamagoEntity.TAMAGO_ASSEMBLY:
			TamagoCCLogger.println(3,"Construction of the Assembly...");
			AEntity assembly = generateAssembly((GAssemblyContainer)entity);
			TamagoCCLogger.println(3,"End of construction");
			TamagoCCLogger.println(3,"Generation of the output in "+targetlanguagebuilder.getLanguage()+" ...");
			res.add(assembly);
			TamagoCCLogger.println(3,"End of generation.");			
			break;
		default:
			TamagoCCLogger.println(3,"TamagoCCGenerator<generate> : Unknow entity");
			throw new TamagoCCException("TamagoCCGenerator<generate> : Unknow entity");
		}
		return res;
	}
}
