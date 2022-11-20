package solution;
import java.nio.file.Path;
import java.sql.*;
import java.time.LocalDate;

import baseclasses.DataLoadingException;
import baseclasses.IPassengerNumbersDAO;

/**
 * The PassengerNumbersDAO is responsible for loading an SQLite database
 * containing forecasts of passenger numbers for flights on dates
 */
public class PassengerNumbersDAO implements IPassengerNumbersDAO 
{
	//create an empty Connection object
	private Connection c = null;
	/**
	 * Returns the number of passenger number entries in the cache
	 * @return the number of passenger number entries in the cache
	 */
	@Override
	public int getNumberOfEntries() 
	{
		try
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT COUNT(FlightNumber) FROM PassengerNumbers");
			
			while (rs.next())
			{
				return Integer.parseInt(rs.getString("COUNT(FlightNumber)"));
			}
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		return 0;
	}

	/**
	 * Returns the predicted number of passengers for a given flight on a given date, or -1 if no data available
	 * @param flightNumber The flight number of the flight to check for
	 * @param date the date of the flight to check for
	 * @return the predicted number of passengers, or -1 if no data available
	 */
	@Override
	public int getPassengerNumbersFor(int flightNumber, LocalDate date) 
	{
		try
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT LoadEstimate"
					+ " FROM PassengerNumbers "
					+ "WHERE Date = '" + date.toString() + "' AND FlightNumber = " + flightNumber);
			
			while (rs.next())
			{
				return Integer.parseInt(rs.getString("LoadEstimate"));
			}
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		return -1;
	}

	/**
	 * Loads the passenger numbers data from the specified SQLite database into a cache for future calls to getPassengerNumbersFor()
	 * Multiple calls to this method are additive, but flight numbers/dates previously cached will be overwritten
	 * The cache can be reset by calling reset() 
	 * @param p The path of the SQLite database to load data from
	 * @throws DataLoadingException If there is a problem loading from the database
	 */
	@Override
	public void loadPassengerNumbersData(Path p) throws DataLoadingException 
	{
		try
		{
			//convert the path to a String
			String pathStr = p.toString();
			
			try
			{
				//connect to the db
				this.c = DriverManager.getConnection("jdbc:sqlite:" + pathStr);
				
				//check if PassengerNumbers table exists in the database
				DatabaseMetaData dbm = c.getMetaData();
				ResultSet rs = dbm.getTables(null, null, "PassengerNumbers", null);
				
				//if the table doesn't exist throw a DataLoadingException
				if(!rs.next())
				{
					throw new DataLoadingException();
				}
			}
			catch (Exception e)
			{
				//There was a problem reading the file
				throw new DataLoadingException(e);
			}
		}
		catch (NullPointerException npe)
		{
			//There path provided was null
			throw new DataLoadingException(npe);
		}
		

	}

	/**
	 * Removes all data from the DAO, ready to start again if needed
	 */
	@Override
	public void reset() 
	{
		try
		{
			this.c.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}

	}

}
