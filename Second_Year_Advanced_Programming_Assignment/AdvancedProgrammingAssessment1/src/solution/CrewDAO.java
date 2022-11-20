package solution;
import org.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import baseclasses.CabinCrew;
import baseclasses.Crew;
import baseclasses.DataLoadingException;
import baseclasses.ICrewDAO;
import baseclasses.Pilot;

/**
 * The CrewDAO is responsible for loading data from JSON-based crew files 
 * It contains various methods to help the scheduler find the right pilots and cabin crew
 */
public class CrewDAO implements ICrewDAO 
{
	
	private List<Crew> crewList = new ArrayList<>();

	/**
	 * Loads the crew data from the specified file, adding them to the currently loaded crew
	 * Multiple calls to this function, perhaps on different files, would thus be cumulative
	 * @param p A Path pointing to the file from which data could be loaded
	 * @throws DataLoadingException if anything goes wrong. The exception's "cause" indicates the underlying exception
	 */
	@Override
	public void loadCrewData(Path p) throws DataLoadingException 
	{
		try 
		{
			//open the file
			BufferedReader reader = Files.newBufferedReader(p);
			
			String line = "";
			String json = "";
			
			//create one string with the full content of the file
			while ((line = reader.readLine()) != null)
			{
				json += line;
			}
			
			// convert the string to a JSON object
			try
			{
				JSONObject crew = new JSONObject(json);
				
				JSONArray pilots = crew.getJSONArray("pilots");
				
				for (int i = 0; i < pilots.length(); i++)
				{
					JSONObject pilot = pilots.getJSONObject(i);
					String forename = pilot.getString("forename");
					String surname = pilot.getString("surname");
					String homeBase = pilot.getString("home_airport");
					String rank = pilot.getString("rank");
					JSONArray typeRatings = pilot.getJSONArray("type_ratings");
					
					Pilot pil = new Pilot();
					pil.setForename(forename);
					pil.setSurname(surname);
					pil.setHomeBase(homeBase);
				
					if (rank.equals("CAPTAIN"))
					{
						pil.setRank(Pilot.Rank.CAPTAIN);
					}
					else
					{
						pil.setRank(Pilot.Rank.FIRST_OFFICER);
					}
					
					for (int j = 0; j < typeRatings.length(); j++)
					{
						pil.setQualifiedFor(typeRatings.get(j).toString());
					}
					
					crewList.add(pil);
				}
				
				
				JSONArray cabinCrew = crew.getJSONArray("cabincrew");
				
				for (int i = 0; i < cabinCrew.length(); i++)
				{
					JSONObject crewmate = cabinCrew.getJSONObject(i);
					String forename = crewmate.getString("forename");
					String surname = crewmate.getString("surname");
					String homeBase = crewmate.getString("home_airport");
					JSONArray typeRatings = crewmate.getJSONArray("type_ratings");
					
					CabinCrew cc = new CabinCrew();
					cc.setForename(forename);
					cc.setSurname(surname);
					cc.setHomeBase(homeBase);
					
					for (int j = 0; j < typeRatings.length(); j++)
					{
						cc.setQualifiedFor(typeRatings.get(j).toString());
					}
					
					crewList.add(cc);
				}
			}
			catch (Exception e)
			{
				//There file was malformed
				throw new DataLoadingException(e);
			}
		}
		
		catch (IOException ioe) 
		{
			//There was a problem reading the file
			throw new DataLoadingException(ioe);
		}
		catch (NullPointerException npe)
		{
			//There path provided was null
			throw new DataLoadingException(npe);
		}

	}
	
	/**
	 * Returns a list of all the cabin crew based at the airport with the specified airport code
	 * @param airportCode the three-letter airport code of the airport to check for
	 * @return a list of all the cabin crew based at the airport with the specified airport code
	 */
	@Override
	public List<CabinCrew> findCabinCrewByHomeBase(String airportCode) 
	{
		List <CabinCrew> outList = new ArrayList<>();
		for (CabinCrew cc : this.getAllCabinCrew())
		{
			if (cc.getHomeBase().equalsIgnoreCase(airportCode))
			{
				outList.add(cc);
			}
		}
		return outList;
	}

	/**
	 * Returns a list of all the cabin crew based at a specific airport AND qualified to fly a specific aircraft type
	 * @param typeCode the type of plane to find cabin crew for
	 * @param airportCode the three-letter airport code of the airport to check for
	 * @return a list of all the cabin crew based at a specific airport AND qualified to fly a specific aircraft type
	 */
	@Override
	public List<CabinCrew> findCabinCrewByHomeBaseAndTypeRating(String typeCode, String airportCode) 
	{
		List <CabinCrew> outList = new ArrayList<>();
		List <String> typeList = new ArrayList<>();
		
		for (CabinCrew cc : this.getAllCabinCrew())
		{
			if (cc.getHomeBase().equalsIgnoreCase(airportCode))
			{
				typeList = cc.getTypeRatings();
				for (String type : typeList)
				{
					if (type.equalsIgnoreCase(typeCode))
					{
						outList.add(cc);
					}
				}
			}
		}
		return outList;
	}

	/**
	 * Returns a list of all the cabin crew currently loaded who are qualified to fly the specified type of plane
	 * @param typeCode the type of plane to find cabin crew for
	 * @return a list of all the cabin crew currently loaded who are qualified to fly the specified type of plane
	 */
	@Override
	public List<CabinCrew> findCabinCrewByTypeRating(String typeCode) 
	{
		List <CabinCrew> outList = new ArrayList<>();
		List <String> typeList = new ArrayList<>();
		
		for (CabinCrew cc : this.getAllCabinCrew())
		{
			typeList = cc.getTypeRatings();
			for (String type : typeList)
			{
				if (type.equalsIgnoreCase(typeCode))
				{
					outList.add(cc);
				}
			}
		}
		return outList;
	}

	/**
	 * Returns a list of all the pilots based at the airport with the specified airport code
	 * @param airportCode the three-letter airport code of the airport to check for
	 * @return a list of all the pilots based at the airport with the specified airport code
	 */
	@Override
	public List<Pilot> findPilotsByHomeBase(String airportCode) 
	{
		List<Pilot> outList = new ArrayList<>();
		for (Pilot p : this.getAllPilots())
		{
			if (p.getHomeBase().equalsIgnoreCase(airportCode))
			{
				outList.add(p);
			}
		}
		return outList;
	}

	/**
	 * Returns a list of all the pilots based at a specific airport AND qualified to fly a specific aircraft type
	 * @param typeCode the type of plane to find pilots for
	 * @param airportCode the three-letter airport code of the airport to check for
	 * @return a list of all the pilots based at a specific airport AND qualified to fly a specific aircraft type
	 */
	@Override
	public List<Pilot> findPilotsByHomeBaseAndTypeRating(String typeCode, String airportCode) 
	{
		List <Pilot> outList = new ArrayList<>();
		List <String> typeList = new ArrayList<>();
		
		for (Pilot p : this.getAllPilots())
		{
			if (p.getHomeBase().equalsIgnoreCase(airportCode))
			{
				typeList = p.getTypeRatings();
				for (String type : typeList)
				{
					if (type.equalsIgnoreCase(typeCode))
					{
						outList.add(p);
					}
				}
			}
		}
		return outList;
	}

	/**
	 * Returns a list of all the pilots currently loaded who are qualified to fly the specified type of plane
	 * @param typeCode the type of plane to find pilots for
	 * @return a list of all the pilots currently loaded who are qualified to fly the specified type of plane
	 */
	@Override
	public List<Pilot> findPilotsByTypeRating(String typeCode) 
	{
		List <Pilot> outList = new ArrayList<>();
		List <String> typeList = new ArrayList<>();
		
		for (Pilot p : this.getAllPilots())
		{
			typeList = p.getTypeRatings();
			for (String type : typeList)
			{
				if (type.equalsIgnoreCase(typeCode))
				{
					outList.add(p);
				}
			}
		}
		return outList;
	}

	/**
	 * Returns a list of all the cabin crew currently loaded
	 * @return a list of all the cabin crew currently loaded
	 */
	@Override
	public List<CabinCrew> getAllCabinCrew() 
	{
		List<CabinCrew> cCrew = new ArrayList<>();
		
		for (Crew c : this.crewList)
		{
			if (c instanceof CabinCrew)
			{
				cCrew.add((CabinCrew)c);
			}
		}
		return cCrew;
	}

	/**
	 * Returns a list of all the crew, regardless of type
	 * @return a list of all the crew, regardless of type
	 */
	@Override
	public List<Crew> getAllCrew() 
	{
		List<Crew> allCrew = new ArrayList<>();
		
		for (Crew c : this.crewList)
		{
			allCrew.add(c);
		}
		return allCrew;
	}

	/**
	 * Returns a list of all the pilots currently loaded
	 * @return a list of all the pilots currently loaded
	 */
	@Override
	public List<Pilot> getAllPilots() 
	{
		List<Pilot> pilots = new ArrayList<>();
		
		for (Crew c : this.crewList)
		{
			if (c instanceof Pilot)
			{
				pilots.add((Pilot)c);
			}
		}
		return pilots;
	}

	@Override
	public int getNumberOfCabinCrew() 
	{
		return this.getAllCabinCrew().size();
	}

	/**
	 * Returns the number of pilots currently loaded
	 * @return the number of pilots currently loaded
	 */
	@Override
	public int getNumberOfPilots() 
	{
		return this.getAllPilots().size();
	}

	/**
	 * Unloads all of the crew currently loaded, ready to start again if needed
	 */
	@Override
	public void reset() 
	{
		this.crewList.clear();
	}

}
