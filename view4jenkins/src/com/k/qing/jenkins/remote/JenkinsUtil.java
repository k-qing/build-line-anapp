package com.k.qing.jenkins.remote;


public class JenkinsUtil {
	
//	private static Logger logger = Logger.getLogger(JenkinsUtil.class);
//	
//	public static ExecutorJob getExecutorJob(HttpClient httpClient, String name) {
//		ExecutorJob executorJob = new ExecutorJob();
//		logger.debug("Get job " + name);
//		System.out.println("Get job " + name);
//		String xml = ConnectionUtil.getXmlFromGetRequest(httpClient, Common.JENKINS_URL + "/job/" + name + "/config.xml");
//		SAXReader saxReader = new SAXReader();
//		try {
//			Document document = saxReader.read(new ByteArrayInputStream(xml.getBytes()));
//			List<Node> swdlNodeList = document.selectNodes("//com.ericsson.infra.case__executor.ArtsRunUnitInput");
//			for(Node swdlNode : swdlNodeList) {
//				String suitePath = swdlNode.selectSingleNode("./suitePath").getText();
//				String vm = swdlNode.selectSingleNode("./vm").getText();
//				String sshUser = swdlNode.selectSingleNode("./sshUser").getText();
//				String sshPw = swdlNode.selectSingleNode("./sshPw").getText();
//				
//				CDEExecutor cdeExecutor = new CDEExecutor();
//				cdeExecutor.setSuitePath(suitePath);
//				cdeExecutor.setVm(vm);
//				cdeExecutor.setSshUser(sshUser);
//				cdeExecutor.setSshPwd(sshPw);
//				
//				executorJob.addCDEExecutor(cdeExecutor);
//				executorJob.setName(name);
//				
////				CDESoftwareUpgrade cdeSoftwareUpgrade = new CDESoftwareUpgrade();
////				cdeSoftwareUpgrade.setNodeIp(nodeIp);
////				cdeSoftwareUpgrade.setNr(nr);
////				softwareUpgradeJob.addCDESoftwareUpgrade(cdeSoftwareUpgrade);
////				softwareUpgradeJob.setName(name);
//			}
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return executorJob;
//	}
//	
//	public static SoftwareUpgradeJob getSoftwareUpgradeJob(HttpClient httpClient, String name) {
//		SoftwareUpgradeJob softwareUpgradeJob = new SoftwareUpgradeJob();
//		
//		String xml = ConnectionUtil.getXmlFromGetRequest(httpClient, Common.JENKINS_URL + "/job/" + name + "/config.xml");
//		SAXReader saxReader = new SAXReader();
//		try {
//			Document document = saxReader.read(new ByteArrayInputStream(xml.getBytes()));
//			List<Node> swdlNodeList = document.selectNodes("//com.ericsson.infra.swdl.SPR2SWDLNode");
//			for(Node swdlNode : swdlNodeList) {
//				String nodeIp = swdlNode.selectSingleNode("./nodeIp").getText();
//				String nr = swdlNode.selectSingleNode("./nr").getText();
//				CDESoftwareUpgrade cdeSoftwareUpgrade = new CDESoftwareUpgrade();
//				cdeSoftwareUpgrade.setNodeIp(nodeIp);
//				cdeSoftwareUpgrade.setNr(nr);
//				softwareUpgradeJob.addCDESoftwareUpgrade(cdeSoftwareUpgrade);
//				softwareUpgradeJob.setName(name);
//			}
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return softwareUpgradeJob;
//	}
//	
//	public static List<String> getAllViewNameList(HttpClient httpClient) {
//		List<String> viewNameList = new ArrayList<String>();
//		String xml = ConnectionUtil.getXmlFromGetRequest(httpClient, Common.JENKINS_URL + "/api/xml");
//		SAXReader saxReader = new SAXReader();
//		try {
//			Document document = saxReader.read(new ByteArrayInputStream(xml.getBytes()));
//			List<Node> swdlNodeList = document.selectNodes("//view");
//			for(Node swdlNode : swdlNodeList) {
//				String viewName = swdlNode.selectSingleNode("./name").getText();
//				viewNameList.add(viewName);
//			}
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return viewNameList;
//	}
//	
//	public static List<String> getAllJobsOfPipeline(HttpClient httpClient, String name) throws ClientProtocolException, IOException {
//		List<String> projectList = new ArrayList<String>();
//		SAXReader saxReader = new SAXReader();
//		try {
//			
//			String xml = ConnectionUtil.getXmlFromGetRequest(httpClient, Common.JENKINS_URL + "/view/" + name + "/config.xml");
//			
////			Document document = saxReader.read(new URL(Common.JENKINS_URL + "/view/" + name + "/config.xml"));
//			Document document = saxReader.read(new ByteArrayInputStream(xml.getBytes()));
//			String firstJob = document.selectSingleNode("//selectedJob").getText();
//			projectList = getChildProjects(httpClient, firstJob);
//			
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return projectList;
//	}
//	
//	private static List<String> getChildProjects(HttpClient httpClient, String projectName) {
//		List<String> projectList = new ArrayList<String>();
//		Queue<String> unvisited = new LinkedList<String>();
//		
//		unvisited.add(projectName);
//		
//		// Use Breadth-First-Search to find all upstream builds.
//        while (!unvisited.isEmpty()) {
//
//            String currentProject = unvisited.poll();
//
//            List<String> downstreamProjects = getDownstreamProjects(httpClient, currentProject);
//
//            for (String downstreamProject : downstreamProjects) {
//                // We assume the last build belongs to this pipeline. As the
//                // same job is
//                // not executed at the same time.
//                if (downstreamProject != null) {
//                    if (!unvisited.contains(downstreamProject)) { // Avoid multiple
//                        // entries having the
//                        // same parent.
//                        unvisited.add(downstreamProject);
//                    }
//                }
//            }
//
//            // Do not add the build itself.
//            if (!currentProject.equals(projectName)) {
//            	projectList.add(0, currentProject);
//            }
//        }
//		
//		return projectList;
//	}
//	
//	private static List<String> getDownstreamProjects(HttpClient httpClient, String projectName) {
//		List<String> projectList = new ArrayList<String>();
//		SAXReader saxReader = new SAXReader();
//		try {
//			
//			String xml = ConnectionUtil.getXmlFromGetRequest(httpClient, Common.JENKINS_URL + "/job/" + projectName + "/config.xml");
//			System.out.println(projectName);
//			Document document = saxReader.read(new ByteArrayInputStream(xml.getBytes()));
//			
//			Node childProjectsNode = document.selectSingleNode("//childProjects");
//			if(childProjectsNode != null) {
//				String chlidProjects = childProjectsNode.getText();
//				if(chlidProjects != null && !chlidProjects.isEmpty()) {
//					String[] projects = chlidProjects.split(",");
//					for(String project : projects) {
//						if(project != null && !project.isEmpty()) {
//							projectList.add(project.trim());
//						}
//					}
//				}
//			}
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return projectList;
//	}
//
//	public static void main(String[] args) throws ClientProtocolException, IOException {
//		HttpClient httpClient = ConnectionUtil.getHttpClient("ejiaqsu", "850930Sjq7");
//		JenkinsUtil.getSoftwareUpgradeJob(httpClient, "atlantic_swdl");
////		getAllJobsOfPipeline("SP_R2_ProdReg");
//	}
}
