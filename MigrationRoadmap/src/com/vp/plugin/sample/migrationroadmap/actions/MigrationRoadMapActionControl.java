package com.vp.plugin.sample.migrationroadmap.actions;

import java.awt.Color;
import java.sql.Date;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.DiagramManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import com.vp.plugin.diagram.IDiagramTypeConstants;
import com.vp.plugin.diagram.IMigrationRoadmapUIModel;
import com.vp.plugin.model.IMigrationRoadmapCheckPoint;
import com.vp.plugin.model.IMigrationRoadmapDeliverable;
import com.vp.plugin.model.IMigrationRoadmapDeliverableDependency;
import com.vp.plugin.model.IMigrationRoadmapInvestmentPoint;
import com.vp.plugin.model.IMigrationRoadmapModel;
import com.vp.plugin.model.IMigrationRoadmapProgramme;
import com.vp.plugin.model.IMigrationRoadmapProject;
import com.vp.plugin.model.IMigrationRoadmapProjectBar;
import com.vp.plugin.model.IMigrationRoadmapTimeline;
import com.vp.plugin.model.IMigrationRoadmapTransitionTarget;
import com.vp.plugin.model.factory.IModelElementFactory;

public class MigrationRoadMapActionControl implements VPActionController {

	@Override
	public void performAction(VPAction arg0) {
		// Create migration roadmap diagram
		DiagramManager dm = ApplicationManager.instance().getDiagramManager();
		IMigrationRoadmapUIModel migrationRoadMapDiagram = (IMigrationRoadmapUIModel) dm.createDiagram(IDiagramTypeConstants.DIAGRAM_TYPE_MIGRATION_ROADMAP);
		migrationRoadMapDiagram.setName("Migration Roadmap - Migration Roadmap Example");
		
		// Create IMigrationRoadmapModel 
		// and specify the name of the Baseline Architecture 
		// and Target Architecture
		IMigrationRoadmapModel migrationRoadMap = IModelElementFactory.instance().createMigrationRoadmapModel();
		migrationRoadMapDiagram.setMigrationRoadmapModelAddress(migrationRoadMap.getAddress());
		migrationRoadMap.setBaselineArchitectureName("Baseline Architecture");
		migrationRoadMap.setTargetArchitectureName("Target Architecture");
		
		// Create timeline
		IMigrationRoadmapTimeline timeline = IModelElementFactory.instance().createMigrationRoadmapTimeline();
		// Specify the start time as millisecond value in string type
		Date startDate = Date.valueOf("2017-07-19");				
		timeline.setStartDate(startDate.getTime() + "");
		// Specify the timeline with 1 year as overall duration 
		timeline.setDurationUnit(IMigrationRoadmapTimeline.DURATION_UNIT_YEAR);
		timeline.setDurationUnitCount(1);
		// Specify the timeline with monthly time unit
		timeline.setIntervalUnit(IMigrationRoadmapTimeline.INTERVAL_UNIT_MONTH);		
		timeline.setIntervalUnitCount(1);
		// Each time unit with 80px width
		timeline.setTimeUnitWidth(80);	
		// Add the timelink to migration roadmap
		migrationRoadMap.setTimeline(timeline);
				
		IMigrationRoadmapProgramme progUnifiedCRM = IModelElementFactory.instance().createMigrationRoadmapProgramme();
		progUnifiedCRM.setName("Unified CRM");
		// Specify the color of program (for project bars) in RGB value
		progUnifiedCRM.setColor(Color.ORANGE.getRGB());
		migrationRoadMap.addProgramme(progUnifiedCRM);
		
		IMigrationRoadmapProject prjCRMIntegration = IModelElementFactory.instance().createMigrationRoadmapProject();
		prjCRMIntegration.setName("CRM System Integration");
		progUnifiedCRM.addProject(prjCRMIntegration);
		
		IMigrationRoadmapProjectBar barCRM = prjCRMIntegration.createMigrationRoadmapProjectBar();
		barCRM.setEndTime(160);	
		prjCRMIntegration.addBar(barCRM);

		IMigrationRoadmapProject prjDbMigration = IModelElementFactory.instance().createMigrationRoadmapProject();
		prjDbMigration.setName("Database Migration");
		progUnifiedCRM.addProject(prjDbMigration);
		
		IMigrationRoadmapProjectBar barDBMigration = prjDbMigration.createMigrationRoadmapProjectBar();
		barDBMigration.setStartTime(160);
		barDBMigration.setEndTime(200);		
		prjDbMigration.addBar(barDBMigration);
				
		IMigrationRoadmapProgramme progBackOfficeInt = IModelElementFactory.instance().createMigrationRoadmapProgramme();
		progBackOfficeInt.setName("Back Office Int.");
		progBackOfficeInt.setColor(Color.PINK.getRGB());
		migrationRoadMap.addProgramme(progBackOfficeInt);
		
		IMigrationRoadmapProject prjHWSetup = IModelElementFactory.instance().createMigrationRoadmapProject();
		prjHWSetup.setName("Hardware Setup");
		progBackOfficeInt.addProject(prjHWSetup);
		
		IMigrationRoadmapProjectBar barHWSetup = prjHWSetup.createMigrationRoadmapProjectBar();
		barHWSetup.setEndTime(50);
		prjHWSetup.addBar(barHWSetup);
		
		IMigrationRoadmapProject prjSystemModification = IModelElementFactory.instance().createMigrationRoadmapProject();
		prjSystemModification.setName("System Modification");
		progBackOfficeInt.addProject(prjSystemModification);
		
		IMigrationRoadmapProjectBar barSysMod = prjSystemModification.createMigrationRoadmapProjectBar();
		barSysMod.setEndTime(440);
		prjSystemModification.addBar(barSysMod);
		
		IMigrationRoadmapProject prjPhasingOut = IModelElementFactory.instance().createMigrationRoadmapProject();
		prjPhasingOut.setName("Phrasing out Legacy");
		progBackOfficeInt.addProject(prjPhasingOut);
		
		IMigrationRoadmapProjectBar barPhraseOut = prjPhasingOut.createMigrationRoadmapProjectBar();
		barPhraseOut.setStartTime(440);
		barPhraseOut.setEndTime(520);
		prjPhasingOut.addBar(barPhraseOut);		
		
		IMigrationRoadmapProgramme progDigitalCustomerExp = IModelElementFactory.instance().createMigrationRoadmapProgramme();
		progDigitalCustomerExp.setName("Digital Customer Exp.");
		progDigitalCustomerExp.setColor(Color.CYAN.getRGB());
		migrationRoadMap.addProgramme(progDigitalCustomerExp);
		
		IMigrationRoadmapProject prjIoTDataWarehousing = IModelElementFactory.instance().createMigrationRoadmapProject();
		prjIoTDataWarehousing.setName("IoT-Based Data Warehousing");
		progDigitalCustomerExp.addProject(prjIoTDataWarehousing);
		
		IMigrationRoadmapProjectBar barIoT = prjIoTDataWarehousing.createMigrationRoadmapProjectBar();
		barIoT.setStartTime(520);
		barIoT.setEndTime(720);
		prjIoTDataWarehousing.addBar(barIoT);
		
		IMigrationRoadmapProject prjSocialMedia = IModelElementFactory.instance().createMigrationRoadmapProject();
		prjSocialMedia.setName("Social Media Apps Development");
		progDigitalCustomerExp.addProject(prjSocialMedia);
		
		IMigrationRoadmapProjectBar barSocialMedia = prjSocialMedia.createMigrationRoadmapProjectBar();
		barSocialMedia.setStartTime(520);
		barSocialMedia.setEndTime(590);
		prjSocialMedia.addBar(barSocialMedia);
		
		IMigrationRoadmapProject prjDataAnalysis = IModelElementFactory.instance().createMigrationRoadmapProject();
		prjDataAnalysis.setName("Automated Data Analysis");
		progDigitalCustomerExp.addProject(prjDataAnalysis);
		
		IMigrationRoadmapProjectBar barDataAnalysis = prjDataAnalysis.createMigrationRoadmapProjectBar();
		barDataAnalysis.setStartTime(720);
		barDataAnalysis.setEndTime(820);
		prjDataAnalysis.addBar(barDataAnalysis);
		
		IMigrationRoadmapProgramme progDocMgmt = IModelElementFactory.instance().createMigrationRoadmapProgramme();
		progDocMgmt.setName("Doc. Mgmt.");
		progDocMgmt.setColor(Color.MAGENTA.getRGB());
		migrationRoadMap.addProgramme(progDocMgmt);
		
		IMigrationRoadmapProject prjBackupServer = IModelElementFactory.instance().createMigrationRoadmapProject();
		prjBackupServer.setName("Doc. Mgmt. Backup Server Setup");
		progDocMgmt.addProject(prjBackupServer);
		
		IMigrationRoadmapProjectBar barBackupServer = prjBackupServer.createMigrationRoadmapProjectBar();
		barBackupServer.setStartTime(720);
		barBackupServer.setEndTime(880);
		prjBackupServer.addBar(barBackupServer);

		IMigrationRoadmapDeliverable crmDeliverable = IModelElementFactory.instance().createMigrationRoadmapDeliverable();
		// Specify the time of the deliverable being generated
		// the time is relative to the start time of the project bar
		crmDeliverable.setTime(150);
		barCRM.addDeliverable(crmDeliverable);
		
		IMigrationRoadmapDeliverable dbMigrationDeliverable = IModelElementFactory.instance().createMigrationRoadmapDeliverable();
		dbMigrationDeliverable.setTime(10);
		barDBMigration.addDeliverable(dbMigrationDeliverable);
		
		IMigrationRoadmapDeliverableDependency depCRMDBMigration = IModelElementFactory.instance().createMigrationRoadmapDeliverableDependency();
		depCRMDBMigration.setFromDeliverable(crmDeliverable);
		depCRMDBMigration.setToDeliverable(dbMigrationDeliverable);
		migrationRoadMap.addDeliverableDependencie(depCRMDBMigration);
		
		IMigrationRoadmapDeliverable sysModDeliverable = IModelElementFactory.instance().createMigrationRoadmapDeliverable();
		sysModDeliverable.setTime(430);
		barSysMod.addDeliverable(sysModDeliverable);
		
		IMigrationRoadmapDeliverable phraseOutStartDeliverable = IModelElementFactory.instance().createMigrationRoadmapDeliverable();
		phraseOutStartDeliverable.setTime(10);
		barPhraseOut.addDeliverable(phraseOutStartDeliverable);
		
		IMigrationRoadmapDeliverableDependency depSysModPhraseOut = IModelElementFactory.instance().createMigrationRoadmapDeliverableDependency();
		depSysModPhraseOut.setFromDeliverable(sysModDeliverable);
		depSysModPhraseOut.setToDeliverable(phraseOutStartDeliverable);
		migrationRoadMap.addDeliverableDependencie(depSysModPhraseOut);
		
		IMigrationRoadmapDeliverable phraseOutEndDeliverable = IModelElementFactory.instance().createMigrationRoadmapDeliverable();
		phraseOutEndDeliverable.setTime(70);
		barPhraseOut.addDeliverable(phraseOutEndDeliverable);
		
		IMigrationRoadmapDeliverable iotStartDeliverable = IModelElementFactory.instance().createMigrationRoadmapDeliverable();
		iotStartDeliverable.setTime(17);
		barIoT.addDeliverable(iotStartDeliverable);
		
		IMigrationRoadmapDeliverableDependency depPhraseOutIoT = IModelElementFactory.instance().createMigrationRoadmapDeliverableDependency();
		depPhraseOutIoT.setFromDeliverable(phraseOutEndDeliverable);
		depPhraseOutIoT.setToDeliverable(iotStartDeliverable);
		migrationRoadMap.addDeliverableDependencie(depPhraseOutIoT);
		
		IMigrationRoadmapDeliverable socialMediaDeliverable = IModelElementFactory.instance().createMigrationRoadmapDeliverable();
		socialMediaDeliverable.setTime(17);
		barSocialMedia.addDeliverable(socialMediaDeliverable);
		
		IMigrationRoadmapDeliverableDependency depPhraseOutSocialMedia = IModelElementFactory.instance().createMigrationRoadmapDeliverableDependency();
		depPhraseOutSocialMedia.setFromDeliverable(phraseOutEndDeliverable);
		depPhraseOutSocialMedia.setToDeliverable(socialMediaDeliverable);
		migrationRoadMap.addDeliverableDependencie(depPhraseOutSocialMedia);
		
		IMigrationRoadmapDeliverable iotEndDeliverable = IModelElementFactory.instance().createMigrationRoadmapDeliverable();
		iotEndDeliverable.setTime(190);
		barIoT.addDeliverable(iotEndDeliverable);
		
		IMigrationRoadmapDeliverable dataAnalysisDeliverable = IModelElementFactory.instance().createMigrationRoadmapDeliverable();
		dataAnalysisDeliverable.setTime(10);
		barDataAnalysis.addDeliverable(dataAnalysisDeliverable);
		
		IMigrationRoadmapDeliverableDependency depIoTDataAnalysis = IModelElementFactory.instance().createMigrationRoadmapDeliverableDependency();
		depIoTDataAnalysis.setFromDeliverable(iotEndDeliverable);
		depIoTDataAnalysis.setToDeliverable(dataAnalysisDeliverable);
		migrationRoadMap.addDeliverableDependencie(depIoTDataAnalysis);
		
		IMigrationRoadmapDeliverable backupServerDeliverable = IModelElementFactory.instance().createMigrationRoadmapDeliverable();
		backupServerDeliverable.setTime(10);
		barBackupServer.addDeliverable(backupServerDeliverable);
		
		IMigrationRoadmapDeliverableDependency depIoTBackupServer = IModelElementFactory.instance().createMigrationRoadmapDeliverableDependency();
		depIoTBackupServer.setFromDeliverable(iotEndDeliverable);
		depIoTBackupServer.setToDeliverable(backupServerDeliverable);
		migrationRoadMap.addDeliverableDependencie(depIoTBackupServer);
		
		IMigrationRoadmapTransitionTarget transitionA = IModelElementFactory.instance().createMigrationRoadmapTransitionTarget();
		transitionA.setName("Transition A");
		transitionA.setTime(200);
		migrationRoadMap.addTransitionTarget(transitionA);		
		
		IMigrationRoadmapTransitionTarget transitionB = IModelElementFactory.instance().createMigrationRoadmapTransitionTarget();
		transitionB.setName("Transition B");
		transitionB.setTime(520);
		migrationRoadMap.addTransitionTarget(transitionB);		
		
		// Create investment point
		IMigrationRoadmapInvestmentPoint investmentPt1 = IModelElementFactory.instance().createMigrationRoadmapInvestmentPoint();
		investmentPt1.setTime(30);
		migrationRoadMap.addInvestmentPoint(investmentPt1);
		
		IMigrationRoadmapInvestmentPoint investmentPt2 = IModelElementFactory.instance().createMigrationRoadmapInvestmentPoint();
		investmentPt2.setTime(200);
		migrationRoadMap.addInvestmentPoint(investmentPt2);
		
		IMigrationRoadmapInvestmentPoint investmentPt3 = IModelElementFactory.instance().createMigrationRoadmapInvestmentPoint();
		investmentPt3.setTime(540);	
		migrationRoadMap.addInvestmentPoint(investmentPt3);
		
		// Create check point
		IMigrationRoadmapCheckPoint cp1 = IModelElementFactory.instance().createMigrationRoadmapCheckPoint();
		cp1.setName("Review");
		// Specify the color of the check point
		cp1.setColor(Color.ORANGE.getRGB());
		cp1.setTime(190);
		migrationRoadMap.addCheckPoint(cp1);
		
		IMigrationRoadmapCheckPoint cp2 = IModelElementFactory.instance().createMigrationRoadmapCheckPoint();
		cp2.setName("Review");
		cp2.setColor(Color.BLUE.getRGB());
		cp2.setTime(510);
		migrationRoadMap.addCheckPoint(cp2);

		IMigrationRoadmapCheckPoint cp3 = IModelElementFactory.instance().createMigrationRoadmapCheckPoint();
		cp3.setName("Review");
		cp3.setColor(Color.GREEN.getRGB());
		cp3.setTime(620);
		migrationRoadMap.addCheckPoint(cp3);

		IMigrationRoadmapCheckPoint cp4 = IModelElementFactory.instance().createMigrationRoadmapCheckPoint();
		cp4.setName("Review");
		cp4.setColor(Color.CYAN.getRGB());
		cp4.setTime(870);
		migrationRoadMap.addCheckPoint(cp4);

		dm.openDiagram(migrationRoadMapDiagram);
		
	}

	@Override
	public void update(VPAction arg0) {
		// TODO Auto-generated method stub
		
	}

}
