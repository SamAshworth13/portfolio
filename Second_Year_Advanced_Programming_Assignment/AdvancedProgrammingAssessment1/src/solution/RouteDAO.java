package solution;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import baseclasses.DataLoadingException;
import baseclasses.IRouteDAO;
import baseclasses.Route;

/**
 * The RouteDAO parses XML files of route information, each route specifying
 * where the airline flies from, to, and on which day of the week
 */
public class RouteDAO implements IRouteDAO 
{
	private List<Route> routeList = new ArrayList<>(); 

	/**
	 * Finds all flights that depart on the specified day of the week
	 * @param dayOfWeek A three letter day of the week, e.g. "Tue"
	 * @return A list of all routes that depart on this day
	 */
	@Override
	public List<Route> findRoutesByDayOfWeek(String dayOfWeek) 
	{
		List<Route> outList = new ArrayList<>();
		
		for (Route r : this.routeList)
		{
			if (r.getDayOfWeek().equalsIgnoreCase(dayOfWeek))
			{
				outList.add(r);
			}
		}
		return outList;
	}

	/**
	 * Finds all of the flights that depart from a specific airport on a specific day of the week
	 * @param airportCode the three letter code of the airport to search for, e.g. "MAN"
	 * @param dayOfWeek the three letter day of the week code to search for, e.g. "Tue"
	 * @return A list of all routes from that airport on that day
	 */
	@Override
	public List<Route> findRoutesByDepartureAirportAndDay(String airportCode, String dayOfWeek) 
	{
		List<Route> outList = new ArrayList<>();
		
		for (Route r : this.routeList)
		{
			if (r.getDayOfWeek().equalsIgnoreCase(dayOfWeek) && r.getDepartureAirportCode().equalsIgnoreCase(airportCode))
			{
				outList.add(r);
			}
		}
		return outList;
	}

	/**
	 * Finds all of the flights that depart from a specific airport
	 * @param airportCode the three letter code of the airport to search for, e.g. "MAN"
	 * @return A list of all of the routes departing the specified airport
	 */
	@Override
	public List<Route> findRoutesDepartingAirport(String airportCode) 
	{
		List<Route> outList = new ArrayList<>();
		
		for (Route r : this.routeList)
		{
			if (r.getDepartureAirportCode().equalsIgnoreCase(airportCode))
			{
				outList.add(r);
			}
		}
		return outList;
	}

	/**
	 * Finds all of the flights that depart on the specified date
	 * @param date the date to search for
	 * @return A list of all routes that depart on this date
	 */
	@Override
	public List<Route> findRoutesbyDate(LocalDate date) 
	{
		List<Route> outList = new ArrayList<>();
		String day = date.getDayOfWeek().toString();
		day = day.substring(0, 3);
		for (Route r : this.routeList)
		{
			if (r.getDayOfWeek().equalsIgnoreCase(day))
			{
				outList.add(r);
			}
		}
		return outList;
	}

	/**
	 * Returns The full list of all currently loaded routes
	 * @return The full list of all currently loaded routes
	 */
	@Override
	public List<Route> getAllRoutes() 
	{
		List<Route> allRoutes = new ArrayList<>();
		
		for (Route r : this.routeList)
		{
			allRoutes.add(r);
		}
		return allRoutes;
	}

	/**
	 * Returns The number of routes currently loaded
	 * @return The number of routes currently loaded
	 */
	@Override
	public int getNumberOfRoutes() 
	{
		return this.routeList.size();
	}

	/**
	 * Loads the route data from the specified file, adding them to the currently loaded routes
	 * Multiple calls to this function, perhaps on different files, would thus be cumulative
	 * @param p A Path pointing to the file from which data could be loaded
	 * @throws DataLoadingException if anything goes wrong. The exception's "cause" indicates the underlying exception
	 */
	@Override
	public void loadRouteData(Path p) throws DataLoadingException 
	{
		try
		{
			//load the XML
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = db.parse(p.toFile());
			//get the root node of the file
			try
			{
				Element root = doc.getDocumentElement();
				//get a list of the child nodes
				NodeList routes = root.getChildNodes();
				
				//loop through each Route node
				for (int i = 0; i < routes.getLength(); i++)
				{
					//booleans used to detect duplicate tags
					boolean flightNumFound = false;
					boolean dayOfWeekFound = false;
					boolean depTimeFound = false;
					boolean depAPFound = false;
					boolean depAPCodeFound = false;
					boolean arrTimeFound = false;
					boolean arrAPFound = false;
					boolean arrAPCodeFound = false;
					boolean durationFound = false;
					Node n = routes.item(i);
					NodeList details = n.getChildNodes();
					Route r = new Route();
					//loop through children of each Route node
					for (int j = 0; j < details.getLength(); j++)
					{
						Node d = details.item(j);
						if (d.getNodeName().equals("FlightNumber") && !flightNumFound)
						{
							r.setFlightNumber(Integer.parseInt(d.getChildNodes().item(0).getNodeValue()));
							flightNumFound = true;
						}
						else if (d.getNodeName().equals("DayOfWeek") && ! dayOfWeekFound)
						{
							//check that the dayOfWeek is valid
							if (d.getChildNodes().item(0).getNodeValue().equals("Mon")
								|| d.getChildNodes().item(0).getNodeValue().equals("Tue")
								|| d.getChildNodes().item(0).getNodeValue().equals("Wed")
								|| d.getChildNodes().item(0).getNodeValue().equals("Thu")
								|| d.getChildNodes().item(0).getNodeValue().equals("Fri")
								|| d.getChildNodes().item(0).getNodeValue().equals("Sat")
								|| d.getChildNodes().item(0).getNodeValue().equals("Sun"))
							{
								r.setDayOfWeek(d.getChildNodes().item(0).getNodeValue());
								dayOfWeekFound = true;
							}
							else
							{
								//if an invalid day of the week was given, throw a DataLoadingException
								throw new DataLoadingException();
							}
						}
						else if (d.getNodeName().equals("DepartureTime") && !depTimeFound)
						{
							r.setDepartureTime(LocalTime.parse(d.getChildNodes().item(0).getNodeValue()));
							depTimeFound = true;
						}
						else if(d.getNodeName().equals("DepartureAirport") && !depAPFound)
						{
							r.setDepartureAirport(d.getChildNodes().item(0).getNodeValue());
							depAPFound = true;
						}
						else if(d.getNodeName().equals("DepartureAirportIATACode") && !depAPCodeFound)
						{
							r.setDepartureAirportCode(d.getChildNodes().item(0).getNodeValue());
							depAPCodeFound = true;
						}
						else if(d.getNodeName().equals("ArrivalTime") && !arrTimeFound)
						{
							r.setArrivalTime(LocalTime.parse(d.getChildNodes().item(0).getNodeValue()));
							arrTimeFound = true;
						}
						else if(d.getNodeName().equals("ArrivalAirport") && !arrAPFound)
						{
							r.setArrivalAirport(d.getChildNodes().item(0).getNodeValue());
							arrAPFound = true;
						}
						else if(d.getNodeName().equals("ArrivalAirportIATACode") && !arrAPCodeFound)
						{
							r.setArrivalAirportCode(d.getChildNodes().item(0).getNodeValue());
							arrAPCodeFound = true;
						}
						else if(d.getNodeName().equals("Duration") && !durationFound)
						{
							r.setDuration(Duration.parse(d.getChildNodes().item(0).getNodeValue()));
							durationFound = true;
						}
						else if (d.getNodeName().equals("FlightNumber") || d.getNodeName().equals("DayOfWeek")
								|| d.getNodeName().equals("DepartureTime") || d.getNodeName().equals("DepartureAirport")
								|| d.getNodeName().equals("DepartureAirportIATACode") || d.getNodeName().equals("ArrivalTime")
								|| d.getNodeName().equals("ArrivalAirport") || d.getNodeName().equals("ArrivalAirportIATACode")
								|| d.getNodeName().equals("Duration"))
						{
							//if a duplicate tag was found, throw a DataLoadingException
							throw new DataLoadingException();
						}
					}
					//add all routes with data entered in them to routeList
					if (flightNumFound && dayOfWeekFound && depTimeFound && depAPFound && depAPCodeFound && arrTimeFound
						&& arrAPFound && arrAPCodeFound && durationFound)
					{
						routeList.add(r);
					}
					else if (i%2 != 0)
					{
						//if data hasn't been entered into the route and i is odd, a tag must have the wrong name
						throw new DataLoadingException();
					}
				}
			}
			catch (Exception e)
			{
				//There file was malformed
				throw new DataLoadingException(e);
			}	
		}
		catch (ParserConfigurationException | SAXException | IOException e)
		{
			//There was a problem reading the file
			throw new DataLoadingException(e);
		}
		catch (NullPointerException npe)
		{
			//There path provided was null
			throw new DataLoadingException(npe);
		}

	}

	/**
	 * Unloads all of the crew currently loaded, ready to start again if needed
	 */
	@Override
	public void reset() 
	{
		routeList.clear();
	}

}
