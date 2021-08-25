package com.adobe.aem.sample.core.service.impl;

import java.io.IOException;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.sample.core.models.ObjectMapperScoresModel;
import com.adobe.aem.sample.core.service.HttpConfiguration;
import com.adobe.aem.sample.core.service.HttpService;
import com.adobe.aem.sample.core.utils.Network;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Impl of HttpService interface This class reads values from the OSGi
 * Configuration
 */

@Component(service = HttpService.class, immediate = true)
@Designate(ocd = HttpConfiguration.class)
public class HttpServiceImpl implements HttpService {

	private static final Logger log = LoggerFactory.getLogger(HttpServiceImpl.class);

	/**
	 * Instance of the OSGi configuration class
	 */
	private HttpConfiguration configuration;

	@Activate
	protected void activate(HttpConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	public String makeHttpCall(SlingHttpServletRequest req) {

		try {

			/**
			 * Reading values from the configuration
			 */
			boolean enable = configuration.enableConfig();
			String protocol = configuration.getProtocol();
			String server = configuration.getServer();
			String endpoint = configuration.getEndpoint();

			/**
			 * Constructing the URL
			 */
			String url = protocol + "://" + server + "/" + endpoint;

			/**
			 * Make HTTP call only if "enable" is true
			 */
			if (enable) {
				/**
				 * Making the actual HTTP call
				 * url being used: https://my-json-server.typicode.com/enghelizabeth/average-golf-scores/average-golf-scores
				 */
				String response = Network.readJson(url);
				
				// log.info("json response: " + response);

				return response;

			} else {

				log.info("Configuration is not enabled - HttpServiceImpl");

				return "Configuration not enabled";
			}

		} catch (Exception e) {

			log.error(e.getMessage(), e);

			return "Error occurred" + e.getMessage();
		}
	}

	@Override
	public void addPlayerStats(SlingHttpServletRequest req, String jsonResponse)
			throws JsonParseException, JsonMappingException, IOException {

		// add the node in this directory
		int index = 0;
		String newNodeName = "item";
		ResourceResolver resolver = req.getResourceResolver();
		String nodepath = req.getParameter("pagepath") + "/jcr:content/root/responsivegrid/golfscores/playerStats";

		Resource nodeResource = resolver.getResource(nodepath);
		Node scoreNode = nodeResource.adaptTo(Node.class);
		log.info("Right before object mapper");
		try {
			/**
			 * ObjectMapper - JSON to scores
			 */
			ObjectMapper mapper = new ObjectMapper();

			// Need to type reference for list of objects
			TypeReference<List<ObjectMapperScoresModel>> mapType = new TypeReference<List<ObjectMapperScoresModel>>() {};

			// mapping json string to class
			List<ObjectMapperScoresModel> jsonToScoresList = mapper.readValue(jsonResponse, mapType);

			String tempPath;
			NodeIterator itr;
			for (ObjectMapperScoresModel o : jsonToScoresList) {
				newNodeName = "item";
				nodeResource = resolver.getResource(nodepath);
				scoreNode = nodeResource.adaptTo(Node.class);
				itr = scoreNode.getNodes();
				newNodeName += index++;
				scoreNode.addNode(newNodeName);

				// get the new node to set the properties
				tempPath = nodepath + "/" + newNodeName;
				nodeResource = resolver.getResource(tempPath);
				scoreNode = nodeResource.adaptTo(Node.class);

				scoreNode.setProperty("rankthisweek", o.rankthisweek);
				scoreNode.setProperty("ranklastweek", o.ranklastweek);
				scoreNode.setProperty("playername", o.playername);
				scoreNode.setProperty("rounds", o.rounds);
				scoreNode.setProperty("average", o.average);
				scoreNode.setProperty("totalstrokes", o.totalstrokes);
				scoreNode.setProperty("totaladjustment", o.totaladjustment);
				scoreNode.setProperty("totalrounds", o.totalrounds);
				scoreNode.getSession().save();
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

}
